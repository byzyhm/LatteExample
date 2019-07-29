package com.zlcp.lattecore.wechat.template;

import com.blankj.utilcode.util.ToastUtils;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.zlcp.lattecore.wechat.BaseWXPayEntryActivity;

public class WXPayEntryTemplate extends BaseWXPayEntryActivity {

    @Override
    protected void onPaySuccess() {
        ToastUtils.showShort("支付成功");
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onPayFail() {
        ToastUtils.showShort("支付失败");
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onPayCancel() {
        ToastUtils.showShort("取消支付");
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    public void onReq(BaseReq baseReq) {
    }
}
