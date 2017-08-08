package com.nightcat.repository;

import com.nightcat.entity.ExpComment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExpCommentRepository extends AbstractRepository<ExpComment> {
    public List<ExpComment> findByExpId(String expId) {
        return findBy("exp_id", expId);
    }
}
