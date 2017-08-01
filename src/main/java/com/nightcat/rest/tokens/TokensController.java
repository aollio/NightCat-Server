package com.nightcat.rest.tokens;

import com.nightcat.common.ErrorCode;
import com.nightcat.common.Response;
import com.nightcat.common.utility.Assert;
import com.nightcat.entity.Token;
import com.nightcat.entity.User;
import com.nightcat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.nightcat.common.constant.HttpStatus.BAD_REQUEST;

/**
 * @author Aollio
 * @date 15/05/2017
 */
@RestController
@RequestMapping("/tokens")
public class TokensController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenManager tokenManager;

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

        user = userRepository.findByPhone(phone);

        //check user password
        if (user == null ||
                !user.getPassword().equals(password)) {
            return Response.error(ErrorCode.PHONE_OR_PASSWORD_ERROR);
        }

        tokenModel = tokenManager.createToken(user.getUid());
        return Response.ok(tokenModel);
    }

//    @DeleteMapping
//    @Authorization
//    public Response logout(@CurrentUser UserEntity user) {
//        tokenManager.delToken(user.getUid());
//        return Response.ok();
//    }


}
