package com.vankiachain.pocketvkt.modules.resourcemanager.resourcehome.fragment.net;

import com.vankiachain.pocketvkt.base.BaseView;
import com.vankiachain.pocketvkt.bean.BlockChainAccountInfoBean;

/**
 * Created by pocketVkt on 2017/12/26.
 */
public interface NetView extends BaseView {

    void getBlockchainAccountInfoDataHttp(BlockChainAccountInfoBean.DataBean blockChainAccountInfoBean);

    void getDataHttpFail(String msg);

}
