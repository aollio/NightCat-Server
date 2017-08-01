package com.nightcat.repository;

import com.nightcat.entity.Honor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HonorRepository extends AbstractRepository<Honor> {


    public List<Honor> findByUid(String uid) {
        return findBy("uid", uid);
    }

}