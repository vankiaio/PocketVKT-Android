package com.vankiachain.pocketvkt.modules.transaction.redpacket.makeredpacket;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.vankiachain.pocketvkt.app.MyApplication;
import com.vankiachain.pocketvkt.base.BasePresent;
import com.vankiachain.pocketvkt.base.BaseUrl;
import com.vankiachain.pocketvkt.bean.CoinRateBean;
import com.vankiachain.pocketvkt.bean.RedPacketHistoryBean;
import com.vankiachain.pocketvkt.bean.ResponseBean;
import com.vankiachain.pocketvkt.bean.SendRedPacketBean;
import com.vankiachain.pocketvkt.net.HttpUtils;
import com.vankiachain.pocketvkt.net.callbck.JsonCallback;

import java.util.HashMap;
import java.util.List;

/**
 * Created by pocketVkt on 2017/12/26.
 */

public class RedPacketPresenter extends BasePresent<RedPacketView> {
    private Context mContext;

    public RedPacketPresenter(Context context) {
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

    public void getRedPacketHistoryData(String account, String type) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("uid", MyApplication.getInstance().getUserBean().getWallet_uid());
        hashMap.put("account", account);
        hashMap.put("type", type);//资产类型
        HttpUtils.postRequest(BaseUrl.getHTTP_get_red_packet_history, mContext, hashMap, new JsonCallback<ResponseBean<List<RedPacketHistoryBean.DataBean>>>() {
            @Override
            public void onSuccess(Response<ResponseBean<List<RedPacketHistoryBean.DataBean>>> response) {
                if (response.body().code == 0) {
                    view.getRedPacketHistoryDataHttp(response.body().data);
                } else {
                    view.getDataHttpFail(response.body().message);
                }
            }
        });
    }

    public void sendRedPacketData(String account, String amount, String packetCount, String type) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("uid", MyApplication.getInstance().getUserBean().getWallet_uid());
        hashMap.put("account", account);
        hashMap.put("amount", amount);
        hashMap.put("packetCount", packetCount);
        hashMap.put("type", type);//资产类型 0 oct 1 vkt 2 其他
        HttpUtils.postRequest(BaseUrl.HTTP_send_red_packet, mContext, hashMap, new JsonCallback<ResponseBean<SendRedPacketBean.DataBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<SendRedPacketBean.DataBean>> response) {
                if (response.body().code == 0) {
                    view.sendRedPacketDataHttp(response.body().data);
                } else {
                    view.getDataHttpFail(response.body().message);
                }
            }
        });
    }
}
