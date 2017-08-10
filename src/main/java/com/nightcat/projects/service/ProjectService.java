package com.nightcat.projects.service;

import com.nightcat.common.timing.Timer;
import com.nightcat.entity.DesignType;
import com.nightcat.entity.ProjectImage;
import com.nightcat.entity.Project;
import com.nightcat.repository.ProjectImagesRepository;
import com.nightcat.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectImagesRepository projectImagesRepository;

    @Autowired
    private Timer timer;

    public List<ProjectImage> findPicturesByProjId(String proj_id) {
        return projectImagesRepository.findByProjId(proj_id);
    }

    public List<Project> findTimelineByUid(String uid,
                                           DesignType type,
                                           int limit,
                                           Timestamp since_time,
                                           Timestamp max_time) {
        return projectRepository.findByTypeAndUid(uid, type, limit, since_time, max_time);
    }

    public List<Project> findByType(DesignType type, int limit, Timestamp since_time, Timestamp max_time) {
        return projectRepository.findByType(type, limit, since_time, max_time);
    }


    public void save(Project project) {
        projectRepository.save(project);
    }


    public void saveOrUpdate(Project project) {
        projectRepository.saveOrUpdate(project);
    }

    public void delete(Project project) {
        projectRepository.delete(project);
    }

    public void update(Project project) {
        projectRepository.update(project);
    }

    public List<Project> sort(List<Project> T) {
        return projectRepository.sort(T);
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }


    public List<Project> findBy(String[] keys, String[] values, boolean isLikeQuery) {
        return projectRepository.findBy(keys, values, isLikeQuery);
    }

    public Project findById(String id) {
        return projectRepository.findById(id);
    }

    public List<Project> findBy(Map<String, String> attr, boolean likeQuery) {
        return projectRepository.findBy(attr, likeQuery);
    }


}
