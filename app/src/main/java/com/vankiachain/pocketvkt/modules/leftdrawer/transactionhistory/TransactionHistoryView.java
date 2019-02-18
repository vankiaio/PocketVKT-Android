package com.vankiachain.pocketvkt.modules.leftdrawer.transactionhistory;

import com.vankiachain.pocketvkt.base.BaseView;
import com.vankiachain.pocketvkt.bean.TransferHistoryBean;

/**
 * Created by pocketVkt on 2017/12/26.
 */
public interface TransactionHistoryView extends BaseView {


    void getTransferHistoryDataHttp(TransferHistoryBean.DataBeanX transferHistoryBean);


    void getDataHttpFail(String msg);
}
