package com.nightcat.repository;

import com.nightcat.entity.Project;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class ProjectRepository extends AbstractRepository<Project> {


    public List<Project> findByType(String type, int limit, Timestamp since_time, Timestamp max_time) {
        return (List<Project>) getCriteriaWithTimeAndType(type, limit, since_time, max_time).list();
    }

    public List<Project> findByTypeAndUid(String uid, String type, int limit, Timestamp since_time, Timestamp max_time) {
        Criteria criteria = getCriteriaWithTimeAndType(type, limit, since_time, max_time);
        criteria.add(Restrictions.eq("uid", uid));
        return (List<Project>) criteria.list();
    }

    private Criteria getCriteriaWithTimeAndType(String type, int limit, Timestamp since_time, Timestamp max_time) {

        Criteria criteria = getCriteria(limit);
        criteria.add(Restrictions.like("type", type));
        criteria.add(Restrictions.between("create_time", since_time, max_time));

        return criteria;
    }
}
