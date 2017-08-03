package com.nightcat.users.service;

import com.nightcat.config.annotation.Authorization;
import com.nightcat.entity.DesignerProfile;
import com.nightcat.repository.DesignerProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DesignerProfileService {


    @Autowired
    private DesignerProfileRepository designerProfileRepository;


    public void save(DesignerProfile designerProfile) {
        designerProfileRepository.save(designerProfile);
    }

    public void saveOrUpdate(DesignerProfile designerProfile) {
        designerProfileRepository.saveOrUpdate(designerProfile);
    }

    public void delete(DesignerProfile designerProfile) {
        designerProfileRepository.delete(designerProfile);
    }

    public void update(DesignerProfile designerProfile) {
        designerProfileRepository.update(designerProfile);
    }

    public List<DesignerProfile> sort(List<DesignerProfile> T) {
        return designerProfileRepository.sort(T);
    }

    public List<DesignerProfile> findAll() {
        return designerProfileRepository.findAll();
    }

    public List<DesignerProfile> findBy(String[] keys, String[] values) {
        return designerProfileRepository.findBy(keys, values);
    }

    public List<DesignerProfile> findBy(String key, String value) {
        return designerProfileRepository.findBy(key, value);
    }

    public List<DesignerProfile> findBy(String key, String value, boolean isLikeQuery) {
        return designerProfileRepository.findBy(key, value, isLikeQuery);
    }

    public List<DesignerProfile> findBy(String[] keys, String[] values, boolean isLikeQuery) {
        return designerProfileRepository.findBy(keys, values, isLikeQuery);
    }

    public DesignerProfile findById(String id) {
        return designerProfileRepository.findById(id);
    }

    public DesignerProfile findByIds(Map<String, String> idAndValues) {
        return designerProfileRepository.findByIds(idAndValues);
    }

    public List<DesignerProfile> findBy(Map<String, String> attr, boolean likeQuery) {
        return designerProfileRepository.findBy(attr, likeQuery);
    }

}
