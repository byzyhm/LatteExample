package com.zlcp.latteec.main.cart.pay;

/**
 * Created by zl_freedom
 * Note: 支付宝回调监听
 */

public interface IAlPayResultListener {

    //支付成功的回调
    void onPaySuccess();

    //支付中的回调
    void onPaying();

    //支付失败的回调
    void onPayFail();

    //用户取消操作的回调
    void onPayCancel();

    //支付网络连接出现异常的回调
    void onPayConnectError();
}
