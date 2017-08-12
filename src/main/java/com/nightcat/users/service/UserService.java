package com.nightcat.users.service;

import com.nightcat.common.utility.Assert;
import com.nightcat.common.utility.Util;
import com.nightcat.entity.DesignerProfile;
import com.nightcat.entity.User;
import com.nightcat.entity.vo.UserVo;
import com.nightcat.repository.DesignerProfileRepository;
import com.nightcat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.nightcat.common.constant.HttpStatus.*;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DesignerProfileRepository profileRep;


    public void save(User user) {
        User olduser = userRepository.findByPhone(user.getPhone());
        Assert.isNull(olduser, BAD_REQUEST, "手机号已被使用");

        user.setUid(Util.uuid());
        user.setImtoken(user.getUid());
        userRepository.save(user);
    }


    public User findByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    public void saveOrUpdate(User user) {
        userRepository.saveOrUpdate(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public void update(User user) {
        userRepository.update(user);
    }


    public UserVo findById(String id) {

        User user = userRepository.findById(id);
        if (user == null) return null;

        DesignerProfile profile = profileRep.findById(id);
        return UserVo.from(user, profile);
    }

}
