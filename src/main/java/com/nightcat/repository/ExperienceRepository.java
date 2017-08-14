package com.nightcat.repository;

import com.nightcat.entity.Experience;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExperienceRepository extends AbstractRepository<Experience> {

    public List<Experience> findByUid(String uid) {
        Criteria criteria = getCriteria();
        criteria.add(Restrictions.eq("uid", uid));
        return criteria.list();
    }

}