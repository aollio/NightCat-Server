package com.nightcat.event;


/**
 * Created by finderlo on 15/04/2017.
 * 事件，系统内所有事件都需要在此枚举中
 *
 * @author Aollio
 */
public enum Event {


    /**
     * 项目发布事件
     */
    ProjectPublished_Project,
    /**
     * 项目被抢单事件
     */
    ProjectGrabbedEvent_ProjectBidder,

    ProjectCancelByEmployer_Project,

    ProejectCancelByDesigner,

    ProjectSelectDesigner_Project, ProjectConfirmByDesigner_Project, ProjectModifyByDesigner_Project, ProjectCommentByEmployer_ProjectComment, Event() {
    }

}
