package com.vankiachain.pocketvkt.modules.account.createaccount;

import com.vankiachain.pocketvkt.base.BaseView;

/**
 * Created by pocketVkt on 2018/1/18.
 */

public interface CreateAccountView extends BaseView {


    void postVktAccountDataHttp();
    void getDataHttpFail(String msg);
    void setMainAccountHttp();
}
