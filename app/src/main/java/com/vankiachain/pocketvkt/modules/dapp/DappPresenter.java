package com.vankiachain.pocketvkt.modules.dapp;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.vankiachain.pocketvkt.base.BasePresent;
import com.vankiachain.pocketvkt.base.BaseUrl;
import com.vankiachain.pocketvkt.bean.DappBean;
import com.vankiachain.pocketvkt.bean.DappCommpanyBean;
import com.vankiachain.pocketvkt.net.HttpUtils;
import com.vankiachain.pocketvkt.bean.ResponseBean;
import com.vankiachain.pocketvkt.net.callbck.JsonCallback;

import java.util.HashMap;
import java.util.List;

/**
 * Created by pocketVkt on 2017/12/26.
 */

public class DappPresenter extends BasePresent<DappView> {
    private Context mContext;

    public DappPresenter(Context context) {
        this.mContext = context;
    }

    public void getData() {

        HttpUtils.getRequets(BaseUrl.HTTP_dapp_commpany_list, mContext, new HashMap<String, String>(), new JsonCallback<ResponseBean<List<DappCommpanyBean.DataBean>>>() {
            @Override
            public void onSuccess(Response<ResponseBean<List<DappCommpanyBean.DataBean>>> response) {
                if (response.body().code == 0) {
                    view.getDappCommpanyDataHttp(response.body().data);
                } else {
                    view.getDataHttpFail(response.body().message);
                }
            }
        });
        HttpUtils.getRequets(BaseUrl.HTTP_dapp_list, mContext, new HashMap<String, String>(), new JsonCallback<ResponseBean<List<DappBean.DataBean>>>() {
            @Override
            public void onSuccess(Response<ResponseBean<List<DappBean.DataBean>>> response) {
                if (response.body().code == 0) {
                    view.getDappDataHttp(response.body().data);
                } else {
                    view.getDataHttpFail(response.body().message);
                }
            }
        });
    }
}
