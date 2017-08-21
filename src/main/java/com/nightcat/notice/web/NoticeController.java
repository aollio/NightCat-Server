package com.nightcat.notice.web;

import com.framework.annotation.Authorization;
import com.framework.annotation.CurrentUser;
import com.nightcat.common.Response;
import com.nightcat.entity.UserNotice;
import com.nightcat.entity.User;
import com.nightcat.notice.service.NoticeService;
import com.nightcat.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.nightcat.common.Response.ok;

@RestController
@RequestMapping("/notices")
public class NoticeController {


    @Autowired
    private NoticeRepository repository;

    @Autowired
    private NoticeService notoServ;

    @GetMapping
    @Authorization
    public Response all(@CurrentUser User user) {
        List<UserNotice> result = notoServ.findByUid(user.getUid());
        return ok(notoServ.toVo(result));
    }


}
