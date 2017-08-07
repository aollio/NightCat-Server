package com.nightcat.config;

import com.nightcat.common.base.BaseObject;
import com.nightcat.common.utility.Util;
import com.nightcat.entity.Project;
import com.nightcat.entity.User;
import com.nightcat.projects.service.ProjectsService;
import com.nightcat.repository.NotificationRepository;
import com.nightcat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;

@Profile({"dev", "aollio"})
@Configuration
public class ApplicationStartup extends BaseObject implements ApplicationListener<ContextRefreshedEvent> {


    /**
     * 生成测试数据
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("开始生成测试数据");
        saveTestData();
    }

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private ProjectsService projectsService;


    @Autowired
    private NotificationRepository notificationRepository;


    String empUid = "450be21f7c714357a1892ca5291a859c";
    String desUid = "005a9015c52c4ccbb5c669b960ef0bb7";

    public void saveTestData() {
        saveUser();
        saveDesignerProfile();
        saveHonor();
        saveExperience();

        saveProjects();
        saveProjectsBidder();
        saveProjectsComments();
    }

    private void saveProjectsComments() {
        //TODO 生成项目评论消息
    }

    private void saveProjectsBidder() {
        //TODO 生成项目抢单信息
    }

    private void saveExperience() {
        //todo 生成设计师工作经历
    }

    private void saveHonor() {
        //TODO 生成设计师荣誉信息
    }

    private void saveDesignerProfile() {
        //todo 生成设计师详细信息
    }

    private void saveUser() {
        //TODO 生成会员. 会员属性要有雇主或者设计师
    }

    private void saveProjects() {
        //TODO 生成项目类型
    }


}