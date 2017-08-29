package com.yemao.projects.service

import com.yemao.common.base.BaseObject
import com.yemao.projects.models.ProjectBidder
import com.yemao.repository.ProjectBidderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProjectBidderService : BaseObject() {

    @Autowired lateinit var bidderRep: ProjectBidderRepository

    fun findByUidAndProjectId(uid: String, proj_id: String): ProjectBidder? {
        return bidderRep.findByUidAndProjectId(uid, proj_id)
    }

    fun findByProjectId(proj_id: String): List<ProjectBidder> {
        return bidderRep.findByProjectId(proj_id)
    }

    fun save(projectBidder: ProjectBidder) {
        bidderRep.save(projectBidder)
    }

    fun saveOrUpdate(projectBidder: ProjectBidder) {
        bidderRep.saveOrUpdate(projectBidder)
    }

    fun update(projectBidder: ProjectBidder) {
        bidderRep.update(projectBidder)
    }

}