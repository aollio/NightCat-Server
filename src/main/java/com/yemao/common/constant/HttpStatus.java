package com.yemao.common.constant;

/**
 * @author finderlo
 * @date 16/05/2017
 */
public class HttpStatus {

    public static final int BAD_REQUEST = 400;

    public static final int UNAUTHORIZATON = 401;

    public static final int FORBBID = 403;

    /**
     * 所查询资源不存在. e.g.查询某个用户的封面, 封面不存在, 则返回此状态吗
     */
    public static final int NOT_FOUND = 404;

    public static final int SERVER_ERROR = 500;


}
