package com.yemao.ads.web;

import com.google.gson.Gson;
import com.yemao.ads.service.AdService;
import com.yemao.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ads")
public class AdsController {

    @Autowired
    private AdService adServ;

    @GetMapping("/d/home/slides")
    public Response desHomeSlides() {
        return Response.ok(adServ.getDesHomeSlides());
    }


    @PostMapping
    public Response hello(
            @RequestParam(required = false) List<String> img_urls) {
        if (img_urls != null) {
            System.out.println(img_urls.size());
            System.out.println(img_urls.toString());
            System.out.println(new Gson().toJson(img_urls));
        }
        return Response.ok();
    }
}
