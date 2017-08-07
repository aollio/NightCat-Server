package com.nightcat.users.web;

import com.nightcat.common.Response;
import com.nightcat.users.service.DesignerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@RestController
@RequestMapping("/users/designers")
public class DesignersController {


    @Autowired
    private DesignerService designerService;

    @GetMapping
    public Response query(
            // 查询设计师类型
            @RequestParam(required = false) String type,
            // 设计师昵称
            @RequestParam(required = false) String nickname,
            @RequestParam(required = false) String position,
            @RequestParam(required = false) String official,
            @RequestParam(required = false) String page,
            @RequestParam(required = false) String limit) {
        DesignerService.Official official1 = DesignerService.Official.ALL;

        if ("1".equals(official)) {
            official1 = DesignerService.Official.OFFICIAL;
        } else if ("2".equals(official)) {
            official1 = DesignerService.Official.NOT_OFFICIAL;
        }

        int page_int = 0;
        int limit_int = 0;

        try {
            if (isNotEmpty(page)) {
                int x = Integer.parseInt(page);
                page_int = x;
            }
            if (isNotEmpty(limit)) {
                int y = Integer.parseInt(limit);
                limit_int = y;
            }
        } catch (Exception e) {

        }

        DesignerService.QueryBuilder builder = designerService
                .query()
                .type(type)
                .nickname(nickname)
                .position(position)
                .official(official1)
                .limit(limit_int)
                .page(page_int);

        return Response.ok(builder.list());
    }

}
