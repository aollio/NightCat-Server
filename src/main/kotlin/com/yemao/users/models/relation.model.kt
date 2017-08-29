package com.yemao.users.models

import com.yemao.entity.EntityModel
import com.yemao.utility.Util
import java.sql.Timestamp
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user_follow")
data class Relation(
        @Id var id: String = "",
        /** 关系的发起者, 比如雇主关注设计师, 雇主是发起者 */
        @Column(name = "from_id")var from: String = "",
        @Column(name = "to_id") var to: String = "",
        var create_time: Timestamp = Util.now(),
        var type: String = ""
) : EntityModel