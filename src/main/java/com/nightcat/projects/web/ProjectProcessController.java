package com.nightcat.projects.web;

import com.framework.annotation.Authorization;
import com.framework.annotation.CurrentUser;
import com.nightcat.common.Response;
import com.nightcat.common.base.BaseController;
import com.nightcat.utility.Assert;
import com.nightcat.utility.Util;
import com.nightcat.entity.*;
import com.nightcat.entity.Project.Status;
import com.nightcat.entity.ProjectComment.Type;
import com.nightcat.event.EventManager;
import com.nightcat.projects.service.ProjectBidderService;
import com.nightcat.projects.service.ProjectProcessService;
import com.nightcat.projects.service.ProjectService;
import com.nightcat.vo.VoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.nightcat.common.Response.ok;
import static com.nightcat.common.constant.HttpStatus.*;
import static com.nightcat.utility.Util.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectProcessController extends BaseController {

    /**
     * 设计师抢单.
     * 如果重复了, 则会返回这个错误
     */
    public static final int PROJECT_ALREADY_GRAB = 406;
    /**
     * 设计师抢单.
     * 项目不是发布状态
     */
    public static final int PROJECT_NOT_PUBLISH = 407;


    @Autowired
    private ProjectProcessService processServ;

    @Autowired
    private ProjectService projServ;

    @Autowired
    private ProjectBidderService bidderServ;

    @Autowired
    private EventManager eventManager;

    @Autowired
    private VoService voService;

    @PostMapping
    @Authorization
    public Response publish(
            @CurrentUser User user,
            Integer type,
            String title,
            @RequestParam(required = false) String content,
            @RequestParam(required = false) Integer period,
            Integer budget,
            Integer area,
            Integer area_count,
            Integer depth,
            Timestamp end_time,
            Timestamp start_time,
            Timestamp due_time,
            @RequestParam(required = false) List<String> img_urls
    ) {

        Assert.isEmployer(user, BAD_REQUEST, "发布项目必须为雇主");

        DesignType designType = Util.enumFromOrigin(type, DesignType.class);
        Assert.notNull(designType, BAD_REQUEST, "参数'type'不对");
        Assert.isFalse(designType == DesignType.UNDEFINDED, BAD_REQUEST, "参数'type'不对");
        Assert.strExist(title, BAD_REQUEST, "'title' not exist");

        Assert.notNull(depth, BAD_REQUEST, "'depth' not exist");
        //revise Integer value
        budget = revise(budget);
        area = revise(area);
        area_count = revise(area_count);
        depth = revise(depth);
        period = revise(period);

        Assert.notZero(budget, BAD_REQUEST, "'budget' not exist or wrong value");
        Assert.notZero(area, BAD_REQUEST, "'area' not exist or wrong value");
        Assert.notZero(area_count, BAD_REQUEST, "'area_count' not exist or wrong value");

        Project.Depth projDepth = Util.enumFromOrigin(depth, Project.Depth.class);
        Assert.notNull(projDepth, BAD_REQUEST, "'depth' not exist or wrong");

        if (img_urls == null) {
            img_urls = new LinkedList<>();
        }
        //todo 判断3个时间大小

        Project project = new Project();

        project.setType(designType);
        project.setTitle(title);
        project.setContent(content);
        project.setPeriod(period);
        project.setBudget(BigDecimal.valueOf(budget));
        project.setArea(area);
        project.setArea_count(area_count);
        project.setDepth(projDepth);

        project.setEnd_time(end_time);
        project.setStart_time(start_time);
        project.setDue_time(due_time);

        project.setCreate_by(user.getUid());

        Project newProj = processServ.publish(project, img_urls);

        return okVo(newProj);
    }

    /**
     * 设计师抢单
     */
    @PostMapping("/grab")
    @Authorization
    public Response grab(
            @CurrentUser User user,
            String id,
            Double price,
            String cycle,
            String description
    ) {
        //check user is a designer
        Assert.isDesigner(user, BAD_REQUEST, "only designer can grab project");

        //check all param is right
        price = revise(price);
        Assert.notZero(price, BAD_REQUEST, "'price' not exist or wrong value");

        Assert.strExist(cycle, BAD_REQUEST, "'cycle' not exist");
        Assert.strExist(description, BAD_REQUEST, "'description' not exist");

        //generate bidder record
        ProjectBidder bidder = new ProjectBidder();
        bidder.setId(uuid());
        bidder.setUid(user.getUid());
        bidder.setCreate_time(now());
        bidder.setProj_id(id);
        bidder.setDescription(description);
        bidder.setCycle(cycle);
        bidder.setPrice(price);
        //log
        bidder = processServ.grab(bidder);
        return ok(bidder);
    }

    /**
     * 雇主选择设计师
     */
    @PostMapping("/select")
    @Authorization
    public Response select(
            @CurrentUser User user,
            String id,
            String uid
    ) {
        //check user is a designer
        Assert.isEmployer(user, BAD_REQUEST,
                "only employer can select designer");

        //check the project exist
        Assert.notNull(projServ.findById(id), BAD_REQUEST,
                "the project is not exist");

        //check user is already grab this project
        Assert.notNull(bidderServ.findByUidAndProjectId(uid, id),
                BAD_REQUEST, "the designer is not grab this project");


        ProjectBidder bidder = bidderServ.findByUidAndProjectId(uid, id);

        processServ.select(bidder);
        return ok(bidder);
    }


    /**
     * 设计师确认
     */
    @PostMapping("/designer_confirm")
    @Authorization
    public Response designer_confirm(@CurrentUser User user,
                                     String id) {
        //todo;
        Assert.isDesigner(user, BAD_REQUEST, "只有设计师才能确认");
        //
        Project project = projServ.findById(id);

        Assert.notNull(project, BAD_REQUEST, "项目不存在");


        Assert.equals(user.getUid(), project.getBidder(),
                BAD_REQUEST, "你没有抢到这个项目, 不能确认");

        processServ.designer_confirm(project);
        return ok();
    }


    /**
     * 设计师修改
     */
    @PostMapping("/modify")
    @Authorization
    public Response modify(
            @CurrentUser User user,
            String id,
            @RequestParam(required = false) Integer type,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String content,
            @RequestParam(required = false) Integer period,
            @RequestParam(required = false) Integer budget,
            @RequestParam(required = false) Integer area,
            @RequestParam(required = false) Integer area_count,
            @RequestParam(required = false) Integer depth,
            @RequestParam(required = false) Timestamp end_time,
            @RequestParam(required = false) Timestamp start_time,
            @RequestParam(required = false) List<String> img_urls,
            //修改项目 备注
            @RequestParam(required = false) String modify_mark
    ) {

        Project project = projServ.findById(id);
        Assert.notNull(project, BAD_REQUEST, "项目不存在");
        Assert.isTrue(project.getStatus() == Status.DesignerConfirm_WaitModify,
                BAD_REQUEST, "项目无法修改");
        Assert.equals(project.getBidder(), user.getUid(),
                BAD_REQUEST, "您无权修改此项目");


        if (type != null) {
            DesignType designType = Util.enumFromOrigin(type, DesignType.class);
            Assert.notNull(designType, BAD_REQUEST, "参数'type'不对");
            Assert.isFalse(designType == DesignType.UNDEFINDED,
                    BAD_REQUEST, "参数'type'不对");
            project.setType(designType);
        }

        if (Util.strExist(title)) project.setTitle(title);
        if (Util.strExist(content)) project.setContent(content);

        if (period != null) project.setPeriod(period);
        if (budget != null) project.setBudget(BigDecimal.valueOf(budget));
        if (area != null) project.setArea(area);
        if (area_count != null) project.setArea_count(area_count);
        if (depth != null) {
            Project.Depth projDepth = Util.enumFromOrigin(depth, Project.Depth.class);
            Assert.notNull(projDepth, BAD_REQUEST,
                    "'depth' not exist or wrong");
            project.setDepth(projDepth);
        }
        if (Util.strExist(modify_mark)) project.setModify_mark(modify_mark);
        if (end_time != null) project.setEnd_time(end_time);
        if (start_time != null) project.setStart_time(start_time);
        if (img_urls == null) img_urls = new LinkedList<>();

        project.setModify_by(user.getUid());
        project.setModify_time(now());


        Project newProj = processServ.modify(project, img_urls);

        return okVo(newProj);
    }

    @PostMapping("/money")
    @Authorization
    public Response money(@CurrentUser User user,
                          String id) {

        Assert.strExist(id, BAD_REQUEST, "'id' not exist");

        Project project = projServ.findById(id);

        Assert.notNull(project, BAD_REQUEST, "项目不存在");
        Assert.isTrue(project.getStatus() == Status.DesignerModify_WaitPay,
                BAD_REQUEST, "还没有到支付的阶段");

        Assert.equals(project.getCreate_by(), user.getUid(),
                BAD_REQUEST, "您不是雇主, 无法递交此产品");

        project.setStatus(Status.PayComplete_WaitDesign);
        projServ.update(project);
        return ok();
    }

    /**
     * 设计师交付
     */
    @PostMapping("/commit")
    @Authorization
    public Response commit(
            @CurrentUser User user,
            String id) {

        Assert.strExist(id, BAD_REQUEST, "'id' not exist");

        Project project = projServ.findById(id);
        Assert.notNull(project, BAD_REQUEST, "项目不存在");
        Assert.isTrue(project.getStatus() == Status.PayComplete_WaitDesign,
                BAD_REQUEST, "项目无法修改");
        Assert.equals(project.getBidder(), user.getUid(),
                BAD_REQUEST, "您不是项目设计者,无法递交此产品");

        processServ.commit(project);
        return ok();

    }

    @PostMapping("/harvest")
    @Authorization
    public Response harvest(
            @CurrentUser User user,
            String id) {
        Assert.strExist(id, BAD_REQUEST, "'id' not exist");
        Project project = projServ.findById(id);
        Assert.notNull(project, BAD_REQUEST, "项目不存在");
        Assert.isTrue(project.getStatus() == Status.DesignComplete_WaitHarvest,
                BAD_REQUEST, "项目无法收货");
        Assert.equals(project.getCreate_by(), user.getUid(),
                BAD_REQUEST, "您不是项目创建者, 无法确认收货");

        processServ.harvest(project);
        return ok();
    }

    @PostMapping("/comment")
    @Authorization
    public Response comment(
            @CurrentUser User user,
            @RequestParam(required = true) String id,
            @RequestParam(required = false) String content,
            @RequestParam(required = true) Integer score,
            @RequestParam(required = true) Integer type
    ) {
        Assert.isEmployer(user, BAD_REQUEST,
                "只有雇主才可以评论进行中的项目");
        Assert.notNull(score, BAD_REQUEST,
                "请选择星级");
        Assert.isTrue(score >= 0 && score <= 10,
                BAD_REQUEST, "星级只能0-5之间");
        Assert.notNull(type, BAD_REQUEST,
                "请选择好评还是中评, 或者是差评");
        Assert.isTrue(type >= 0 && type <= Type.values().length,
                BAD_REQUEST, "选择类型不对");
        Type cmtType = Util.enumFromOrigin(type, Type.class);

        Project project = projServ.findById(id);
        Assert.notNull(project, BAD_REQUEST, "项目不存在");
        Assert.isTrue(project.getStatus() == Status.EmployerHarvest_WaitComment,
                BAD_REQUEST, "项目现在无法评论");

        ProjectComment comment = new ProjectComment();
        comment.setProj_id(project.getId());
        comment.setComment_time(now());
        comment.setContent(content);
        comment.setEmployer(true);
        comment.setScore(score);
        comment.setType(cmtType);
        comment.setUid(user.getUid());

        processServ.comment(project, comment);

        return ok(comment);
    }

    @PostMapping("/cancel")
    @Authorization
    public Response cancel(
            @CurrentUser User user,
            String id,
            String cancel_reason
    ) {
        Assert.strExist(cancel_reason, BAD_REQUEST, "param 'cancel_reason' not exist");
        Project project = projServ.findById(id);
        Assert.notNull(project, BAD_REQUEST, "the project not exist");

        if (user.getRole() == User.Role.EMPLOYER) {
            Project result = processServ.cancelByEmployer(user, project, cancel_reason);
            return okVo(result);
        } else {
            Project result = processServ.cancelByDesigner(user, project, cancel_reason);
            return okVo(result);
        }
    }

    @PostMapping("/tester")
    @Authorization
    public Response tester(
            @CurrentUser User user,
            String id
    ) {
        Project project = projServ.findById(id);
        Assert.notNull(project, BAD_REQUEST, "the project not exist");
        project.setStatus(Status.Platform_InterPose);
        projServ.update(project);
        return ok();

    }


    @Override
    protected VoService getVoService() {
        return voService;
    }
}
