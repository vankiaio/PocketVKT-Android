package com.vankiachain.pocketvkt.modules.account.importaccount;

import com.vankiachain.pocketvkt.base.BaseView;
import com.vankiachain.pocketvkt.bean.BlockChainAccountInfoBean;

/**
 * Created by pocketVkt on 2017/12/26.
 */
public interface ImportAccountView extends BaseView {

    void getBlockchainAccountInfoDataHttp(BlockChainAccountInfoBean.DataBean blockChainAccountInfoBean);

    void setMainAccountHttp();

    void getDataHttpFail(String msg);

    void postVktAccountDataHttp();
}
