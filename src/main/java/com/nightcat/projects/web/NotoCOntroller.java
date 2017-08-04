package com.nightcat.projects.web;

import com.nightcat.common.Response;
import com.nightcat.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notis")
public class NotoCOntroller {

    @Autowired
    private NotificationRepository repository;


    @GetMapping
    public Response all() {
        return Response.ok(repository.findAll());
    }

}
