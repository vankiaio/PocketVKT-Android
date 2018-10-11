package com.oraclechain.pocketvkt.modules.dapp;

import com.oraclechain.pocketvkt.base.BaseView;
import com.oraclechain.pocketvkt.bean.DappBean;
import com.oraclechain.pocketvkt.bean.DappCommpanyBean;

import java.util.List;

/**
 * Created by pocketEos on 2017/12/26.
 * 获取friendslist
 */

public interface DappView extends BaseView {
    void getDappCommpanyDataHttp(List<DappCommpanyBean.DataBean> dappCommpanyBean);

    void getDappDataHttp(List<DappBean.DataBean> dappBean);

    void getDataHttpFail(String msg);
}
