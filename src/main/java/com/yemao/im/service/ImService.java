package com.yemao.im.service;

import com.yemao.common.base.BaseObject;
import com.yemao.users.models.User;
import com.yemao.utility.Util;
import com.yemao.utility.wangyi.CheckSumBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.yemao.utility.Util.strExist;
import static com.yemao.utility.Util.uuid;

@Controller
public class ImService extends BaseObject {

    private String appKey = "47045ca57e2ca57f805cb24563e34160";
    private String appSecret = "818942ff3fac";

    private String nonce = "12345";

    /**
     * {"info":
     * {"token":"c38f44b2227761fedfccfa52c6949df0","accid":"cefc9d621eff42e4916fbc3813ca12c7","name":""}
     * ,"code":200}
     */

    private HttpPost getPost() {
        String url = "https://api.netease.im/nimserver/user/create.action";
        HttpPost httpPost = new HttpPost(url);
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);//参考 计算CheckSum的java代码


        // 设置请求的header
        httpPost.addHeader("AppKey", appKey);
        httpPost.addHeader("Nonce", nonce);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        //todo

        return httpPost;
    }

    public boolean registerIm(User user) {
        DefaultHttpClient httpClient = new DefaultHttpClient();

        HttpPost post = getPost();

        // 设置请求的参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("accid", uuid()));
        if (strExist(user.getNickname())) nvps.add(new BasicNameValuePair("name", user.getNickname()));
        try {
            post.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;

        }

        // 执行请求
        HttpResponse response = null;
        try {
            response = httpClient.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
            return false;

        }
        // 打印执行结果
        try {
            System.out.println("输出");
            String json = EntityUtils.toString(response.getEntity(), "utf-8");
            ImResponse imRep = Util.fromJson(json, ImResponse.class);
            if (imRep.getCode() != 200) {
                return false;
            }
            logger.info("注册nim: " + json);

            ImUser imUser = imRep.info;
            user.setIm_token(imUser.getToken());
            user.setAccid(imUser.accid);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static class ImUser {
        private String token;
        private String accid;
        private String name;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getAccid() {
            return accid;
        }

        public void setAccid(String accid) {
            this.accid = accid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class ImResponse {
        private String desc;
        private ImUser info;
        private int code;


        public ImUser getInfo() {
            return info;
        }

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public void setInfo(ImUser info) {
            this.info = info;
        }
    }

}
