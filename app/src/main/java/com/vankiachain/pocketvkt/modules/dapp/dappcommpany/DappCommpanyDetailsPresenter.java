package com.vankiachain.pocketvkt.modules.dapp.dappcommpany;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.vankiachain.pocketvkt.base.BasePresent;
import com.vankiachain.pocketvkt.base.BaseUrl;
import com.vankiachain.pocketvkt.bean.DappBean;
import com.vankiachain.pocketvkt.net.HttpUtils;
import com.vankiachain.pocketvkt.bean.ResponseBean;
import com.vankiachain.pocketvkt.net.callbck.JsonCallback;

import java.util.HashMap;
import java.util.List;

/**
 * Created by pocketVkt on 2017/12/26.
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
