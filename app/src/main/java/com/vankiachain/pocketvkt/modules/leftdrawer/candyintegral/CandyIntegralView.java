package com.vankiachain.pocketvkt.modules.leftdrawer.candyintegral;

import com.vankiachain.pocketvkt.base.BaseView;
import com.vankiachain.pocketvkt.bean.CandyScoreBean;
import com.vankiachain.pocketvkt.bean.CandyUserTaskBean;
import com.vankiachain.pocketvkt.bean.HotEquitiesBean;

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
