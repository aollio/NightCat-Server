package com.yemao.users.models

import com.yemao.entity.EntityModel
import com.yemao.utility.Util
import java.sql.Timestamp
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table


enum class Sex {
    UNDEFINED, MALE, FEMALE
}

/** Role */
enum class Role {
    DESIGNER, EMPLOYER
}


/** 夜猫用户表 */
@Entity
@Table(name = "ym_user")
data class User(
        @Id var uid: String = "",
        var password: String = "",
        /** 真实姓名 */
        var realname: String = "",
        /** 身份证号码 */
        var id_card: String = "",
        var nickname: String = "",
        /** 头像url */
        var img_url: String = "",
        @Column(name = "user_sex") var sex: Sex = Sex.UNDEFINED,
        var role: Role = Role.DESIGNER,
        var phone: String = "",
        var create_time: Timestamp = Util.now(),
        var last_login_time: Timestamp = Util.now(),
        var wechat: String = "",
        var weibo: String = "",
        var platform: String = "",
        var client_id: String = "",
        var im_token: String = "",
        var accid: String = "",
        @Column(name = "is_del") var del: Boolean = false
) : EntityModel {

}


/** 用户认证信息审核表 */
@Entity
@Table(name = "ym_user_authentication")
data class Authentication(
        @Id var id: String = "",
        var uid: String = "",
        /** 认证类型 */
        var type: Type = Type.Position,
        var level: String = "",
        /** 证件名称, 用户上传用于审核 */
        var name: String = "",
        /** 证件号码. 人工审核填写 */
        var id_number: String = "",
        /** 证件图片, 用户上传 */
        var img_url: String = "",
        var status: Status = Status.Comfirming,
        var create_time: Timestamp = Util.now()
) : EntityModel {
    enum class Type {
        /** 职称认证 */
        Position,
        /** 学历认证 */
        School
    }

    enum class Status {
        Comfirming, Success, Fail
    }
}

/** 用户经历评论表 */
@Entity
@Table(name = "ym_user_experience_comments")
data class ExpComment(
        @Id var id: String = "",
        /** 用户经历id */
        var exp_id: String = "",
        /** 发出评论的用户id */
        var uid: String = "",
        /** 评论内容 */
        var content: String = "",
        var type: String = "",
        @Column(name = "comment_time") var create_time: Timestamp = Util.now()

) : EntityModel


/** 用户经历表 */
@Entity
@Table(name = "ym_user_experience")
data class Experience(
        @Id var id: String = "",
        var uid: String = "",
        var name: String = "",
        var description: String = "",
        var img_url: String = "",
        var fav_count: Int = 0,
        var comment_count: Int = 0,
        var view_count: Int = 0,
        var create_time: Timestamp = Util.now()
) : EntityModel

/** 用户荣誉表 */
@Entity
@Table(name = "ym_user_honor")
data class Honor(
        @Id var id: String = "",
        var uid: String = "",
        var name: String = "",
        var description: String = "",
        var img_url: String = "",
        var get_time: Timestamp = Util.now(),
        var create_time: Timestamp = Util.now()
) : EntityModel

@Entity
@Table(name = "ym_users_token")
data class Token(
        @Id var token: String? = null,
        var uid: String? = null,
        var last_active_time: Timestamp? = null
) : EntityModel