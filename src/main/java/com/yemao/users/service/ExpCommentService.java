package com.yemao.users.service;

import com.yemao.users.repository.ExpCommentRepository;
import com.yemao.users.models.ExpComment;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpCommentService {

    @Autowired
    private ExpCommentRepository commentRep;

    public List<ExpComment> findByExpId(String expId) {
        return commentRep.findByExpId(expId);
    }

    public void save(ExpComment userExpComment) {
        commentRep.save(userExpComment);
    }

    public void saveOrUpdate(ExpComment userExpComment) {
        commentRep.saveOrUpdate(userExpComment);
    }

    public void update(ExpComment userExpComment) {
        commentRep.update(userExpComment);
    }

    public ExpComment findById(String id) {
        return commentRep.findById(id);
    }

    public Criteria getCriteria() {
        return commentRep.getCriteria();
    }

    public Criteria getCriteria(int limit) {
        return commentRep.getCriteria(limit);
    }
}
