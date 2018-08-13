package com.cobweb.pay.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;

/**
 * @author: XRom
 * @createdTime: 2018-08-12 23:12:01
 */
public class AliPay {

    private static final String APP_ID = "2016091700533068";
    private static final String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDF42r5AqUQ8qDDsuuiTRE/xutPDiF7ZLGIL+OxQk8k7RSp7wIfgoG3UaJJ6j8s0zsdRSUieDhn/buQJtGI00t94EllyoZbros+W+BKF6LfLCqu/ZZbAkToe/j0/F4JtP6MrLeiDCRQAaK5xXu+hFTwieXHxVHJ7Ls/B/lKj+wV2j3fbtUSOKfwj2BUrg2UF+zlNXbf6n6bj1Vzuqsxt89/nF4Ov3/RopxYbO6L4NlNU6UFKv2TJg6f2wG3/lLgFpzI32WK9uRjcCVWoPd/el94djmPzp6Id4Mi2Y94Cuvh5PdvnXsHrfFOJ39mU4IycyUngHFWUFfjh+/ltmyx+lNxAgMBAAECggEAXghw/p65HVKN/jB5NAh3F91IV6qZUm9IxTQseEDQJvXZid6fHaYAScIq4gDDwGMnhgMjYDvMYe36QMeRbfkpuEjp18rjCZHpbNpvvAV7SZ1NMxYhamGrqOWwKYn6jZZKF0LDh+dJIhOE5c3nvWJYkmyUmdczxEYPvCXn2fyF7OOP8YpgxSHwi6ntgUiOsRwNyW7OvLcrTdqa/Ezuja/Ci5SVgSsLJbTx/II7eEyAfsDmPuwesQroPr21uPphCusXuCPKC/2qHnPeYxVpfJ0tB/okMbtkgoHuPudzIyE1uvs1YgXD7SxmBGB9MFYQ7op6yNbPhTuaaxMLKqbUTKfoAQKBgQDkNOJpTCigo1ULwwCmcpJvXh0L+OWivZ3/uXtHEKfkBO5YzHzj6CGfuoOrE78WOIeoM9T2EN7HEOONLBn/w9dvLTCRKDnLAgZIx49rPuZKtYGqi3gxyAhl57mDKrm6JHoegxY8kQwt/liWS2YJM6WyDJoc6+xCLw7DjiQBbQ7uMQKBgQDd/UJ3tretnslwJZQVjejeI2B+JGB7vtqXHyVGrbX4X85iM2jdPeTRZf6BWR/uOmKxPaD0DMND/mLs0mOom76GBEiQ8n/jmgT1FKsVgABt1BO2bTeMBeBeaXU6RAqsLFHFqV/ilZqNVIXJDiSOLrt8QHQx0uErXk+5Uxme2SopQQKBgGcpKPikvvDvJPDuNAsKW/wQNV1GJLjOsu/5LytO8jZ3rC9kMUjtlNWaAQ+DG6y13U1CMRETS0H99n/I4e/m3iqZ6QyVfJqyzZh07fZzNrOrHXS1G56VaIZ4nDoZRGBLQREt/Q1gHHtmMTGv3+5k9JBqdfiBBgpdL3Vjeg9S957hAoGAcM/YrG/Nrkzof7aJJ98DPYhq7A/efGSEP5JTZjS3Ki69+ke3/d8LXqLfkKpTQMSpDPuNxRiLTw2DBmjwvmnPi9m3avnhpi/KXyYOv5FaJnZFQksElIMT/8bGDUMwzUiEkde+Y661aSdQCdnHF/6FxuIWao+JYHnAOCUT+xbPh0ECgYEAudPbjVO1g8QNxlD9IhI0+hq+yOyfvevJi/466u0GFf1g3t8N0xd3yHrOnGq++/HpbaNkeOlDCwfDoqWG8LEVFWPqCLH9LT8/rflU7BzgYBDJ5Ajhb8C2tFF81toccu1SFp5Y1uuXseGZ6uZfziFDPoMw4stGxGKTJ4dF/v3EsLg=";
    private static final String CHARSET = "UTF-8";
    private static final String SIGN_TYPE = "RSA2";
    private static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA3WW7Yp9DU688dH20V7ZoCTwRnPZL+MOG8DA3N689bOdTMoHwCq7LxrWfoFbjU8/nWTgnUowD33yMKbfJb7Z4dwKLRZXRmBfApuvdussFiH8Zxw2VnCFqgELF6Wf1F+UjghlMre+qdUQ8rgVyqt+mBk8UMUEJw6+OzSko7GmLfeXOw6NgXuQlRGzhwn0mBDc6hfgB/rc6BGc42vQ4sCCJ9hehkWXpiRe0NBdRklfu6dzs6bOJ2W8VN5HoPP6rXX8fmDw2Lkgp1YYsxxNJ7MxY2XOQKu8oWqUyLgSweybgUCHyz22bzRXQw57B//PyjJS6V9szfMjWkmGckwKcpBzKWQIDAQAB";

    public void pay() throws AlipayApiException {
        String outtradeno = "123456789"; //业务订单号

        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("我是测试数据");
        model.setSubject("App支付测试Java");
        model.setOutTradeNo(outtradeno);
        model.setTimeoutExpress("30m");
        model.setTotalAmount("0.01");
        model.setProductCode("2088102176051371");
        request.setBizModel(model);
        request.setNotifyUrl("http://cobweb.zxr1998.com.cn/test/callback/aliPay");
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            new AliPay().pay();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }
}
