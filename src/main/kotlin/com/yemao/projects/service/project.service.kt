package com.yemao.projects.service

import com.yemao.common.base.BaseObject
import com.yemao.entity.DesignType
import com.yemao.projects.models.Project
import com.yemao.projects.models.ProjectImage
import com.yemao.repository.ProjectBidderRepository
import com.yemao.repository.ProjectImagesRepository
import com.yemao.repository.ProjectRepository
import com.yemao.users.models.Role
import com.yemao.utility.Util
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.util.HashSet

@Service
class ProjectService : BaseObject() {


    @Autowired lateinit var projRep: ProjectRepository
    @Autowired lateinit var imgRep: ProjectImagesRepository
    @Autowired lateinit var bidderRep: ProjectBidderRepository

    fun findTimelineByUid(role: Role,
                          uid: String,
                          type: DesignType,
                          limit: Int,
                          since_time: Timestamp,
                          max_time: Timestamp): Collection<Project> {
        if (role === Role.EMPLOYER) {
            return projRep.findByTypeAndUid(uid, type, limit, since_time, max_time)
        }
        if (role === Role.DESIGNER) {
            val projects = HashSet<Project>()
            //todo 可能会造成重复
            projects.addAll(projRep.findByBidder(uid))
            bidderRep.findByUid(uid).forEach { bidder ->
                val project = projRep.findById(bidder.proj_id)
                //todo 只要是竞标的都返回
                projects.add(project)
            }
            return projects
        }
        return ArrayList()
    }

    fun findByType(type: DesignType, limit: Int, since_time: Timestamp, max_time: Timestamp): List<Project> {
        return projRep.findByType(type, limit, since_time, max_time)
    }


    fun save(project: Project) {
        projRep.save(project)
    }


    fun saveOrUpdate(project: Project) {
        projRep.saveOrUpdate(project)
    }


    fun update(project: Project) {
        projRep.update(project)
    }


    fun findById(id: String?): Project? {
        return projRep.findById(id)
    }


    fun saveImgUrls(img_urls: List<String?>?, project: Project) {
        if (img_urls == null) return
        img_urls.forEach { url ->
            if (url != null && url.isNotEmpty()) {
                val image = ProjectImage()
                image.id = Util.uuid()
                image.proj_id = project.id
                image.create_time = Util.now()
                image.img_url = url
                imgRep.save(image)
            }
        }
        logger.info("保存项目图片, 项目id:" + project.id + ", 图片个数: " + img_urls.size + " . 图片详情: " + img_urls.toString())
    }


}