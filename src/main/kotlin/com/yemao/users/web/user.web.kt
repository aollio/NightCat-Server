package com.yemao.users.web

import com.framework.annotation.Authorization
import com.framework.annotation.CurrentUser
import com.framework.annotation.EnumParam
import com.yemao.common.Response
import com.yemao.common.base.BaseController
import com.yemao.entity.DesignType
import com.yemao.users.models.Profile
import com.yemao.users.models.Position
import com.yemao.users.models.Role
import com.yemao.users.models.User
import com.yemao.users.service.ExperienceService
import com.yemao.users.service.ProfileService
import com.yemao.users.service.UserService
import com.yemao.utility.Assert
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal
import com.yemao.utility.Util.*

@RestController
@RequestMapping("/users")
class UserController : BaseController() {

    @Autowired lateinit var userService: UserService

    @Autowired lateinit var profileService: ProfileService

    @Autowired lateinit var expService: ExperienceService

    /**
     * 注册一个用户
     */
    @PostMapping
    fun register(nickname: String?,
                 password: String?,
                 @EnumParam role: Role,
                 phone: String,
                 img_url: String?): Response {

        Assert.strExist(nickname, BaseController.BAD_REQUEST, "nickname is null")
        Assert.strExist(password, BaseController.BAD_REQUEST, "password is null")
        Assert.strExist(phone, BaseController.BAD_REQUEST, "phone is null")

        val user = User()
        user.nickname = nickname!!
        user.password = password!!
        user.role = role
        user.phone = phone
        user.img_url = img_url ?: "assets/img/cat-n23.png"

        userService.save(user)
        return okVo(user)
    }

    /**
     * 用户上传设计师详细信息
     */
    @PostMapping("/profile")
    @Authorization
    fun profile(
            @CurrentUser user: User,
            @RequestParam nickname: String,
            @RequestParam service_length: Double,
            @EnumParam position: Position,
            @RequestParam school: String,
            @RequestParam hourly_wage: BigDecimal,
            @EnumParam type: DesignType,
            @RequestParam summary: String): Response {

        Assert.isTrue(user.role === Role.DESIGNER,
                BaseController.BAD_REQUEST, "只有设计师才可以编辑资料")

        if (!emptyStr(nickname)) {
            user.nickname = nickname
            userService.update(user)
        }

        var profile: Profile? = profileService.findById(user.uid)
        if (profile == null) {
            //用户详情不存在, 创建一个新的记录
            profile = Profile()
            profile.uid = user.uid
            profile.create_time = now()
        }

        // 用户详情已经存在了
        if (service_length != 0.0) profile.service_length = service_length
        profile.position = position
        if (!emptyStr(school)) profile.school = school
        if (hourly_wage == BigDecimal.ZERO) profile.hourly_wage = hourly_wage
        profile.type = type
        if (!emptyStr(summary)) profile.summary = summary
        profileService.saveOrUpdate(profile)

        return okVo(profile)
    }

    /**
     * 获取用户详情
     */
    @GetMapping("/show")
    @Authorization
    fun show(uid: String): Response {
        val target = userService.findById(uid)
        Assert.notNull(target, BaseController.BAD_REQUEST, "用户不存在")
        return okVo(target)
    }

    @GetMapping("/show_accid")
    @Authorization
    fun showAccid(accid: String): Response {
        Assert.strExist(accid, BaseController.BAD_REQUEST, "accid 不存在")
        val target = userService.findByAccid(accid)
        Assert.notNull(target, BaseController.BAD_REQUEST, "用户不存在")
        return okVo(target)
    }

    @GetMapping("/comments")
    @Authorization
    fun showComments(@CurrentUser user: User): Response {
        return okVo(expService.findByUid(user.uid))
    }

}