package com.nightcat.repository;

import com.nightcat.entity.Notice;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NoticeRepository extends AbstractRepository<Notice> {
    public List<Notice> findByUid(String uid) {
        Criteria criteria = getCriteria();
        criteria.add(Restrictions.eq("uid",uid));
        criteria.add(Restrictions.eq("del",false));
        return criteria.list();
    }
}