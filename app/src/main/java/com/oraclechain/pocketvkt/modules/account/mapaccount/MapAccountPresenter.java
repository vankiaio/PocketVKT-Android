package com.oraclechain.pocketvkt.modules.account.mapaccount;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.oraclechain.pocketvkt.app.MyApplication;
import com.oraclechain.pocketvkt.base.BasePresent;
import com.oraclechain.pocketvkt.base.BaseUrl;
import com.oraclechain.pocketvkt.bean.GetAccountsBean;
import com.oraclechain.pocketvkt.bean.ResponseBean;
import com.oraclechain.pocketvkt.net.HttpUtils;
import com.oraclechain.pocketvkt.net.callbck.JsonCallback;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by pocketVkt on 2017/12/26.
 */

public class MapAccountPresenter extends BasePresent<MapAccountView> {
    private Context mContext;

    public MapAccountPresenter(Context context) {
        this.mContext = context;
    }

    public void getAccountInfoData(String public_key) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("public_key", public_key);
        HttpUtils.postRequest(BaseUrl.getHTTP_GetAccounts, mContext, new JSONObject(hashMap).toString(), new JsonCallback<GetAccountsBean>() {
            @Override
            public void onSuccess(Response<GetAccountsBean> response) {
                    view.getBlackAccountDataHttp(response.body());
            }
        });


    }

    public void postVktAccountData(String vktAccountName, String uid) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("uid", uid);
        hashMap.put("vktAccountName", vktAccountName);
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
