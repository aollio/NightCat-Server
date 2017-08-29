package com.yemao.users.web

import com.framework.annotation.Authorization
import com.framework.annotation.CurrentUser
import com.yemao.common.Response
import com.yemao.common.base.BaseController
import com.yemao.users.models.User
import com.yemao.users.service.RelationService
import com.yemao.users.service.UserService
import com.yemao.utility.Assert
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class RelationController : BaseController() {

    @Autowired
    private lateinit var relationService: RelationService

    @Autowired lateinit var userService: UserService


    @GetMapping("/followers")
    @Authorization
    fun followers(@CurrentUser user: User): Response {
        return okVo(relationService.findFollowers(user.uid))
    }

    @GetMapping("/following")
    @Authorization
    fun following(@CurrentUser user: User): Response {
        return okVo(relationService.findFollowing(user.uid))
    }

    @PostMapping("/follow")
    @Authorization
    fun follow(@CurrentUser user: User, uid: String?): Response {
        Assert.notNull(userService
                .findById(uid), BaseController.BAD_REQUEST, "用户不存在")
        val re = relationService.follow(user.uid, uid!!)
        return okVo(re)
    }

    @PostMapping("/unfollow")
    @Authorization
    fun unfollow(@CurrentUser user: User, uid: String?): Response {
        Assert.notNull(userService
                .findById(uid), BaseController.BAD_REQUEST, "用户不存在")
        relationService.unfollow(user.uid, uid!!)
        return BaseController.ok()
    }


}
