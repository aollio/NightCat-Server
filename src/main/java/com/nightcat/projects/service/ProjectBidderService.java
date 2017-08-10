package com.nightcat.projects.service;

import com.nightcat.entity.ProjectBidder;
import com.nightcat.repository.ProjectBidderRepository;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectBidderService {

    @Autowired
    private ProjectBidderRepository bidderRep;

    public ProjectBidder findByUidAndProjectId(String uid, String proj_id) {
        return bidderRep.findByUidAndProjectId(uid, proj_id);
    }

    public List<ProjectBidder> findByProjectId(String proj_id){
        return bidderRep.findByProjectId(proj_id);
    }

    public void save(ProjectBidder projectBidder) {
        bidderRep.save(projectBidder);
    }

    public void saveOrUpdate(ProjectBidder projectBidder) {
        bidderRep.saveOrUpdate(projectBidder);
    }

    public void update(ProjectBidder projectBidder) {
        bidderRep.update(projectBidder);
    }

    public Criteria getCriteria() {
        return bidderRep.getCriteria();
    }

    public Criteria getCriteria(int limit) {
        return bidderRep.getCriteria(limit);
    }
}
