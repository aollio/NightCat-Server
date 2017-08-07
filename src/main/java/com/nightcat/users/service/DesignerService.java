package com.nightcat.users.service;

import com.nightcat.common.constant.Constant;
import com.nightcat.entity.DesignType;
import com.nightcat.entity.DesignerProfile;
import com.nightcat.repository.DesignerProfileRepository;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.hibernate.criterion.Restrictions.*;

@Service
public class DesignerService {

    @Autowired
    private DesignerProfileRepository designerRepository;

    public Query query() {
        return new Query(designerRepository.getCriteria());
    }

    public enum Official {
        ALL, OFFICIAL, NOT_OFFICIAL
    }

    public static class Query {

        private Criteria criteria;

        // 查询设计师类型
        private DesignType type = DesignType.UNDEFINDED;
        // 设计师昵称
        private String nickname;
        private String position;
        private Official official = Official.ALL;

        private int page;
        private int limit;

        private Query(Criteria criteria) {
            this.criteria = criteria;
        }

        @SuppressWarnings("unchecked")
        public List<DesignerProfile> list() {

            //page limit
            if (page <= 0) page = 0;
            if (limit <= 0) limit = Constant.DEFAULT_LIMIT;

            criteria.setFirstResult(page);
            criteria.setMaxResults(limit);

            //(type) 'AND' 其他查询条件
            if (type != DesignType.UNDEFINDED) criteria.add(Restrictions.eq("type", type));
            
            Disjunction disjunction = Restrictions.disjunction();
            if (isNotEmpty(nickname)) disjunction.add(like("nickname", nickname));
            if (isNotEmpty(position)) disjunction.add(like("position", position));

            criteria.add(disjunction);

            switch (official) {
                case ALL:
                    break;
                case OFFICIAL:
                    criteria.add(eq("official", true));
                    break;
                case NOT_OFFICIAL:
                    criteria.add(eq("official", false));
                    break;
            }


            return (List<DesignerProfile>) criteria.list();
        }


        public Query page(int page) {
            this.page = page;
            return this;
        }

        public Query limit(int limit) {
            this.limit = limit;
            return this;
        }

        public Query type(DesignType type) {
            this.type = type;
            return this;
        }


        public Query nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }


        public Query position(String position) {
            this.position = position;
            return this;
        }

        public Query official(Official official) {
            this.official = official;
            return this;
        }
    }

}
