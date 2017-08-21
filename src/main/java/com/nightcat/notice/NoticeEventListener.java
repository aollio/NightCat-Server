package com.nightcat.notice;

import com.nightcat.entity.UserNotice;
import com.nightcat.entity.Project;
import com.nightcat.entity.ProjectBidder;
import com.nightcat.event.Event;
import com.nightcat.event.EventExecutor;
import com.nightcat.event.EventManager;
import com.nightcat.notice.service.NoticeService;
import com.nightcat.projects.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NoticeEventListener {

    @Autowired
    private NoticeService noticeServ;

    @Autowired
    private ProjectService projServ;

    @Autowired
    public void registerEventListener(EventManager manager) {
        manager.register(Event.ProjectPublished_Project, projectPublishEventExecutor());
        manager.register(Event.ProjectGrabbedEvent_ProjectBidder, projectGrabbedProjectBidderEventExecutor());
        manager.register(Event.ProjectSelectDesigner_Project, projectSelectDesignerByEmployerEventExecutor());
    }


    EventExecutor projectSelectDesignerByEmployerEventExecutor() {
        return (event, context) -> {
            Project project = (Project) context;
            noticeServ
                    .sender()
                    .type(UserNotice.Type.PROJECT_CHOOSE)
                    .content("您抢单成功了, 项目ID: " + project.getId())
                    .uid(project.getBidder())
                    .send();
        };
    }

    private EventExecutor projectPublishEventExecutor() {
        return new EventExecutor() {
            @Override
            public void execute(Event event, Object context) {

            }
        };
    }

    private EventExecutor projectGrabbedProjectBidderEventExecutor() {
        return (event, context) -> {
            //消息通知项目发布者 雇主
            ProjectBidder bidder = (ProjectBidder) context;
            Project project = projServ.findById(bidder.getProj_id());
            noticeServ
                    .sender()
                    .uid(project.getCreate_by())
                    .content("你的订单被抢单")
                    .type(UserNotice.Type.PROJECT_GRABBED)
                    .send();
        };
    }


}
