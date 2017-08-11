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
    ProjectPublishedEvent,
    /**
     * 项目被抢单事件
     */
    ProjectGrabEvent,

    ProjectCancelByEmployer,

    ProejectCancelByDesigner,

    Event() {
    }

}
