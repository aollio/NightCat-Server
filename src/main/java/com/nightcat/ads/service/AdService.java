package com.nightcat.ads.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class AdService {

    List<String> desHomeSlides = new LinkedList<>();


    public AdService() {
        desHomeSlides.add("http://image.aollio.com/home-default.png");
        desHomeSlides.add("http://image.aollio.com/home-default.png");
        desHomeSlides.add("http://image.aollio.com/home-default.png");
    }

    public List<String> getDesHomeSlides() {
        return desHomeSlides;
    }
}
