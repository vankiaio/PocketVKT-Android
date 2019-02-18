package com.vankiachain.pocketvkt.modules.transaction.transferaccounts;

import android.content.Context;

import com.google.gson.Gson;
import com.lzy.okgo.model.Response;
import com.vankiachain.pocketvkt.base.BasePresent;
import com.vankiachain.pocketvkt.base.BaseUrl;
import com.vankiachain.pocketvkt.bean.AccountDetailsBean;
import com.vankiachain.pocketvkt.bean.CoinRateBean;
import com.vankiachain.pocketvkt.bean.PostChainHistoryBean;
import com.vankiachain.pocketvkt.bean.ResponseBean;
import com.vankiachain.pocketvkt.bean.TransferHistoryBean;
import com.vankiachain.pocketvkt.net.HttpUtils;
import com.vankiachain.pocketvkt.net.callbck.JsonCallback;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;

/**
 * Created by pocketVkt on 2017/12/26.
 */

public class TransferAccountsPresenter extends BasePresent<TransferAccountsView> {
    private Context mContext;

    public TransferAccountsPresenter(Context context) {
        this.mContext = context;
    }

    public void getCoinRateData(String coinmarket_id) {//获取token汇率
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

    public void getAccountDetailsData(final String name) {//动态获取账号资产信息
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("name", name);
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


    public void getTransferHistoryData(PostChainHistoryBean postChainHistoryBean) {//获取账号交易历史
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

    public void pushAction(String contract, String action, String message, String scopes, String permissionAccount, String permissionName ){


        // can make
        String[] permissions = ( StringUtils.isEmpty(permissionAccount) || StringUtils.isEmpty( permissionName))
                ? null : new String[]{permissionAccount + "@" + permissionName };


  /*      addDisposable(
                mDataManager.pushAction(contract, action, message.replaceAll("\\r|\\n","")
                        , permissions)
                        .mergeWith( jsonObject -> mDataManager.addAccountHistory( getAccountListForHistory( contract, permissionAccount) ))
                        .subscribeOn( getSchedulerProvider().io())
                        .observeOn( getSchedulerProvider().ui())
                        .subscribeWith(new RxCallbackWrapper<PushTxnResponse>( this) {
                                           @Override
                                           public void onNext(PushTxnResponse result) {
                                               if (!isViewAttached()) return;

                                               getMvpView().showLoading(false);

                                               getMvpView().showResult( Utils.prettyPrintJson( result ), result.toString() );
                                           }
                                       }
                        )*/

    }


}
