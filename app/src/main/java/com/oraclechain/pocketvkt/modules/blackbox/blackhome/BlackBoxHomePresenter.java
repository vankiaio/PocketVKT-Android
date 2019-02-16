package com.oraclechain.pocketvkt.modules.blackbox.blackhome;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.oraclechain.pocketvkt.base.BasePresent;
import com.oraclechain.pocketvkt.base.BaseUrl;
import com.oraclechain.pocketvkt.bean.AccountDetailsBean;
import com.oraclechain.pocketvkt.bean.AccountWithCoinBean;
import com.oraclechain.pocketvkt.bean.ResponseBean;
import com.oraclechain.pocketvkt.net.HttpUtils;
import com.oraclechain.pocketvkt.net.callbck.JsonCallback;
import com.oraclechain.pocketvkt.utils.RegexUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by pocketVkt on 2018/1/18.
 */

public class BlackBoxHomePresenter extends BasePresent<BlackBoxHomeView> {
    private Context mContext;

    public BlackBoxHomePresenter(Context context) {
        this.mContext = context;
    }
    public void getAccountDetailsData(final String name ) {

        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("name", name);
        HttpUtils.postRequest(BaseUrl.HTTP_vkt_get_account, mContext, hashMap, new JsonCallback<ResponseBean<AccountDetailsBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<AccountDetailsBean>> response) {
                if (response.body().code == 0) {
                    if (response.body().data.getAccount_name().equals(name)) {
                        List<AccountWithCoinBean> accountWithCoinBeens = new ArrayList<>();
                        AccountWithCoinBean  vkt = new AccountWithCoinBean();
                        vkt.setCoinName("VKT");
                        vkt.setCoinForCny(RegexUtil.subZeroAndDot(response.body().data.getVkt_balance_cny()));
                        vkt.setCoinNumber(RegexUtil.subZeroAndDot(response.body().data.getVkt_balance()));
                        vkt.setCoinImg(response.body().data.getAccount_icon());
                        vkt.setVkt_market_cap_usd(response.body().data.getVkt_market_cap_usd());
                        vkt.setVkt_market_cap_cny(response.body().data.getVkt_market_cap_cny());
                        vkt.setVkt_price_cny(response.body().data.getVkt_price_cny());
                        if (response.body().data.getVkt_price_change_in_24h().contains("-")) {
                            vkt.setCoinUpsAndDowns(response.body().data.getVkt_price_change_in_24h() + "%");
                        } else {
                            vkt.setCoinUpsAndDowns("+" + response.body().data.getVkt_price_change_in_24h() + "%");
                        }
                        accountWithCoinBeens.add(vkt);
                        AccountWithCoinBean oct = new AccountWithCoinBean();
                        oct.setCoinName("OCT");
                        oct.setCoinForCny(RegexUtil.subZeroAndDot(response.body().data.getOct_balance_cny()));
                        oct.setCoinNumber(RegexUtil.subZeroAndDot(response.body().data.getOct_balance()));
                        oct.setCoinImg(response.body().data.getAccount_icon());
                        oct.setOct_market_cap_cny(response.body().data.getOct_market_cap_cny());
                        oct.setOct_market_cap_usd(response.body().data.getOct_market_cap_usd());
                        oct.setOct_price_cny(response.body().data.getOct_price_cny());
                        if (response.body().data.getOct_price_change_in_24h().contains("-")) {
                            oct.setCoinUpsAndDowns(response.body().data.getOct_price_change_in_24h() + "%");
                        } else {
                            oct.setCoinUpsAndDowns("+" +response.body().data.getOct_price_change_in_24h() + "%");
                        }
                        accountWithCoinBeens.add(oct);
                        view.getAccountDetailsDataHttp(accountWithCoinBeens);
                    }
                } else {
                    view.getDataHttpFail(response.body().message);
                }
            }
        });
    }
}

