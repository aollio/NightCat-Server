package com.yemao.projects.models

import com.yemao.entity.EntityModel
import com.yemao.utility.Util
import java.sql.Timestamp
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "ym_project_comments")
data class Comment(
        /**
         * 评论id
         */
        @Id var id: String = "",
        /**
         * 项目id
         */
        var proj_id: String = "",
        /**
         * 发出评论的用户id
         */
        var uid: String? = "",
        /**
         * 发出用户是否为雇主
         */
        @Column(name = "is_employer") var employer: Boolean = false,
        /**
         * 评论分数. 0 - 10分. 0分: 0星; 10分: 5星
         */
        var score: Int = 0,
        /**
         * 评论内容
         */
        var content: String = "",
        /**
         * 评论时间
         */
        var comment_time: Timestamp? = Util.now(),
        /**
         * 评论类型
         */
        var type: Type? = Type.General
) : EntityModel {
    enum class Type {
        Bad, General, Good
    }
}

@Entity
@Table(name = "ym_project_dynamic")
data class Dynamic(
        @Id var id: String = "",
        var proj_id: String = "",
        var uid: String = "",
        /** 是否是项目发布者发出的动态 */
        @Column(name = "is_publisher") var publisher: Boolean = false,
        var type: Type? = Type.Publish,
        var content: String = "",
        var img_url: String = "http://about:blank",
        var create_time: Timestamp = Util.now()
) : EntityModel {
    enum class Type {
        Publish,
        /** 被设计师抢单 */
        Grabbed,
        SelectDesignerByEmployer,
        /** 设计师确认 */
        Designer_Confirm
    }
}

@Entity
@Table(name = "ym_project_images")
data class ProjectImage(
        @Id var id: String = "",
        var proj_id: String = "",
        var img_url: String = "http://about:blank",
        var create_time: Timestamp = Util.now()
) : EntityModel

@Entity
@Table(name = "ym_project_bidders")
data class ProjectBidder(
        @Id  var id: String = "",
        var proj_id: String = "",
        var uid: String = "",
        var create_time: Timestamp? = Util.now(),
        @Column(name = "is_del") var close: Boolean = false,
        /** 报价 */
        var price: Double = 0.toDouble(),
        /** 任务周期 */
        var cycle: String = "",
        /** 方案说明 */
        var description: String = ""
) : EntityModel