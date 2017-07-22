package com.nightcat.util;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.Primitives;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.StringReader;
import java.lang.reflect.Type;

@Service
public class JSONUtil {

    private final Gson gson;

    @Autowired
    public JSONUtil(Gson gson) {
        this.gson = gson;
    }

    public String toJson(Object o) {
        return gson.toJson(o);
    }

    public <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException {
        return gson.fromJson(json, classOfT);
    }

    public <T> T fromJson(String json, Type typeOfT) throws JsonSyntaxException {
        return gson.fromJson(json, typeOfT);
    }

}
