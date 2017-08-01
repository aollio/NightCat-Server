package com.nightcat.repository;

import com.nightcat.entity.FeaturedProject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class FeaturedProjectRepository extends AbstractRepository<FeaturedProject> {

    public List<FeaturedProject> findAll(int limit, Timestamp since_time, Timestamp max_time) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session
                .createQuery(
                        "From FeaturedProject p where  p.create_time between :low and :high"
                );

        query.setTimestamp("low", since_time);
        query.setTimestamp("high", max_time);

        query.setFirstResult(0);
        query.setMaxResults(limit);

        return (List<FeaturedProject>) query.list();
    }

}
