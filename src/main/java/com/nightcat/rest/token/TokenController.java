package com.nightcat.rest.token;

import com.nightcat.common.SimResponse;
import com.nightcat.entity.Token;
import com.nightcat.entity.User;
import com.nightcat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.nightcat.common.constant.HttpStatus.BAD_REQUEST;

/**
 * @author finderlo
 * @date 15/05/2017
 */
@RestController
@RequestMapping("/tokens")
public class TokenController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenManager tokenManager;

    /**
     * 登陆，缺少必选参数时，返回400HTTP状态码Status
     *
     * @author Aollio
     * @date 15/05/2017
     */
    @PostMapping
    public SimResponse login(
            @RequestParam(defaultValue = "") String uid,
            @RequestParam(defaultValue = "") String phone,
            @RequestParam String password,
            @RequestParam(defaultValue = "0") int type) {
        if (uid.equals("") && phone.equals("")) {
            return SimResponse.error(BAD_REQUEST, "uid or phone parameter not exist");
        }

        User user;
        Token tokenModel;

        if (!uid.equals("")) {
            user = userRepository.findById(uid);
        } else {
//            user = userRepository.findByPhone(phone);
        }

//        if (user == null ||
//                !user.getPassword().equals(password)) {
//            return SimResponse.error(ErrorCode.PHONE_OR_PASSWORD_ERROR);
//        }
//
//        tokenModel = tokenManager.createToken(user.getUid());
//        return SimResponse.ok(tokenModel);
        return null;
    }

//    @DeleteMapping
//    @Authorization
//    public SimResponse logout(@CurrentUser UserEntity user) {
//        tokenManager.delToken(user.getUid());
//        return SimResponse.ok();
//    }


}
