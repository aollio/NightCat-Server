package com.yemao.users.service;

import com.yemao.common.base.BaseObject;
import com.yemao.common.constant.Constant;
import com.yemao.im.service.ImService;
import com.yemao.users.models.Profile;
import com.yemao.users.models.User;
import com.yemao.users.repository.UserRepository;
import com.yemao.utility.Assert;
import com.yemao.utility.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.yemao.common.constant.HttpStatus.*;

@Service
public class UserService extends BaseObject {


    @Autowired
    private UserRepository userRep;

    @Autowired
    private ProfileService profileServ;

    @Autowired
    private ImService imServ;

    public void save(User user) {
        User olduser = userRep.findByPhone(user.getPhone());
        Assert.isNull(olduser, BAD_REQUEST, "手机号已被使用");

        user.setUid(Util.uuid());

        if (imServ.registerIm(user)) {
            logger.error("注册nim失败");
        }

        if (user.getImg_url() == null) {
            user.setImg_url(Constant.randomAvatar());
        }

        if (user.getRole() == User.Role.DESIGNER) {
            saveDesigner(user);
        }

        userRep.save(user);
    }

    private void saveDesigner(User user) {
        Profile profile = new Profile();
        profile.setUid(user.getUid());
        profileServ.save(profile);
    }


    public User findByPhone(String phone) {
        return userRep.findByPhone(phone);
    }

    public void saveOrUpdate(User user) {
        userRep.saveOrUpdate(user);
    }

    public void delete(User user) {
        userRep.delete(user);
    }

    public void update(User user) {
        userRep.update(user);
    }


    public User findById(String id) {

        User user = userRep.findById(id);
        if (user == null) return null;

        Profile profile = profileServ.findById(id);
        return user;
    }

    public User findByAccid(String accid) {
        User user = userRep.findByAccid(accid);
        if (user == null) return null;

        return user;
    }


}
