package com.nightcat.config;

import com.nightcat.common.utility.Util;
import com.nightcat.entity.Noti;
import com.nightcat.entity.Project;
import com.nightcat.entity.User;
import com.nightcat.projects.service.ProjectsService;
import com.nightcat.repository.NotificationRepository;
import com.nightcat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;

@Configuration
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {


    /**
     * 生成测试数据
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        saveUser();
        saveProjects();
        saveNotifications();
        System.out.println("启动完成");
    }

    @Autowired
    UserRepository userRepository;

    String empUid = "450be21f7c714357a1892ca5291a859c";
    String desUid = "005a9015c52c4ccbb5c669b960ef0bb7";

    public void saveUser() {

        User des = new User();
        des.setUid(desUid);
        des.setRole(User.Role.DESIGNER);
        des.setPhone("18200000000");
        des.setPassword("123456");
        des.setCreate_time(Util.now());
        des.setNickname("薄荷创意");
        des.setDel(false);
        des.setSummary("用创意给你带来夏日中的凉意");
        des.setType("室内设计");
        userRepository.save(des);

        User des1 = new User();
        des1.setUid("005a9015c52c4ccbb5c669b960efdbb7");
        des1.setRole(User.Role.DESIGNER);
        des1.setPhone("17321226809");
        des1.setPassword("123456");
        des1.setCreate_time(Util.now());
        des1.setNickname("张玮");
        des1.setDel(false);
        des1.setSummary("多年工程师设计经验");
        des1.setType("建筑设计");
        userRepository.save(des1);


        User des3 = new User();
        des3.setUid("00529015c52c4ccbb5c669b960ef0bb7");
        des3.setRole(User.Role.DESIGNER);
        des3.setPhone("陈佳丽");
        des3.setPassword("17321226809");
        des3.setCreate_time(Util.now());
        des3.setNickname("陈佳利");
        des3.setType("室内设计");
        des3.setDel(false);
        userRepository.save(des3);

        //==============================

        User emp = new User();
        emp.setUid(empUid);
        emp.setRole(User.Role.EMPLOYER);
        emp.setPhone("13052116492");
        emp.setPassword("123456");
        emp.setCreate_time(Util.now());
        emp.setNickname("王富强");
        emp.setDel(false);
        userRepository.save(emp);

    }

    @Autowired
    private ProjectsService projectsService;

    public void saveProjects() {

        Project project1 = new Project();
        project1.setId("id0");
        project1.setCreate_by(empUid);
        project1.setType("建筑设计");
        project1.setContent("大厦装修设计图, 一楼大厅");
        project1.setBudget(BigDecimal.valueOf(13000));
        project1.setArea(100);
        project1.setStatus(Project.Status.Publish);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, Calendar.JULY, 27);
        project1.setCreate_time(new Timestamp(calendar.getTime().getTime()));

        project1.setCreate_time(Util.now());

        project1.setPeriod(24);

        projectsService.save(project1);



        Project project2 = new Project();
        project2.setId("id2");
        project2.setCreate_by(empUid);
        project2.setType("建筑设计");
        project2.setContent("结构设计要求如下：1.按照所提供的三维模型设计达到指定功能要求。2.复杂完整的模型图。3.根据手绘完成设计图");
        project2.setBudget(BigDecimal.valueOf(23000));
        project2.setArea(200);

        project2.setBidder(desUid);

        project2.setPeriod(30);


        Calendar c9 = Calendar.getInstance();
        c9.set(2017, Calendar.JULY, 29);
        project2.setBid_time(new Timestamp(c9.getTime().getTime()));
        project2.setStatus(Project.Status.ConfirmDesigner_WaitDesignerConfitm);


        Calendar c2 = Calendar.getInstance();
        c2.set(2017, Calendar.JULY, 28);
        project2.setCreate_time(new Timestamp(c2.getTime().getTime()));

        projectsService.save(project2);




        Project project3 = new Project();
        project3.setId("id3");
        project3.setCreate_by(empUid);
        project3.setType("建筑设计");
        project3.setContent("能够实地考察后, 画出详细的设计图. 要求较高的精确度");
        project3.setBudget(BigDecimal.valueOf(33000));
        project3.setArea(300);
        project3.setStatus(Project.Status.Publish);
        project3.setPeriod(10);

        Calendar c4 = Calendar.getInstance();
        c4.set(2017, Calendar.JULY, 28);
        project3.setCreate_time(new Timestamp(c4.getTime().getTime()));


        projectsService.save(project3);

        Project project4 = new Project();
        project4.setId("id4");
        project4.setCreate_by(empUid);
        project4.setType("建筑设计");
        project4.setContent("根据用户的需要, 画出完善的复合需求的设计图. 可能会有多次修改, 需要设计师较好的沟通能力");
        project4.setBudget(BigDecimal.valueOf(43000));
        project4.setArea(400);
        project4.setStatus(Project.Status.BothConfirm_WaitEmployerPay);
        project4.setPeriod(100);


        Calendar c5 = Calendar.getInstance();
        c5.set(2017, Calendar.JULY, 20);
        project4.setCreate_time(new Timestamp(c5.getTime().getTime()));


        projectsService.save(project4);
    }

    @Autowired
    protected NotificationRepository notificationRepository;

    public void saveNotifications() {
        Noti noti = new Noti();
        noti.setId("id0");
        noti.setContent("恭喜你被评为5星用户");
        noti.setType("系统消息");
        noti.setUid("asda");
        Calendar calendar5 = Calendar.getInstance();
        calendar5.set(2017, Calendar.JULY, 16);
        noti.setCreate_time(new Timestamp(calendar5.getTime().getTime()));
        notificationRepository.save(noti);

        Noti noti1 = new Noti();
        noti1.setId("id2");
        noti1.setContent("你的项目沟通已经有了进展, 请你近日到服务大厅联系工作人员");
        noti1.setType("订单消息");
        noti1.setUid("asda");
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2017, Calendar.JULY, 15);
        noti1.setCreate_time(new Timestamp(calendar1.getTime().getTime()));
        notificationRepository.save(noti1);

        Noti noti3 = new Noti();
        noti3.setId("id3");
        noti3.setContent("最近, 你过得还好吗. 上海等地今日长期高温, 建议您少外出, 多家里乘凉.");
        noti3.setType("推送消息");
        noti3.setUid("asda");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, Calendar.JULY, 26);
        noti3.setCreate_time(new Timestamp(calendar.getTime().getTime()));
        notificationRepository.save(noti3);

//        Noti noti4 = new Noti();
//        noti4.setId("id4");
//        noti4.setContent("");
//        noti4.setType("系统消息");
//        noti4.setDescription("消息描述");
//        noti4.setUid(null);
//        notificationRepository.save(noti4);
//
//        Noti noti5 = new Noti();
//        noti5.setId("id5");
//        noti5.setContent("恭喜你被评为5星用户");
//        noti5.setType("系统消息");
//        noti5.setDescription("消息描述");
//        noti5.setUid(null);
//        notificationRepository.save(noti5);
    }

}