package com.yemao.users.web

import com.yemao.common.Response
import com.yemao.common.base.BaseController
import com.yemao.users.models.User
import com.yemao.users.repository.UserRepository
import com.yemao.users.service.TokenService
import com.yemao.utility.Assert
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


/**
 * @author Aollio
 * @date 15/05/2017
 */
@RestController
@RequestMapping("/tokens")
class TokenController : BaseController() {

    @Autowired lateinit var userRep: UserRepository

    @Autowired lateinit var tokenServ: TokenService

    /**
     * 登陆, 返回TOKEN，缺少必选参数时，返回400HTTP状态码Status
     *
     * @author Aollio
     * @date 15/05/2017
     */
    @PostMapping
    fun login(phone: String?, password: String?): Response {
        Assert.strExist(phone, BAD_REQUEST, "phone parameter not exist")
        val user: User? = userRep.findByPhone(phone)
        //check user password
        Assert.notNull(user, BAD_REQUEST, "用户不存在")
        Assert.equals(user!!.password, password, BAD_REQUEST, "密码不正确")

        val token_model = tokenServ.createToken(user.uid)
        return ok(token_model)
    }

}
