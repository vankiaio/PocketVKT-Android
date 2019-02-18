package com.vankiachain.pocketvkt.modules.transaction.redpacket.continueredpacket;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.vankiachain.pocketvkt.base.BasePresent;
import com.vankiachain.pocketvkt.base.BaseUrl;
import com.vankiachain.pocketvkt.bean.CoinRateBean;
import com.vankiachain.pocketvkt.bean.RedPacketDetailsBean;
import com.vankiachain.pocketvkt.bean.ResponseBean;
import com.vankiachain.pocketvkt.net.HttpUtils;
import com.vankiachain.pocketvkt.net.callbck.JsonCallback;

import java.util.HashMap;

/**
 * Created by pocketVkt on 2017/12/26.
 */

public class ContinueRedpacketPresenter extends BasePresent<ContinueRedPacketView> {
    private Context mContext;

    public ContinueRedpacketPresenter(Context context) {
        this.mContext = context;
    }

    public void getCoinRateData(String coinmarket_id) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("coinmarket_id", coinmarket_id);
        HttpUtils.postRequest(BaseUrl.HTTP_vkt_get_coin_rate, mContext, hashMap, new JsonCallback<ResponseBean<CoinRateBean.DataBean>>() {
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

    public void getRedPacketDetailsData(String redpacket_id) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("id", redpacket_id);
        HttpUtils.postRequest(BaseUrl.getHTTP_get_red_packet_details_history, mContext, hashMap, new JsonCallback<ResponseBean<RedPacketDetailsBean.DataBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<RedPacketDetailsBean.DataBean>> response) {
                if (response.body().code == 0) {
                    view.getRedPacketDetailsDataHttp(response.body().data);
                } else {
                    view.getDataHttpFail(response.body().message);
                }
            }
        });
    }
}
