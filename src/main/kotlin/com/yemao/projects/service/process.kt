package com.yemao.projects.service

import com.yemao.common.base.BaseObject
import com.yemao.common.constant.HttpStatus.BAD_REQUEST
import com.yemao.event.Event
import com.yemao.event.EventManager
import com.yemao.projects.models.Comment
import com.yemao.projects.models.Project
import com.yemao.projects.models.ProjectBidder
import com.yemao.projects.models.Status
import com.yemao.projects.util.ProjectUtil
import com.yemao.repository.ProjectBidderRepository
import com.yemao.repository.ProjectCommentRepository
import com.yemao.repository.ProjectRepository
import com.yemao.users.models.User
import com.yemao.utility.Assert
import com.yemao.utility.Assert.notNull
import com.yemao.utility.Util
import com.yemao.utility.Util.now
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProjectProcessService : BaseObject() {

    @Autowired lateinit var bidderRep: ProjectBidderRepository
    @Autowired lateinit var projRep: ProjectRepository
    @Autowired lateinit var commentRep: ProjectCommentRepository
    @Autowired lateinit var projServ: ProjectService
    @Autowired lateinit var eventManager: EventManager

    /**
     * 设计师抢单.
     * 如果重复了, 则会返回这个错误
     */
    val PROJECT_ALREADY_GRAB = 406
    /**
     * 设计师抢单.
     * 项目不是发布状态
     */
    val PROJECT_NOT_PUBLISH = 407


    /**
     * 雇主发布, /TODO
     */
    fun publish(project: Project, img_urls: List<String>): Project {
        project.id = projRep.newId()
        project.status = Status.Publish
        project.create_time = now()
        projRep.save(project)
        projServ.saveImgUrls(img_urls, project)
        eventManager.publish(Event.ProjectPublished_Project, project)

        return project
    }

    /**
     * 用户抢单
     */
    fun grab(bidder: ProjectBidder): ProjectBidder {

        val project = projRep.findById(bidder.proj_id)
        //check the project exist
        notNull(project, BAD_REQUEST,
                "the project not exist")
        Assert.isTrue(project.status == Status.Publish,
                PROJECT_NOT_PUBLISH, "项目无法抢单了")

        //check user is already grab this project
        Assert.isNull(bidderRep.findByUidAndProjectId(bidder.uid, bidder.proj_id),
                PROJECT_ALREADY_GRAB, "你已经抢过这个项目了")

        //update project
        project.grab_count = project.grab_count + 1
        projRep.update(project)
        //log and toast both
        bidderRep.save(bidder)
        eventManager.publish(Event.ProjectGrabbedEvent_ProjectBidder, bidder)
        return bidder
    }


    /**
     * 雇主选择设计师
     */
    fun select(bidder: ProjectBidder) {
        //update project status
        val project = projRep.findById(bidder.proj_id)

        Assert.isTrue(project.status == Status.Publish,
                BAD_REQUEST, "只有发布状态的项目才可以选择设计师")

        project.status = Status.ConfirmDesigner_WaitDesignerConfirm
        project.bid_time = now()
        project.bidder = bidder.uid
        projRep.update(project)
        eventManager.publish(Event.ProjectSelectDesigner_Project, project)
    }


    /**
     * 设计师确认
     */
    fun designer_confirm(project: Project) {
        Assert.isTrue(project.status == Status.ConfirmDesigner_WaitDesignerConfirm,
                BAD_REQUEST, "项目不是等待你确认的状态")

        project.status = Status.DesignerConfirm_WaitModify

        projRep.update(project)
        //todo 发布事件
        eventManager.publish(Event.ProjectConfirmByDesigner_Project, project)
    }


    fun modify(project: Project, img_urls: List<String>): Project {

        projServ.saveImgUrls(img_urls, project)
        project.status = Status.DesignerModify_WaitPay
        //todo 发布一个支付订单
        projServ.update(project)
        eventManager.publish(Event.ProjectModifyByDesigner_Project, project)
        return project
    }


    fun commit(project: Project) {
        project.status = Status.DesignComplete_WaitHarvest
        projRep.update(project)
        eventManager.publish(Event.ProjectCommitedByDesigner_Project, project)
    }


    fun harvest(project: Project) {
        project.status = Status.EmployerHarvest_WaitComment
        projRep.update(project)
        //todo 项目完成 , 设计师收到钱
        eventManager.publish(Event.ProjectHarvestByEmployer_Project, project)
    }

    /**
     * 雇主评论
     */
    fun comment(project: Project, comment: Comment) {
        project.status = Status.Complete
        comment.id = Util.uuid()
        commentRep.save(comment)
        projServ.update(project)
        eventManager.publish(Event.ProjectCommentByEmployer_ProjectComment, comment)
    }


    //todo
    fun cancelByEmployer(user: User, project: Project, cancel_reason: String): Project {

        Assert.isTrue(ProjectUtil.employerCancel(project),
                BAD_REQUEST, "current status can not cancel")
        project.status = Status.Cancel
        project.cancel_reason = cancel_reason
        eventManager.publish(Event.ProjectCancelByEmployer_Project, project)
        return project
    }

    //todo
    fun cancelByDesigner(user: User, project: Project, cancel_reason: String): Project {

        Assert.isTrue(ProjectUtil.designerCancel(project),
                BAD_REQUEST, "current status can not cancel")
        //todo
        project.status = Status.Cancel
        project.cancel_reason = cancel_reason
        eventManager.publish(Event.ProejectCancelByDesigner, project)
        return project
    }
}