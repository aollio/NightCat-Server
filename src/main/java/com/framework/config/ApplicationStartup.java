package com.framework.config;

import com.nightcat.common.base.BaseObject;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

import com.nightcat.entity.*;
import com.nightcat.projects.service.ProjectService;
import com.nightcat.repository.NotificationRepository;
import com.nightcat.repository.UserRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.transaction.annotation.Transactional;

import static com.nightcat.common.utility.Util.now;
import static com.nightcat.common.utility.Util.uuid;

@Profile({"dev", "aollio"})
@Configuration
@Transactional
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
    private ProjectService projService;


    @Autowired
    private NotificationRepository notificationRepository;


    String empUid = "450be21f7c714357a1892ca5291a859c";
    String desUid = "005a9015c52c4ccbb5c669b960ef0bb7";

    public void saveTestData() {
        saveUser();
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


    @Autowired
    SessionFactory sessionFactory;

    String[] empNickname = {"良锋", "诚凯", "辰鸿", "谷吉", "骏休", "琛运", "杰信", "振文", "鸿加", "腾骏"};
    String[] desNickname = {"妍柏", "云蕾", "桂淑", "桂媛", "璇梦", "莲萱", "婧梓", "曼玉", "楠阳", "梦璐"};
    String[] imgurl = {
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

    Random random = new Random();

    private String getCommentContent() {
        String[] contents = {
                "不错",
                "挺好",
                "嘿嘿, 很棒",
                "技术不错",
                "可以",
                "很不错",
                "给个赞",
                "棒棒棒哒",
                "不错",
        };
        return contents[random.nextInt(contents.length)];
    }

    private void saveUser() {
        //TODO 生成会员. 会员属性要有雇主或者设计师

        for (int i = 0; i < empNickname.length; i++) {
            User user = new User();
            user.setNickname(empNickname[i]);
            user.setPassword("123456");
            user.setPhone("1390000000" + i);
            user.setDel(false);
            user.setUid("emp" + i);
            user.setRole(User.Role.EMPLOYER);
            user.setImg_url(imgurl[i]);
            sessionFactory.getCurrentSession().save(user);
        }

        for (int i = 0; i < desNickname.length; i++) {
            User user = new User();
            user.setNickname(desNickname[i]);
            user.setPassword("123456");
            user.setPhone("1330000000" + i);
            user.setDel(false);
            user.setUid("des" + i);
            user.setImg_url(imgurl[i]);
            user.setRole(User.Role.DESIGNER);

            DesignerProfile profile = new DesignerProfile();
            profile.setUid(user.getUid());
            profile.setType(DesignType.values()[i % DesignType.values().length]);
            profile.setSummary(i % 2 == 0 ? "这是设计师简介" : null);
            profile.setService_length(random.nextInt(20) / 10.0);
            profile.setHourly_wage(BigDecimal.valueOf(100 * i));
            profile.setTotal_works(random.nextInt(500));

            int experi_count = new Random().nextInt(15);

            for (int j = 0; j < experi_count; j++) {
                Experience experience = new Experience();
                experience.setId(uuid());
                experience.setUid(user.getUid());
                experience.setName("设计摩天大楼");
                experience.setDescription("设计了许多层的摩天大楼");
                experience.setCreate_time(now());

                sessionFactory.getCurrentSession().save(experience);

                int exp_cmt_count = random.nextInt(15);
                for (int k = 0; k < exp_cmt_count; k++) {
                    ExpComment comment = new ExpComment();
                    comment.setId(uuid());
                    comment.setExp_id(experience.getId());
                    comment.setComment_time(now());
                    comment.setContent(getCommentContent());
                    comment.setUid((k % 2 == 0 ? "des" : "emp") + random.nextInt(desNickname.length));
                    sessionFactory.getCurrentSession().save(comment);
                }

                int honor_count = new Random().nextInt(50);

                for (int k = 0; k < honor_count; k++) {
                    Honor honor = new Honor();
                    honor.setUid(user.getUid());
                    honor.setId(uuid());
                    honor.setCreate_time(now());
                    honor.setGet_time(now());
                    honor.setImg_url("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502197693524&di=80f9c946a65ec6f97d92bc386a1bcbd2&imgtype=0&src=http%3A%2F%2Fimage.sowm.cn%2Fue6nEj.gif");
                    sessionFactory.getCurrentSession().save(honor);
                }


            }


            sessionFactory.getCurrentSession().save(profile);
            sessionFactory.getCurrentSession().save(user);

        }
    }

    private void saveProjects() {


        for (int i = 0; i < 40; i++) {
            Random random = new Random();
            Project project = new Project();
            project.setArea(i + 5);
            project.setId("projects" + i);
            project.setTitle(i + "CAD施工图、平面图、效果图、" + i +
                    "装修图等，图纸分类有建筑设计、房屋设计、别墅设计" +
                    "、房屋装修、结构");
            project.setType(DesignType.values()[i % DesignType.values().length]);
            project.setBidder("des" + Math.abs(random.nextInt()) % 13);
            project.setStatus(Project.Status.BothConfirm_WaitEmployerPay);
            project.setCreate_time(now());
            project.setDue_time(new Timestamp(System.currentTimeMillis() + 10000000));
            project.setBudget(BigDecimal.valueOf(new Random().nextDouble()));
            project.setFav_count(new Random().nextInt());

            project.setCreate_by("emp" + (Math.abs(random.nextInt() % 13)));
            sessionFactory.getCurrentSession().save(project);


            ProjectImage proectImage = new ProjectImage();
            proectImage.setId(uuid());
            proectImage.setProj_id("projects" + i);
            proectImage.setImg_url("https://cdn.pixabay.com/photo/2013/04/06/11/50/image-editing-101040_960_720.jpg");
            sessionFactory.getCurrentSession().save(proectImage);

        }
        for (int i = 40; i < 60; i++) {
            Project project = new Project();
            project.setArea(i + 5);
            project.setId("projects" + i);
            project.setTitle(i + "CAD施工图、平面图、效果图、" + i +
                    "装修图等，图纸分类有建筑设计、房屋设计、别墅设计" +
                    "、房屋装修、结构");
            project.setType(DesignType.values()[i % DesignType.values().length]);
            project.setStatus(Project.Status.Publish);
            project.setCreate_time(now());

            Calendar calendar = Calendar.getInstance();
            calendar.set(2017, Calendar.AUGUST, 9, 14, 8);
            project.setDue_time(new Timestamp(calendar.getTime().getTime()));
            project.setBudget(BigDecimal.valueOf(new Random().nextDouble()));
            project.setFav_count(new Random().nextInt());

            project.setCreate_by("emp" + (Math.abs(new Random().nextInt() % 13)));

            ProjectImage proectImage = new ProjectImage();
            proectImage.setId(uuid());
            proectImage.setProj_id("projects" + i);
            proectImage.setImg_url("https://cdn.pixabay.com/photo/2013/04/06/11/50/image-editing-101040_960_720.jpg");
            sessionFactory.getCurrentSession().save(proectImage);
            sessionFactory.getCurrentSession().save(project);

        }

    }


}