package com.nightcat.common.base;

import com.nightcat.common.ErrorCode;
import com.nightcat.common.Response;
import com.nightcat.entity.EntityModel;
import com.nightcat.vo.VoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;


public abstract class BaseController extends BaseObject {


    public Object vo(EntityModel model) {
        return getVoService().from(model);
    }

    public Collection<Object> vo(Collection<? extends EntityModel> models) {
        return getVoService().from(models);
    }

    protected abstract VoService getVoService();


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
