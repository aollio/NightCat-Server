package com.yemao.users.repository;

import com.yemao.repository.AbstractQueryRepository;
import com.yemao.repository.AbstractRepository;
import com.yemao.users.models.Relation;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RelationRepository extends AbstractRepository<Relation> {

    public List<Relation> findFollowers(String uid) {
        return query().to(uid).list();
    }

    public List<Relation> findFollowing(String uid) {
        return query().from(uid).list();

    }

    public Relation findByFromAndTo(String from, String to) {
        return query().to(to).from(from).first();
    }

    public Query query() {
        return new Query(getCriteria());
    }

    public static class Query
            extends AbstractQueryRepository.Query<Relation, Query> {

        public Query(Criteria criteria) {
            super(criteria);
        }

        Query from(String uid) {
            eq("from", uid);
            return this;
        }

        Query to(String uid) {
            eq("to", uid);
            return this;
        }

        @Override
        public Query getThis() {
            return this;
        }
    }
}
