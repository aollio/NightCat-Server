package com.yemao.projects.web;

import com.framework.annotation.Authorization;
import com.framework.annotation.CurrentUser;
import com.yemao.common.Response;
import com.yemao.common.base.BaseController;
import com.yemao.common.constant.Constant;
import com.yemao.entity.DesignType;
import com.yemao.projects.models.Project;
import com.yemao.projects.models.ProjectImage;
import com.yemao.projects.service.ProjectBidderService;
import com.yemao.projects.service.ProjectService;
import com.yemao.projects.repository.ProjectImagesRepository;
import com.yemao.users.models.User;
import com.yemao.utility.Assert;
import com.yemao.utility.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import static com.yemao.utility.Util.emptyStr;
import static com.yemao.utility.Util.now;
import static com.yemao.utility.Util.timeFromStr;

@RestController
@RequestMapping("/projects")
public class ProjectController extends BaseController {


    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectBidderService bidderService;

    @Autowired
    private ProjectImagesRepository imgRep;


    /**
     * 返回项目首页的timeline
     */
    @GetMapping("/timeline")
    public Response timeline(
            @RequestParam Integer type,
            @RequestParam(required = false, defaultValue = "20") Integer limit,
            @RequestParam(name = "since_time", required = false, defaultValue = "0") String since_time_str,
            @RequestParam(name = "max_time", required = false) String max_time_str) {

        Timestamp since_time = emptyStr(since_time_str) ? new Timestamp(0) : timeFromStr(since_time_str);
        Timestamp max_time = emptyStr(max_time_str) ? now() : timeFromStr(max_time_str);

        DesignType designType = DesignType.UNDEFINDED;

        if (type != null) {
            DesignType temp = Util.enumFromOrigin(type, DesignType.class);
            Assert.notNull(temp, BAD_REQUEST, "设计类型不对");
            designType = temp;
        }

//        limit = limit == null ? Constant.DEFAULT_LIMIT : limit;

        return okVo(projectService.findByType(designType, Constant.DEFAULT_LIMIT, since_time, max_time));
    }


    /**
     * 用户项目列表
     */
    @GetMapping("user_timeline")
    @Authorization
    public Response user_time(@CurrentUser User user,
                              @RequestParam(required = false) Integer type,
                              @RequestParam(required = false) Integer limit,
                              @RequestParam(name = "since_time", required = false, defaultValue = "0") String since_time_str,
                              @RequestParam(name = "max_time", required = false) String max_time_str) {

        DesignType designType = DesignType.UNDEFINDED;

//        if (type != null) {
//            DesignType temp = Util.enumFromOrigin(type, DesignType.class);
//            Assert.notNull(temp, BAD_REQUEST, "设计类型不对");
//            designType = temp;
//        }

        limit = limit == null ? Constant.DEFAULT_LIMIT : limit;

        Timestamp since_time = timeFromStr(since_time_str);
        Timestamp max_time = emptyStr(max_time_str) ? now() : timeFromStr(max_time_str);

        Collection<Project> projects = projectService.findTimelineByUid(user.getRole(),
                user.getUid(), designType, limit,
                since_time, max_time);


        return okVo(projects);
    }

    /**
     * 获取项目的抢单列表
     */
    @GetMapping("/grabber_list")
    @Authorization
    public Response grabber_list(String id) {
        //todo
        return ok(bidderService.findByProjectId(id));
    }


    /**
     * 显示项目详细信息
     */
    @GetMapping("/show")
    public Response show(String id) {
        Assert.strExist(id, BAD_REQUEST, "参数id不存在");

        Project project = projectService.findById(id);

        Assert.notNull(project, NOT_FOUND, "项目不存在");
        return okVo(project);
    }

    @Deprecated
    @GetMapping("/imgs")
    public Response showImg(String id) {
        Assert.strExist(id, BAD_REQUEST, "参数id不存在");

        List<ProjectImage> project = imgRep.findByProjId(id);

        return ok(project);
    }

}
