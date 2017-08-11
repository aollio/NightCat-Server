package com.nightcat.projects.service;

import com.nightcat.common.timing.Timer;
import com.nightcat.entity.DesignType;
import com.nightcat.entity.ProjectImage;
import com.nightcat.entity.Project;
import com.nightcat.entity.User;
import com.nightcat.repository.ProjectBidderRepository;
import com.nightcat.repository.ProjectImagesRepository;
import com.nightcat.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projRep;

    @Autowired
    private ProjectImagesRepository imgsRep;

    @Autowired
    private ProjectBidderRepository bidderRep;

    @Autowired
    private Timer timer;

    public List<ProjectImage> findPicturesByProjId(String proj_id) {
        return imgsRep.findByProjId(proj_id);
    }

    public List<Project> findTimelineByUid(User.Role role,
                                           String uid,
                                           DesignType type,
                                           int limit,
                                           Timestamp since_time,
                                           Timestamp max_time) {
        if (role == User.Role.EMPLOYER) {
            return projRep.findByTypeAndUid(uid, type, limit, since_time, max_time);
        }
        if (role == User.Role.DESIGNER) {
            List<Project> projects = new LinkedList<>();
            //todo 可能会造成重复
            projects.addAll(projRep.findByBidder(uid));
            bidderRep.findByUid(uid).forEach(bidder -> {
                Project project = projRep.findById(bidder.getProj_id());
                if (project.getStatus() == Project.Status.Publish) {
                    projects.add(findById(bidder.getProj_id()));
                }
            });
            return projects;
        }
        return new LinkedList<>();
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

    public void delete(Project project) {
        projRep.delete(project);
    }

    public void update(Project project) {
        projRep.update(project);
    }

    public List<Project> sort(List<Project> T) {
        return projRep.sort(T);
    }

    public List<Project> findAll() {
        return projRep.findAll();
    }


    public List<Project> findBy(String[] keys, String[] values, boolean isLikeQuery) {
        return projRep.findBy(keys, values, isLikeQuery);
    }

    public Project findById(String id) {
        return projRep.findById(id);
    }

    public List<Project> findBy(Map<String, String> attr, boolean likeQuery) {
        return projRep.findBy(attr, likeQuery);
    }


}
