package com.nightcat.projects.service;

import com.nightcat.repository.ProjBidderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectProcessService {

    @Autowired
    private ProjBidderRepository bidderRepository;



}
