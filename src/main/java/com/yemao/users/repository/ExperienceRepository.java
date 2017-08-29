package com.yemao.users.repository;

import com.yemao.repository.AbstractRepository;
import com.yemao.users.models.Experience;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SuppressWarnings("unchecked")
public class ExperienceRepository extends AbstractRepository<Experience> {

    public List<Experience> findByUid(String uid) {
        return queryDefault()
                .add(Restrictions.eq("uid", uid))
                .list();
    }

}