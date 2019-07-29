package com.zlcp.lattecore.wechat.template;


import com.zlcp.lattecore.wechat.BaseWXEntryActivity;
import com.zlcp.lattecore.wechat.LatteWeChat;

public class WXEntryTemplate extends BaseWXEntryActivity {

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0,0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        LatteWeChat.getInstance().getSignInCallback().onSignInsuccess(userInfo);
    }
}
