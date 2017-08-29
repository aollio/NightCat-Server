package com.yemao.im.web;

import com.yemao.common.Response;
import com.yemao.common.base.BaseController;
import com.yemao.entity.ImMessage;
import com.yemao.repository.ImMessageRepository;
import com.yemao.users.models.User;
import com.yemao.users.service.UserService;
import com.yemao.utility.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/im")
public class ImController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private ImMessageRepository messageRepository;

    @PostMapping("/receive")
    public Response receMsg(@RequestBody(required = false) ImMessage message) {

        logger.info(message.toString());

        if (!"1".equals(message.getEventType())) {
            //只保留对话消息
            return ok();
        }

        User from = userService.findByAccid(message.getFromAccount());
        if (from != null) message.setFromUid(from.getUid());
        User to = userService.findByAccid(message.getTo());
        if ((to != null)) message.setToUid(to.getUid());

        if (Util.strExist(message.getMsgidServer())) {
            message.setId(message.getMsgidServer());
        } else message.setId(Util.uuid());

        messageRepository.saveOrUpdate(message);
        return ok();
    }

}
