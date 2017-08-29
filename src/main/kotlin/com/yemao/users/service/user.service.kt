package com.yemao.users.service

import com.yemao.common.base.BaseObject
import com.yemao.common.constant.Constant
import com.yemao.im.service.ImService
import com.yemao.users.models.User
import com.yemao.users.repository.UserRepository
import com.yemao.utility.Assert
import com.yemao.utility.Util
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.yemao.common.constant.HttpStatus.*
import com.yemao.users.models.Profile
import com.yemao.users.models.Role

@Service
class UserService : BaseObject() {

    @Autowired
    private lateinit var userRep: UserRepository

    @Autowired
    private lateinit var profileService: ProfileService

    @Autowired
    private lateinit var imServ: ImService

    fun save(user: User) {
        val olduser = userRep.findByPhone(user.phone)
        Assert.isNull(olduser, BAD_REQUEST, "手机号已被使用")

        user.uid = Util.uuid()

        if (imServ.registerIm(user)) {
            logger.error("注册nim失败")
        }
        user.img_url = if (user.img_url.isEmpty()) Constant.randomAvatar() else user.img_url
        if (user.role == Role.DESIGNER) saveDesigner(user)
        userRep.save(user)
    }

    private fun saveDesigner(user: User) {
        val profile = Profile()
        profile.uid = user.uid
        profileService.save(profile)
    }

    fun query(): UserRepository.Query = userRep.query()

    fun findByPhone(phone: String): User? = userRep.findByPhone(phone)

    fun saveOrUpdate(user: User) = userRep.saveOrUpdate(user)

    fun delete(user: User) = userRep.delete(user)

    fun update(user: User) = userRep.update(user)

    fun findById(id: String?): User? = userRep.findById(id)

    fun findByAccid(accid: String): User? = userRep.findByAccid(accid)


}