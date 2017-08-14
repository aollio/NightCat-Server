package com.nightcat.entity;

import com.nightcat.Application;
import com.nightcat.common.base.BaseObject;
import com.nightcat.utility.Util;
import com.nightcat.im.web.ImService;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Random;

import static com.nightcat.utility.Util.now;
import static com.nightcat.utility.Util.uuid;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class TestData extends BaseObject {

    @Test
    @Rollback(false)
    public void onApplicationEvent() {
        logger.info("开始生成测试数据");
        saveTestData();
    }


    public void saveTestData() {
        saveUser();
        saveExperience();

        saveProjects();
        saveProjectsBidder();
        saveProjectsComments();


    }


    private void saveProjects() {


        for (int i = 0; i < 10; i++) {

            Random random = new Random();
            Project project = new Project();
            project.setArea(i + 5);
            project.setId("projects" + i);

            project.setTitle(titles[i % titles.length]);


            project.setType(DesignType.values()[i % DesignType.values().length]);
            if (project.getType() == DesignType.UNDEFINDED) {
                project.setType(DesignType.Types_1);
            }

            project.setStatus(Project.Status.Publish);
            project.setCreate_time(now());
            project.setDue_time(new Timestamp(System.currentTimeMillis() + 100000000));
            project.setBudget(BigDecimal.valueOf(new Random().nextInt(10000)));

            project.setCreate_by("emp0");

            sessionFactory.getCurrentSession().save(project);


            ProjectImage proectImage = new ProjectImage();
            proectImage.setId(uuid());
            proectImage.setProj_id("projects" + i);
            proectImage.setImg_url("https://cdn.pixabay.com/photo/2013/04/06/11/50/image-editing-101040_960_720.jpg");

            sessionFactory.getCurrentSession().save(proectImage);

        }

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
            "assets/img/cat-n23.png",
            "assets/img/cat-n54.png",
    };


    String exp正方形_Prefix = "http://image.aollio.com/abc";
    int expRectanglelength = 80;

    String[] names = new String[]{
            "漕河泾办公研发园区“华鑫天地 ”",
            "比利时：Wadi旅馆",
            "iD艺象艺术区青年旅社",
            "郑州：建业艾美酒店",
            "三亚：君澜七仙岭热带雨林温泉酒店西餐厅",
            "东京火车站改造成“mAAch ecute神田万世桥”商业区",
            "普吉岛纳卡酒店与度假村",
            "雀巢新总部大楼",
            "iniala海滨别墅酒店",
            "IN/OUT企业总部翻新",
            "Arctia船务有限公司新总部"
    };

    String[] descs = new String[]{
            "一举赢得由国资开发商上海仪电(集团)有限公司举办的办公和商店综合体项目竞赛。",
            "位于奥地利施第里亚地热区的中央，它不仅是一个豪华温泉酒店，也是一个完整的艺术作品。",
            "停靠的破冰船是卡塔亚诺卡海岸环境的重要组成部分。",
            "这座25层的建筑由5层高的裙房公共区域以及350间客房的主楼组成。",
            "酒店还有两间餐厅，通过底板上的垂直开口在视觉上互相连接。",
            "酒店最闪耀的场所是三楼的舞会厅，设计的设想是悬挂金色金属网和水晶吊灯的笼子，",
            "典型酒店电梯大堂和客房走道无止尽的重复和单调，整个客房塔楼由一系列的三层高的中庭组成，空间有艺术装置的成列。"
    };

    private String randomName() {
        return names[random.nextInt(names.length)];
    }

    private String randomDesc() {
        return descs[random.nextInt(descs.length)];
    }

    private String randomAvatar() {
        return random.nextBoolean() ? imgurl[0] : imgurl[1];
    }

    private String random正方形() {
        return exp正方形_Prefix + random.nextInt(expRectanglelength);
    }

    @Autowired
    ImService imService;
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

        //雇主
        User emp = new User();
        emp.setNickname(empNickname[0]);
        emp.setPassword("123456");
        emp.setPhone("1390000000" + 0);
        emp.setDel(false);
        emp.setUid("emp" + 0);
        emp.setRole(User.Role.EMPLOYER);
        emp.setImg_url(imgurl[0]);
        imService.registerIm(emp);
        sessionFactory.getCurrentSession().save(emp);


        //设计师
        for (int i = 0; i < desNickname.length; i++) {

            User user = new User();
            user.setNickname(desNickname[i]);
            user.setPassword("123456");
            user.setPhone("1330000000" + i);
            user.setDel(false);
            user.setUid("des" + i);
            user.setImg_url(randomAvatar());
            user.setRole(User.Role.DESIGNER);

            DesignerProfile profile = new DesignerProfile();
            profile.setUid(user.getUid());
            profile.setType(DesignType.values()[i % DesignType.values().length]);
            profile.setSummary(i % 2 == 0 ? "这里是设计师简介" : null);
            profile.setService_length(random.nextInt(20) / 10.0);
            profile.setHourly_wage(BigDecimal.valueOf(100 * i));
            profile.setTotal_works(random.nextInt(500));

            int experi_count = new Random().nextInt(4);

            for (int j = 0; j < experi_count; j++) {
                Experience experience = new Experience();
                experience.setId(uuid());
                experience.setUid(user.getUid());
                experience.setName(randomName());
                experience.setDescription(randomDesc());
                experience.setCreate_time(now());
                experience.setImg_url(random正方形());

                int exp_cmt_count = random.nextInt(4);

                experience.setComment_count(exp_cmt_count);
                experience.setView_count(exp_cmt_count * 4);
                experience.setFav_count(exp_cmt_count * 2);

                sessionFactory.getCurrentSession().save(experience);

                for (int k = 0; k < exp_cmt_count; k++) {
                    ExpComment comment = new ExpComment();
                    comment.setId(uuid());
                    comment.setExp_id(experience.getId());
                    comment.setComment_time(now());
                    comment.setContent(getCommentContent());
                    comment.setUid((k % 2 == 0 ? "des" : "emp") + random.nextInt(desNickname.length));
                    sessionFactory.getCurrentSession().save(comment);
                }
            }

            int honor_count = new Random().nextInt(5);

            for (int k = 0; k < honor_count; k++) {
                Honor honor = randomHonor();
                honor.setUid(user.getUid());
                sessionFactory.getCurrentSession().save(honor);
            }


            imService.registerIm(user);
            sessionFactory.getCurrentSession().save(profile);
            sessionFactory.getCurrentSession().save(user);

        }
    }


    private Honor randomHonor() {
        String[] names = new String[]{
                "ASLA景观专业奖",
                "将美带入生活",
                "Haverkamp的时尚生活",
                "Tequila Casa Pujol 87包装设计 ",

        };
        String[] descs = new String[]{
                "这是绝对一流的：美丽，简单，完整。将水面上升的手法现代时尚。" +
                        "该设计还为人们提供了遮荫顶棚和透水表面，是在校园设计中罕见的智能设计",
                "产品里面有合适的材料与工具，甚至还有超详细易懂的浇筑指南。我们用DV" +
                        "p，总结出一个最合适的操作步骤。",
                "将带有自己独特标签的设计集合在一起展示，为观众呈现出不一样的时尚盛宴。" +
                        "他们试图通过这一系列作品打破人们对于时尚的认知。当时尚为装饰所用时，他的实用性是否依然重要？",

                "此次设计师对该产品的设计正是要突出龙舌兰的独有气质。并彰显Tequila Casa Pujol 87品牌的品质。" +
                        "设计师采用金色瓶盖压身，黄蓝相间的丝带缠绕瓶颈，纯白色的标签展现其醇厚的味觉感受，" +
                        "雕刻的瓶身是永恒的象征，包装设计简单而不乏品质。"

        };

        int ran = random.nextInt(names.length);

        Honor honor = new Honor();
        honor.setId(uuid());
        honor.setCreate_time(now());
        honor.setGet_time(now());
        honor.setName(names[ran]);
        honor.setDescription(descs[ran]);
        honor.setImg_url(random正方形());
        return honor;
    }

    String[] titles = {
            "CAD施工图、平面图、效果图装修图等，图纸分类有建筑设计、房屋设计、别墅设计房屋装修、结构",
            "室内空间设计、室内家具与陈设设计、建筑制图、房屋建筑学、装饰构造、装饰预算",
            "装饰材料、室内绿化设计、室内住宅设计、照明设计",
            "室内装修装修、室内装修陈设、家具设计及技术与管理",
            "IN企业总部翻新",
            "OUT企业总部翻新",
            "Arctia船务有限公司新总部",
            "iD艺象艺术区青年旅社"
    };

}
