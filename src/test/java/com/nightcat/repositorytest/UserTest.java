package com.nightcat.repositorytest;

import com.nightcat.Application;
import com.nightcat.repository.ProjectRepository;
import com.nightcat.utility.Util;
import com.nightcat.entity.User;
import com.nightcat.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class UserTest {

    @Autowired
    UserRepository userService;

//    @Test
    public void test() {
        User emp = new User();
        emp.setUid("450be21f7c714357a1892ca5291a859c");
        emp.setRole(User.Role.EMPLOYER);
        emp.setPhone("000");
        emp.setPassword("123456");
        emp.setCreate_time(Util.now());
        emp.setNickname("小李雇主");
        emp.setDel(false);
        userService.save(emp);

        User des = new User();
        des.setUid("005a9015c52c4ccbb5c669b960ef0bb7");
        des.setRole(User.Role.DESIGNER);
        des.setPhone("111");
        des.setPassword("123456");
        des.setCreate_time(Util.now());
        des.setNickname("设计师");
        des.setDel(false);
        userService.save(des);
    }


    @Autowired
    ProjectRepository repository;

    @Test
    public void pproj(){
        System.out.println(Util.toJson(repository.findByBidder("des0")));
    }
}
