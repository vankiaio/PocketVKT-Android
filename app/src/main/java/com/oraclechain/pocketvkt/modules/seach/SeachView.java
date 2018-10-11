package com.oraclechain.pocketvkt.modules.seach;

import com.oraclechain.pocketvkt.base.BaseView;
import com.oraclechain.pocketvkt.bean.BlockChainAccountInfoBean;

/**
 * Created by pocketEos on 2017/12/26.
 */
public interface SeachView extends BaseView {

    void getBlockchainAccountInfoDataHttp(BlockChainAccountInfoBean.DataBean blockChainAccountInfoBean);

    void getDataHttpFail(String msg);
}
