package com.yemao.utility.wangyi;


import com.yemao.utility.Util;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 短信发送工具类
 *
 * @author Administrator
 */
public class MobileMessageSend {
    private static final String SERVER_URL = "https://api.netease.im/sms/sendcode.action";//发送验证码的请求路径URL
    private static final String APP_KEY = "47045ca57e2ca57f805cb24563e34160";//网易云信分配的账号
    private static final String APP_SECRET = "818942ff3fac";//网易云信分配的密钥
    private static final String NONCE = "123456";//随机数

    public static Verify sendMsg(String phone) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost post = new HttpPost(SERVER_URL);

        String curTime = String.valueOf((new Date().getTime() / 1000L));
        String checkSum = CheckSumBuilder.getCheckSum(APP_SECRET, NONCE, curTime);

        //设置请求的header
        post.addHeader("AppKey", APP_KEY);
        post.addHeader("Nonce", NONCE);
        post.addHeader("CurTime", curTime);
        post.addHeader("CheckSum", checkSum);
        post.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        //设置请求参数
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("mobile", phone));//手机号码
        nameValuePairs.add(new BasicNameValuePair("templateid", "3064129"));//模板id 
        nameValuePairs.add(new BasicNameValuePair("codeLen", "6"));//验证码长度（4-10） 

        post.setEntity(new UrlEncodedFormEntity(nameValuePairs, "utf-8"));

        //执行请求
        HttpResponse response = httpclient.execute(post);
        String responseEntity = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println("验证码：" + responseEntity);
        //判断是否发送成功，发送成功返回true
       /* String code= JSON.parseObject(responseEntity).getString("code");
        return code;*/

        //判断是否发送成功，发送成功返回true
        // JSONObject jsonStr= JSON.parseObject(responseEntity);
        // System.out.println(jsonStr.toString());
        return Util.fromJson(responseEntity, Verify.class);
    }


    public static void main(String[] args) {
        String mobileNumber = "1821769989";//接收验证码的手机号码
        try {
            Verify str = MobileMessageSend.sendMsg(mobileNumber);
            System.out.println("str:" + str);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public static class Verify {
        int code;
        String msg;
        String obj;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getObj() {
            return obj;
        }

        public void setObj(String obj) {
            this.obj = obj;
        }

        @Override
        public String toString() {
            return Util.toJson(this);
        }
    }

}