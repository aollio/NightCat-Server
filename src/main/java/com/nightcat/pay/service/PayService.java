package com.nightcat.pay.service;

import com.nightcat.repository.AppOrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayService {
        @Autowired
        private AppOrderDao appOrderDao;
}
