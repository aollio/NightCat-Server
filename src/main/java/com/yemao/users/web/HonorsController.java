package com.yemao.users.web;

import com.framework.annotation.Authorization;
import com.framework.annotation.CurrentUser;
import com.yemao.common.Response;
import com.yemao.users.models.Honor;
import com.yemao.users.models.User.Role;
import com.yemao.users.models.User;
import com.yemao.users.service.HonorService;
import com.yemao.utility.Assert;
import com.yemao.utility.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

import static com.yemao.common.Response.ok;
import static com.yemao.common.constant.HttpStatus.BAD_REQUEST;


@RestController
@RequestMapping("/users/honors")
public class HonorsController {


    @Autowired
    private HonorService honorService;

    @GetMapping
    public Response honors(@RequestParam String uid) {
        Assert.strExist(uid, BAD_REQUEST, "用户id不存在");
        return ok(honorService.findByUid(uid));
    }

    @PostMapping
    @Authorization
    public Response upload(
            @CurrentUser User user,
            @RequestParam String name,
            @RequestParam String img_url,
            @RequestParam String get_time
    ) {

        Assert.isTrue(user.getRole() == Role.DESIGNER,
                BAD_REQUEST, "设计师才可以上传荣耀");

        Assert.strExist(name, BAD_REQUEST, "荣誉名称不存在");
        Assert.strExist(img_url, BAD_REQUEST, "荣誉图片路径不存在");
        Assert.strExist(get_time, BAD_REQUEST, "荣誉获取时间不存在");
        Timestamp timestamp = Util.timeFromStr(get_time);
        Assert.notNull(timestamp, BAD_REQUEST, "获取时间格式非法");

        Honor honor = new Honor();
        honor.setGet_time(timestamp);
        honor.setImg_url(img_url);
        honor.setName(name);
        honor.setUid(user.getUid());

        honorService.save(honor);

        return ok(honor);
    }

}
