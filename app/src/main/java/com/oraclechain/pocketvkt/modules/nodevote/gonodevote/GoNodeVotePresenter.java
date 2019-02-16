package com.oraclechain.pocketvkt.modules.nodevote.gonodevote;

import android.content.Context;

import com.google.gson.Gson;
import com.lzy.okgo.model.Response;
import com.oraclechain.pocketvkt.base.BasePresent;
import com.oraclechain.pocketvkt.base.BaseUrl;
import com.oraclechain.pocketvkt.bean.RequestNodeListBean;
import com.oraclechain.pocketvkt.bean.ResultNodeListBean;
import com.oraclechain.pocketvkt.net.HttpUtils;
import com.oraclechain.pocketvkt.net.callbck.JsonCallback;

/**
 * Created by pocketVkt on 2017/12/26.
 */

public class GoNodeVotePresenter extends BasePresent<GoNodeVoteView> {
    private Context mContext;

    public GoNodeVotePresenter(Context context) {
        this.mContext = context;
    }

    public void getNodeListData(int page) {
        RequestNodeListBean requestNodeListBean = new RequestNodeListBean();
        requestNodeListBean.setPageNum(page+"");//分页开始的节点名称
        requestNodeListBean.setPageSize("10");
        HttpUtils.postRequest(BaseUrl.getHTTP_GetBpJson, mContext, new Gson().toJson(requestNodeListBean), new JsonCallback<ResultNodeListBean>() {
            @Override
            public void onSuccess(Response<ResultNodeListBean> response) {
                view.getNodeListDataHttp(response.body());
            }
        });
    }
}
