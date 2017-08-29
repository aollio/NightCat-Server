package com.yemao.users.web;

import com.framework.annotation.Authorization;
import com.framework.annotation.CurrentUser;
import com.framework.annotation.EnumParam;
import com.yemao.common.Response;
import com.yemao.common.base.BaseController;
import com.yemao.entity.DesignType;
import com.yemao.users.models.Profile.*;
import com.yemao.users.models.Profile;
import com.yemao.users.models.User.*;
import com.yemao.users.models.User;
import com.yemao.users.service.ExperienceService;
import com.yemao.users.service.ProfileService;
import com.yemao.users.service.UserService;
import com.yemao.utility.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static com.yemao.utility.Util.emptyStr;
import static com.yemao.utility.Util.now;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;


    @Autowired
    private ExperienceService expService;

    /**
     * 注册一个用户
     */
    @PostMapping

    public Response register(
            @RequestParam String nickname,
            @RequestParam String password,
            @EnumParam Role role,
            @RequestParam String phone,
            @RequestParam(name = "img_url", required = false) String img_url) {

        Assert.strExist(nickname, BAD_REQUEST, "nickname is null");
        Assert.strExist(password, BAD_REQUEST, "password is null");
        Assert.strExist(phone, BAD_REQUEST, "phone is null");

        User user = new User();
        user.setNickname(nickname);
        user.setPassword(password);
        user.setRole(role);
        user.setPhone(phone);
        user.setImg_url(img_url);

        userService.save(user);

        return okVo(user);
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
            @EnumParam Position position,
            @RequestParam String school,
            @RequestParam BigDecimal hourly_wage,
            @EnumParam DesignType type,
            @RequestParam String summary) {

        Assert.isTrue(user.getRole() == Role.DESIGNER,
                BAD_REQUEST, "只有设计师才可以编辑资料");

        if (!emptyStr(nickname)) {
            user.setNickname(nickname);
            userService.update(user);
        }

        Profile profile = profileService.findById(user.getUid());
        if (profile == null) {
            //用户详情不存在, 创建一个新的记录
            profile = new Profile();
            profile.setUid(user.getUid());
            profile.setCreate_time(now());
        }

        // 用户详情已经存在了
        if (service_length != 0) profile.setService_length(service_length);
        profile.setPosition(position);
        if (!emptyStr(school)) profile.setSchool(school);
        if (hourly_wage.equals(BigDecimal.ZERO)) profile.setHourly_wage(hourly_wage);
        profile.setType(type);
        if (!emptyStr(summary)) profile.setSummary(summary);
        profileService.saveOrUpdate(profile);

        return okVo(profile);
    }

    /**
     * 获取用户详情
     */
    @GetMapping("/show")
    @Authorization
    public Response show(String uid) {
        User target = userService.findById(uid);
        Assert.notNull(target, BAD_REQUEST, "用户不存在");
        return okVo(target);
    }

    @GetMapping("/show_accid")
    @Authorization
    public Response showAccid(String accid) {
        Assert.strExist(accid, BAD_REQUEST, "accid 不存在");
        User target = userService.findByAccid(accid);
        Assert.notNull(target, BAD_REQUEST, "用户不存在");
        return okVo(target);
    }

    @GetMapping("/comments")
    @Authorization
    public Response showComments(@CurrentUser User user) {
        return okVo(expService.findByUid(user.getUid()));
    }
}
