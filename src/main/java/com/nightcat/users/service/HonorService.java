package com.nightcat.users.service;

import com.nightcat.common.utility.Util;
import com.nightcat.entity.Honor;
import com.nightcat.repository.HonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class HonorService {

    @Autowired
    private HonorRepository honorRepository;


    public List<Honor> findByUid(String uid) {
        return honorRepository.findByUid(uid);
    }

    public void save(Honor honor) {
        honor.setId(Util.uuid());
        honorRepository.save(honor);
    }

    public void saveOrUpdate(Honor honor) {
        honorRepository.saveOrUpdate(honor);
    }

    public void delete(Honor honor) {
        honorRepository.delete(honor);
    }

    public void update(Honor honor) {
        honorRepository.update(honor);
    }

    public List<Honor> sort(List<Honor> T) {
        return honorRepository.sort(T);
    }

    public List<Honor> findAll() {
        return honorRepository.findAll();
    }

    public List<Honor> findBy(String[] keys, String[] values) {
        return honorRepository.findBy(keys, values);
    }

    public List<Honor> findBy(String key, String value) {
        return honorRepository.findBy(key, value);
    }

    public List<Honor> findBy(String key, String value, boolean isLikeQuery) {
        return honorRepository.findBy(key, value, isLikeQuery);
    }

    public List<Honor> findBy(String[] keys, String[] values, boolean isLikeQuery) {
        return honorRepository.findBy(keys, values, isLikeQuery);
    }

    public Honor findById(String id) {
        return honorRepository.findById(id);
    }

    public Honor findByIds(Map<String, String> idAndValues) {
        return honorRepository.findByIds(idAndValues);
    }

    public List<Honor> findBy(Map<String, String> attr, boolean likeQuery) {
        return honorRepository.findBy(attr, likeQuery);
    }


}
