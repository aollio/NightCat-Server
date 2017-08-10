package com.nightcat.repository;

import com.nightcat.entity.Pay_orders;
import org.springframework.stereotype.Repository;

@Repository
public class AppOrderDao extends AbstractRepository<Pay_orders> {


    @Override
    public Pay_orders findById(String id) {
        Pay_orders result = super.findById(id);
        if (result.getISDEL() != 1){
            return null;
        }
        return result;
    }
}
