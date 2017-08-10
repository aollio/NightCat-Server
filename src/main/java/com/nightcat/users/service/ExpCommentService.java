package com.nightcat.users.service;

import com.nightcat.entity.ExpComment;
import com.nightcat.repository.ExpCommentRepository;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpCommentService {

    @Autowired
    private ExpCommentRepository cmtRepository;

    public List<ExpComment>findByExpId(String expId){
        return cmtRepository.findByExpId(expId);
    }
    public void save(ExpComment expComment) {
        cmtRepository.save(expComment);
    }

    public void saveOrUpdate(ExpComment expComment) {
        cmtRepository.saveOrUpdate(expComment);
    }

    public void update(ExpComment expComment) {
        cmtRepository.update(expComment);
    }

    public ExpComment findById(String id) {
        return cmtRepository.findById(id);
    }

    public Criteria getCriteria() {
        return cmtRepository.getCriteria();
    }

    public Criteria getCriteria(int limit) {
        return cmtRepository.getCriteria(limit);
    }
}
