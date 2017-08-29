package com.yemao.users.web

import com.framework.annotation.Authorization
import com.framework.annotation.CurrentUser
import com.yemao.common.Response
import com.yemao.common.base.BaseController
import com.yemao.users.models.Honor
import com.yemao.users.models.Role
import com.yemao.users.models.User
import com.yemao.users.service.HonorService
import com.yemao.utility.Assert
import com.yemao.utility.Util
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users/honors")
class HonorController : BaseController() {

    @Autowired lateinit var honorService: HonorService

    @GetMapping
    fun honors(uid: String?): Response {
        Assert.strExist(uid!!, BaseController.BAD_REQUEST, "用户id不存在")
        return ok(honorService.findByUid(uid))
    }

    @PostMapping
    @Authorization
    fun upload(
            @CurrentUser user: User,
            name: String?, img_url: String?, get_time: String?): Response {

        Assert.isTrue(user.role === Role.DESIGNER,
                BaseController.BAD_REQUEST, "设计师才可以上传荣耀")

        Assert.strExist(name!!, BaseController.BAD_REQUEST, "荣誉名称不存在")
        Assert.strExist(img_url!!, BaseController.BAD_REQUEST, "荣誉图片路径不存在")
        Assert.strExist(get_time!!, BaseController.BAD_REQUEST, "荣誉获取时间不存在")
        val timestamp = Util.timeFromStr(get_time)
        Assert.notNull(timestamp, BaseController.BAD_REQUEST, "获取时间格式非法")

        val honor = Honor()
        honor.get_time = timestamp
        honor.img_url = img_url
        honor.name = name
        honor.uid = user.uid

        honorService.save(honor)

        return BaseController.ok(honor)
    }

}
