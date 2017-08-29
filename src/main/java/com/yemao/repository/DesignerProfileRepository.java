package com.yemao.repository;

import com.yemao.entity.DesignType;
import com.yemao.users.models.Profile;
import com.yemao.users.models.Official;
import com.yemao.users.models.Position;
import com.yemao.utility.Util;
import org.hibernate.Criteria;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

@Repository
public class DesignerProfileRepository extends AbstractRepository<Profile> {


    public Query query() {
        return new Query(getCriteria());
    }

    public static class Query extends AbstractQueryRepository.Query<Profile, Query> {


        public Query(Criteria criteria) {
            super(criteria);
        }


        public Query l_position(String position) {
            if (Util.strExist(position)) {
                like("position", position);
            }
            return this;
        }

        public Query type(DesignType type) {
            if (type != DesignType.UNDEFINDED)
                eq("type", type);
            return this;
        }


        public Query official(Official official) {
            if (official != Official.NOT)
                eq("official", official);
            return this;
        }

        @Override
        public Query getThis() {
            return this;
        }

        @NotNull
        public Query position(@NotNull Position position) {
            if (position != Position.ALL) {
                eq("position", position);
            }
            return this;
        }
    }
}