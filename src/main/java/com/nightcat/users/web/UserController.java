package com.nightcat.users.web;

import com.nightcat.common.Response;
import com.nightcat.utility.Assert;
import com.framework.annotation.Authorization;
import com.framework.annotation.CurrentUser;
import com.framework.annotation.EnumParam;
import com.nightcat.entity.DesignType;
import com.nightcat.entity.DesignerProfile;
import com.nightcat.entity.User;
import com.nightcat.entity.vo.UserVo;
import com.nightcat.users.service.DesignerProfileService;
import com.nightcat.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static com.nightcat.common.constant.HttpStatus.*;
import static com.nightcat.utility.Util.emptyStr;
import static com.nightcat.utility.Util.now;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private DesignerProfileService designerService;

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
     * 用户上传设计师详细信息
     */
    @PostMapping("/profile")
    @Authorization
    public Response profile(
            @CurrentUser User user,
            @RequestParam String nickname,
            @RequestParam int service_length,
            @RequestParam String position,
            @RequestParam String school,
            @RequestParam BigDecimal hourly_wage,
            @EnumParam DesignType type,
            @RequestParam String summary) {

        Assert.isTrue(user.getRole() == User.Role.DESIGNER,
                BAD_REQUEST, "只有设计师才可以编辑资料");

        if (!emptyStr(nickname)) {
            user.setNickname(nickname);
            userService.update(user);
        }

        DesignerProfile profile = designerService.findById(user.getUid());
        if (profile == null) {
            //用户详情不存在, 创建一个新的记录
            profile = new DesignerProfile();
            profile.setUid(user.getUid());
            profile.setCreate_time(now());
        }

        // 用户详情已经存在了
        if (service_length != 0) profile.setService_length(service_length);
        if (!emptyStr(position)) profile.setPosition(position);
        if (!emptyStr(school)) profile.setSchool(school);
        if (hourly_wage.equals(BigDecimal.ZERO)) profile.setHourly_wage(hourly_wage);
        profile.setType(type);
        if (!emptyStr(summary)) profile.setSummary(summary);
        designerService.saveOrUpdate(profile);
        return Response.ok(profile);
    }

    /**
     * 获取用户详情
     */
    @GetMapping("/show")
    @Authorization
    public Response show(String uid) {
        UserVo target = userService.findById(uid);
        Assert.notNull(target, BAD_REQUEST, "用户不存在");
        return Response.ok(target);
    }

    @GetMapping("/show_accid")
    @Authorization
    public Response showAccid(String accid) {
        Assert.strExist(accid, BAD_REQUEST, "accid 不存在");
        UserVo target = userService.findByAccid(accid);
        Assert.notNull(target, BAD_REQUEST, "用户不存在");
        return Response.ok(target);
    }

}
