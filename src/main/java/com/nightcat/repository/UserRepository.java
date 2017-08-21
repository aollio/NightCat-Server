package com.nightcat.repository;

import com.nightcat.entity.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository extends AbstractRepository<User> {


    public User findByPhone(String phone) {
        List<User> users = query().phone(phone).list();
        if (users.size() == 0) {
            return null;
        } else return users.get(0);
    }

    public User findByAccid(String accid) {
        List<User> users = query().accid(accid).list();
        if (users == null || users.size() == 0) return null;
        return users.get(0);
    }

    public List<User> findByLnickname(String nickname) {
        return query().l_nickname(nickname).list();
    }

    protected UserQuery query() {
        return new UserQuery(getCriteria());
    }

    public static class UserQuery {

        Criteria criteria;

        UserQuery(Criteria criteria) {
            this.criteria = criteria;
        }

        UserQuery l_nickname(String nickname) {
            like("nickname", nickname);
            return this;
        }

        UserQuery accid(String accid) {
            eq("accid", accid);
            return this;
        }

        UserQuery phone(String phone) {
            eq("phone", phone);
            return this;
        }

        UserQuery eq(String key, Object value) {
            criteria.add(Restrictions.eq(key, value));
            return this;
        }

        UserQuery like(String key, Object value) {
            criteria.add(Restrictions.like(key, value));
            return this;
        }

        List<User> list() {
            return criteria.list();
        }


    }
}