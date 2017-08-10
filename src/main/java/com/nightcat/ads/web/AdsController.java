package com.nightcat.ads.web;

import com.nightcat.ads.service.AdService;
import com.nightcat.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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

}
