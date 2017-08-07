package com.nightcat.users.service;

import com.nightcat.common.constant.Constant;
import com.nightcat.common.utility.Util;
import com.nightcat.entity.DesignerProfile;
import com.nightcat.repository.DesignerProfileRepository;
import com.sun.org.apache.bcel.internal.generic.IFNONNULL;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Junction;
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


    @Autowired
    private SessionFactory sessionFactory;

    public QueryBuilder query() {
        return new QueryBuilder(designerRepository.getCriteria());
    }


    public enum Official {
        ALL, OFFICIAL, NOT_OFFICIAL
    }

    public static class QueryBuilder {

        private Criteria criteria;

        // 查询设计师类型
        String type;
        // 设计师昵称
        String nickname;
        String position;
        Official official = Official.ALL;

        int page;
        int limit;

        private QueryBuilder(Criteria criteria) {
            this.criteria = criteria;
        }

        @SuppressWarnings("unchecked")
        public List<DesignerProfile> list() {

            //page limit
            if (page < 0) page = 0;
            if (limit <= 0) limit = Constant.DEFAULT_LIMIT;

            criteria.setFirstResult(page);
            criteria.setMaxResults(limit);

            //(type) 'AND' 其他查询条件
            if (isNotEmpty(type)) criteria.add(Restrictions.eq("type", type));

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


        public QueryBuilder page(int page) {
            this.page = page;
            return this;
        }

        public QueryBuilder limit(int limit) {
            this.limit = limit;
            return this;
        }

        public QueryBuilder type(String type) {
            this.type = type;
            return this;
        }


        public QueryBuilder nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }


        public QueryBuilder position(String position) {
            this.position = position;
            return this;
        }

        public QueryBuilder official(Official official) {
            this.official = official;
            return this;
        }
    }

}
