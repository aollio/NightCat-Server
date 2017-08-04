package com.nightcat.users.web;

import com.nightcat.common.Response;
import com.nightcat.common.utility.Assert;
import com.nightcat.config.annotation.Authorization;
import com.nightcat.config.annotation.CurrentUser;
import com.nightcat.entity.User;
import com.nightcat.entity.UserAuthentication;
import com.nightcat.repository.UserAuthenticationRepository;
import com.nightcat.users.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.nightcat.common.constant.HttpStatus.*;

@RestController
@RequestMapping("/users/authentication")
public class UserAuthenticationController {

    @Autowired
    private AuthenticationService authService;

    /**
     * 用户上传认证
     */
    @PostMapping
    @Authorization
    public Response upload(
            @CurrentUser User user,
            String realname,
            String id_card,
            String position,
            String position_img_url,
            String school,
            String school_img_url
    ) {

        Assert.strExist(realname, BAD_REQUEST, "param realname not exist");
        Assert.strExist(id_card, BAD_REQUEST, "param id_card not exist");
        Assert.strExist(position, BAD_REQUEST, "param position not exist");
        Assert.strExist(position_img_url, BAD_REQUEST, "param position_img_url not exist");
        Assert.strExist(school, BAD_REQUEST, "param school not exist");
        Assert.strExist(school_img_url, BAD_REQUEST, "param school_img_url not exist");


        authService.upload(user, realname, id_card, position, position_img_url,
                school, school_img_url);

        return Response.ok();
    }

}
