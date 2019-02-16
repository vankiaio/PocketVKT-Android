package com.oraclechain.pocketvkt.modules.account.createaccount;

import com.oraclechain.pocketvkt.base.BaseView;

/**
 * Created by pocketVkt on 2018/1/18.
 */

public interface CreateAccountView extends BaseView {


    void postVktAccountDataHttp();
    void getDataHttpFail(String msg);
    void setMainAccountHttp();
}
