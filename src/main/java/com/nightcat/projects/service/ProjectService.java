package com.nightcat.projects.service;

import com.nightcat.common.base.BaseObject;
import com.nightcat.common.timing.Timer;
import com.nightcat.entity.DesignType;
import com.nightcat.entity.ProjectImage;
import com.nightcat.entity.Project;
import com.nightcat.entity.User;
import com.nightcat.vo.model.ProjectVo;
import com.nightcat.repository.ProjectBidderRepository;
import com.nightcat.repository.ProjectImagesRepository;
import com.nightcat.repository.ProjectRepository;
import com.nightcat.users.service.UserService;
import com.nightcat.utility.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

import static com.nightcat.utility.Util.now;
import static com.nightcat.utility.Util.uuid;

@Service
public class ProjectService extends BaseObject {

    @Autowired
    private ProjectRepository projRep;

    @Autowired
    private ProjectImagesRepository imgRep;

    @Autowired
    private ProjectBidderRepository bidderRep;

    @Autowired
    private Timer timer;


    public Collection<Project> findTimelineByUid(User.Role role,
                                                 String uid,
                                                 DesignType type,
                                                 int limit,
                                                 Timestamp since_time,
                                                 Timestamp max_time) {
        if (role == User.Role.EMPLOYER) {
            List<Project> projectList = projRep.findByTypeAndUid(uid, type, limit, since_time, max_time);
//            projectList.sort(Comparator.comparingLong(value -> value.getCreate_time().getTime()));
//            Collections.reverse(projectList);
            return projectList;
        }
        if (role == User.Role.DESIGNER) {
            Set<Project> projects = new HashSet<>();
            //todo 可能会造成重复
            projects.addAll(projRep.findByBidder(uid));
            bidderRep.findByUid(uid).forEach(bidder -> {
                Project project = projRep.findById(bidder.getProj_id());
                //todo 只要是竞标的都返回
                projects.add(project);
            });

//            List<Project> projectList = new LinkedList<>(projects);

//            projectList.sort(Comparator.comparingLong(value -> value.getCreate_time().getTime()));
//            Collections.reverse(projectList);
            return projects;
        }
        return new HashSet<>();
    }

    public List<Project> findByType(DesignType type, int limit, Timestamp since_time, Timestamp max_time) {
        return projRep.findByType(type, limit, since_time, max_time);
    }


    public void save(Project project) {
        projRep.save(project);
    }


    public void saveOrUpdate(Project project) {
        projRep.saveOrUpdate(project);
    }


    public void update(Project project) {
        projRep.update(project);
    }


    public Project findById(String id) {
        return projRep.findById(id);
    }



    public void saveImgUrls(List<String> img_urls, Project project) {
        if (img_urls == null) return;
        img_urls.forEach(url -> {
            ProjectImage image = new ProjectImage();
            image.setId(uuid());
            image.setProj_id(project.getId());
            image.setCreate_time(now());
            image.setImg_url(url);
            imgRep.save(image);
        });
        logger.info("保存项目图片, 项目id:" + project.getId() + ", 图片个数: " + img_urls.size() + " . 图片详情: " + img_urls.toString());
    }


}
