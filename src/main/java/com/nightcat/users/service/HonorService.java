package com.nightcat.users.service;

import com.nightcat.utility.Util;
import com.nightcat.entity.UserHonor;
import com.nightcat.repository.HonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HonorService {

    @Autowired
    private HonorRepository honorRep;


    public List<UserHonor> findByUid(String uid) {
        return honorRep.findByUid(uid);
    }

    public void save(UserHonor honor) {
        honor.setCreate_time(Util.now());
        honor.setId(Util.uuid());
        honorRep.save(honor);
    }

    public void saveOrUpdate(UserHonor honor) {
        honorRep.saveOrUpdate(honor);
    }

    public void delete(UserHonor honor) {
        honorRep.delete(honor);
    }

    public void update(UserHonor honor) {
        honorRep.update(honor);
    }

    public List<UserHonor> sort(List<UserHonor> T) {
        return honorRep.sort(T);
    }

    public List<UserHonor> findAll() {
        return honorRep.findAll();
    }

    public List<UserHonor> findBy(String[] keys, String[] values) {
        return honorRep.findBy(keys, values);
    }

    public List<UserHonor> findBy(String key, String value) {
        return honorRep.findBy(key, value);
    }

    public List<UserHonor> findBy(String key, String value, boolean isLikeQuery) {
        return honorRep.findBy(key, value, isLikeQuery);
    }

    public List<UserHonor> findBy(String[] keys, String[] values, boolean isLikeQuery) {
        return honorRep.findBy(keys, values, isLikeQuery);
    }

    public UserHonor findById(String id) {
        return honorRep.findById(id);
    }

    public UserHonor findByIds(Map<String, String> idAndValues) {
        return honorRep.findByIds(idAndValues);
    }

    public List<UserHonor> findBy(Map<String, String> attr, boolean likeQuery) {
        return honorRep.findBy(attr, likeQuery);
    }


}
