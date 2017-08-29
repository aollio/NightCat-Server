package com.yemao.notice.service

import com.yemao.common.CatException
import com.yemao.common.base.BaseObject
import com.yemao.notice.models.Notice
import com.yemao.notice.repository.NoticeRepository
import com.yemao.utility.Util.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class NoticeService : BaseObject() {

    @Autowired lateinit var noticeRepository: NoticeRepository

    fun sender(): NoticeSender {
        return NoticeSender(noticeRepository)
    }

    fun findByUid(uid: String): List<Notice> {
        return noticeRepository.findByUid(uid)
    }


    fun findById(id: String): Notice? {
        return noticeRepository.findById(id)
    }

    fun read(notice: Notice) {
        notice.read = true
        noticeRepository.update(notice)
    }

    fun del(notice: Notice) {
        notice.del = true
        noticeRepository.update(notice)
    }


    class NoticeSender internal constructor(private val noticeRep: NoticeRepository) {

        private val id = uuid()
        private var content: String? = null
        private var uid: String? = null
        private val create_time = now()
        private val read = false
        private var type: Notice.Type = Notice.Type.SYSTEM
        private val del = false

        fun content(content: String): NoticeSender {
            this.content = content
            return this
        }

        fun uid(uid: String): NoticeSender {
            this.uid = uid
            return this
        }


        fun type(type: Notice.Type): NoticeSender {
            this.type = type
            return this
        }

        fun send() {

            if (uid == null) {
                throw CatException("NoticeSender uid must be exist")
            }

            if (content == null) {
                throw CatException("NoticeSender content must be exist")
            }

            val userNotice = Notice(id, content as String, uid as String,
                    create_time, read, type, del)

            this.noticeRep.save(userNotice)
        }
    }
}