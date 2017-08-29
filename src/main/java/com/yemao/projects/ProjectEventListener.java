package com.yemao.projects;

import com.yemao.projects.models.*;
import com.yemao.event.Event;
import com.yemao.event.EventExecutor;
import com.yemao.event.EventManager;
import com.yemao.projects.service.DynamicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjectEventListener {

    @Autowired
    private DynamicService dynamicServ;

    @Autowired
    public void registerEventListener(EventManager manager) {
        manager.register(Event.ProjectPublished_Project, projectPublishEventExecutor());
        manager.register(Event.ProjectGrabbedEvent_ProjectBidder, projectGrabbedEventExecutor());
        manager.register(Event.ProjectSelectDesigner_Project, projectSelectDesigner_Project());
    }

    private EventExecutor projectSelectDesigner_Project() {
        return (event, context) -> {
            Project project = (Project) context;
            dynamicServ
                    .logger()
                    .project(project.getId())
                    .publisher(true)
                    .user(project.getCreate_by())
                    .type(Dynamic.Type.SelectDesignerByEmployer)
                    .content("雇主选择了设计师")
                    .log();
        };
    }

    private EventExecutor projectPublishEventExecutor() {
        return (event, context) -> {
            if (event != Event.ProjectPublished_Project) return;
            //生成订单动态
            Project project = (Project) context;
            dynamicServ.logger()
                    .user(project.getCreate_by())
                    .content("发布订单成功")
                    .type(Dynamic.Type.Publish)
                    .publisher(true)
                    .project(project.getId())
                    .log();
        };
    }

    private EventExecutor projectGrabbedEventExecutor() {
        return (event, context) -> {
            ProjectBidder bidder = (ProjectBidder) context;
            //生成订单动态
            dynamicServ.logger()
                    .user(bidder.getUid())
                    .content("抢单")
                    .type(Dynamic.Type.Grabbed)
                    .project(bidder.getProj_id())
                    .log();
        };
    }
}
