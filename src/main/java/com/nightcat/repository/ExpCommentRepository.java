package com.nightcat.repository;

import com.nightcat.entity.UserExpComment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExpCommentRepository extends AbstractRepository<UserExpComment> {

    public List<UserExpComment> findByExpId(String expId) {
        return queryDefault().eq("exp_id", expId).list();
    }
}
