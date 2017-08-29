package com.yemao.users.repository

import com.yemao.repository.AbstractQueryRepository
import com.yemao.repository.AbstractRepository
import com.yemao.users.models.User
import org.hibernate.Criteria
import org.springframework.stereotype.Repository


@Repository
open class UserRepository : AbstractRepository<User>() {


    fun findByPhone(phone: String?): User? {
        if (phone == null) return null
        val users = query().phone(phone).list()
        return if (users.size == 0) null else users[0]
    }

    fun findByAccid(accid: String): User? = query().accid(accid).first()

    fun findByLnickname(nickname: String): List<User> {
        return query().l_nickname(nickname).list()
    }

    fun query(): Query {
        return Query(getCriteria())
    }

    class Query internal constructor(criteria: Criteria) : AbstractQueryRepository.Query<User, Query>(criteria) {

        fun l_nickname(nickname: String?): Query {
            if (nickname == null || nickname.isEmpty()) {
                return this
            }
            like("nickname", nickname)
            return this
        }

        fun accid(accid: String): Query {
            eq("accid", accid)
            return this
        }

        fun phone(phone: String): Query {
            eq("phone", phone)
            return this
        }


        override fun getThis(): Query {
            return this
        }


    }
}
