package com.nightcat.notice.model;

import com.nightcat.entity.Notice;
import com.nightcat.entity.Project;
import com.nightcat.projects.service.ProjectService;
import com.nightcat.users.service.UserService;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeContentTemplate {

    @Autowired
    private UserService userServ;
    @Autowired
    private ProjectService projServ;

}