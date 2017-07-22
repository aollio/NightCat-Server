package com.nightcat.web;

import com.nightcat.model.User;
import com.nightcat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public User hello() {
        User user = new User();
        user.setUid("getuid");

        user.setPassword("123");
        user.setPhone("123123123");
        user.setDel(false);
        user.setNickname("getnickname");
        user.setRole("01");

        return user;
    }

    @PostMapping
    public Map posthello(
            @RequestParam String phone,
            @RequestParam String password
    ) {
        User user = new User();
        user.setUid("getuid");

        user.setPassword("123");
        user.setPhone("123123123");
        user.setDel(false);
        user.setNickname("getnickname");
        user.setRole("01");

        HashMap map = new HashMap();
        map.put("user", user);
        map.put("param-phone", phone);
        map.put("param-password", password);
        return map;
    }


    @PostMapping("/login")
    public User login(
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String password
    ) {
//        User user = userService.login(phone, password);
//        User user = new User();
//        user.setUid("getuid");
//
//        user.setPassword("123");
//        user.setPhone("123123123");
//        user.setDel(false);
//        user.setNickname("getnickname");
//        user.setRole("01");
//        return user;

        return null;
    }

//    @PostMapping
//    public Response register(
//            @RequestParam("nickname") String nickname,
//            @RequestParam("phone") String phone,
//            @RequestParam("password") String password
//    ) {
//        User user = userService.register(nickname, phone, password);
//        if (user == null) {
//            return Response.error(HttpStatus.WRONG_AUGUMENT);
//        }
//        return Response.ok(user);
//    }
}
