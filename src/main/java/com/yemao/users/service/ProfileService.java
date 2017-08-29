package com.yemao.users.service;

import com.yemao.users.repository.ProfileRepository;
import com.yemao.users.models.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {


    @Autowired
    private ProfileRepository profileRep;


    public ProfileRepository.Query query() {
        return profileRep.query();
    }

    public void save(Profile designerProfile) {
        profileRep.save(designerProfile);
    }

    public void saveOrUpdate(Profile designerProfile) {
        profileRep.saveOrUpdate(designerProfile);
    }

    public void delete(Profile designerProfile) {
        profileRep.delete(designerProfile);
    }

    public void update(Profile designerProfile) {
        profileRep.update(designerProfile);
    }

    public List<Profile> sort(List<Profile> T) {
        return profileRep.sort(T);
    }


    public List<Profile> findBy(String[] keys, String[] values, boolean isLikeQuery) {
        return profileRep.findBy(keys, values, isLikeQuery);
    }

    public Profile findById(String id) {
        return profileRep.findById(id);
    }

}
