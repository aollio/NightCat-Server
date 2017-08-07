package com.nightcat.config;

import com.nightcat.common.base.BaseObject;

import java.util.*;

import com.nightcat.common.utility.Util;
import com.nightcat.entity.DesignerProfile;
import com.nightcat.entity.Project;
import com.nightcat.entity.User;
import com.nightcat.projects.service.ProjectsService;
import com.nightcat.repository.NotificationRepository;
import com.nightcat.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;

import static com.nightcat.entity.Project.Status.BothConfirm_WaitEmployerPay;

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

    @Autowired
    SessionFactory sessionFactory;

    private void saveUser() {
        //TODO 生成会员. 会员属性要有雇主或者设计师
        String[] empNickname = {"良锋", "诚凯", "辰鸿", "谷吉", "骏休", "琛运", "杰信", "振文", "鸿加", "腾骏"};
        String[] desNickname = {"妍柏", "云蕾", "桂淑", "桂媛", "璇梦", "莲萱", "婧梓", "曼玉", "楠阳", "梦璐", "明清", "子月", "初晓"};
        String[] desImgUrl = {
                "assets/img/if_cat_emoji_face_smily-9-01_2361853.png",
                "assets/img/if_cat_emoji_face_smily-20-01_2361861.png",
                "assets/img/if_cat_emoji_face_smily-24-01_2361865.png",
                "assets/img/if_cat_emoji_face_smily-29-01_2361869.png",
                "assets/img/if_cat_emoji_face_smily-35-01_2361874.png",
                "assets/img/if_cat_emoji_face_smily-38-01_2361877.png",
                "assets/img/if_cat_emoji_face_smily-9-01_2361853.png",
                "assets/img/if_cat_emoji_face_smily-20-01_2361861.png",
                "assets/img/if_cat_emoji_face_smily-24-01_2361865.png",
                "assets/img/if_cat_emoji_face_smily-29-01_2361869.png",
                "assets/img/if_cat_emoji_face_smily-35-01_2361874.png",
                "assets/img/if_cat_emoji_face_smily-24-01_2361865.png",
                "assets/img/if_cat_emoji_face_smily-29-01_2361869.png",
                "assets/img/if_cat_emoji_face_smily-35-01_2361874.png",
        };
        String[] empImgUrl = {
                "assets/img/if_cat_emoji_face_smily-9-01_2361853.png",
                "assets/img/if_cat_emoji_face_smily-20-01_2361861.png",
                "assets/img/if_cat_emoji_face_smily-24-01_2361865.png",
                "assets/img/if_cat_emoji_face_smily-29-01_2361869.png",
                "assets/img/if_cat_emoji_face_smily-35-01_2361874.png",
                "assets/img/if_cat_emoji_face_smily-9-01_2361853.png",
                "assets/img/if_cat_emoji_face_smily-20-01_2361861.png",
                "assets/img/if_cat_emoji_face_smily-24-01_2361865.png",
                "assets/img/if_cat_emoji_face_smily-29-01_2361869.png",
                "assets/img/if_cat_emoji_face_smily-35-01_2361874.png",
                "assets/img/if_cat_emoji_face_smily-38-01_2361877.png",

        };
        DesignerProfile.Type[] desType = {
                DesignerProfile.Type.Types_1,
                DesignerProfile.Type.Types_2,
                DesignerProfile.Type.Types_3,
                DesignerProfile.Type.Types_4,
                DesignerProfile.Type.Types_5,
                DesignerProfile.Type.Types_6,
                DesignerProfile.Type.Types_7,
                DesignerProfile.Type.Types_8,
                DesignerProfile.Type.Types_9,
                DesignerProfile.Type.Types_10,
                DesignerProfile.Type.Types_11,
                DesignerProfile.Type.Types_12,
                DesignerProfile.Type.Types_13,
        };
        for (int i = 0; i < empNickname.length; i++) {
            User user = new User();
            user.setNickname(empNickname[i]);
            user.setPassword("123456");
            user.setPhone("1390000000" + i);
            user.setDel(false);
            user.setUid("emp" + i);
            user.setRole(User.Role.EMPLOYER);
            user.setImg_url(empImgUrl[i]);
            sessionFactory.getCurrentSession().save(user);
        }
        for (int i = 0; i < desNickname.length; i++) {
            User user = new User();
            user.setNickname(desNickname[i]);
            user.setPassword("123456");
            user.setPhone("1330000000" + i);
            user.setDel(false);
            user.setUid("des" + i);
            user.setImg_url(desImgUrl[i]);
            user.setRole(User.Role.DESIGNER);
            DesignerProfile profile = new DesignerProfile();
            profile.setUid(user.getUid());
            profile.setType(desType[i]);
            sessionFactory.getCurrentSession().save(profile);
            sessionFactory.getCurrentSession().save(user);

        }
    }

    private void saveProjects() {
        //TODO 生成项目类型

        DesignerProfile.Type[] projectType = {
                DesignerProfile.Type.Types_1,
                DesignerProfile.Type.Types_2,
                DesignerProfile.Type.Types_3,
                DesignerProfile.Type.Types_4,
                DesignerProfile.Type.Types_5,
                DesignerProfile.Type.Types_6,
                DesignerProfile.Type.Types_7,
                DesignerProfile.Type.Types_8,
                DesignerProfile.Type.Types_9,
                DesignerProfile.Type.Types_10,
                DesignerProfile.Type.Types_11,
                DesignerProfile.Type.Types_12,
                DesignerProfile.Type.Types_13,
                DesignerProfile.Type.Types_1,
                DesignerProfile.Type.Types_2,
                DesignerProfile.Type.Types_3,
                DesignerProfile.Type.Types_4,
                DesignerProfile.Type.Types_5,
                DesignerProfile.Type.Types_6,
                DesignerProfile.Type.Types_7,
                DesignerProfile.Type.Types_8,
                DesignerProfile.Type.Types_9,
                DesignerProfile.Type.Types_10,
                DesignerProfile.Type.Types_11,
                DesignerProfile.Type.Types_12,
                DesignerProfile.Type.Types_13,
                DesignerProfile.Type.Types_1,
                DesignerProfile.Type.Types_2,
                DesignerProfile.Type.Types_3,
                DesignerProfile.Type.Types_4,
                DesignerProfile.Type.Types_5,
                DesignerProfile.Type.Types_6,
                DesignerProfile.Type.Types_7,
                DesignerProfile.Type.Types_8,
                DesignerProfile.Type.Types_9,
                DesignerProfile.Type.Types_10,
                DesignerProfile.Type.Types_11,
                DesignerProfile.Type.Types_12,
                DesignerProfile.Type.Types_13,
                DesignerProfile.Type.Types_1,
                DesignerProfile.Type.Types_2,
                DesignerProfile.Type.Types_3,
                DesignerProfile.Type.Types_4,
                DesignerProfile.Type.Types_5,
                DesignerProfile.Type.Types_6,
                DesignerProfile.Type.Types_7,
                DesignerProfile.Type.Types_8,
                DesignerProfile.Type.Types_9,
                DesignerProfile.Type.Types_10,
                DesignerProfile.Type.Types_11,
                DesignerProfile.Type.Types_12,
                DesignerProfile.Type.Types_13,
                DesignerProfile.Type.Types_1,
                DesignerProfile.Type.Types_2,
                DesignerProfile.Type.Types_3,
                DesignerProfile.Type.Types_4,
                DesignerProfile.Type.Types_5,
                DesignerProfile.Type.Types_6,
                DesignerProfile.Type.Types_7,
                DesignerProfile.Type.Types_8,
        };


        for (int i = 0; i < 40; i++) {
            Random random = new Random();
            Project project = new Project();
            project.setArea(i + 5);
            project.setId("projects" + i);
            project.setTitle(i + "CAD施工图、平面图、效果图、" + i +
                    "装修图等，图纸分类有建筑设计、房屋设计、别墅设计" +
                    "、房屋装修、结构");
            project.setType(projectType[i]);
            project.setBidder("des" + Math.abs(random.nextInt()) % 13);
            project.setStatus(Project.Status.BothConfirm_WaitEmployerPay);

            sessionFactory.getCurrentSession().save(project);

        }
        for (int i = 40; i < 60; i++) {
            Project project = new Project();
            project.setArea(i + 5);
            project.setId("projects" + i);
            project.setTitle(i + "CAD施工图、平面图、效果图、" + i +
                    "装修图等，图纸分类有建筑设计、房屋设计、别墅设计" +
                    "、房屋装修、结构");
            project.setType(projectType[i]);
            project.setStatus(Project.Status.Publish);
            sessionFactory.getCurrentSession().save(project);

        }

    }


}