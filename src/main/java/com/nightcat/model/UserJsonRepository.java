package com.nightcat.model;

import com.google.gson.Gson;
import com.nightcat.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserRepository
 */
@Repository
public class UserJsonRepository {

    private final JSONUtil jsonUtil;

    private final RedisTemplate<String, String> redisTemplate;

    private static final String INDEX_KEY_PREFIX = "User:";
    private static final String INDEX_PHONE_PREFIX = INDEX_KEY_PREFIX + "Phone:";

    @Autowired
    public UserJsonRepository(JSONUtil jsonUtil, RedisTemplate<String, String> redisTemplate) {
        this.jsonUtil = jsonUtil;
        this.redisTemplate = redisTemplate;
    }


    public void save(User user) {
        String key = INDEX_KEY_PREFIX.concat(user.getUid());
        //放入主对象,维护主键索引
        redisTemplate.opsForValue().set(key, jsonUtil.toJson(user));
        //维护手机号索引

    }


    public User getByUid(String username) {
        String key = INDEX_KEY_PREFIX + username;
        User user = null;
        String userJson = redisTemplate.opsForValue().get(key);
        if (userJson != null && !userJson.equals("")) {
            user = new Gson().fromJson(userJson, User.class);
        }
        return user;
    }

    public User getByPhone(String phone) {
        String key = INDEX_PHONE_PREFIX + phone;
        User user = null;
        String userJson = redisTemplate.opsForValue().get(key);
        if (userJson != null && !userJson.equals("")) {
            user = new Gson().fromJson(userJson, User.class);
        }
        return user;
    }


    public void delByKey(String key) {
        redisTemplate.opsForValue().getOperations().delete(key);
    }
}

