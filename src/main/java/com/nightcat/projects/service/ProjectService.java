package com.nightcat.projects.service;

import com.nightcat.common.base.BaseObject;
import com.nightcat.common.timing.Timer;
import com.nightcat.entity.DesignType;
import com.nightcat.entity.ProjectImage;
import com.nightcat.entity.Project;
import com.nightcat.entity.User;
import com.nightcat.entity.vo.ProjectVo;
import com.nightcat.repository.ProjectBidderRepository;
import com.nightcat.repository.ProjectImagesRepository;
import com.nightcat.repository.ProjectRepository;
import com.nightcat.users.service.UserService;
import com.nightcat.utility.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.management.BufferPoolMXBean;
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
            return projRep.findByTypeAndUid(uid, type, limit, since_time, max_time);
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

            return projects;
        }
        return new HashSet<>();
    }

    public Set<Project> findByType(DesignType type, int limit, Timestamp since_time, Timestamp max_time) {
        return new HashSet<>(projRep.findByType(type, limit, since_time, max_time));
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

    public ProjectVo toVo(Project project) {
        if (project == null) return null;
        List<ProjectImage> imgs = imgRep.findByProjId(project.getId());
        return from(project, imgs);

    }

    public Collection<ProjectVo> toVo(Collection<Project> projects) {

        Set<ProjectVo> results = new HashSet<>();

        projects.forEach(proj -> {
            results.add(toVo(proj));
        });

        return results;
    }

    @Autowired
    private UserService userServ;

    public ProjectVo from(Project p, List<ProjectImage> img_urls) {
        ProjectVo vo = new ProjectVo();


        vo.setId(p.getId());
        vo.setTitle(p.getTitle());
        vo.setType(p.getType());
        vo.setContent(p.getContent());
        vo.setBudget(p.getBudget());
        vo.setArea(p.getArea());
        vo.setArea_count(p.getArea_count());
        vo.setDepth(p.getDepth());
        vo.setPeriod(p.getPeriod());
        vo.setStart_time(p.getStart_time());
        vo.setEnd_time(p.getEnd_time());
        vo.setStatus(p.getStatus());
        vo.setCreate_by(p.getCreate_by());
        vo.setCreate_time(p.getCreate_time());
        vo.setGood(p.isGood());
        vo.setModify_by(p.getModify_by());
        vo.setModify_mark(p.getModify_by());
        vo.setDue_time(p.getDue_time());
        vo.setFav_count(p.getFav_count());
        vo.setGrab_count(p.getGrab_count());
        vo.setCancel_reason(p.getCancel_reason());

        if (img_urls != null) {
            img_urls.forEach(e -> {
                if (e.getProj_id().equals(p.getId()))
                    vo.getImg_urls().add(e.getImg_url());
            });
        }

        vo.setCreator(userServ.findById(p.getCreate_by()));

        if (Util.strExist(p.getBidder())) {
            vo.setBidder(userServ.findById(p.getBidder()));
        }

        return vo;
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
