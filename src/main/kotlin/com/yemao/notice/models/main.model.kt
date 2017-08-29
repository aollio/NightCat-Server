package com.yemao.notice.models

import com.yemao.entity.EntityModel
import com.yemao.utility.Util
import java.sql.Timestamp
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name = "ym_user_notice")
data class Notice(
        @Id var id: String = "",
        var content: String = "",
        var uid: String = "",
        var create_time: Timestamp = Util.now(),
        @Column(name = "is_read") var read: Boolean = false,
        var type: Type = Type.SYSTEM,
        @Column(name = "is_del") var del: Boolean = false
) : EntityModel {
    enum class Type constructor(title: String) {
        SYSTEM("系统消息"),
        NOTICE("推送消息"),
        PROJECT_GRABBED("订单消息"),
        PROJECT_CHOOSE("订单消息");

        var title: String
            internal set

        init {
            this.title = title
        }
    }


}


