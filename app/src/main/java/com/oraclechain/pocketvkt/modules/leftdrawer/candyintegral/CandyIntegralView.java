package com.oraclechain.pocketvkt.modules.leftdrawer.candyintegral;

import com.oraclechain.pocketvkt.base.BaseView;
import com.oraclechain.pocketvkt.bean.CandyScoreBean;
import com.oraclechain.pocketvkt.bean.CandyUserTaskBean;
import com.oraclechain.pocketvkt.bean.HotEquitiesBean;

import java.util.List;

/**
 * Created by pocketVkt on 2017/12/26.
 * 获取friendslist
 */
public interface CandyIntegralView extends BaseView {

    void getCandyScoreDataHttp(CandyScoreBean.DataBean messageBean);

    void getHotEquitiesDataHttp(List<HotEquitiesBean.DataBean> mDataBeans);

    void getCandyTaskDataHttp(List<CandyUserTaskBean.DataBean> mDataBeans);

    void getDataHttpFail(String msg);
}
