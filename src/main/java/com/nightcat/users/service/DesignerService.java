package com.nightcat.users.service;

import com.nightcat.common.constant.Constant;
import com.nightcat.entity.DesignerProfile;
import com.nightcat.repository.DesignerProfileRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.hibernate.criterion.Restrictions.between;
import static org.hibernate.criterion.Restrictions.like;

@Service
public class DesignerService {

    private DesignerProfileRepository designerRepository;

    public QueryBuilder query(){
        return new QueryBuilder(designerRepository.getCriteria());
    }

    public static class QueryBuilder {

        private Criteria criteria;

        // 查询设计师类型
        String type;
        // 设计师昵称
        String nickname;
        // 简介
        String summary;
        String phone;
        String position;
        String official;

        double min_service_length;
        double max_service_length;

        double min_hourly_wage;
        double max_hourly_wage;
        //最低星级
        int min_star_level;
        //最高星级
        int max_star_level;
        int page;
        int limit;

        private QueryBuilder(Criteria criteria) {
            this.criteria = criteria;
        }

        @SuppressWarnings("unchecked")
        public List<DesignerProfile> list() {

            //(type) 'AND' 其他查询条件
            if (isNotEmpty(type)) {
                criteria.add(Restrictions.eq("type", type));
            }

            //(nickname 'OR' summary 'OR' phone 'OR' position) 'AND' 其他查询条件
            Junction disjunction = Restrictions.disjunction();
            if (isNotEmpty(nickname)) {
                disjunction.add(like("nickname", nickname));
            }
            if (isNotEmpty(summary)) {
                disjunction.add(like("summary", summary));
            }
            if (isNotEmpty(phone)) {
                disjunction.add(like("phone", phone));
            }
            if (isNotEmpty(position)) {
                disjunction.add(like("position", position));
            }
            criteria.add(disjunction);

            //('BETWEEN' min_* 'AND' max_*) 'AND' 其他查询条件

            //service_length
            if (max_service_length <= 0) this.max_service_length = 1000;
            if (min_service_length <= 0) this.min_service_length = 0;
            criteria.add(
                    between("service_length",
                            min_hourly_wage, max_hourly_wage));

            //hourly_wage
            if (max_hourly_wage <= 0) this.max_hourly_wage = 100000;
            if (min_hourly_wage <= 0) this.min_hourly_wage = 0;
            criteria.add(
                    between("hourly_wage",
                            min_hourly_wage, max_hourly_wage));

            //star_level
            if (max_star_level > 10 || max_star_level < 0) max_star_level = 10;
            if (min_star_level < 0 || min_star_level > 10) min_star_level = 0;
            criteria.add(
                    between("star_level",
                            min_star_level, max_star_level));

            //todo
            //is official
            if (isNotEmpty(official)){

            }

            //page limit
            if (page < 0) page = 0;
            if (limit <= 0) limit = Constant.DEFAULT_LIMIT;
            criteria.setFirstResult(page);
            criteria.setMaxResults(limit);

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

        public QueryBuilder summary(String summary) {
            this.summary = summary;
            return this;
        }

        public QueryBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public QueryBuilder position(String position) {
            this.position = position;
            return this;
        }

        public QueryBuilder official(String official) {
            this.official = official;
            return this;
        }

        public QueryBuilder min_hourly_wage(double min_hourly_wage) {
            this.min_hourly_wage = min_hourly_wage;
            return this;
        }

        public QueryBuilder max_hourly_wage(int max_hourly_wage) {
            this.max_hourly_wage = max_hourly_wage;
            return this;
        }

        public QueryBuilder min_star_level(int min_star_level) {
            this.min_star_level = min_star_level;
            return this;
        }

        public QueryBuilder max_star_level(int max_star_level) {
            this.max_star_level = max_star_level;
            return this;
        }

        public QueryBuilder min_service_length(double min_service_length) {
            this.min_service_length = min_service_length;
            return this;
        }

        public QueryBuilder max_service_length(double max_service_length) {
            this.max_service_length = max_service_length;
            return this;
        }


    }

}
