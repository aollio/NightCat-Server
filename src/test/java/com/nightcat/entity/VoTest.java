package com.nightcat.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nightcat.Application;
import com.nightcat.vo.model.UserVo;
import com.nightcat.repository.DesignerProfileRepository;
import com.nightcat.repository.UserRepository;
import org.junit.Test;
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
