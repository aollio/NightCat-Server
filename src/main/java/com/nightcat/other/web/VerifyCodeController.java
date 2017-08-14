package com.nightcat.other.web;

import com.nightcat.common.Response;
import com.nightcat.common.constant.HttpStatus;
import com.nightcat.utility.Util;
import com.nightcat.utility.wangyi.MobileMessageSend;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/verify_code")
public class VerifyCodeController {


    @GetMapping
    public Response verify_code(@RequestParam(required = false) String phone) throws IOException {

        if (Util.emptyStr(phone)) return Response.error(HttpStatus.BAD_REQUEST, "手机号不存在");
        //返回414 错误码

        MobileMessageSend.Verify verify = MobileMessageSend.sendMsg(phone);
        Response response = new Response();
        response.setStatus(verify.getCode());
        response.setMessage(verify.getMsg());
        response.setContent(verify.getObj());
        if (response.getStatus() == 414) {
            response.setMessage("手机号格式不正确");
        }

        return response;
    }
}
