package com.yemao.notice.web

import com.framework.annotation.Authorization
import com.framework.annotation.CurrentUser
import com.yemao.common.Response
import com.yemao.common.base.BaseController
import com.yemao.notice.repository.NoticeRepository
import com.yemao.notice.service.NoticeService
import com.yemao.users.models.User
import com.yemao.utility.Assert
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/notices")
class NoticeController : BaseController() {

    @Autowired lateinit var repository: NoticeRepository
    @Autowired lateinit var noticeService: NoticeService

    @GetMapping
    @Authorization
    fun all(@CurrentUser user: User): Response {
        val result = noticeService.findByUid(user.uid)
        return okVo(result)
    }


    @PostMapping("/read")
    @Authorization
    fun read(id: String): Response {
        val notice = noticeService.findById(id)
        Assert.notNull(notice, BaseController.BAD_REQUEST, "消息不存在")
        noticeService.read(notice!!)
        return BaseController.ok()
    }

    @PostMapping("/del")
    @Authorization
    fun del(id: String): Response {
        val notice = noticeService.findById(id)
        Assert.notNull(notice, BaseController.BAD_REQUEST, "消息不存在")
        noticeService.del(notice!!)
        return BaseController.ok()
    }
}