package com.nightcat.users.web;

import com.nightcat.common.Response;
import com.nightcat.common.utility.Assert;
import com.nightcat.common.utility.Util;
import com.nightcat.entity.DesignType;
import com.nightcat.users.service.DesignerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.nightcat.common.constant.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("/users/designers")
public class DesignerController {


    @Autowired
    private DesignerService designerServ;

    @GetMapping
    public Response query(
            // 查询设计师类型
            @RequestParam(required = false) Integer type,
            // 设计师昵称
            @RequestParam(required = false) String nickname,
            @RequestParam(required = false) String position,
            @RequestParam(required = false) String official,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer limit) {
        DesignerService.Official official1 = DesignerService.Official.ALL;

        if ("1".equals(official)) {
            official1 = DesignerService.Official.OFFICIAL;
        } else if ("2".equals(official)) {
            official1 = DesignerService.Official.NOT_OFFICIAL;
        }

        DesignType designType = DesignType.UNDEFINDED;

        if (type != null) {
            DesignType temp = Util.enumFromOrigin(type, DesignType.class);
            Assert.notNull(temp, BAD_REQUEST, "类型不对");
            designType = temp;
        }

        int page_int = page == null ? 0 : page <= 0 ? -1 * page : page;
        int limit_int = limit == null ? 0 : limit <= 0 ? -1 * limit : limit;

        DesignerService.Query builder = designerServ
                .query()
                .type(designType)
                .nickname(nickname)
                .position(position)
                .official(official1)
                .limit(limit_int)
                .page(page_int);

        return Response.ok(builder.list());
    }

}
