package com.nightcat.users.web;

import com.nightcat.common.Response;
import com.nightcat.common.utility.Assert;
import com.nightcat.common.utility.Util;
import com.nightcat.config.annotation.Authorization;
import com.nightcat.config.annotation.CurrentUser;
import com.nightcat.entity.Experience;
import com.nightcat.entity.User;
import com.nightcat.users.service.UsersExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.nightcat.common.constant.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("/users/experience")
public class ExperienceController {


    @Autowired
    private UsersExperienceService experienceService;


    /**
     * 获取用户经历, 返回一个List
     */
    @GetMapping
    @Authorization
    public Response getExperience(@RequestParam String uid) {
        Assert.strExist(uid, BAD_REQUEST, "用户id不存在");
        return Response.ok(experienceService.findByUid(uid));
    }

    @PostMapping
    @Authorization
    public Response upload(
            @CurrentUser User user,
            @RequestParam String name,
            @RequestParam String description
    ) {

        Assert.isTrue(user.getRole() == User.Role.DESIGNER,
                BAD_REQUEST, "设计师才可以上传荣耀");

        Assert.strExist(name, BAD_REQUEST, "经历名称不存在");
        Assert.strExist(description, BAD_REQUEST, "经历描述不能为空");

        Experience experience = new Experience();
        experience.setName(name);
        experience.setDescription(description);

        experience.setUid(user.getUid());
        experience.setCreate_time(Util.now());

        experienceService.save(experience);
        return Response.ok(experience);
    }
}
