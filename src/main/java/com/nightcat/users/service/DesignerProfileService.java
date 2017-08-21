package com.nightcat.users.service;

import com.nightcat.entity.DesignerProfile;
import com.nightcat.repository.DesignerProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DesignerProfileService {


    @Autowired
    private DesignerProfileRepository profileRep;


    public void save(DesignerProfile designerProfile) {
        profileRep.save(designerProfile);
    }

    public void saveOrUpdate(DesignerProfile designerProfile) {
        profileRep.saveOrUpdate(designerProfile);
    }

    public void delete(DesignerProfile designerProfile) {
        profileRep.delete(designerProfile);
    }

    public void update(DesignerProfile designerProfile) {
        profileRep.update(designerProfile);
    }

    public List<DesignerProfile> sort(List<DesignerProfile> T) {
        return profileRep.sort(T);
    }


    public List<DesignerProfile> findBy(String[] keys, String[] values, boolean isLikeQuery) {
        return profileRep.findBy(keys, values, isLikeQuery);
    }

    public DesignerProfile findById(String id) {
        return profileRep.findById(id);
    }

}
