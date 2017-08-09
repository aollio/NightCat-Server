package com.nightcat.projects.service;

import com.nightcat.entity.Project;
import com.nightcat.repository.ProjectBidderRepository;
import com.nightcat.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectProcessService {

    @Autowired
    private ProjectBidderRepository bidderRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public Project publish(Project project) {
        project.setId(projectRepository.newId());
        project.setStatus(Project.Status.Publish);
        projectRepository.save(project);
        //todo 生成订单动态
        return project;
    }
}
