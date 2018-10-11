package com.oraclechain.pocketvkt.modules.transaction.transferaccounts;

import com.oraclechain.pocketvkt.base.BaseView;
import com.oraclechain.pocketvkt.bean.AccountDetailsBean;
import com.oraclechain.pocketvkt.bean.CoinRateBean;
import com.oraclechain.pocketvkt.bean.TransferHistoryBean;

/**
 * Created by pocketEos on 2017/12/26.
 */
public interface TransferAccountsView extends BaseView {
    void getCoinRateDataHttp(CoinRateBean.DataBean coinRateBean);

    void getAccountDetailsDataHttp(AccountDetailsBean accountDetailsBean);


    void getTransferHistoryDataHttp(TransferHistoryBean.DataBeanX transferHistoryBean);

    void getDataHttpFail(String msg);
}
