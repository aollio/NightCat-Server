package com.nightcat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author finderlo
 * @date 15/05/2017
 */
@Configuration
@Component
public class RedisConfig  {

    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }

//    @Bean
//    public JedisConnectionFactory redisConnectionFactory() {
//        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
//         Defaults
//        redisConnectionFactory.setHostName("192.168.1.166");
//        redisConnectionFactory.setPort(6379);
//        return redisConnectionFactory;
//    }
//
//    @Bean
//    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf) {
//        RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
//        redisTemplate.setConnectionFactory(cf);
//        return redisTemplate;
//    }



}
