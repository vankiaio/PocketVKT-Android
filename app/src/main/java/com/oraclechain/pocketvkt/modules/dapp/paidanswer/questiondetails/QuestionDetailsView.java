package com.oraclechain.pocketvkt.modules.dapp.paidanswer.questiondetails;

import com.oraclechain.pocketvkt.base.BaseView;
import com.oraclechain.pocketvkt.bean.ChainInfoBean;
import com.oraclechain.pocketvkt.bean.GetChainJsonBean;
import com.oraclechain.pocketvkt.bean.GetRequiredKeysBean;

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
