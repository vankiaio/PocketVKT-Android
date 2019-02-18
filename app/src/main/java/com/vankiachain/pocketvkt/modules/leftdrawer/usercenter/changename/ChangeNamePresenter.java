package com.vankiachain.pocketvkt.modules.leftdrawer.usercenter.changename;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.vankiachain.pocketvkt.app.MyApplication;
import com.vankiachain.pocketvkt.base.BasePresent;
import com.vankiachain.pocketvkt.base.BaseUrl;
import com.vankiachain.pocketvkt.net.HttpUtils;
import com.vankiachain.pocketvkt.bean.ResponseBean;
import com.vankiachain.pocketvkt.net.callbck.JsonCallback;

import java.util.HashMap;

/**
 * Created by pocketVkt on 2018/1/18.
 */

public class ChangeNamePresenter extends BasePresent<ChangeNameView> {

    private Context mContext;

    public ChangeNamePresenter(Context context) {
        this.mContext = context;
    }
    public void HTTP_updateNameData(String userName) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("uid",  MyApplication.getInstance().getUserBean().getWallet_uid());
        hashMap.put("userName", userName);
        HttpUtils.postRequest(BaseUrl.HTTP_updateName, mContext, hashMap, new JsonCallback<ResponseBean<String>>() {
            @Override
            public void onSuccess(Response<ResponseBean<String>> response) {
                if (response.body().code == 0) {
                    view.updateNameDataHttp();
                } else {
                    view.getDataHttpFail(response.body().message);
                }
            }
        });
    }

}

