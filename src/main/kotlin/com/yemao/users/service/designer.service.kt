package com.yemao.users.service

import com.yemao.common.base.BaseObject
import com.yemao.repository.DesignerProfileRepository
import com.yemao.users.models.Profile
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProfileService : BaseObject() {

    @Autowired lateinit var profileRep: DesignerProfileRepository

    fun query(): DesignerProfileRepository.Query {
        return profileRep.query()
    }

    fun save(profile: Profile) = profileRep.save(profile)

    fun findById(uid: String): Profile? = profileRep.findById(uid)
    fun saveOrUpdate(profile: Profile) = profileRep.saveOrUpdate(profile)
}