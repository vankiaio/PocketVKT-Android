package com.oraclechain.pocketvkt.modules.leftdrawer.messagecenter;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.oraclechain.pocketvkt.base.BasePresent;
import com.oraclechain.pocketvkt.base.BaseUrl;
import com.oraclechain.pocketvkt.bean.MessageCenterBean;
import com.oraclechain.pocketvkt.net.HttpUtils;
import com.oraclechain.pocketvkt.bean.ResponseBean;
import com.oraclechain.pocketvkt.net.callbck.JsonCallback;

import java.util.HashMap;
import java.util.List;

/**
 * Created by pocketVkt on 2017/12/26.
 */

public class MessageCenterPresenter extends BasePresent<MessageCenterView> {
    private Context mContext;

    public MessageCenterPresenter(Context context) {
        this.mContext = context;
    }

    public void getData(int offset) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("offset", offset + "");
        hashMap.put("size", "10");
        HttpUtils.postRequest(BaseUrl.HTTP_getMagList, mContext, hashMap, new JsonCallback<ResponseBean<List<MessageCenterBean.DataBean>>>() {
            @Override
            public void onSuccess(Response<ResponseBean<List<MessageCenterBean.DataBean>>> response) {
                if (response.body().code == 0) {
                    view.getListDataHttp(response.body().data);
                } else {
                    view.getDataHttpFail(response.body().message);
                }
            }
        });
    }

}
