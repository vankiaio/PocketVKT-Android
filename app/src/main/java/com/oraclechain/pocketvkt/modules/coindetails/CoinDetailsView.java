package com.oraclechain.pocketvkt.modules.coindetails;

import com.oraclechain.pocketvkt.base.BaseView;
import com.oraclechain.pocketvkt.bean.SparkLinesBean;
import com.oraclechain.pocketvkt.bean.TransferHistoryBean;

/**
 * Created by pocketEos on 2017/12/26.
 */
public interface CoinDetailsView extends BaseView {


    void getTransferHistoryDataHttp(TransferHistoryBean.DataBeanX transferHistoryBean);

    void getSparklinesData(SparkLinesBean.DataBean dataBean);


    void getDataHttpFail(String msg);
}
