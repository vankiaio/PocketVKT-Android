package com.oraclechain.pocketvkt.modules.dapp.paidanswer.paidanswerhome.fragment;

import com.oraclechain.pocketvkt.base.BaseView;
import com.oraclechain.pocketvkt.bean.PaidAnswerBean;

/**
 * Created by pocketVkt on 2017/12/26.
 * 获取有问必答问题列表
 */
public interface PaidAnswerView extends BaseView {

    void getQuestionListDataHttp(PaidAnswerBean.DataBeanX paidAnswerBean);

    void getDataHttpFail(String msg);
}
