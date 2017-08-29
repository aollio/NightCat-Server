package com.yemao.users.web;

import com.framework.annotation.Authorization;
import com.framework.annotation.CurrentUser;
import com.yemao.common.Response;
import com.yemao.common.base.BaseController;
import com.yemao.users.models.Experience;
import com.yemao.users.models.User.*;
import com.yemao.users.models.User;
import com.yemao.users.service.ExpCommentService;
import com.yemao.users.service.ExperienceService;
import com.yemao.utility.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/experience")
public class ExperienceController extends BaseController {


    @Autowired
    private ExperienceService expService;


    @Autowired
    private ExpCommentService expCmtService;

    /**
     * 获取用户经历, 返回一个List
     */
    @GetMapping
    public Response getExperience(@RequestParam String uid) {
        Assert.strExist(uid, BAD_REQUEST, "用户id不存在");
        return okVo(expService.findByUid(uid));
    }

    @GetMapping("/comments")
    public Response getExpComment(String id) {
        Assert.strExist(id, BAD_REQUEST, "用户经历id不存在");
        return okVo(expCmtService.findByExpId(id));
    }

    @PostMapping
    @Authorization
    public Response upload(
            @CurrentUser User user,
            @RequestParam String name,
            @RequestParam String description
    ) {

        Assert.isTrue(user.getRole() == Role.DESIGNER,
                BAD_REQUEST, "设计师才可以上传荣耀");

        Assert.strExist(name, BAD_REQUEST, "经历名称不存在");
        Assert.strExist(description, BAD_REQUEST, "经历描述不能为空");

        Experience exp = new Experience();
        exp.setName(name);
        exp.setDescription(description);
        exp.setUid(user.getUid());

        expService.save(exp);

        return okVo(exp);
    }

}
