package com.nightcat.pay.web;

import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nightcat.common.base.BaseController;
import com.nightcat.entity.PayOrder;
import com.nightcat.repository.AppOrderDao;
import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.beecloud.BCCache;
import cn.beecloud.BeeCloud; 


@Controller
@RequestMapping(value="/appWebhooks")
public class AppWebhookController extends BaseController {

	private static String appID="07028a19-f3a8-44f6-b3d7-839ec59cf63c";
	private static String testSecret="5e23c8fe-8357-4500-a85c-4ee2042dd84e";
	private static String appSecret="cc46da9a-daa6-413c-ac66-a6aa666284e5";
	private static String masterSecret="6e31d811-2952-471b-9bd5-f2f225701062";

	@Autowired
	private AppOrderDao appOrderDao;

	 Logger log = Logger.getLogger(this.getClass());

     boolean verifySign(String text,String masterKey,String signature) {
       boolean isVerified = verify(text, signature, masterKey, "UTF-8");
       if (!isVerified) {
           return false;
       }
       return true;
   }


    boolean verify(String text, String sign, String key, String inputCharset) {
       text = text + key;
       String mysign = DigestUtils.md5Hex(getContentBytes(text, inputCharset));
       return mysign.equals(sign);
   }

   byte[] getContentBytes(String content, String charset) {
       if (charset == null || "".equals(charset)) {
           return content.getBytes();
       }
       try {
           return content.getBytes(charset);
       } catch (UnsupportedEncodingException e) {
           throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
       }
   } 

	   

	    /**
		 * 支付成功后回调地址
		 * @throws Exception 
		 */
		@RequestMapping({ "/success" })
		@ResponseBody
		public String success(HttpServletRequest request,HttpServletResponse response) throws Exception{
			BeeCloud.registerApp(appID,testSecret,appSecret,masterSecret); 
			Logger log = Logger.getLogger(this.getClass()); 
		    StringBuffer json = new StringBuffer();
		    String line = null; 

		    try {
		        request.setCharacterEncoding("utf-8");
		        BufferedReader reader = request.getReader();
		        while ((line = reader.readLine()) != null) {
		            json.append(line);
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    JSONObject jsonObj = JSONObject.fromObject(json.toString());

		    String signature = jsonObj.getString("signature");
		    String transactionId=jsonObj.getString("transaction_id");
		    String transactionType=jsonObj.getString("transaction_type");
		    String channelType=jsonObj.getString("channel_type");
		    String transactionFee=jsonObj.getString("transaction_fee");

		    StringBuffer toSign = new StringBuffer();
		    toSign.append(BCCache.getAppID()).append(transactionId)
		            .append(transactionType).append(channelType)
		            .append(transactionFee);
		   boolean status = verifySign(toSign.toString(),masterSecret,signature);
		    if (status) { //验证成功
				System.out.println("验证成功");
		        //return "success"; //请不要修改或删除
				String transaction_id = jsonObj.getString("transaction_id");
				PayOrder orders = appOrderDao.findById(transaction_id);
				if (orders != null) {
					BigDecimal price = (BigDecimal) orders.getPRICE();//数据库中订单的金额
					String transaction_fee = jsonObj.getString("transaction_fee"); //beecloud平台订单的金额
					BigDecimal result = price.multiply(new BigDecimal(100));//将price乘以100，变为分
					int amount = result.intValue();
					if (String.valueOf(amount).equals(transaction_fee)) {//订单金额匹配
						orders.setSTATUS("03");
						orders.setDATE((Timestamp) new Date());
						appOrderDao.update(orders);
						return "success";
					} else {
						System.out.println("fail3");
						return "fail";
					}
				} else {
					System.out.println("fail4");
					return "fail";
				}
		        // 处理业务逻辑
		    } else { //验证失败
		    	return "fail";
		    }
		} 
	} 