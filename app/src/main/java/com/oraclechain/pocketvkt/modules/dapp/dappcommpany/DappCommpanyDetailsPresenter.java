package com.oraclechain.pocketvkt.modules.dapp.dappcommpany;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.oraclechain.pocketvkt.base.BasePresent;
import com.oraclechain.pocketvkt.base.BaseUrl;
import com.oraclechain.pocketvkt.bean.DappBean;
import com.oraclechain.pocketvkt.net.HttpUtils;
import com.oraclechain.pocketvkt.bean.ResponseBean;
import com.oraclechain.pocketvkt.net.callbck.JsonCallback;

import java.util.HashMap;
import java.util.List;

/**
 * Created by pocketEos on 2017/12/26.
 */

public class DappCommpanyDetailsPresenter extends BasePresent<DappCommpanyDetailsView> {
    private Context mContext;

    public DappCommpanyDetailsPresenter(Context context) {
        this.mContext = context;
    }

    public void getData(int id) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("id", id + "");
        HttpUtils.postRequest(BaseUrl.HTTP_commpany_dapp_list, mContext, hashMap, new JsonCallback<ResponseBean<List<DappBean.DataBean>>>() {
            @Override
            public void onSuccess(Response<ResponseBean<List<DappBean.DataBean>>> response) {
                if (response.body().code == 0) {
                    view.getDappCommpanyDataHttp(response.body().data);
                } else {
                    view.getDataHttpFail(response.body().message);
                }
            }
        });
    }

}
