package com.vankiachain.pocketvkt.modules.dapp.paidanswer.questiondetails;

import com.vankiachain.pocketvkt.base.BaseView;
import com.vankiachain.pocketvkt.bean.ChainInfoBean;
import com.vankiachain.pocketvkt.bean.GetChainJsonBean;
import com.vankiachain.pocketvkt.bean.GetRequiredKeysBean;

/**
 * Created by pocketVkt on 2017/12/26.
 */
public interface QuestionDetailsView extends BaseView {


    void getChainInfoHttp(ChainInfoBean.DataBean chainInfoBean);

    void getChainJsonHttp(GetChainJsonBean.DataBean getChainJsonBean);

    void getRequiredKeysHttp(GetRequiredKeysBean.DataBean getRequiredKeysBean);

    void pushtransactionHttp();

    void getDataHttpFail(String msg);
}
