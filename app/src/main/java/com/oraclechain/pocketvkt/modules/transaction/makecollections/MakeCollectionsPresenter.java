package com.oraclechain.pocketvkt.modules.transaction.makecollections;

import android.content.Context;

import com.google.gson.Gson;
import com.lzy.okgo.model.Response;
import com.oraclechain.pocketvkt.base.BasePresent;
import com.oraclechain.pocketvkt.base.BaseUrl;
import com.oraclechain.pocketvkt.bean.CoinRateBean;
import com.oraclechain.pocketvkt.bean.PostChainHistoryBean;
import com.oraclechain.pocketvkt.bean.TransferHistoryBean;
import com.oraclechain.pocketvkt.net.HttpUtils;
import com.oraclechain.pocketvkt.bean.ResponseBean;
import com.oraclechain.pocketvkt.net.callbck.JsonCallback;

import java.util.HashMap;

/**
 * Created by pocketEos on 2017/12/26.
 */

public class MakeCollectionsPresenter extends BasePresent<MakeCollectionsView> {
    private Context mContext;

    public MakeCollectionsPresenter(Context context) {
        this.mContext = context;
    }

    public void getCoinRateData(String coinmarket_id) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("coinmarket_id", coinmarket_id);
        HttpUtils.postRequest(BaseUrl.HTTP_eos_get_coin_rate, mContext, hashMap, new JsonCallback<ResponseBean<CoinRateBean.DataBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<CoinRateBean.DataBean>> response) {
                if (response.body().code == 0) {
                    view.getCoinRateDataHttp(response.body().data);
                } else {
                    view.getDataHttpFail(response.body().message);
                }
            }
        });
    }

    public void getTransferHistoryData(PostChainHistoryBean postChainHistoryBean) {
        HttpUtils.postRequest(BaseUrl.HTTP_get_transaction_history, mContext, new Gson().toJson(postChainHistoryBean), new JsonCallback<TransferHistoryBean>() {
            @Override
            public void onSuccess(Response<TransferHistoryBean> response) {
                if (response.body().getCode().equals("0")) {
                    view.getTransferHistoryDataHttp(response.body().getData());
                } else {
                    view.getDataHttpFail(response.body().getMsg());
                }
            }
        });
    }
}
