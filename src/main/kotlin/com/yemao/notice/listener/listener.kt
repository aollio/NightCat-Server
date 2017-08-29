package com.yemao.notice.listener

import com.yemao.event.Event
import com.yemao.event.EventExecutor
import com.yemao.event.EventManager
import com.yemao.notice.models.Notice
import com.yemao.notice.service.NoticeService
import com.yemao.projects.models.Project
import com.yemao.projects.models.ProjectBidder
import com.yemao.projects.service.ProjectService
import org.omg.CORBA.Object
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class NoticeEventListener {

    @Autowired lateinit var noticeServ: NoticeService

    @Autowired lateinit var projServ: ProjectService

    @Autowired
    fun registerEventListener(manager: EventManager) {
//        manager.register(Event.ProjectPublished_Project, projectPublishEventExecutor())
        manager.register(Event.ProjectGrabbedEvent_ProjectBidder, projectGrabbedProjectBidderEventExecutor())
        manager.register(Event.ProjectSelectDesigner_Project, projectSelectDesignerByEmployerEventExecutor())
    }


    internal fun projectSelectDesignerByEmployerEventExecutor(): EventExecutor {
        return EventExecutor { event, context ->
            val project = context as Project
            noticeServ
                    .sender()
                    .type(Notice.Type.NOTICE)
                    .content("您抢单成功了, 项目ID: " + project.id)
                    .uid(project.bidder)
                    .send()
        }
    }

//    private fun projectPublishEventExecutor(): EventExecutor {
//        return EventExecutor { event, context -> }
//    }

    private fun projectGrabbedProjectBidderEventExecutor(): EventExecutor {
        return EventExecutor { event, context ->
            //消息通知项目发布者 雇主
            val bidder = context as ProjectBidder
            val project = projServ.findById(bidder.proj_id)
            noticeServ
                    .sender()
                    .uid(project!!.create_by)
                    .content("你的订单被抢单")
                    .type(Notice.Type.NOTICE)
                    .send()
        }
    }

}