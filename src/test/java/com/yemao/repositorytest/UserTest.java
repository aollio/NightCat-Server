package com.yemao.repositorytest;

import com.yemao.Application;
import com.yemao.entity.DesignType;
import com.yemao.users.repository.ProfileRepository;
import com.yemao.users.models.Official;
import com.yemao.users.models.Position;
import com.yemao.users.models.Role;
import com.yemao.users.models.User;
import com.yemao.users.repository.UserRepository;
import com.yemao.users.web.DesignerController;
import com.yemao.utility.Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class UserTest {

    @Autowired
    UserRepository userService;

    //    @Test
    public void test() {
        User emp = new User();
        emp.setUid("450be21f7c714357a1892ca5291a859c");
        emp.setRole(Role.EMPLOYER);
        emp.setPhone("000");
        emp.setPassword("123456");
        emp.setCreate_time(Util.now());
        emp.setNickname("小李雇主");
        emp.setDel(false);
        userService.save(emp);

        User des = new User();
        des.setUid("005a9015c52c4ccbb5c669b960ef0bb7");
        des.setRole(Role.DESIGNER);
        des.setPhone("111");
        des.setPassword("123456");
        des.setCreate_time(Util.now());
        des.setNickname("设计师");
        des.setDel(false);
        userService.save(des);
    }


    @Autowired
    ProfileRepository repository;

    @Autowired
    DesignerController designerController;

    @Test
    public void pppp() {
        System.out.println(
                ((List) (designerController
                        .query(DesignType.UNDEFINDED, null, Position.LOW,
                                Official.NOT, null, null).getContent())).size()

        );
    }

    public static void main(String[] args) {
//        repository.query().type(DesignType.UNDEFINDED).list().size()

    }
}
