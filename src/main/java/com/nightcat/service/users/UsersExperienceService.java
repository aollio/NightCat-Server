package com.nightcat.service.users;

import com.nightcat.common.utility.Assert;
import com.nightcat.common.utility.Util;
import com.nightcat.entity.Experience;
import com.nightcat.repository.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.nightcat.common.constant.HttpStatus.*;

@Service
public class UsersExperienceService {

    @Autowired
    private ExperienceRepository experienceRepository;

    public List<Experience> findByUid(String uid) {
        return experienceRepository.findByUid(uid);
    }

    public void save(Experience experience) {
        experience.setId(Util.uuid());
        experienceRepository.save(experience);
    }

    public void saveOrUpdate(Experience experience) {
        experienceRepository.saveOrUpdate(experience);
    }

    public void delete(Experience experience) {
        experienceRepository.delete(experience);
    }

    public void update(Experience experience) {
        experienceRepository.update(experience);
    }

    public List<Experience> sort(List<Experience> T) {
        return experienceRepository.sort(T);
    }

    public List<Experience> findAll() {
        return experienceRepository.findAll();
    }

    public List<Experience> findBy(String[] keys, String[] values) {
        return experienceRepository.findBy(keys, values);
    }

    public List<Experience> findBy(String key, String value) {
        return experienceRepository.findBy(key, value);
    }

    public List<Experience> findBy(String key, String value, boolean isLikeQuery) {
        return experienceRepository.findBy(key, value, isLikeQuery);
    }

    public List<Experience> findBy(String[] keys, String[] values, boolean isLikeQuery) {
        return experienceRepository.findBy(keys, values, isLikeQuery);
    }

    public Experience findById(String id) {
        return experienceRepository.findById(id);
    }

    public Experience findByIds(Map<String, String> idAndValues) {
        return experienceRepository.findByIds(idAndValues);
    }

    public List<Experience> findBy(Map<String, String> attr, boolean likeQuery) {
        return experienceRepository.findBy(attr, likeQuery);
    }
}
