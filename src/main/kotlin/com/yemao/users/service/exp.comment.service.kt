package com.yemao.users.service

import com.yemao.common.base.BaseObject
import com.yemao.repository.ExpCommentRepository
import com.yemao.users.models.ExpComment
import org.hibernate.Criteria
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ExpCommentService : BaseObject() {

    @Autowired lateinit var commentRep: ExpCommentRepository

    fun findByExpId(expId: String): List<ExpComment> {
        return commentRep.findByExpId(expId)
    }

    fun save(expComment: ExpComment) {
        commentRep.save(expComment)
    }

    fun saveOrUpdate(expComment: ExpComment) {
        commentRep.saveOrUpdate(expComment)
    }

    fun update(expComment: ExpComment) {
        commentRep.update(expComment)
    }

    fun findById(id: String): ExpComment? {
        return commentRep.findById(id)
    }

}