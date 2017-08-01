package com.nightcat.rest.users;

import com.nightcat.common.Response;
import com.nightcat.common.utility.Assert;
import com.nightcat.config.annotation.Authorization;
import com.nightcat.config.annotation.EnumParam;
import com.nightcat.entity.User;
import com.nightcat.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.nightcat.common.constant.HttpStatus.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;


    /**
     * 注册一个用户
     */
    @PostMapping
    public Response register(
            @RequestParam String nickname,
            @RequestParam String password,
            @EnumParam User.Role role,
            @RequestParam String phone,
            @RequestParam(name = "img_url", required = false) String img_url) {

        Assert.strExist(nickname, BAD_REQUEST, "nickname is null");
        Assert.strExist(password, BAD_REQUEST, "passowrd is null");
        Assert.strExist(phone, BAD_REQUEST, "phone is null");

        User user = new User();
        user.setNickname(nickname);
        user.setPassword(password);
        user.setRole(role);
        user.setPhone(phone);
        user.setImg_url(img_url);

        userService.save(user);

        return Response.ok(user);
    }

    /**
     * 获取用户详情
     */
    @GetMapping("/show")
    @Authorization
    public Response show(@RequestParam String uid) {

        User target = userService.findById(uid);

        Assert.notNull(target, BAD_REQUEST, "用户不存在");

        return Response.ok(target);
    }







}
