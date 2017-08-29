package com.yemao.pay.service;

import com.yemao.repository.AppOrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayService {
        @Autowired
        private AppOrderDao appOrderDao;
}
