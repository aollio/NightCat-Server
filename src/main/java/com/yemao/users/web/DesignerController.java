package com.yemao.users.web;

import com.framework.annotation.EnumParam;
import com.yemao.common.Response;
import com.yemao.common.base.BaseController;
import com.yemao.entity.DesignType;
import com.yemao.users.repository.ProfileRepository;
import com.yemao.users.models.Profile.Position;
import com.yemao.users.models.Profile;
import com.yemao.users.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;


@RestController
@RequestMapping("/users/designers")
public class DesignerController extends BaseController {


    @Autowired
    private ProfileService designerService;

    @GetMapping
    public Response query(
            // 查询设计师类型
            @EnumParam(required = false, defaultValue = 0) DesignType type,
            // 设计师昵称
            @RequestParam(required = false) String nickname,
            @EnumParam(required = false, defaultValue = 0) Position position,
            @EnumParam(required = false, defaultValue = 0) Profile.Official official,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer limit) {

        int page_int = page == null ? 0 : page <= 0 ? -1 * page : page;
        int limit_int = limit == null ? 0 : limit <= 0 ? -1 * limit : limit;

        ProfileRepository.Query builder = designerService
                .query()
//                .nickname(nickname)
                .type(type)
                //todo
                .position(position)
                .official(official);
//                .limit(limit_int)
//                .page(page_int);

        List<Profile> profileList = builder.list();
        return okVo(profileList);
    }

}
