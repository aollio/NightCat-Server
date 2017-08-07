package com.nightcat.users.service;

import com.nightcat.common.utility.Util;
import com.nightcat.entity.User;
import com.nightcat.entity.UserAuthentication;
import com.nightcat.repository.UserAuthenticationRepository;
import com.nightcat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class AuthenticationService {


    @Autowired
    private UserAuthenticationRepository authRepository;

    @Autowired
    private UserRepository userRepository;

    public void save(UserAuthentication auth) {
        authRepository.save(auth);
    }


    @Transactional(rollbackFor = Exception.class)
    public void upload(
            User user,
            String realname,
            String id_card,
            String position,
            String position_img_url,
            String school,
            String school_img_url
    ) {

        //更新用户信息
        user.setRealname(realname);
        user.setId_card(id_card);
        userRepository.update(user);


        //保存职称认证审核单
        UserAuthentication position_auth = new UserAuthentication();
        position_auth.setUid(user.getUid());
        position_auth.setImg_url(position_img_url);
        position_auth.setName(position);
        position_auth.setStatus(UserAuthentication.Status.Comfirming);
        position_auth.setType(UserAuthentication.Type.Position);
        position_auth.setCreate_time(Util.now());

        authRepository.save(position_auth);

        //保存学历认证审核单
        UserAuthentication school_auth = new UserAuthentication();
        school_auth.setUid(user.getUid());
        school_auth.setImg_url(school_img_url);
        school_auth.setName(school);
        school_auth.setStatus(UserAuthentication.Status.Comfirming);
        school_auth.setType(UserAuthentication.Type.School);
        school_auth.setCreate_time(Util.now());

        authRepository.save(school_auth);
    }

    public void saveOrUpdate(UserAuthentication userAuthentication) {
        authRepository.saveOrUpdate(userAuthentication);
    }

    public void delete(UserAuthentication userAuthentication) {
        authRepository.delete(userAuthentication);
    }

    public void update(UserAuthentication userAuthentication) {
        authRepository.update(userAuthentication);
    }

    public List<UserAuthentication> sort(List<UserAuthentication> T) {
        return authRepository.sort(T);
    }

    public List<UserAuthentication> findAll() {
        return authRepository.findAll();
    }

    public List<UserAuthentication> findBy(String[] keys, String[] values) {
        return authRepository.findBy(keys, values);
    }

    public List<UserAuthentication> findBy(String key, String value) {
        return authRepository.findBy(key, value);
    }

    public List<UserAuthentication> findBy(String key, String value, boolean isLikeQuery) {
        return authRepository.findBy(key, value, isLikeQuery);
    }

    public List<UserAuthentication> findBy(String[] keys, String[] values, boolean isLikeQuery) {
        return authRepository.findBy(keys, values, isLikeQuery);
    }

    public UserAuthentication findById(String id) {
        return authRepository.findById(id);
    }

    public UserAuthentication findByIds(Map<String, String> idAndValues) {
        return authRepository.findByIds(idAndValues);
    }

    public List<UserAuthentication> findBy(Map<String, String> attr, boolean likeQuery) {
        return authRepository.findBy(attr, likeQuery);
    }


}
