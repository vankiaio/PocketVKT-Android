package com.oraclechain.pocketvkt.modules.account.mapaccount;

import com.oraclechain.pocketvkt.base.BaseView;
import com.oraclechain.pocketvkt.bean.GetAccountsBean;

/**
 * Created by pocketVkt on 2017/12/26.
 */
public interface MapAccountView extends BaseView {

    void getBlackAccountDataHttp(GetAccountsBean getAccountsBean);

    void setMainAccountHttp();

    void getDataHttpFail(String msg);

    void postVktAccountDataHttp();
}
