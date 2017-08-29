package com.yemao.users.repository;

import com.yemao.repository.AbstractRepository;
import com.yemao.users.models.ExpComment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExpCommentRepository extends AbstractRepository<ExpComment> {

    public List<ExpComment> findByExpId(String expId) {
        return queryDefault().eq("exp_id", expId).list();
    }
}
