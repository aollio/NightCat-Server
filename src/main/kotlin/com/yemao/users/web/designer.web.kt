package com.yemao.users.web

import com.framework.annotation.EnumParam
import com.yemao.common.Response
import com.yemao.common.base.BaseController
import com.yemao.entity.DesignType
import com.yemao.users.models.Official
import com.yemao.users.models.Position
import com.yemao.users.service.ProfileService
import com.yemao.users.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users/designers")
class DesignerController : BaseController() {

    @Autowired
    private lateinit var profileService: ProfileService

    @Autowired
    private lateinit var userService: UserService

    @GetMapping
    fun query(
            // 查询设计师类型
            @EnumParam(required = false, default = 0) type: DesignType,
            // 设计师昵称
            nickname: String?,
            @EnumParam(required = false, default = 0) position: Position,
            @EnumParam(required = false, default = 0) official: Official,
            page: Int?,
            limit: Int?): Response {


        val real_page = if (page == null) -1 else if (page <= 0) -1 * page else page
        val real_limit = if (limit == null) -1 else if (limit <= 0) -1 * limit else limit

        val builder = profileService
                .query()
                .type(type)
                .position(position)
                .official(official)
                .limit(real_page)
                .page(real_limit)

        val profileList = builder.list()

        //query nickname
        //todo
//        val users = userService.query().l_nickname(nickname).list()
//        logger.info("query designers size: " + profileList.size)
//        users.addAll(profileList)

        return okVo(profileList)
    }

}

