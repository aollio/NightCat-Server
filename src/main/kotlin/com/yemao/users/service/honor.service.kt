package com.yemao.users.service

import com.yemao.common.base.BaseObject
import com.yemao.repository.HonorRepository
import com.yemao.users.models.Honor
import com.yemao.utility.Util
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class HonorService : BaseObject() {

    @Autowired lateinit var honorRep: HonorRepository


    fun findByUid(uid: String): List<Honor> {
        return honorRep.findByUid(uid)
    }

    fun save(honor: Honor) {
        honor.create_time = Util.now()
        honor.id = Util.uuid()
        honorRep.save(honor)
    }

    fun saveOrUpdate(honor: Honor) {
        honorRep.saveOrUpdate(honor)
    }

    fun delete(honor: Honor) {
        honorRep.delete(honor)
    }

    fun update(honor: Honor) {
        honorRep.update(honor)
    }

    fun sort(T: List<Honor>): List<Honor> {
        return honorRep.sort(T)
    }

    fun findAll(): List<Honor> {
        return honorRep.findAll()
    }

    fun findBy(keys: Array<String>, values: Array<String>): List<Honor> {
        return honorRep.findBy(keys, values)
    }

    fun findBy(key: String, value: String): List<Honor> {
        return honorRep.findBy(key, value)
    }

    fun findBy(key: String, value: String, isLikeQuery: Boolean): List<Honor> {
        return honorRep.findBy(key, value, isLikeQuery)
    }

    fun findBy(keys: Array<String>, values: Array<String>, isLikeQuery: Boolean): List<Honor> {
        return honorRep.findBy(keys, values, isLikeQuery)
    }

    fun findById(id: String): Honor? {
        return honorRep.findById(id)
    }

    fun findByIds(idAndValues: Map<String, String>): Honor? {
        return honorRep.findByIds(idAndValues)
    }

    fun findBy(attr: Map<String, String>, likeQuery: Boolean): List<Honor> {
        return honorRep.findBy(attr, likeQuery)
    }


}