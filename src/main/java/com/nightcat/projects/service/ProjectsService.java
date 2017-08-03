package com.nightcat.projects.service;

import com.nightcat.config.annotation.Authorization;
import com.nightcat.entity.FeaturedProject;
import com.nightcat.entity.Project;
import com.nightcat.repository.FeaturedProjectRepository;
import com.nightcat.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class ProjectsService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private FeaturedProjectRepository featuredProjectRepository;

    /**
     * 返回精选项目列表
     */
    public List<Project> findFeature(int limit, Timestamp since_time, Timestamp max_time) {
        List<FeaturedProject> featuredProjects =
                featuredProjectRepository.findAll(limit, since_time, max_time);
        List<Project> result = new LinkedList<>();
        featuredProjects.forEach(fp -> result.add(projectRepository.findById(fp.getProj_id())));
        return result;
    }

    public List<Project> findTimelineByUid(String uid,
                                           String type,
                                           int limit,
                                           Timestamp since_time,
                                           Timestamp max_time) {
        return projectRepository.findByTypeAndUid(uid, type, limit, since_time, max_time);
    }

    public List<Project> findByType(String type, int limit, Timestamp since_time, Timestamp max_time) {
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

    public List<Project> findBy(String[] keys, String[] values) {
        return projectRepository.findBy(keys, values);
    }

    public List<Project> findBy(String key, String value) {
        return projectRepository.findBy(key, value);
    }

    public List<Project> findBy(String key, String value, boolean isLikeQuery) {
        return projectRepository.findBy(key, value, isLikeQuery);
    }

    public List<Project> findBy(String[] keys, String[] values, boolean isLikeQuery) {
        return projectRepository.findBy(keys, values, isLikeQuery);
    }

    public Project findById(String id) {
        return projectRepository.findById(id);
    }

    public Project findByIds(Map<String, String> idAndValues) {
        return projectRepository.findByIds(idAndValues);
    }

    public List<Project> findBy(Map<String, String> attr, boolean likeQuery) {
        return projectRepository.findBy(attr, likeQuery);
    }


}
