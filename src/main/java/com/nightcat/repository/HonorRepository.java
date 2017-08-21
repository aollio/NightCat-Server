package com.nightcat.repository;

import com.nightcat.entity.UserHonor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HonorRepository extends AbstractRepository<UserHonor> {


    public List<UserHonor> findByUid(String uid) {
        return findBy("uid", uid);
    }

}