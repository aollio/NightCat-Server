package com.yemao.common.constant;


import java.util.Random;

/**
 * @author finderlo
 * @date 21/04/2017
 */
public class Constant {

    public static final String AUTHORIZATION = "token";
    public static final String CURRENT_USER_ID = "current_user_id";
    public static final int ORDER_ACCEPT_UNCOMPLETED_COUNT_LIMIT = 10;
    public static final int USER_CAN_CREATE_ORDER_CREDIT_LIMIT = 60;
    public static final int USER_CREATE_ORDER_MAX_COUNT = 10;
    public static final String SYSTEM_MESSAGE_SENDER = "0";

    public static int TOKEN_EXPIRES_HOUR = 240;

    public static int DEFAULT_LIMIT = 200;

    public static Random random = new Random(47);

    public static String randomAvatar() {
        return random.nextBoolean() ? "http://image.aollio.com/9_05.png" : "http://image.aollio.com/10_03.png";
    }
}
