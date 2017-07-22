package com.nightcat;

import com.nightcat.model.User;
import com.nightcat.model.UserJsonRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test() throws Exception {
        // 保存字符串
        stringRedisTemplate.opsForValue().set("aaa", "13");
    }

    @Autowired
    private UserJsonRepository userJsonRepository;

    @Test
    public void test1() throws Exception {
        // 保存字符串
        User user = new User();
        user.setPhone("1234123");
        user.setNickname("nickname");
        user.setDel(true);
        user.setRealname("Aollio");
        user.setRole("01");
        user.setUid("as");
        userJsonRepository.save(user);
        Assert.assertTrue("as".equals(userJsonRepository.getByUid("as").getUid()));
    }


}
