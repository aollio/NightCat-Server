package com.nightcat.users.service;

import com.nightcat.entity.UserExpComment;
import com.nightcat.repository.ExpCommentRepository;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpCommentService {

    @Autowired
    private ExpCommentRepository commentRep;

    public List<UserExpComment> findByExpId(String expId) {
        return commentRep.findByExpId(expId);
    }

    public void save(UserExpComment userExpComment) {
        commentRep.save(userExpComment);
    }

    public void saveOrUpdate(UserExpComment userExpComment) {
        commentRep.saveOrUpdate(userExpComment);
    }

    public void update(UserExpComment userExpComment) {
        commentRep.update(userExpComment);
    }

    public UserExpComment findById(String id) {
        return commentRep.findById(id);
    }

    public Criteria getCriteria() {
        return commentRep.getCriteria();
    }

    public Criteria getCriteria(int limit) {
        return commentRep.getCriteria(limit);
    }
}
