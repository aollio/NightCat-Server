package com.nightcat.projects.service;

import com.nightcat.entity.ProjectBidder;
import com.nightcat.repository.ProjectBidderRepository;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectBidderService {

    @Autowired
    private ProjectBidderRepository projectBidderRepository;

    public ProjectBidder findByUidAndProjectId(String uid, String proj_id) {
        return projectBidderRepository.findByUidAndProjectId(uid, proj_id);
    }

    public void save(ProjectBidder projectBidder) {
        projectBidderRepository.save(projectBidder);
    }

    public void saveOrUpdate(ProjectBidder projectBidder) {
        projectBidderRepository.saveOrUpdate(projectBidder);
    }

    public void update(ProjectBidder projectBidder) {
        projectBidderRepository.update(projectBidder);
    }

    public Criteria getCriteria() {
        return projectBidderRepository.getCriteria();
    }

    public Criteria getCriteria(int limit) {
        return projectBidderRepository.getCriteria(limit);
    }
}
