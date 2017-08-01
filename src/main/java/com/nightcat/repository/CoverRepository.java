package com.nightcat.repository;

import com.nightcat.entity.Cover;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CoverRepository extends AbstractRepository<Cover> {

    public Cover findByUid(String uid) {
        List<Cover> covers = findBy("uid", uid);
        if (covers.size() == 0) {
            return null;
        } else return covers.get(0);
    }

}