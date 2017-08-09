package com.nightcat.projects.service;

import com.nightcat.common.CatException;
import com.nightcat.common.utility.Assert;
import com.nightcat.entity.Notice;
import com.nightcat.entity.Project;
import com.nightcat.entity.ProjectBidder;
import com.nightcat.entity.ProjectDynamic;
import com.nightcat.notice.service.NoticeService;
import com.nightcat.repository.DynamicRepository;
import com.nightcat.repository.ProjectBidderRepository;
import com.nightcat.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.nightcat.common.constant.HttpStatus.BAD_REQUEST;
import static com.nightcat.common.utility.Util.now;
import static com.nightcat.common.utility.Util.revise;
import static com.nightcat.common.utility.Util.uuid;
import static com.nightcat.projects.web.ProjectProcessController.PROJECT_ALREADY_GRAB;
import static com.nightcat.projects.web.ProjectProcessController.PROJECT_NOT_PUBLISH;

@Service
public class ProjectProcessService {

    @Autowired
    private ProjectBidderRepository bidderRep;


    @Autowired
    private ProjectRepository projRep;

    @Autowired
    private DynamicRepository dynamicRepository;

    @Autowired
    private NoticeService noticeService;

    public void grab(ProjectBidder bidder) {


        Project project = projRep.findById(bidder.getProj_id());

        //check the project exist
        Assert.notNull(project, BAD_REQUEST,
                "the project not exist");
        Assert.isTrue(project.getStatus() == Project.Status.Publish,
                PROJECT_NOT_PUBLISH, "项目无法抢单了");

        //check user is already grab this project
        Assert.isNull(bidderRep.findByUidAndProjectId(bidder.getUid(), bidder.getProj_id()),
                PROJECT_ALREADY_GRAB, "already grab this project");

        //log and toast both
        bidderRep.save(bidder);

        //生成订单动态
        logger()
                .user(bidder.getUid())
                .content("抢单")
                .type(ProjectDynamic.Type.Grabbed)
                .project(bidder.getProj_id())
                .log();

        //消息通知项目发布者 雇主
        noticeService
                .sender()
                .uid(project.getCreate_by())
                .content("你的订单被抢单")
                .type(Notice.Type.PROJECT)
                .send();

    }

    public Project publish(Project project) {
        project.setId(projRep.newId());
        project.setStatus(Project.Status.Publish);
        project.setCreate_time(now());
        projRep.save(project);

        //生成订单动态
        logger()
                .user(project.getCreate_by())
                .content("发布订单成功")
                .type(ProjectDynamic.Type.Publish)
                .publisher(true)
                .project(project.getId())
                .log();
        return project;
    }


    public DynamicLogger logger() {
        return new DynamicLogger(dynamicRepository);
    }


    private static class DynamicLogger {


        private String proj_id;
        private String uid;
        private boolean publisher;
        private ProjectDynamic.Type type;
        private String content;
        private String img_url;

        private DynamicRepository dynamicRepository;

        private DynamicLogger(DynamicRepository dynamicRepository) {
            this.dynamicRepository = dynamicRepository;
        }


        public DynamicLogger project(String proj_id) {
            this.proj_id = proj_id;
            return this;
        }

        public DynamicLogger user(String uid) {
            this.uid = uid;
            return this;
        }

        public DynamicLogger publisher(boolean publisher) {
            this.publisher = publisher;
            return this;
        }

        public DynamicLogger type(ProjectDynamic.Type type) {
            this.type = type;
            return this;
        }

        public DynamicLogger content(String content) {
            this.content = content;
            return this;
        }

        public DynamicLogger img(String img_url) {
            this.img_url = img_url;
            return this;
        }


        public ProjectDynamic log() {

            ProjectDynamic dynamic = new ProjectDynamic();
            dynamic.setId(uuid());
            dynamic.setCreate_time(now());

            if (proj_id == null) {
                throw new CatException("Build Project Dynamic proj_id must be exist");
            }

            if (uid == null) {
                throw new CatException("Build Project Dynamic uid must be exist");
            }

            if (type == null) {
                throw new CatException("Build Project Dynamic type must be exist");
            }

            dynamic.setProj_id(proj_id);
            dynamic.setUid(uid);
            dynamic.setPublisher(publisher);
            dynamic.setType(type);
            dynamic.setContent(content);
            dynamic.setImg_url(img_url);

            dynamicRepository.save(dynamic);
            return dynamic;
        }
    }
}
