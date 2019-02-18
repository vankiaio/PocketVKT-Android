package com.vankiachain.pocketvkt.modules.account.createaccount;

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

public class CreateAccountPresenter extends BasePresent<CreateAccountView> {

    private Context mContext;

    public CreateAccountPresenter(Context context) {
        this.mContext = context;
    }




    public void postVktAccountData(String vktAccountName ,String owner_key, String active_key) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("uid", MyApplication.getInstance().getUserBean().getWallet_uid());
        hashMap.put("vktAccountName", vktAccountName);
        hashMap.put("ownerKey", owner_key);
        hashMap.put("activeKey", active_key);
        HttpUtils.postRequest(BaseUrl.HTTP_add_new_vkt, mContext, hashMap, new JsonCallback<ResponseBean<String>>() {
            @Override
            public void onSuccess(Response<ResponseBean<String>> response) {
                if (response.body().code == 0) {
                    view.postVktAccountDataHttp();
                } else {
                    view.getDataHttpFail(response.body().message);
                }
            }
        });
    }

    public void setMianAccountData(String vktAccountName) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("uid", MyApplication.getInstance().getUserBean().getWallet_uid());
        hashMap.put("vktAccountName", vktAccountName);
        HttpUtils.postRequest(BaseUrl.HTTP_set_mian_account, mContext, hashMap, new JsonCallback<ResponseBean<String>>() {
            @Override
            public void onSuccess(Response<ResponseBean<String>> response) {
                if (response.body().code == 0) {
                    view.setMainAccountHttp();
                } else {
                    view.getDataHttpFail(response.body().message);
                }
            }
        });
    }
}

