package com.nightcat.users.web;

import com.nightcat.common.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/designers")
public class DesignersController {

    @GetMapping
    public Response query(
            // 查询设计师类型
            @RequestParam(required = false, defaultValue = "-1") String type,
            // 设计师昵称
            @RequestParam(required = false, defaultValue = "-1") String nickname,
            // 简介
            @RequestParam(required = false, defaultValue = "-1") String summary,
            @RequestParam(required = false, defaultValue = "-1") String phone,
            @RequestParam(required = false, defaultValue = "-1") String position,
            @RequestParam(required = false, defaultValue = "-1") String official,
            @RequestParam(required = false, defaultValue = "0") String min_service_length,
            @RequestParam(required = false, defaultValue = "100") String max_service_length,
            @RequestParam(required = false, defaultValue = "0") String min_hourly_wage,
            @RequestParam(required = false, defaultValue = "100000") String max_hourly_wage,
            //最低星级
            @RequestParam(required = false, defaultValue = "0") String min_star_level,
            //最高星级
            @RequestParam(required = false, defaultValue = "10") String max_star_level,
            @RequestParam(required = false, defaultValue = "1") String page,
            @RequestParam(required = false, defaultValue = "20") String limit
    )
    {


        return null;
    }

}
