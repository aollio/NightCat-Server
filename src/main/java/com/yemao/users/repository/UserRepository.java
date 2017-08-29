package com.yemao.users.repository;


import com.yemao.repository.AbstractQueryRepository;
import com.yemao.repository.AbstractRepository;
import com.yemao.users.models.User;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository extends AbstractRepository<User> {


    public User findByPhone(String phone) {
        if (phone == null) return null;
        List<User> users = query().phone(phone).list();
        if (users.size() == 0) {
            return null;
        } else return users.get(0);
    }

    public User findByAccid(String accid) {
        return query().
                accid(accid).
                first();
    }

    public List<User> findByLnickname(String nickname) {
        return query().l_nickname(nickname).list();
    }

    public Query query() {
        return new Query(getCriteria());
    }

    public static class Query
            extends AbstractQueryRepository.Query<User, Query> {

        public Query(Criteria criteria) {
            super(criteria);
        }

        public Query l_nickname(String nickname) {
            if (nickname == null || nickname.isEmpty()) {
                return this;
            }
            like("nickname", nickname);
            return this;
        }

        public Query accid(String accid) {
            eq("accid", accid);
            return this;
        }

        public Query phone(String phone) {
            eq("phone", phone);
            return this;
        }


        @Override
        public Query getThis() {
            return this;
        }


    }
}
