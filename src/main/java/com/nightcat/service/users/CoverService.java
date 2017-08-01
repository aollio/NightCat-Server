package com.nightcat.service.users;

import com.nightcat.common.utility.Assert;
import com.nightcat.common.utility.Util;
import com.nightcat.entity.Cover;
import com.nightcat.repository.CoverRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.nightcat.common.constant.HttpStatus.NOT_FOUND;

@Service
public class CoverService {

    public static Logger logger = Logger.getLogger(CoverService.class);

    @Autowired
    CoverRepository coverRepository;

    public Cover findByUid(String uid) {
        Cover cover = coverRepository.findByUid(uid);
        Assert.notNull(cover, NOT_FOUND, "用户封面不存在");
        return cover;
    }


    /**
     * 用户更新封面
     */
    public Cover update(String uid, String img_url) {
        Cover cover = coverRepository.findByUid(uid);
        logger.info("update cover:" + cover);
        if (cover != null) {
            cover.setImg_url(img_url);
            coverRepository.update(cover);
            return cover;
        }


        cover = new Cover();
        cover.setImg_url(img_url);
        cover.setCreate_time(Util.now());
        cover.setId(Util.uuid());
        cover.setUid(uid);

        coverRepository.save(cover);

        return cover;
    }

    public void save(Cover cover) {
        coverRepository.save(cover);
    }

    public void saveOrUpdate(Cover cover) {
        coverRepository.saveOrUpdate(cover);
    }

    public void delete(Cover cover) {
        coverRepository.delete(cover);
    }


    public List<Cover> sort(List<Cover> T) {
        return coverRepository.sort(T);
    }

    public List<Cover> findAll() {
        return coverRepository.findAll();
    }

    public List<Cover> findBy(String[] keys, String[] values) {
        return coverRepository.findBy(keys, values);
    }

    public List<Cover> findBy(String key, String value) {
        return coverRepository.findBy(key, value);
    }

    public List<Cover> findBy(String key, String value, boolean isLikeQuery) {
        return coverRepository.findBy(key, value, isLikeQuery);
    }

    public List<Cover> findBy(String[] keys, String[] values, boolean isLikeQuery) {
        return coverRepository.findBy(keys, values, isLikeQuery);
    }

    public Cover findById(String id) {
        return coverRepository.findById(id);
    }

    public Cover findByIds(Map<String, String> idAndValues) {
        return coverRepository.findByIds(idAndValues);
    }

    public List<Cover> findBy(Map<String, String> attr, boolean likeQuery) {
        return coverRepository.findBy(attr, likeQuery);
    }


}
