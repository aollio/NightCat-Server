package com.nightcat.repository;

import com.nightcat.entity.DesignType;
import com.nightcat.entity.DesignerProfile;
import com.nightcat.users.service.DesignerService;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

@Repository
public class DesignerProfileRepository extends AbstractRepository<DesignerProfile> {


    @Override
    protected ProfileQuery query() {
        return new ProfileQuery(getCriteria());
    }

    public static class ProfileQuery extends Query<DesignerProfile, ProfileQuery> {

        public ProfileQuery(Criteria criteria) {
            super(criteria, new ProfileQuery(criteria));
        }


        public ProfileQuery position(String position) {
            eq("position", position);
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

    }
}