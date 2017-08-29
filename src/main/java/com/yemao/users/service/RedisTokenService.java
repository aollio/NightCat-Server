package com.yemao.users.service;

import com.yemao.common.constant.Constant;
import com.yemao.users.models.Token;
import com.yemao.utility.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author Aollio
 * @date 15/05/2017
 */
@Component
public class RedisTokenService implements TokenService {

    private Logger logger = LoggerFactory.getLogger(RedisTokenService.class);

    private StringRedisTemplate redis;

    @Autowired
    public void setRedis(StringRedisTemplate redis) {
        this.redis = redis;
        redis.setKeySerializer(new JdkSerializationRedisSerializer());
    }

    @Override
    public Token createToken(String uid) {
        String token = Util.uuid().replace("_", "");
        token = uid + "_" + token;
        logger.info("create tokens. uid:" + uid + "  tokens:" + token);
        Token tokenModel = new Token(token, uid);

        String oldtoken = redis.boundValueOps(uid).get();

        if (oldtoken != null) {
            redis.boundValueOps(uid).expire(Constant.TOKEN_EXPIRES_HOUR, TimeUnit.DAYS);
            return new Token(oldtoken, uid);
        }

        //set expire time
        redis.boundValueOps(uid).set(token, Constant.TOKEN_EXPIRES_HOUR, TimeUnit.DAYS);
        return tokenModel;
    }

    @Override
    public boolean checkToken(Token model) {
        if (model == null) {
            logger.warn("model is null");
            return false;
        }

        String token = redis.boundValueOps(model.getUid()).get();

        if (token == null || !token.equals(model.getToken())) {
            return false;
        }

        redis.boundValueOps(model.getUid()).expire(Constant.TOKEN_EXPIRES_HOUR, TimeUnit.DAYS);
        logger.info("check model is right, update the tokens expires time. uid: " + model.getUid());
        return true;
    }

    @Override
    public Token getToken(String authentication) {
        if (authentication == null || authentication.length() == 0) {
            logger.error("authentication is null or length is 0");
            return null;
        }
        String[] param = authentication.split("_");
        if (param.length != 2) {
            logger.error("authentication is not format[uid_uuid]");
            return null;
        }

        String uid = param[0];
        return new Token(authentication, uid);
    }


    @Override
    public void delToken(String uid) {
        redis.delete(uid);
    }
}
