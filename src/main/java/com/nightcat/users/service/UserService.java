package com.nightcat.users.service;

import com.nightcat.common.base.BaseObject;
import com.nightcat.utility.Assert;
import com.nightcat.utility.Util;
import com.nightcat.entity.DesignerProfile;
import com.nightcat.entity.User;
import com.nightcat.entity.vo.UserVo;
import com.nightcat.im.web.ImService;
import com.nightcat.repository.DesignerProfileRepository;
import com.nightcat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.nightcat.common.constant.HttpStatus.*;

@Service
public class UserService extends BaseObject {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DesignerProfileRepository profileRep;

    @Autowired
    private ImService imServ;

    public void save(User user) {
        User olduser = userRepository.findByPhone(user.getPhone());
        Assert.isNull(olduser, BAD_REQUEST, "手机号已被使用");

        user.setUid(Util.uuid());
        if (imServ.registerIm(user)) {
            logger.error("注册nim失败");
        }
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

    public UserVo findByAccid(String accid) {
        User user = userRepository.findByAccid(accid);
        if (user == null) return null;

        DesignerProfile profile = profileRep.findById(user.getUid());
        return UserVo.from(user, profile);
    }
}
