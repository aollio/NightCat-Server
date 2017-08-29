package com.yemao.other;

import com.yemao.entity.EntityModel;
import com.yemao.entity.UserNotice;
import com.yemao.users.models.User;

public class Tester {


    public void tran(UserNotice notice) {
        System.out.println("notice");
    }

    public void tran(User notice) {
        System.out.println("user");
    }

//    @SuppressWarnings(("unchecked")
//    public void from(EntityModel notice) {
//        System.out.println("notice");
//        tran(notice);
//    }

    public static void main(String[] args) {
        EntityModel model = new User();
        Tester tester = new Tester();
//        tester.from(model);
    }

    static class Super {
    }

    static class Child extends Super {
    }

}
