package com.yemao.users.service;

import com.yemao.users.repository.AuthenticationRepository;
import com.yemao.users.repository.UserRepository;
import com.yemao.utility.Util;
import com.yemao.users.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthenticationService {


    @Autowired
    private AuthenticationRepository authRepository;

    @Autowired
    private UserRepository userRepository;

    public void save(Authentication auth) {
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
        Authentication position_auth = new Authentication();
        position_auth.setUid(user.getUid());
        position_auth.setImg_url(position_img_url);
        position_auth.setName(position);
        position_auth.setStatus(Authentication.Status.Comfirming);
        position_auth.setType(Authentication.Type.Position);
        position_auth.setCreate_time(Util.now());
        position_auth.setId(Util.uuid());

        authRepository.save(position_auth);

        //保存学历认证审核单
        Authentication school_auth = new Authentication();
        school_auth.setUid(user.getUid());
        school_auth.setImg_url(school_img_url);
        school_auth.setName(school);
        school_auth.setStatus(Authentication.Status.Comfirming);
        school_auth.setType(Authentication.Type.School);
        school_auth.setCreate_time(Util.now());
        school_auth.setId(Util.uuid());

        authRepository.save(school_auth);

    }

    public void saveOrUpdate(Authentication userAuthentication) {
        authRepository.saveOrUpdate(userAuthentication);
    }

    public void delete(Authentication userAuthentication) {
        authRepository.delete(userAuthentication);
    }

    public void update(Authentication userAuthentication) {
        authRepository.update(userAuthentication);
    }


    public List<Authentication> findAll() {
        return authRepository.findAll();
    }

    public List<Authentication> findBy(String[] keys, String[] values) {
        return authRepository.findBy(keys, values);
    }

    public List<Authentication> findBy(String key, String value) {
        return authRepository.findBy(key, value);
    }

    public List<Authentication> findBy(String key, String value, boolean isLikeQuery) {
        return authRepository.findBy(key, value, isLikeQuery);
    }

    public List<Authentication> findBy(String[] keys, String[] values, boolean isLikeQuery) {
        return authRepository.findBy(keys, values, isLikeQuery);
    }

    public Authentication findById(String id) {
        return authRepository.findById(id);
    }


}
