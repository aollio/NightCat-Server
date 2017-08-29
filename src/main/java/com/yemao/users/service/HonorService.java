package com.yemao.users.service;

import com.yemao.users.repository.HonorRepository;
import com.yemao.users.models.Honor;
import com.yemao.utility.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HonorService {

    @Autowired
    private HonorRepository honorRep;


    public List<Honor> findByUid(String uid) {
        return honorRep.findByUid(uid);
    }

    public void save(Honor honor) {
        honor.setCreate_time(Util.now());
        honor.setId(Util.uuid());
        honorRep.save(honor);
    }

    public void saveOrUpdate(Honor honor) {
        honorRep.saveOrUpdate(honor);
    }

    public void delete(Honor honor) {
        honorRep.delete(honor);
    }

    public void update(Honor honor) {
        honorRep.update(honor);
    }

    public List<Honor> sort(List<Honor> T) {
        return honorRep.sort(T);
    }

    public List<Honor> findAll() {
        return honorRep.findAll();
    }

    public List<Honor> findBy(String[] keys, String[] values) {
        return honorRep.findBy(keys, values);
    }

    public List<Honor> findBy(String key, String value) {
        return honorRep.findBy(key, value);
    }

    public List<Honor> findBy(String key, String value, boolean isLikeQuery) {
        return honorRep.findBy(key, value, isLikeQuery);
    }

    public List<Honor> findBy(String[] keys, String[] values, boolean isLikeQuery) {
        return honorRep.findBy(keys, values, isLikeQuery);
    }

    public Honor findById(String id) {
        return honorRep.findById(id);
    }

    public Honor findByIds(Map<String, String> idAndValues) {
        return honorRep.findByIds(idAndValues);
    }

    public List<Honor> findBy(Map<String, String> attr, boolean likeQuery) {
        return honorRep.findBy(attr, likeQuery);
    }


}
