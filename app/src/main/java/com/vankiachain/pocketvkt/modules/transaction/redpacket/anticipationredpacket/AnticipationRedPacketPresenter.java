package com.vankiachain.pocketvkt.modules.transaction.redpacket.anticipationredpacket;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.vankiachain.pocketvkt.base.BasePresent;
import com.vankiachain.pocketvkt.base.BaseUrl;
import com.vankiachain.pocketvkt.bean.AuthRedPacketBean;
import com.vankiachain.pocketvkt.bean.ResponseBean;
import com.vankiachain.pocketvkt.net.HttpUtils;
import com.vankiachain.pocketvkt.net.callbck.JsonCallback;

import java.util.HashMap;

/**
 * Created by pocketVkt on 2017/12/26.
 */

public class AnticipationRedPacketPresenter extends BasePresent<AnticipationRedPacketView> {
    private Context mContext;

    public AnticipationRedPacketPresenter(Context context) {
        this.mContext = context;
    }

    public void getAuthRedPacketData(String id, String transactionId) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("id", id);
        hashMap.put("transactionId", transactionId);
        HttpUtils.postRequest(BaseUrl.getHTTP_get_red_packet_auth_message, mContext, hashMap, new JsonCallback<ResponseBean<AuthRedPacketBean.DataBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<AuthRedPacketBean.DataBean>> response) {
                if (response.body().code == 0) {
                    view.getAuthRedPacketDataHttp(response.body().data);
                } else {
                    view.getDataHttpFail(response.body().message);
                }
            }
        });
    }
}
