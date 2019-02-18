package com.vankiachain.pocketvkt.modules.coindetails;

import com.vankiachain.pocketvkt.base.BaseView;
import com.vankiachain.pocketvkt.bean.SparkLinesBean;
import com.vankiachain.pocketvkt.bean.TransferHistoryBean;

/**
 * Created by pocketVkt on 2017/12/26.
 */
public interface CoinDetailsView extends BaseView {


    void getTransferHistoryDataHttp(TransferHistoryBean.DataBeanX transferHistoryBean);

    void getSparklinesData(SparkLinesBean.DataBean dataBean);


    void getDataHttpFail(String msg);
}
