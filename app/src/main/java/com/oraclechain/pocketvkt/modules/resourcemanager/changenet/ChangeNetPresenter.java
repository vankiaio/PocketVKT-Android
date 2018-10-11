package com.oraclechain.pocketvkt.modules.resourcemanager.changenet;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.oraclechain.pocketvkt.base.BasePresent;
import com.oraclechain.pocketvkt.base.BaseUrl;
import com.oraclechain.pocketvkt.bean.AccountDetailsBean;
import com.oraclechain.pocketvkt.bean.ResponseBean;
import com.oraclechain.pocketvkt.net.HttpUtils;
import com.oraclechain.pocketvkt.net.callbck.JsonCallback;

import java.util.HashMap;

/**
 * Created by pocketEos on 2017/12/26.
 */

public class ChangeNetPresenter extends BasePresent<ChangeNetView> {
    private Context mContext;

    public ChangeNetPresenter(Context context) {
        this.mContext = context;
    }

    public void getAccounteData(String account) {


        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("name", account);
        HttpUtils.postRequest(BaseUrl.HTTP_eos_get_account, mContext, hashMap, new JsonCallback<ResponseBean<AccountDetailsBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<AccountDetailsBean>> response) {
                if (response.body().code == 0) {
                    view.getAccountDetailsDataHttp(response.body().data);
                } else {
                    view.getDataHttpFail(response.body().message);
                }
            }
        });
    }

}
