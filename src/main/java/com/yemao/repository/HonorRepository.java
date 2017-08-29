package com.yemao.repository;

import com.yemao.users.models.Honor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HonorRepository extends AbstractRepository<Honor> {


    public List<Honor> findByUid(String uid) {
        return findBy("uid", uid);
    }

}