package com.yemao.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yemao.Application;
import com.yemao.repository.DesignerProfileRepository;
import com.yemao.users.repository.UserRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class VoTest {


    @Autowired
    UserRepository userRepository;

    @Autowired
    DesignerProfileRepository designerProfileRepository;

    @Autowired
    ObjectMapper objectMapper;


}
