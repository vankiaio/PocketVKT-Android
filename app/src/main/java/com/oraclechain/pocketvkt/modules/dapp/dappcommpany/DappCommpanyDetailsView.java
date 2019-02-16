package com.oraclechain.pocketvkt.modules.dapp.dappcommpany;

import com.oraclechain.pocketvkt.base.BaseView;
import com.oraclechain.pocketvkt.bean.DappBean;

import java.util.List;

/**
 * Created by pocketVkt on 2017/12/26.
 * 获取friendslist
 */
public interface DappCommpanyDetailsView extends BaseView {

    void getDappCommpanyDataHttp(List<DappBean.DataBean> dappBean);


    void getDataHttpFail(String msg);
}
