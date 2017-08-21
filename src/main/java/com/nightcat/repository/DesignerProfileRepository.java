package com.nightcat.repository;

import com.nightcat.entity.DesignType;
import com.nightcat.entity.DesignerProfile;
import com.nightcat.entity.User;
import com.nightcat.users.service.DesignerService;
import com.nightcat.utility.Util;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DesignerProfileRepository extends AbstractRepository<DesignerProfile> {


    public ProfileQuery query() {
        return new ProfileQuery(getCriteria());
    }

    public static class ProfileQuery {

        Criteria criteria;

        public ProfileQuery(Criteria criteria) {
            this.criteria = criteria;
        }


        public ProfileQuery position(String position) {
            if (Util.strExist(position)) {
                eq("position", position);
            }
            return this;
        }


        public ProfileQuery l_position(String position) {
            like("position", position);
            return this;
        }

        public ProfileQuery type(DesignType type) {
            if (type != DesignType.UNDEFINDED)
                eq("type", type);
            return this;
        }


        public ProfileQuery official(DesignerService.Official official) {
            switch (official) {
                case ALL:
                    break;
                case OFFICIAL:
                    eq("official", true);
                    break;
                case NOT_OFFICIAL:
                    eq("official", false);
                    break;
            }
            return this;
        }

        public ProfileQuery eq(String key, Object value) {
            criteria.add(Restrictions.eq(key, value));
            return this;
        }

        public ProfileQuery like(String key, Object value) {
            criteria.add(Restrictions.like(key, value));
            return this;
        }

        public List<DesignerProfile> list() {
            return criteria.list();
        }

    }
}