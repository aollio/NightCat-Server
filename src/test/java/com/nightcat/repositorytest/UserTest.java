package com.nightcat.repositorytest;

import com.nightcat.Application;
import com.nightcat.common.utility.Util;
import com.nightcat.entity.User;
import com.nightcat.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class UserTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void test() {
        User user = new User();
        user.setUid(Util.uuid());
        user.setRole(User.Role.DESIGNER);
        user.setPhone("123456");
        user.setPassword("123456");
        user.setDel(false);
        userRepository.saveOrUpdate(user);
    }

}
