package com.oraclechain.pocketvkt.modules.leftdrawer.transactionhistory;

import com.oraclechain.pocketvkt.base.BaseView;
import com.oraclechain.pocketvkt.bean.TransferHistoryBean;

/**
 * Created by pocketEos on 2017/12/26.
 */
public interface TransactionHistoryView extends BaseView {


    void getTransferHistoryDataHttp(TransferHistoryBean.DataBeanX transferHistoryBean);


    void getDataHttpFail(String msg);
}
