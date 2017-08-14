package com.nightcat.users.service;

import com.nightcat.entity.ExpComment;
import com.nightcat.entity.vo.ExpCommentVo;
import com.nightcat.entity.vo.ExpVo;
import com.nightcat.utility.Util;
import com.nightcat.entity.Experience;
import com.nightcat.repository.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class UserExpService {

    @Autowired
    private ExperienceRepository experienceRepository;

    @Autowired
    private UserService userServ;

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

    public ExpCommentVo toVo(ExpComment src) {
        ExpCommentVo vo = new ExpCommentVo();
        Util.less2more(src, vo);
        vo.setCreator(userServ.findById(src.getUid()));
        return vo;
    }

    public List<ExpCommentVo> toVo(List<ExpComment> src) {
        List<ExpCommentVo> vos = new LinkedList<>();
        src.forEach(e -> vos.add(toVo(e)));
        return vos;
    }

    public ExpVo toVo(Experience src) {
        ExpVo vo = new ExpVo();
        Util.less2more(src, vo);
        vo.setCreator(userServ.findById(src.getUid()));
        return vo;
    }

    public List<ExpVo> toVoExp(List<Experience> src) {
        List<ExpVo> vos = new LinkedList<>();
        src.forEach(e -> vos.add(toVo(e)));
        return vos;
    }


}
