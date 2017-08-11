package com.nightcat.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.framework.annotation.Authorization;
import com.google.gson.Gson;
import com.nightcat.Application;
import com.nightcat.entity.vo.UserVo;
import com.nightcat.repository.DesignerProfileRepository;
import com.nightcat.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class VoTest {


    @Autowired
    UserRepository userRepository;

    @Autowired
    DesignerProfileRepository designerProfileRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void userVo() throws JsonProcessingException {
        User designer = userRepository.findById("emp0");
        DesignerProfile profile = designerProfileRepository.findById("emp0");
        UserVo vo = UserVo.from(designer, profile);
        System.out.println(objectMapper.writeValueAsString(vo));
    }

}
