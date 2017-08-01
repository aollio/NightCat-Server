package com.nightcat.repository;

import com.nightcat.entity.User;
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

}