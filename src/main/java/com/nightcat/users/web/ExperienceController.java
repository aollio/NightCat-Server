package com.nightcat.users.web;

import com.nightcat.common.Response;
import com.nightcat.common.base.BaseController;
import com.nightcat.utility.Assert;
import com.nightcat.utility.Util;
import com.framework.annotation.Authorization;
import com.framework.annotation.CurrentUser;
import com.nightcat.entity.UserExperience;
import com.nightcat.entity.User;
import com.nightcat.users.service.ExpCommentService;
import com.nightcat.users.service.UserExpService;
import com.nightcat.vo.VoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.nightcat.common.constant.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("/users/experience")
public class ExperienceController extends BaseController {


    @Autowired
    private UserExpService expService;


    @Autowired
    private ExpCommentService expCmtService;

    @Autowired
    private VoService voService;

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

        Assert.isTrue(user.getRole() == User.Role.DESIGNER,
                BAD_REQUEST, "设计师才可以上传荣耀");

        Assert.strExist(name, BAD_REQUEST, "经历名称不存在");
        Assert.strExist(description, BAD_REQUEST, "经历描述不能为空");

        UserExperience exp = new UserExperience();
        exp.setName(name);
        exp.setDescription(description);
        exp.setUid(user.getUid());

        expService.save(exp);

        return okVo(exp);
    }

    @Override
    protected VoService getVoService() {
        return voService;
    }
}
