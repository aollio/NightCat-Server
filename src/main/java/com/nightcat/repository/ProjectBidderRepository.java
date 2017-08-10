package com.nightcat.repository;

import com.nightcat.entity.ProjectBidder;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectBidderRepository extends AbstractRepository<ProjectBidder> {


    public List<ProjectBidder> findByUid(String uid) {
        return super.findBy("uid", uid);
    }

    public ProjectBidder findByUidAndProjectId(String uid, String proj_id) {
        Criteria criteria = getCriteria();
        criteria.add(Restrictions.eq("uid", uid));
        criteria.add(Restrictions.eq("proj_id", proj_id));

        List result = criteria.list();

        if (result.size() == 0) {
            return null;
        }
        return (ProjectBidder) result.get(0);
    }
}