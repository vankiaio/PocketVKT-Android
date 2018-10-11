package com.oraclechain.pocketvkt.modules.account.importaccount;

import com.oraclechain.pocketvkt.base.BaseView;
import com.oraclechain.pocketvkt.bean.BlockChainAccountInfoBean;

/**
 * Created by pocketEos on 2017/12/26.
 */
public interface ImportAccountView extends BaseView {

    void getBlockchainAccountInfoDataHttp(BlockChainAccountInfoBean.DataBean blockChainAccountInfoBean);

    void setMainAccountHttp();

    void getDataHttpFail(String msg);

    void postEosAccountDataHttp();
}
