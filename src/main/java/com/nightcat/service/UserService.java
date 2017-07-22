package com.nightcat.service;

import com.nightcat.model.User;
import com.nightcat.model.UserJsonRepository;
import com.nightcat.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {


    @Autowired
    private UserJsonRepository userJsonRepository;

    public User login(String phone, String password) {
        User user = userJsonRepository.getByPhone(phone);
        if (user == null) {
            return null;
        }
        if (!Util.equalsPassword(user, password)) {
            return null;
        }
        user.setPassword("");
        return user;
    }

    public User register(String nickname, String phone, String password) {
        if (userJsonRepository.getByPhone(phone) != null) {
            return null;
        }
        String uid = UUID.randomUUID().toString();
        User user = new User();
        user.setUid(uid);
        user.setPhone(phone);
        user.setPassword(password);
        userJsonRepository.save(user);
        return user;
    }


}
