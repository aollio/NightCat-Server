package com.nightcat.repository;

import com.nightcat.entity.UserExperience;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SuppressWarnings("unchecked")
public class ExperienceRepository extends AbstractRepository<UserExperience> {

    public List<UserExperience> findByUid(String uid) {
        return query()
                .add(Restrictions.eq("uid", uid))
                .list();
    }

}