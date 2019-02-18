package com.vankiachain.pocketvkt.modules.leftdrawer.systemsetting;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.vankiachain.pocketvkt.base.BasePresent;
import com.vankiachain.pocketvkt.base.BaseUrl;
import com.vankiachain.pocketvkt.bean.SystemInfoBean;
import com.vankiachain.pocketvkt.net.HttpUtils;
import com.vankiachain.pocketvkt.bean.ResponseBean;
import com.vankiachain.pocketvkt.net.callbck.JsonCallback;

import java.util.HashMap;

/**
 * Created by pocketVkt on 2018/1/18.
 */

public class SystemSettingPresenter extends BasePresent<SystemSettingView> {
    private Context mContext;

    public SystemSettingPresenter(Context context) {
        this.mContext = context;
    }

    public void getSystemInfo(final String id) {//1：法律条款和隐私政策，2：关于Pocket VKT的内容
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("id", id);
        HttpUtils.postRequest(BaseUrl.HTTP_get_system_info, mContext, hashMap, new JsonCallback<ResponseBean<SystemInfoBean.DataBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<SystemInfoBean.DataBean>> response) {
                if (response.body().code == 0) {
                    view.getSystemInfoHttp(response.body().data, id);
                } else {
                    view.getDataHttpFail(response.body().message);
                }
            }
        });
    }

}

