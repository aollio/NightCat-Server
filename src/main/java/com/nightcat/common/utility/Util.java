package com.nightcat.common.utility;


import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.util.*;


/**
 * @author finderlo
 * @date 21/04/2017
 */
public class Util {

    private static Logger logger = LoggerFactory.getLogger(Util.class);

    private static Gson gson = new Gson();

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> cls) {
        return gson.fromJson(json, cls);
    }

    public static String uuid() {
        String raw = UUID.randomUUID().toString();
        return raw.replace("-", "");
    }

    public static boolean emptyStr(String str) {
        if (str == null || str.trim().equals("")) {
            return true;
        }
        return false;
    }

    public static boolean strExist(String str) {
        return !emptyStr(str);
    }



    public static Timestamp timeFromStr(String timestr) {
        try {
            return new Timestamp(Long.parseLong(timestr));
        } catch (Exception e) {
            return null;
        }
    }


    public static <T extends Enum<T>> T enumFromOrigin(int origin, Class<T> classT) {
        T[] values = classT.getEnumConstants();
        for (T t : values) {
            Enum ty = (Enum) t;
            if (ty.ordinal() == origin) {
                return t;
            }
        }
        return null;
    }

    public static void writeToResponse(HttpServletResponse response, Object o) throws IOException {
        response.getWriter().append(o.toString()).flush();
    }

    public static Map<String, Object> entityToMap(Object object) {
        Map<String, Object> attr = new HashMap<>();
        if (object == null) {
            return attr;
        }
        Class clazz = object.getClass();
        try {
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                attr.put(field.getName(), field.get(object));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return attr;
    }


    public static Timestamp now() {
        return new Timestamp(System.currentTimeMillis());
    }


    public static String md5(String str) {

        try {
            // 确定计算方法
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            // 加密后的字符串
            return base64en.encode(md5.digest(str.getBytes("utf-8"))).replace("=", "");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String uniqueFileName(String filename) {
        String suffix = filename.substring(filename.lastIndexOf(".") + 1);
        return System.currentTimeMillis() + "" + new Random().nextInt(10000) + "." + suffix;
    }

    public static String join(String sep, List<String> stringList) {
        if (stringList == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        boolean flag = false;
        for (String string : stringList) {
            if (flag) {
                result.append(sep);
            } else {
                flag = true;
            }
            result.append(string);
        }
        return result.toString();
    }

    public static int revise(Integer integer) {
        return integer == null ? 0 : integer;
    }

    public static double revise(Double integer) {
        return integer == null ? 0 : integer;
    }


    public static void less2more(Object less, Object more) {
        if (less == null) return;


        Map<String, Object> temp = new HashMap<>();
        try {


            for (Field field : less.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                temp.put(field.getName(), field.get(less));
            }
            for (Map.Entry<String, Object> entry : temp.entrySet()) {
                try {
                    Field field = more.getClass().getDeclaredField(entry.getKey());
                    field.setAccessible(true);
                    field.set(more, entry.getValue());
                } catch (NoSuchFieldException ignored) {
                }
            }
        } catch (Exception ignored) {

        }
    }

}
