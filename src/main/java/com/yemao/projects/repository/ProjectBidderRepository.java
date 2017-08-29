package com.yemao.projects.repository;

import com.yemao.projects.models.ProjectBidder;
import com.yemao.repository.AbstractRepository;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.like;

@Repository
public class ProjectBidderRepository extends AbstractRepository<ProjectBidder> {


    public List<ProjectBidder> findByUid(String uid) {
        Criteria criteria = getCriteria();
        criteria.add(eq("uid", uid));
        return criteria.list();
    }

    public ProjectBidder findByUidAndProjectId(String uid, String proj_id) {
        Criteria criteria = getCriteria();
        criteria.add(eq("uid", uid));
        criteria.add(eq("proj_id", proj_id));

        List result = criteria.list();

        if (result.size() == 0) {
            return null;
        }
        return (ProjectBidder) result.get(0);
    }

    public List<ProjectBidder> findByProjectId(String proj_id) {
        Criteria criteria = getCriteria();
        criteria.add(eq("proj_id", proj_id));
        return criteria.list();
    }
}