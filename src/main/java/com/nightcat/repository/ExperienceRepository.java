package com.nightcat.repository;

import com.nightcat.entity.Experience;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExperienceRepository extends AbstractRepository<Experience> {

    public List<Experience> findByUid(String uid) {
        return findBy("uid", uid);
    }

}