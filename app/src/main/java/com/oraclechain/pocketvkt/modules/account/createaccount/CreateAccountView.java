package com.oraclechain.pocketvkt.modules.account.createaccount;

import com.oraclechain.pocketvkt.base.BaseView;

/**
 * Created by pocketEos on 2018/1/18.
 */

public interface CreateAccountView extends BaseView {


    void postEosAccountDataHttp();
    void getDataHttpFail(String msg);
    void setMainAccountHttp();
}
