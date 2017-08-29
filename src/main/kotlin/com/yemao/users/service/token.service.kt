package com.yemao.users.service

import com.yemao.common.base.BaseObject
import com.yemao.common.constant.Constant
import com.yemao.users.models.Token
import com.yemao.utility.Util
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

interface TokenService {
    fun createToken(uid: String): Token

    fun checkToken(token: Token?): Boolean

    fun getToken(authentication: String?): Token?

    fun delToken(uid: String)
}

@Component
class TokenServiceImpl : TokenService, BaseObject() {

    private lateinit var redis: StringRedisTemplate

    @Autowired
    fun setRedisTemplate(redis: StringRedisTemplate) {
        this.redis = redis
        redis.keySerializer = JdkSerializationRedisSerializer()
    }

    override fun createToken(uid: String): Token {
        var token = Util.uuid().replace("_", "")
        token = uid + "_" + token
        logger.info("create tokens. uid:$uid  tokens:$token")
        val tokenModel = Token(token, uid)

        val oldtoken = redis.boundValueOps(uid).get()

        if (oldtoken != null) {
            redis.boundValueOps(uid).expire(Constant.TOKEN_EXPIRES_HOUR.toLong(), TimeUnit.DAYS)
            return Token(oldtoken, uid)
        }

        //set expire time
        redis.boundValueOps(uid).set(token, Constant.TOKEN_EXPIRES_HOUR.toLong(), TimeUnit.DAYS)
        return tokenModel
    }

    override fun checkToken(token: Token?): Boolean {
        if (token == null) {
            logger.warn("model is null")
            return false
        }
        val token_str = redis.boundValueOps(token.uid).get()
        if (token_str == null || token_str != token.token) {
            return false
        }
        redis.boundValueOps(token.uid).expire(Constant.TOKEN_EXPIRES_HOUR.toLong(), TimeUnit.DAYS)
        logger.info("check model is right, update the tokens expires time. uid: " + token.uid)
        return true
    }

    override fun getToken(authentication: String?): Token? {
        if (authentication == null || authentication.isEmpty()) {
            logger.error("authentication is null or length is 0")
            return null
        }
        val param = authentication
                .split("_".toRegex())
                .dropLastWhile { it.isEmpty() }
                .toTypedArray()
        if (param.size != 2) {
            logger.error("authentication is not format[uid_uuid]")
            return null
        }
        val uid = param[0]
        return Token(authentication, uid)
    }


    override fun delToken(uid: String) = redis.delete(uid)

}