package com.yemao.users.service;

import com.yemao.users.repository.ExperienceRepository;
import com.yemao.users.models.Experience;
import com.yemao.utility.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ExperienceService {

    @Autowired
    private ExperienceRepository expRep;

    @Autowired
    private UserService userServ;

    public List<Experience> findByUid(String uid) {
        return expRep.findByUid(uid);
    }

    public void save(Experience exp) {
        exp.setCreate_time(Util.now());
        exp.setId(Util.uuid());
        expRep.save(exp);
    }

    public void saveOrUpdate(Experience userExperience) {
        expRep.saveOrUpdate(userExperience);
    }

    public void delete(Experience userExperience) {
        expRep.delete(userExperience);
    }

    public void update(Experience userExperience) {
        expRep.update(userExperience);
    }

    public List<Experience> sort(List<Experience> T) {
        return expRep.sort(T);
    }

    public List<Experience> findAll() {
        return expRep.findAll();
    }

    public List<Experience> findBy(String[] keys, String[] values) {
        return expRep.findBy(keys, values);
    }

    public List<Experience> findBy(String key, String value) {
        return expRep.findBy(key, value);
    }

    public List<Experience> findBy(String key, String value, boolean isLikeQuery) {
        return expRep.findBy(key, value, isLikeQuery);
    }

    public List<Experience> findBy(String[] keys, String[] values, boolean isLikeQuery) {
        return expRep.findBy(keys, values, isLikeQuery);
    }

    public Experience findById(String id) {
        return expRep.findById(id);
    }

    public Experience findByIds(Map<String, String> idAndValues) {
        return expRep.findByIds(idAndValues);
    }

    public List<Experience> findBy(Map<String, String> attr, boolean likeQuery) {
        return expRep.findBy(attr, likeQuery);
    }





}
