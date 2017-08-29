package com.yemao.pay.web;

import com.yemao.common.base.BaseController;
import com.yemao.vo.VoService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("/appMyhook")
public class AppMyhookController extends BaseController {

    /**
     * 支付成功后回调地址
     */
    @RequestMapping({"/success"})
    @ResponseBody
    public void success(Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String app_id = "07028a19-f3a8-44f6-b3d7-839ec59cf63c";
        String app_secret = "cc46da9a-daa6-413c-ac66-a6aa666284e5";


        String sign = params.get("sign").toString();
        long timestamp = Long.parseLong(params.get("timestamp").toString());

        String tosign = app_id + app_secret + timestamp;
        try {
            String ourSign = DigestUtils.md5Hex(tosign.getBytes("UTF-8"));
            if (sign.equals(ourSign)) {
                response.getWriter().write("success");
            } else {
                response.getWriter().write("fail");
                return;
            }
            response.getWriter().close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String transaction_type = (String) params.get("transaction_type");
        String transaction_id = params.get("transaction_id").toString();
        String channel = (String) params.get("channel_type");
        String sub_channel_type = (String) params.get("sub_channel_type");
        Integer transaction_fee = (Integer) params.get("transaction_fee");
        Map<String, Object> optional = (Map<String, Object>) params.get("optional");
        if (transaction_type.equals("PAY")) {
            boolean success = checkBillSuccess(transaction_id);
            if (success) {
                return;
            }
            Map<String, Object> bill = queryBill(transaction_id);
            Integer fee = (Integer) bill.get("fee");
            //校验
            //fee==transaction_fee
            addDiamond(transaction_id);
        } else if (transaction_type.equals("REFUND")) {
            //去重
            //校验参数
            //处理业务

        }
    }

    private void addDiamond(String transaction_id) {
    }

    private Map<String, Object> queryBill(String transaction_id) {
        return null;
    }

    //校验订单是否已经成功，方便过滤重复的webhook
    private boolean checkBillSuccess(String transaction_id) {
        return false;
    }

    @Override
    protected VoService getVoService() {
        return null;
    }
}
