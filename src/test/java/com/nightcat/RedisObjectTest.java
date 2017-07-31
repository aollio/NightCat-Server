//package com.nightcat;
//
//import com.nightcat.model.User;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringBootConfiguration;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = Application.class)
//public class RedisObjectTest {
//
//    @Autowired
//    private RedisTemplate<String, User> redisTemplate;
//
//    @Test
//    public void test() throws Exception {
//        // 保存对象
//        User user = new User();
//        user.setRole("o1");
//        user.setNickname("nickname");
//        user.setPhone("1231231");
//        user.setDel(true);
//        user.setUid("12312");
//
//
////        redisTemplate.opsForValue().set(user.getUid(), user);
//        User usss =  redisTemplate.opsForValue().get("12312");
//        Assert.assertTrue(usss.isDel());
//    }
//}
