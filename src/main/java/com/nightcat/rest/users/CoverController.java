package com.nightcat.rest.users;

import com.nightcat.common.Response;
import com.nightcat.common.utility.Assert;
import com.nightcat.config.annotation.Authorization;
import com.nightcat.config.annotation.CurrentUser;
import com.nightcat.entity.Cover;
import com.nightcat.entity.User;
import com.nightcat.service.users.CoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.nightcat.common.constant.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("/users/cover")
public class CoverController {

    @Autowired
    private CoverService coverService;


    /**
     * 获取用户封面
     */
    @GetMapping
    @Authorization
    public Response cover(@RequestParam String uid) {
        Assert.strExist(uid, BAD_REQUEST, "用户id不存在");
        return Response.ok(coverService.findByUid(uid));
    }

    /**
     * 上传用户封面
     */
    @PostMapping
    @Authorization
    public Response upload(@CurrentUser User user,
                           @RequestParam String img_url) {
        Assert.strExist(img_url, BAD_REQUEST, "封面URL不存在");

        Cover cover = coverService.update(user.getUid(), img_url);
        return Response.ok(cover);
    }


}
