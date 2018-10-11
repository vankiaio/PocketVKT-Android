package com.oraclechain.pocketvkt.modules.transaction.makecollections;

import com.oraclechain.pocketvkt.base.BaseView;
import com.oraclechain.pocketvkt.bean.CoinRateBean;
import com.oraclechain.pocketvkt.bean.TransferHistoryBean;

/**
 * Created by pocketEos on 2017/12/26.
 */
public interface MakeCollectionsView extends BaseView {
    void getCoinRateDataHttp(CoinRateBean.DataBean coinRateBean);

    void getTransferHistoryDataHttp(TransferHistoryBean.DataBeanX transferHistoryBean);


    void getDataHttpFail(String msg);
}
