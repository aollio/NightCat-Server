package com.yemao.projects.service;

import com.yemao.users.models.User;
import com.yemao.utility.Assert;
import com.yemao.projects.models.*;
import com.yemao.event.Event;
import com.yemao.event.EventManager;
import com.yemao.projects.ProjectUtil;
import com.yemao.projects.repository.ProjectBidderRepository;
import com.yemao.projects.repository.ProjectCommentRepository;
import com.yemao.projects.repository.ProjectImagesRepository;
import com.yemao.projects.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.yemao.common.base.BaseController.BAD_REQUEST;
import static com.yemao.utility.Util.now;
import static com.yemao.utility.Util.revise;
import static com.yemao.utility.Util.uuid;
import static com.yemao.projects.web.ProjectProcessController.PROJECT_ALREADY_GRAB;
import static com.yemao.projects.web.ProjectProcessController.PROJECT_NOT_PUBLISH;

@Service
public class ProjectProcessService {

    @Autowired
    private ProjectBidderRepository bidderRep;


    @Autowired
    private ProjectRepository projRep;

    @Autowired
    private ProjectImagesRepository imgRep;


    @Autowired
    private ProjectCommentRepository commentRep;
    @Autowired
    private ProjectService projServ;
    @Autowired
    private EventManager eventManager;

    /**
     * 雇主发布, /TODO
     */
    public Project publish(Project project, List<String> img_urls) {
        project.setId(projRep.newId());
        project.setStatus(Project.Status.Publish);
        project.setCreate_time(now());
        projRep.save(project);

        projServ.saveImgUrls(img_urls, project);

        eventManager.publish(Event.ProjectPublished_Project, project);

        return project;
    }

    /**
     * 用户抢单
     */
    public ProjectBidder grab(ProjectBidder bidder) {

        Project project = projRep.findById(bidder.getProj_id());

        //check the project exist
        Assert.notNull(project, BAD_REQUEST,
                "the project not exist");
        Assert.isTrue(project.getStatus() == Project.Status.Publish,
                PROJECT_NOT_PUBLISH, "项目无法抢单了");

        //check user is already grab this project
        Assert.isNull(bidderRep.findByUidAndProjectId(bidder.getUid(), bidder.getProj_id()),
                PROJECT_ALREADY_GRAB, "你已经抢过这个项目了");

        //update project
        project.setGrab_count(project.getGrab_count() + 1);
        projRep.update(project);
        //log and toast both
        bidderRep.save(bidder);
        eventManager.publish(Event.ProjectGrabbedEvent_ProjectBidder, bidder);
        return bidder;
    }


    /**
     * 雇主选择设计师
     */
    public void select(ProjectBidder bidder) {
        //update project status
        Project project = projRep.findById(bidder.getProj_id());

        Assert.isTrue(project.getStatus() == Project.Status.Publish,
                BAD_REQUEST, "只有发布状态的项目才可以选择设计师");

        project.setStatus(Project.Status.ConfirmDesigner_WaitDesignerConfirm);
        project.setBid_time(now());
        project.setBidder(bidder.getUid());
        projRep.update(project);
        eventManager.publish(Event.ProjectSelectDesigner_Project, project);
    }


    /**
     * 设计师确认
     */
    public void designer_confirm(Project project) {
        Assert.isTrue(project.getStatus() == Project.Status.ConfirmDesigner_WaitDesignerConfirm,
                BAD_REQUEST, "项目不是等待你确认的状态");

        project.setStatus(Project.Status.DesignerConfirm_WaitModify);

        projRep.update(project);
        //todo 发布事件
        eventManager.publish(Event.ProjectConfirmByDesigner_Project, project);
    }


    public Project modify(Project project, List<String> img_urls) {

        projServ.saveImgUrls(img_urls, project);
        project.setStatus(Project.Status.DesignerModify_WaitPay);
        //todo 发布一个支付订单

        projServ.update(project);
        eventManager.publish(Event.ProjectModifyByDesigner_Project, project);
        return project;
    }


    public void commit(Project project) {


        project.setStatus(Project.Status.DesignComplete_WaitHarvest);

        projRep.update(project);

        eventManager.publish(Event.ProjectCommitedByDesigner_Project, project);
    }


    public void harvest(Project project) {

        project.setStatus(Project.Status.EmployerHarvest_WaitComment);

        projRep.update(project);

        //todo 项目完成 , 设计师收到钱
        eventManager.publish(Event.ProjectHarvestByEmployer_Project, project);
    }

    /**
     * 雇主评论
     */
    public void comment(Project project, Comment comment) {
        project.setStatus(Project.Status.Complete);
        comment.setId(uuid());

        commentRep.save(comment);
        projServ.update(project);
        eventManager.publish(Event.ProjectCommentByEmployer_ProjectComment, comment);
    }


    //todo
    public Project cancelByEmployer(User user, Project project, String cancel_reason) {

        Assert.isTrue(ProjectUtil.employerCancel(project),
                BAD_REQUEST, "current status can not cancel");
        project.setStatus(Project.Status.Cancel);
        project.setCancel_reason(cancel_reason);
        eventManager.publish(Event.ProjectCancelByEmployer_Project, project);
        return project;
    }

    //todo
    public Project cancelByDesigner(User user, Project project, String cancel_reason) {

        Assert.isTrue(ProjectUtil.designerCancel(project),
                BAD_REQUEST, "current status can not cancel");
        //todo
        project.setStatus(Project.Status.Cancel);
        project.setCancel_reason(cancel_reason);
        eventManager.publish(Event.ProejectCancelByDesigner, project);
        return project;
    }

}
