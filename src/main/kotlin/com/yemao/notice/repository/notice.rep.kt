package com.yemao.notice.repository

import com.yemao.notice.models.Notice
import com.yemao.repository.AbstractRepository
import org.hibernate.criterion.Restrictions
import org.springframework.stereotype.Repository

@Repository
class NoticeRepository : AbstractRepository<Notice>() {
    fun findByUid(uid: String): List<Notice> {
        val criteria = criteria
        criteria.add(Restrictions.eq("uid", uid))
        criteria.add(Restrictions.eq("del", false))
        return criteria.list() as List<Notice>
    }
}