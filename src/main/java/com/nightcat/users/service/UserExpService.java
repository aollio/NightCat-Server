package com.nightcat.users.service;

import com.nightcat.utility.Util;
import com.nightcat.entity.UserExperience;
import com.nightcat.repository.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserExpService {

    @Autowired
    private ExperienceRepository expRep;

    @Autowired
    private UserService userServ;

    public List<UserExperience> findByUid(String uid) {
        return expRep.findByUid(uid);
    }

    public void save(UserExperience exp) {
        exp.setCreate_time(Util.now());
        exp.setId(Util.uuid());
        expRep.save(exp);
    }

    public void saveOrUpdate(UserExperience userExperience) {
        expRep.saveOrUpdate(userExperience);
    }

    public void delete(UserExperience userExperience) {
        expRep.delete(userExperience);
    }

    public void update(UserExperience userExperience) {
        expRep.update(userExperience);
    }

    public List<UserExperience> sort(List<UserExperience> T) {
        return expRep.sort(T);
    }

    public List<UserExperience> findAll() {
        return expRep.findAll();
    }

    public List<UserExperience> findBy(String[] keys, String[] values) {
        return expRep.findBy(keys, values);
    }

    public List<UserExperience> findBy(String key, String value) {
        return expRep.findBy(key, value);
    }

    public List<UserExperience> findBy(String key, String value, boolean isLikeQuery) {
        return expRep.findBy(key, value, isLikeQuery);
    }

    public List<UserExperience> findBy(String[] keys, String[] values, boolean isLikeQuery) {
        return expRep.findBy(keys, values, isLikeQuery);
    }

    public UserExperience findById(String id) {
        return expRep.findById(id);
    }

    public UserExperience findByIds(Map<String, String> idAndValues) {
        return expRep.findByIds(idAndValues);
    }

    public List<UserExperience> findBy(Map<String, String> attr, boolean likeQuery) {
        return expRep.findBy(attr, likeQuery);
    }





}
