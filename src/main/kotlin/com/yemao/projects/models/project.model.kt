package com.yemao.projects.models

import com.yemao.entity.DesignType
import com.yemao.entity.EntityModel
import com.yemao.utility.Util
import java.math.BigDecimal
import java.sql.Timestamp
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table


/**
 * 订单的状态流程图
 * http://image.aollio.com/nightcat-project-status-process.png
 */
enum class Status {
    /**
     * 发布项目后, 项目处于发布状态. 这时候设计师可以进行抢单.
     * 如果没有设计师抢单, 并且项目到达截止时间后会进入'Cancel'状态
     */
    Publish,
    /**
     * 雇主选择一位设计师后, 等待设计师确认
     */
    ConfirmDesigner_WaitDesignerConfirm,
    /**
     * 设计时确认后(双方确认)
     */
    DesignerConfirm_WaitModify,
    /**
     * 等待设计师修改. 修改后,等待雇主支付. 这时会生成一个对应的支付订单
     */
    DesignerModify_WaitPay,
    /**
     * 支付完成后, 等待设计师设计
     */
    PayComplete_WaitDesign,
    /**
     * 设计完成后, 等待设计师交付
     */
    DesignComplete_WaitHarvest,

    /**
     * 雇主收货, 等待评价项目. 这时将款项转给设计师账户
     */
    EmployerHarvest_WaitComment,
    /**
     * 若在设计过程中, 雇主和设计师之间发生了无法私下协调的问题. 由平台介入协调. 称为会审
     */
    Platform_InterPose,
    /**
     * 订单正常完成. 评价后状态为完成
     */
    Complete,
    /**
     * 订单非正常完成. e.g. 项目超时未抢单; 会审失败; 雇主没有支付; 等等
     */
    Cancel


}

enum class Depth constructor(text: String) {
    Schema("方案"),
    Expansion("扩出"),
    Construction("施工");

    var text: String
        internal set

    init {
        this.text = text
    }
}

@Entity
@Table(name = "ym_projects")
data class Project(
        @Id var id: String = "",
        var title: String = "",
        /** 项目类型 */
        var type: DesignType = DesignType.UNDEFINDED,
        /** 项目内容 */
        var content: String = "",
        /** 项目预算 */
        var budget: BigDecimal = BigDecimal.ZERO,
        /** 设计面积 */
        var area: Int = 0,
        /** 设计面积个数 */
        var area_count: Int = 0,
        /** 项目深度 */
        var depth: Depth = Depth.Schema,
        /** 项目需要的工时 */
        var period: Int = 0,
        /** 项目开始时间 */
        var start_time: Timestamp = Util.now(),
        /** 项目截止时间 */
        var end_time: Timestamp = Util.now(),
        /** 项目状态 */
        var status: Status = Status.Publish,
        /** 项目创建人uid */
        var create_by: String = "",
        /** 项目的创建时间 */
        var create_time: Timestamp = Util.now(),
        /** 项目是否优秀 */
        @Column(name = "is_good") var good: Boolean = false,
        /** 修改人uid */
        var modify_by: String = "",
        /** 修改人修改项目时间 */
        var modify_time: Timestamp = Util.now(),
        /** 项目备注 */
        var modify_mark: String = "",
        /** 抢单截止时间 */
        var due_time: Timestamp = Util.now(),
        /** 项目喜欢数 */
        var fav_count: Int = 0,
        /** 抢单数量 */
        var grab_count: Int = 0,
        /** 竞标人 竞标设计师 */
        var bidder: String = "",
        /** 竞标人 竞标时间 */
        var bid_time: Timestamp = Util.now(),
        /** 项目是否被删除 */
        @Column(name = "is_del") var del: Boolean = false,
        var cancel_reason: String = "",
        var update_time: Timestamp = Util.now()
) : EntityModel