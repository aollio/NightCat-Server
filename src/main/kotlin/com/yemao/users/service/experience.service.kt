package com.yemao.users.service

import com.yemao.repository.ExperienceRepository
import com.yemao.users.models.Experience
import com.yemao.utility.Util
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ExperienceService {

    @Autowired lateinit var expRep: ExperienceRepository

    @Autowired lateinit var userServ: UserService

    fun findByUid(uid: String): List<Experience> {
        return expRep.findByUid(uid)
    }

    fun save(exp: Experience) {
        exp.create_time = Util.now()
        exp.id = Util.uuid()
        expRep.save(exp)
    }

    fun saveOrUpdate(experience: Experience) {
        expRep.saveOrUpdate(experience)
    }

    fun delete(experience: Experience) {
        expRep.delete(experience)
    }

    fun update(experience: Experience) {
        expRep.update(experience)
    }

    fun sort(T: List<Experience>): List<Experience> {
        return expRep.sort(T)
    }

    fun findAll(): List<Experience> {
        return expRep.findAll()
    }

    fun findBy(keys: Array<String>, values: Array<String>): List<Experience> {
        return expRep.findBy(keys, values)
    }

    fun findBy(key: String, value: String): List<Experience> {
        return expRep.findBy(key, value)
    }

    fun findBy(key: String, value: String, isLikeQuery: Boolean): List<Experience> {
        return expRep.findBy(key, value, isLikeQuery)
    }

    fun findBy(keys: Array<String>, values: Array<String>, isLikeQuery: Boolean): List<Experience> {
        return expRep.findBy(keys, values, isLikeQuery)
    }

    fun findById(id: String): Experience {
        return expRep.findById(id)
    }

    fun findByIds(idAndValues: Map<String, String>): Experience {
        return expRep.findByIds(idAndValues)
    }

    fun findBy(attr: Map<String, String>, likeQuery: Boolean): List<Experience> {
        return expRep.findBy(attr, likeQuery)
    }


}