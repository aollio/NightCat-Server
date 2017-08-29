package com.yemao.projects.lisenter

import com.yemao.common.base.BaseObject
import com.yemao.event.Event
import com.yemao.event.EventExecutor
import com.yemao.event.EventManager
import com.yemao.projects.models.Dynamic
import com.yemao.projects.models.Project
import com.yemao.projects.models.ProjectBidder
import com.yemao.projects.service.DynamicService
import org.omg.CORBA.Object
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ProjectEventListener : BaseObject() {
    @Autowired lateinit var dynamicServ: DynamicService

    @Autowired
    fun registerEventListener(manager: EventManager) {
        manager.register(Event.ProjectPublished_Project, projectPublishEventExecutor())
        manager.register(Event.ProjectGrabbedEvent_ProjectBidder, projectGrabbedEventExecutor())
        manager.register(Event.ProjectSelectDesigner_Project, projectSelectDesigner_Project())
    }

    private fun projectSelectDesigner_Project(): EventExecutor {
        return EventExecutor { event, context ->
            val project = context as Project
            dynamicServ
                    .logger()
                    .project(project.id)
                    .publisher(true)
                    .user(project.create_by)
                    .type(Dynamic.Type.SelectDesignerByEmployer)
                    .content("雇主选择了设计师")
                    .log()
        }
    }

    private fun projectPublishEventExecutor(): EventExecutor {
        return EventExecutor { event, context ->
            //生成订单动态
            val project = context as Project
            dynamicServ.logger()
                    .user(project.create_by)
                    .content("发布订单成功")
                    .type(Dynamic.Type.Publish)
                    .publisher(true)
                    .project(project.id)
                    .log()
        } as EventExecutor
    }

    private fun projectGrabbedEventExecutor(): EventExecutor {
        return EventExecutor { event, context ->
            val bidder = context as ProjectBidder
            //生成订单动态
            dynamicServ.logger()
                    .user(bidder.uid)
                    .content("抢单")
                    .type(Dynamic.Type.Grabbed)
                    .project(bidder.proj_id)
                    .log()
        }
    }

}