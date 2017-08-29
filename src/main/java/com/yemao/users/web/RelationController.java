package com.yemao.users.web;


import com.framework.annotation.Authorization;
import com.framework.annotation.CurrentUser;
import com.yemao.common.Response;
import com.yemao.common.base.BaseController;
import com.yemao.users.models.Relation;
import com.yemao.users.models.User;
import com.yemao.users.service.RelationService;
import com.yemao.users.service.UserService;
import com.yemao.utility.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
class RelationController extends BaseController {

    @Autowired
    private RelationService relationService;

    @Autowired
    UserService userService;


    @GetMapping("/followers")
    @Authorization
    public Response followers(@CurrentUser User user) {
        return okVo(relationService.findFollowers(user.getUid()));
    }

    @GetMapping("/following")
    @Authorization
    public Response following(@CurrentUser User user) {
        return okVo(relationService.findFollowing(user.getUid()));
    }

    @PostMapping("/follow")
    @Authorization
    public Response follow(@CurrentUser User user, String uid) {
        Assert.notNull(userService
                .findById(uid), BaseController.BAD_REQUEST, "用户不存在");
        Relation re = relationService.follow(user.getUid(), uid);
        return okVo(re);
    }

    @PostMapping("/unfollow")
    @Authorization
    public Response unfollow(@CurrentUser User user, String uid) {
        Assert.notNull(userService
                .findById(uid), BaseController.BAD_REQUEST, "用户不存在");
        relationService.unfollow(user.getUid(), uid);
        return BaseController.ok();
    }
}
