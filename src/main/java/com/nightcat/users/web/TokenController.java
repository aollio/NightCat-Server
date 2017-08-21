package com.nightcat.users.web;

import com.nightcat.common.Response;
import com.nightcat.common.base.BaseController;
import com.nightcat.utility.Assert;
import com.nightcat.entity.Token;
import com.nightcat.entity.User;
import com.nightcat.repository.UserRepository;
import com.nightcat.users.service.TokenService;
import com.nightcat.vo.VoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.nightcat.common.constant.HttpStatus.BAD_REQUEST;

/**
 * @author Aollio
 * @date 15/05/2017
 */
@RestController
@RequestMapping("/tokens")
public class TokenController extends BaseController {

    @Autowired
    private UserRepository userRep;

    @Autowired
    private TokenService tokenServ;

    @Autowired
    private VoService voService;

    /**
     * 登陆, 返回TOKEN，缺少必选参数时，返回400HTTP状态码Status
     *
     * @author Aollio
     * @date 15/05/2017
     */
    @PostMapping
    public Response login(
            @RequestParam(defaultValue = "") String phone,
            @RequestParam String password) {
        Assert.strExist(phone, BAD_REQUEST, "phone parameter not exist");

        User user;
        Token tokenModel;
        user = userRep.findByPhone(phone);

        //check user password
        Assert.notNull(user, BAD_REQUEST, "用户不存在");
        Assert.equals(user.getPassword(), password, BAD_REQUEST, "密码不正确");


        tokenModel = tokenServ.createToken(user.getUid());
        return Response.ok(tokenModel);
    }


    @Override
    protected VoService getVoService() {
        return voService;
    }
}
