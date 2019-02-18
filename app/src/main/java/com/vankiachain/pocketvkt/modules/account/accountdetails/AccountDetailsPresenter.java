package com.vankiachain.pocketvkt.modules.account.accountdetails;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.vankiachain.pocketvkt.app.MyApplication;
import com.vankiachain.pocketvkt.base.BasePresent;
import com.vankiachain.pocketvkt.base.BaseUrl;
import com.vankiachain.pocketvkt.bean.ResponseBean;
import com.vankiachain.pocketvkt.net.HttpUtils;
import com.vankiachain.pocketvkt.net.callbck.JsonCallback;

import java.util.HashMap;

/**
 * Created by pocketVkt on 2018/1/18.
 */

public class AccountDetailsPresenter extends BasePresent<AccountDetailsView> {
    private Context mContext;

    public AccountDetailsPresenter(Context context) {
        this.mContext = context;
    }

    public void setMianAccountData(String vktAccountName, final int type) {//0代表直接执行设置主账号操作，1代表先删除后设置主账号
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("uid", MyApplication.getInstance().getUserBean().getWallet_uid());
        hashMap.put("vktAccountName", vktAccountName);
        HttpUtils.postRequest(BaseUrl.HTTP_set_mian_account, mContext, hashMap, new JsonCallback<ResponseBean<String>>() {
            @Override
            public void onSuccess(Response<ResponseBean<String>> response) {
                if (response.body().code == 0) {
                    view.setMainAccountHttp(type);
                } else {
                    view.getDataHttpFail(response.body().message);
                }
            }
        });
    }
}

