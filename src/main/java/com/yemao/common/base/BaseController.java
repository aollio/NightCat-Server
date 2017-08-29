package com.yemao.common.base;

import com.yemao.common.ErrorCode;
import com.yemao.common.Response;
import com.yemao.entity.EntityModel;
import com.yemao.vo.VoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;


public abstract class BaseController extends BaseObject {


    public static final int BAD_REQUEST = 400;

    public static final int UNAUTHORIZATON = 401;

    public static final int FORBBID = 403;

    /**
     * 所查询资源不存在. e.g.查询某个用户的封面, 封面不存在, 则返回此状态吗
     */
    public static final int NOT_FOUND = 404;

    public static final int SERVER_ERROR = 500;



    @Autowired
    protected VoService voService;

    public Object vo(EntityModel model) {
        return getVoService().from(model);
    }

    public Collection<Object> vo(Collection<? extends EntityModel> models) {
        return getVoService().from(models);
    }

    protected VoService getVoService() {
        return voService;
    }


    public Response okVo(EntityModel model) {
        return ok(vo(model));
    }

    public Response okVo(Collection<? extends EntityModel> models) {
        return ok(vo(models));
    }

    public static Response ok() {
        return Response.ok();
    }

    public static Response ok(Object object) {
        return Response.ok(object);
    }

    public static Response error(int status, String message) {
        return Response.error(status, message);
    }

    public static Response error() {
        return Response.error();
    }

    public static Response error(ErrorCode errorCode) {
        return Response.error(errorCode);
    }
}
