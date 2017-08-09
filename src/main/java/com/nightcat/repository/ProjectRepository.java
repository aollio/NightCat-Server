package com.nightcat.repository;

import com.nightcat.entity.DesignType;
import com.nightcat.entity.Project;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

@Repository
public class ProjectRepository extends AbstractRepository<Project> {

    private static final String TABLE_NAME = "ym_projects";

    /**
     * 查询方法 只查询状态为发布状态
     */
    public List<Project> findByType(DesignType type, int limit, Timestamp since_time, Timestamp max_time) {
        return (List<Project>) getCriteriaWithTimeAndType(type, limit, since_time, max_time).list();
    }

    /**
     * 查询方法 只查询状态为发布状态
     */
    public List<Project> findByTypeAndUid(String uid, DesignType type, int limit, Timestamp since_time, Timestamp max_time) {
        Criteria criteria = getCriteriaWithTimeAndType(type, limit, since_time, max_time);
        criteria.add(Restrictions.eq("create_by", uid));
        return (List<Project>) criteria.list();
    }

    /**
     * 查询方法 只查询状态为发布状态
     */
    private Criteria getCriteriaWithTimeAndType(DesignType type, int limit, Timestamp since_time, Timestamp max_time) {

        Criteria criteria = getCriteria(limit);
        if (type != DesignType.UNDEFINDED) criteria.add(Restrictions.like("type", type));
        criteria.add(Restrictions.between("create_time", since_time, max_time));
        criteria.add(Restrictions.eq("status", Project.Status.Publish));
        return criteria;
    }


    public String newId() {
        Session session = sessionFactory.getCurrentSession();

        Calendar calendar = Calendar.getInstance();
        String year = calendar.get(Calendar.YEAR) + "";
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);

        String prefix = new StringBuilder()
                .append(year)
                .append(month < 0 ? "0" + month : month)
                .append(day < 0 ? "0" + day : day)
                .append(hour < 0 ? "0" + hour : hour)
                .append(minutes < 0 ? "0" + hour : hour)
                .append(seconds < 0 ? "0" + seconds : seconds)
                .toString();


        String sql = new StringBuilder()
                .append(" SELECT MAX(id) ")
                .append(" FROM ").append(TABLE_NAME)
                .append(" WHERE id LIKE ")
                .append(" ' ").append(prefix).append(" %' ").toString();

        SQLQuery l = session.createSQLQuery(sql);
        List list = l.list();
        String id = (String) list.get(0);
        if (id == null || "null".equals(id)) {
            //当天没有，生成新的订单号
            id = prefix + "0000";
            return id;
        } else {
            long idd = Long.valueOf(id);
            return String.valueOf(idd + 1);
        }
    }
}
