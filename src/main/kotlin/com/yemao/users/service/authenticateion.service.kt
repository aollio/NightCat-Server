package com.yemao.users.service

import com.yemao.common.base.BaseObject
import com.yemao.repository.UserAuthenticationRepository
import com.yemao.users.models.Authentication
import com.yemao.users.models.User
import com.yemao.users.repository.UserRepository
import com.yemao.utility.Util
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthenticationService : BaseObject() {

    @Autowired
    private lateinit var authRepository: UserAuthenticationRepository

    @Autowired lateinit var userRepository: UserRepository

    fun save(auth: Authentication) {
        authRepository.save(auth)
    }


    @Transactional(rollbackFor = arrayOf(Exception::class))
    fun upload(
            user: User,
            realname: String,
            id_card: String,
            position: String,
            position_img_url: String,
            school: String,
            school_img_url: String
    ) {

        //更新用户信息
        user.realname = realname
        user.id_card = id_card
        userRepository.update(user)


        //保存职称认证审核单
        val position_auth = Authentication()
        position_auth.uid = user.uid
        position_auth.img_url = position_img_url
        position_auth.name = position
        position_auth.status = Authentication.Status.Comfirming
        position_auth.type = Authentication.Type.Position
        position_auth.create_time = Util.now()
        position_auth.id = Util.uuid()

        authRepository.save(position_auth)

        //保存学历认证审核单
        val school_auth = Authentication()
        school_auth.uid = user.uid
        school_auth.img_url = school_img_url
        school_auth.name = school
        school_auth.status = Authentication.Status.Comfirming
        school_auth.type = Authentication.Type.School
        school_auth.create_time = Util.now()
        school_auth.id = Util.uuid()

        authRepository.save(school_auth)

    }

    fun saveOrUpdate(authentication: Authentication) {
        authRepository.saveOrUpdate(authentication)
    }

    fun delete(authentication: Authentication) {
        authRepository.delete(authentication)
    }

    fun update(authentication: Authentication) {
        authRepository.update(authentication)
    }


    fun findAll(): List<Authentication> {
        return authRepository.findAll()
    }

    fun findBy(keys: Array<String>, values: Array<String>): List<Authentication> {
        return authRepository.findBy(keys, values)
    }

    fun findBy(key: String, value: String): List<Authentication> {
        return authRepository.findBy(key, value)
    }

    fun findBy(key: String, value: String, isLikeQuery: Boolean): List<Authentication> {
        return authRepository.findBy(key, value, isLikeQuery)
    }

    fun findBy(keys: Array<String>, values: Array<String>, isLikeQuery: Boolean): List<Authentication> {
        return authRepository.findBy(keys, values, isLikeQuery)
    }

    fun findById(id: String): Authentication? {
        return authRepository.findById(id)
    }

}