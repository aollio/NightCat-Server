package com.yemao.users.models

import com.yemao.entity.DesignType
import com.yemao.entity.EntityModel
import com.yemao.utility.Util
import java.math.BigDecimal
import java.sql.Timestamp
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

enum class Official {
    /** 未认证 */
    NOT,
    /** 职称是否认证, 认证后用户无法修改学校信息 */
    POSITION_ONLY,
    /** 学历是否认证, 认证后用户无法修改学校信息 */
    SCHOOL_ONLY,
    /** 全部都认证 */
    ALL
}

enum class Position {
    ALL, LOW, MIDDLE, HIGH
}


/** 设计师详细资料*/
@Entity
@Table(name = "ym_designer_profile")
data class Profile(
        @Id var uid: String = "",
        /** 用户简介*/
        var summary: String = "",
        /** 设计师类别 */
        var type: DesignType = DesignType.UNDEFINDED,
        /** 工龄, 单位年 e.g. 3.5年工龄 */
        var service_length: Double = 0.0,
        /** 接单数 */
        var bid_count: Int = 0,
        /** 时薪 */
        var hourly_wage: BigDecimal = BigDecimal.ZERO,
        /** 是否官方认证 */
        var official: Official = Official.NOT,
        /** 综合指标 */
        var overall_target: String = "",
        /** 总工时 */
        var total_works: Int = 0,
        /** 接单成功数 */
        var bid_success_count: Int = 0,
        /** 账户余额 */
        var account_balance: BigDecimal = BigDecimal.ZERO,
        var create_time: Timestamp = Util.now(),
        /** 星级 */
        var star_level: Int = 0,
        /** 职称 */
        var position: Position = Position.ALL,
        //        var education,
        /** 学校 */
        var school: String = ""
) : EntityModel