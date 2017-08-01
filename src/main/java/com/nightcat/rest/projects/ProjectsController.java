package com.nightcat.rest.projects;

import com.nightcat.common.Response;
import com.nightcat.common.utility.Assert;
import com.nightcat.common.utility.Util;
import com.nightcat.service.projects.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.print.URIException;
import javax.print.attribute.standard.MediaSize;
import javax.validation.ReportAsSingleViolation;

import java.sql.Timestamp;

import static com.nightcat.common.utility.Util.*;
import static com.nightcat.common.constant.HttpStatus.*;

@RestController
@RequestMapping("/projects")
public class ProjectsController {

    @Autowired
    private ProjectsService projectsService;

    /**
     * 返回项目首页的timeline
     */
    @GetMapping("/timeline")
    public Response timeline(
            @RequestParam String type,
            @RequestParam(required = false, defaultValue = "20") int limit,
            @RequestParam(name = "since_time", required = false, defaultValue = "0") String since_time_str,
            @RequestParam(name = "max_time", required = false) String max_time_str) {

        Assert.strExist(type, BAD_REQUEST, "项目类型不存在");
        Timestamp since_time = timeFromStr(since_time_str);
        Timestamp max_time = emptyStr(max_time_str) ? now() : timeFromStr(max_time_str);
        return Response.ok(projectsService.findByType(type, limit, since_time, max_time));
    }

    /**
     * 精选项目列表
     */
    @GetMapping("/featured")
    public Response featured(
            @RequestParam(required = false, defaultValue = "20") int limit,
            @RequestParam(name = "since_time", required = false, defaultValue = "0") String since_time_str,
            @RequestParam(name = "max_time", required = false) String max_time_str
    ) {
        Timestamp since_time = timeFromStr(since_time_str);
        Timestamp max_time = emptyStr(max_time_str) ? now() : timeFromStr(max_time_str);
        return Response.ok(projectsService.findFeature(limit, since_time, max_time));
    }

}
