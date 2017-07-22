package com.nightcat.model;

import com.nightcat.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;


/**
 * UserRepository
 */
@Repository
public class UserObjRepository {


    private final RedisTemplate<String, User> redisTemplate;

    private static final String INDEX_KEY_PREFIX = "User:";
    private static final String INDEX_PHONE_PREFIX = INDEX_KEY_PREFIX + "Phone:";

    @Autowired
    public UserObjRepository(JSONUtil jsonUtil, RedisTemplate<String, User> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    public void save(User user) {
        String key = INDEX_KEY_PREFIX.concat(user.getUid());
        //放入主对象,维护主键索引
        redisTemplate.opsForValue().set(key, user);
        //维护手机号索引

    }


    public User getByUid(String username) {
        String key = INDEX_KEY_PREFIX + username;
        return redisTemplate.opsForValue().get(key);
    }

    public User getByPhone(String phone) {
        String key = INDEX_PHONE_PREFIX + phone;
        User user = null;
        return redisTemplate.opsForValue().get(key);
    }


    public void delByKey(String key) {
        redisTemplate.opsForValue().getOperations().delete(key);
    }
}

