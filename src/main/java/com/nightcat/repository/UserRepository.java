package com.nightcat.repository;

import com.nightcat.entity.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository extends AbstractRepository<User> {


    public User findByPhone(String phone) {
        List<User> users = findBy("phone", phone);
        if (users.size() == 0) {
            return null;
        } else return users.get(0);
    }

    public User findByAccid(String accid) {
        Criteria criteria = getCriteria();
        criteria.add(Restrictions.eq("accid", accid));
        List<User> users = criteria.list();
        if (users == null || users.size() == 0) {
            return null;
        }
        return users.get(0);
    }
}