package com.nightcat.projects.web;

import com.framework.annotation.Authorization;
import com.framework.annotation.CurrentUser;
import com.nightcat.common.Response;
import com.nightcat.common.utility.Assert;
import com.nightcat.common.utility.Util;
import com.nightcat.entity.DesignType;
import com.nightcat.entity.Project;
import com.nightcat.entity.ProjectBidder;
import com.nightcat.entity.User;
import com.nightcat.projects.service.ProjectBidderService;
import com.nightcat.projects.service.ProjectProcessService;
import com.nightcat.projects.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;

import static com.nightcat.common.constant.HttpStatus.*;
import static com.nightcat.common.utility.Util.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectProcessController {

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
            Timestamp due_time
//todo
//            @RequestParam(required = false)List<String>,
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

        Project newProj = processServ.publish(project);

        return Response.ok(newProj);
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
        return Response.ok(bidder);
    }

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


        //update project status
        Project project = projServ.findById(id);
        project.setStatus(Project.Status.ConfirmDesigner_WaitDesignerConfitm);
        project.setBid_time(now());
        project.setBidder(uid);
        projServ.update(project);

        //todo 发布事件, 消息通知. 写入动态表
        return Response.ok();
    }
}
