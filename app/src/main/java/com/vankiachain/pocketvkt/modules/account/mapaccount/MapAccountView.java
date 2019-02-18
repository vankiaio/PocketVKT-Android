package com.vankiachain.pocketvkt.modules.account.mapaccount;

import com.vankiachain.pocketvkt.base.BaseView;
import com.vankiachain.pocketvkt.bean.GetAccountsBean;

/**
 * Created by pocketVkt on 2017/12/26.
 */
public interface MapAccountView extends BaseView {

    void getBlackAccountDataHttp(GetAccountsBean getAccountsBean);

    void setMainAccountHttp();

    void getDataHttpFail(String msg);

    void postVktAccountDataHttp();
}
