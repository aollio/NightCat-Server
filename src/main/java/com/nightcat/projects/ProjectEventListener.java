package com.nightcat.projects;

import com.nightcat.entity.Project;
import com.nightcat.entity.ProjectBidder;
import com.nightcat.entity.ProjectDynamic;
import com.nightcat.event.Event;
import com.nightcat.event.EventExecutor;
import com.nightcat.event.EventManager;
import com.nightcat.projects.service.DynamicService;
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
                    .type(ProjectDynamic.Type.SelectDesignerByEmployer)
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
                    .type(ProjectDynamic.Type.Publish)
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
                    .type(ProjectDynamic.Type.Grabbed)
                    .project(bidder.getProj_id())
                    .log();
        };
    }
}
