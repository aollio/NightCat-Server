package com.yemao.projects.web

import com.framework.annotation.Authorization
import com.framework.annotation.CurrentUser
import com.framework.annotation.EnumParam
import com.yemao.common.Response
import com.yemao.common.base.BaseController
import com.yemao.entity.DesignType
import com.yemao.event.EventManager
import com.yemao.projects.models.*
import com.yemao.projects.service.ProjectBidderService
import com.yemao.projects.service.ProjectProcessService
import com.yemao.projects.service.ProjectService
import com.yemao.users.models.Role
import com.yemao.users.models.User
import com.yemao.utility.Assert
import com.yemao.utility.Assert.equals
import com.yemao.utility.Assert.isTrue
import com.yemao.utility.Assert.notNull
import com.yemao.utility.Assert.notZero
import com.yemao.utility.Assert.strExist
import com.yemao.utility.Util
import com.yemao.utility.Util.now
import com.yemao.utility.Util.uuid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.sql.Timestamp
import java.util.*
import kotlin.collections.ArrayList

@RestController
@RequestMapping("/projects")
class ProjectProcessController : BaseController() {
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


    @Autowired lateinit var processServ: ProjectProcessService

    @Autowired lateinit var projServ: ProjectService

    @Autowired lateinit var bidderServ: ProjectBidderService


    @PostMapping
    @Authorization
    fun publish(
            @CurrentUser user: User,
            @EnumParam(required = false, default = 0) type: DesignType,
            @RequestParam title: String,
            @RequestParam content: String?,
            @RequestParam period: Int?,
            @RequestParam budget: Int?,
            @RequestParam area: Int?,
            @RequestParam area_count: Int?,
            @EnumParam depth: Depth,
            @RequestParam end_time: Timestamp,
            @RequestParam start_time: Timestamp,
            @RequestParam due_time: Timestamp,
            @RequestParam(required = false) img_urls: List<String>?
    ): Response {

        Assert.isEmployer(user, BAD_REQUEST, "发布项目必须为雇主")

        Assert.isFalse(type == DesignType.UNDEFINDED, BaseController.BAD_REQUEST, "参数'type'不对")
        strExist(title, BAD_REQUEST, "'title' not exist")

        notNull(depth, BAD_REQUEST, "'depth' not exist")
        //revise Integer value

        notZero(budget, BAD_REQUEST, "'budget' not exist or wrong value")
        notZero(area, BAD_REQUEST, "'area' not exist or wrong value")
        notZero(area_count, BAD_REQUEST, "'area_count' not exist or wrong value")

        //todo 判断3个时间大小

        val project = Project()

        project.type = (type)
        project.title = (title)
        project.content = (content!!)
        project.period = (period!!)
        project.budget = (BigDecimal.valueOf(budget!!.toLong()))
        project.area = (area!!)
        project.area_count = (area_count!!)
        project.depth = (depth)

        project.end_time = end_time
        project.start_time = start_time
        project.due_time = due_time

        project.create_by = user.uid

        val newProj = processServ.publish(project, img_urls?: ArrayList())

        return okVo(newProj)
    }

    /**
     * 设计师抢单
     */
    @PostMapping("/grab")
    @Authorization
    fun grab(
            @CurrentUser user: User,
            id: String,
            price: Double?,
            cycle: String?,
            description: String?
    ): Response {
        //check user is a designer
        Assert.isDesigner(user, BaseController.BAD_REQUEST, "only designer can grab project")

        //check all param is right
        notZero(price, BAD_REQUEST, "'price' not exist or wrong value")
        strExist(cycle, BAD_REQUEST, "'cycle' not exist")
        strExist(description, BAD_REQUEST, "'description' not exist")

        //generate bidder record
        var bidder = ProjectBidder()
        bidder.id = (uuid())
        bidder.uid = (user.uid)
        bidder.create_time = (now())
        bidder.proj_id = (id)
        bidder.description = (description!!)
        bidder.cycle = (cycle!!)
        bidder.price = (price!!)
        //log
        bidder = processServ.grab(bidder)
        return BaseController.ok(bidder)
    }

    /**
     * 雇主选择设计师
     */
    @PostMapping("/select")
    @Authorization
    fun select(
            @CurrentUser user: User,
            @RequestParam id: String,
            @RequestParam uid: String
    ): Response {
        //check user is a designer
        Assert.isEmployer(user, BaseController.BAD_REQUEST,
                "only employer can select designer")

        //check the project exist
        notNull(projServ.findById(id), BAD_REQUEST,
                "the project is not exist")

        //check user is already grab this project
        notNull(bidderServ.findByUidAndProjectId(uid, id),
                BAD_REQUEST, "the designer is not grab this project")


        val bidder = bidderServ.findByUidAndProjectId(uid, id)

        processServ.select(bidder!!)
        return BaseController.ok(bidder)
    }


    /**
     * 设计师确认
     */
    @PostMapping("/designer_confirm")
    @Authorization
    fun designer_confirm(@CurrentUser user: User,
                         id: String?): Response {
        //todo;
        Assert.isDesigner(user, BaseController.BAD_REQUEST, "只有设计师才能确认")
        //
        val project = projServ.findById(id)

        notNull(project, BAD_REQUEST, "项目不存在")


        equals(user.uid, project!!.bidder,
                BAD_REQUEST, "你没有抢到这个项目, 不能确认")

        processServ.designer_confirm(project)
        return BaseController.ok()
    }


    /**
     * 设计师修改
     */
    @PostMapping("/modify")
    @Authorization
    fun modify(
            @CurrentUser user: User,
            @RequestParam(required = true) id: String,
            @RequestParam(required = false) type: Int?,
            @RequestParam(required = false) title: String?,
            @RequestParam(required = false) content: String?,
            @RequestParam(required = false) period: Int?,
            @RequestParam(required = false) budget: Int?,
            @RequestParam(required = false) area: Int?,
            @RequestParam(required = false) area_count: Int?,
            @RequestParam(required = false) depth: Int?,
            @RequestParam(required = false) end_time: Timestamp?,
            @RequestParam(required = false) start_time: Timestamp?,
            @RequestParam(required = false) img_urls: List<String>?,
            //修改项目 备注
            @RequestParam(required = false) modify_mark: String?
    ): Response {
        var img_urls = img_urls

        val project = projServ.findById(id)
        notNull(project, BAD_REQUEST, "项目不存在")
        isTrue(project!!.status == Status.DesignerConfirm_WaitModify,
                BAD_REQUEST, "项目无法修改")
        equals(project.bidder, user.uid,
                BAD_REQUEST, "您无权修改此项目")


        if (type != null) {
            val designType = Util.enumFromOrigin(type, DesignType::class.java)
            notNull(designType, BAD_REQUEST, "参数'type'不对")
            Assert.isFalse(designType == DesignType.UNDEFINDED,
                    BaseController.BAD_REQUEST, "参数'type'不对")
            project.type = designType
        }

        if (Util.strExist(title)) project.title = title!!
        if (Util.strExist(content)) project.content = content!!

        if (period != null) project.period = period
        if (budget != null) project.budget = BigDecimal.valueOf(budget.toLong())
        if (area != null) project.area = area
        if (area_count != null) project.area_count = area_count
        if (depth != null) {
            val projDepth = Util.enumFromOrigin(depth, Depth::class.java)
            notNull(projDepth, BAD_REQUEST,
                    "'depth' not exist or wrong")
            project.depth = projDepth
        }
        if (Util.strExist(modify_mark)) project.modify_mark = modify_mark!!
        if (end_time != null) project.end_time = (end_time)
        if (start_time != null) project.start_time = (start_time)
        if (img_urls == null) img_urls = LinkedList()

        project.modify_mark = (user.uid)
        project.modify_time = (now())


        val newProj = processServ.modify(project, img_urls)

        return okVo(newProj)
    }

    @PostMapping("/money")
    @Authorization
    fun money(@CurrentUser user: User,
              id: String): Response {
        val project = projServ.findById(id)

        notNull(project, BAD_REQUEST, "项目不存在")
        isTrue(project!!.status == Status.DesignerModify_WaitPay,
                BAD_REQUEST, "还没有到支付的阶段")

        equals(project.create_by, user.uid,
                BAD_REQUEST, "您不是雇主, 无法递交此产品")

        project.status = Status.PayComplete_WaitDesign
        projServ.update(project)
        return BaseController.ok()
    }

    /**
     * 设计师交付
     */
    @PostMapping("/commit")
    @Authorization
    fun commit(
            @CurrentUser user: User,
            id: String): Response {

        val project = projServ.findById(id)
        notNull(project, BAD_REQUEST, "项目不存在")
        isTrue(project!!.status == Status.PayComplete_WaitDesign,
                BAD_REQUEST, "项目无法修改")
        equals(project.bidder, user.uid,
                BAD_REQUEST, "您不是项目设计者,无法递交此产品")

        processServ.commit(project)
        return BaseController.ok()

    }

    @PostMapping("/harvest")
    @Authorization
    fun harvest(
            @CurrentUser user: User,
            id: String): Response {
        strExist(id, BAD_REQUEST, "'id' not exist")
        val project = projServ.findById(id)
        notNull(project, BAD_REQUEST, "项目不存在")
        isTrue(project!!.status == Status.DesignComplete_WaitHarvest,
                BAD_REQUEST, "项目无法收货")
        equals(project.create_by, user.uid,
                BAD_REQUEST, "您不是项目创建者, 无法确认收货")

        processServ.harvest(project)
        return BaseController.ok()
    }

    @PostMapping("/comment")
    @Authorization
    fun comment(
            @CurrentUser user: User,
            @RequestParam(required = true) id: String,
            content: String?,
            @RequestParam(required = true) score: Int,
            @RequestParam(required = true) type: Int
    ): Response {
        Assert.isEmployer(user, BaseController.BAD_REQUEST,
                "只有雇主才可以评论进行中的项目")
        notNull(score, BAD_REQUEST,
                "请选择星级")
        isTrue(score in 0..10,
                BAD_REQUEST, "星级只能0-5之间")
        notNull(type, BAD_REQUEST,
                "请选择好评还是中评, 或者是差评")
        isTrue(type >= 0 && type <= Comment.Type.values().size,
                BAD_REQUEST, "选择类型不对")
        val cmtType = Util.enumFromOrigin(type, Comment.Type::class.java)

        val project = projServ.findById(id)
        notNull(project, BAD_REQUEST, "项目不存在")
        isTrue(project!!.status == Status.EmployerHarvest_WaitComment,
                BAD_REQUEST, "项目现在无法评论")

        val comment = Comment()
        comment.proj_id = project.id
        comment.comment_time = now()
        comment.content = content ?: ""
        comment.employer = true
        comment.score = score
        comment.type = cmtType
        comment.uid = user.uid

        processServ.comment(project, comment)

        return BaseController.ok(comment)
    }

    @PostMapping("/cancel")
    @Authorization
    fun cancel(
            @CurrentUser user: User,
            id: String,
            cancel_reason: String
    ): Response {
        strExist(cancel_reason, BAD_REQUEST, "param 'cancel_reason' not exist")
        val project = projServ.findById(id)
        notNull(project, BAD_REQUEST, "the project not exist")

        if (user.role === Role.EMPLOYER) {
            val result = processServ.cancelByEmployer(user, project!!, cancel_reason)
            return okVo(result)
        } else {
            val result = processServ.cancelByDesigner(user, project!!, cancel_reason)
            return okVo(result)
        }
    }

    @PostMapping("/tester")
    @Authorization
    fun tester(
            @CurrentUser user: User,
            id: String
    ): Response {
        val project = projServ.findById(id)
        notNull(project, BAD_REQUEST, "the project not exist")
        project!!.status = Status.Platform_InterPose
        projServ.update(project)
        return BaseController.ok()

    }


}
