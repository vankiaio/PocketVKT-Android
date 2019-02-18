package com.vankiachain.pocketvkt.modules.resourcemanager.changememory;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.vankiachain.pocketvkt.base.BasePresent;
import com.vankiachain.pocketvkt.base.BaseUrl;
import com.vankiachain.pocketvkt.bean.AccountDetailsBean;
import com.vankiachain.pocketvkt.bean.ResponseBean;
import com.vankiachain.pocketvkt.bean.TableResultBean;
import com.vankiachain.pocketvkt.net.HttpUtils;
import com.vankiachain.pocketvkt.net.callbck.JsonCallback;

import java.util.HashMap;

/**
 * Created by pocketVkt on 2017/12/26.
 */

public class ChangeMemoryPresenter extends BasePresent<ChangeMemoryView> {
    private Context mContext;

    public ChangeMemoryPresenter(Context context) {
        this.mContext = context;
    }
    public void getTabData(){

        HttpUtils.postRequest(BaseUrl.HTTP_vkt_get_table, mContext, "{\"json\":true,\"code\":\"vktio\",\"scope\":\"vktio\",\"table\":\"rammarket\",\"table_key\":\"\",\"lower_bound\":\"\",\"upper_bound\":\"\",\"limit\":10}", new JsonCallback<ResponseBean<TableResultBean.DataBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<TableResultBean.DataBean>> response) {
                if (response.body().code == 0) {
                    view.getTableDataHttp(response.body().data);
                } else {
                    view.getDataHttpFail(response.body().message);
                }
            }
        });

    }

    public void getAccounteData(String account) {

        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("name", account);
        HttpUtils.postRequest(BaseUrl.HTTP_vkt_get_account, mContext, hashMap, new JsonCallback<ResponseBean<AccountDetailsBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<AccountDetailsBean>> response) {
                if (response.body().code == 0) {
                    view.getAccountDetailsDataHttp(response.body().data);
                } else {
                    view.getDataHttpFail(response.body().message);
                }
            }
        });


    }

}
