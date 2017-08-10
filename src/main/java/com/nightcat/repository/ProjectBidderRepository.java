package com.nightcat.repository;

import com.nightcat.entity.ProjectBidder;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.hibernate.criterion.Restrictions.eq;

@Repository
public class ProjectBidderRepository extends AbstractRepository<ProjectBidder> {


    public List<ProjectBidder> findByUid(String uid) {
        return super.findBy("uid", uid);
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