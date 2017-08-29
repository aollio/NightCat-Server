package com.yemao.users.web

import com.framework.annotation.Authorization
import com.framework.annotation.CurrentUser
import com.yemao.common.Response
import com.yemao.common.base.BaseController
import com.yemao.users.models.Experience
import com.yemao.users.models.Role
import com.yemao.users.models.User
import com.yemao.users.service.ExpCommentService
import com.yemao.users.service.ExperienceService
import com.yemao.utility.Assert
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users/experience")
class ExperienceController : BaseController() {

    @Autowired lateinit var expService: ExperienceService


    @Autowired lateinit var expCmtService: ExpCommentService


    /**
     * 获取用户经历, 返回一个List
     */
    @GetMapping
    fun getExperience(uid: String?): Response {
        Assert.strExist(uid!!, BaseController.BAD_REQUEST, "用户id不存在")
        return okVo(expService.findByUid(uid))
    }

    @GetMapping("/comments")
    fun getExpComment(id: String?): Response {
        Assert.strExist(id!!, BaseController.BAD_REQUEST, "用户经历id不存在")
        return okVo(expCmtService.findByExpId(id))
    }

    @PostMapping
    @Authorization
    fun upload(@CurrentUser user: User, name: String?, description: String?): Response {

        Assert.isTrue(user.role === Role.DESIGNER,
                BaseController.BAD_REQUEST, "设计师才可以上传荣耀")

        Assert.strExist(name!!, BaseController.BAD_REQUEST, "经历名称不存在")
        Assert.strExist(description!!, BaseController.BAD_REQUEST, "经历描述不能为空")

        val exp = Experience()
        exp.name = name
        exp.description = description
        exp.uid = user.uid

        expService.save(exp)

        return okVo(exp)
    }

}