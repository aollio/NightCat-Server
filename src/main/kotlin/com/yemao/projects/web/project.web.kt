package com.yemao.projects.web

import com.framework.annotation.Authorization
import com.framework.annotation.CurrentUser
import com.framework.annotation.EnumParam
import com.yemao.common.Response
import com.yemao.common.base.BaseController
import com.yemao.common.constant.Constant
import com.yemao.entity.DesignType
import com.yemao.projects.service.ProjectBidderService
import com.yemao.projects.service.ProjectService
import com.yemao.repository.ProjectImagesRepository
import com.yemao.users.models.User
import com.yemao.utility.Assert
import com.yemao.utility.Util
import com.yemao.utility.Util.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.sql.Timestamp


@RestController
@RequestMapping("/projects")
class ProjectController : BaseController() {

    @Autowired lateinit var projectService: ProjectService
    @Autowired lateinit var bidderService: ProjectBidderService
    @Autowired lateinit var imgRep: ProjectImagesRepository

    /**
     * 返回项目首页的timeline
     */
    @GetMapping("/timeline")
    fun timeline(
            @RequestParam type: Int?,
            @RequestParam(required = false, defaultValue = "20") limit: Int?,
            @RequestParam(name = "since_time", required = false, defaultValue = "0") since_time_str: String?,
            @RequestParam(name = "max_time", required = false) max_time_str: String?): Response {

        val since_time = if (emptyStr(since_time_str)) Timestamp(0) else timeFromStr(since_time_str)
        val max_time = if (emptyStr(max_time_str)) now() else timeFromStr(max_time_str)

        var designType = DesignType.UNDEFINDED

        if (type != null) {
            val temp = Util.enumFromOrigin(type, DesignType::class.java)
            Assert.notNull(temp, BaseController.BAD_REQUEST, "设计类型不对")
            designType = temp
        }

        //        limit = limit == null ? Constant.DEFAULT_LIMIT : limit;

        return okVo(projectService.findByType(designType, Constant.DEFAULT_LIMIT, since_time, max_time))
    }


    /**
     * 用户项目列表
     */
    @GetMapping("user_timeline")
    @Authorization
    fun user_time(@CurrentUser user: User,
                  @EnumParam(required = false, default = 0) type: DesignType,
                  @RequestParam(required = false) limit: Int?,
                  @RequestParam(name = "since_time", required = false, defaultValue = "0") since_time_str: String?,
                  @RequestParam(name = "max_time", required = false) max_time_str: String?): Response {


        val limit_real = if (limit == null) Constant.DEFAULT_LIMIT else limit

        val since_time = timeFromStr(since_time_str)
        val max_time = if (emptyStr(max_time_str)) now() else timeFromStr(max_time_str)

        val projects = projectService.findTimelineByUid(user.role,
                user.uid, type, limit_real,
                since_time, max_time)
        return okVo(projects)
    }

    /**
     * 获取项目的抢单列表
     */
    @GetMapping("/grabber_list")
    @Authorization
    fun grabber_list(id: String): Response {
        return BaseController.ok(bidderService.findByProjectId(id))
    }


    /**
     * 显示项目详细信息
     */
    @GetMapping("/show")
    fun show(id: String): Response {
        Assert.strExist(id, BaseController.BAD_REQUEST, "参数id不存在")
        val project = projectService.findById(id)
        Assert.notNull(project, BaseController.NOT_FOUND, "项目不存在")
        return okVo(project)
    }

    @Deprecated("")
    @GetMapping("/imgs")
    fun showImg(id: String): Response {
        Assert.strExist(id, BaseController.BAD_REQUEST, "参数id不存在")
        val project = imgRep.findByProjId(id)
        return BaseController.ok(project)
    }


}
