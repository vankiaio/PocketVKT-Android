package com.oraclechain.pocketvkt.modules.account.accountdetails;

import com.oraclechain.pocketvkt.base.BaseView;

/**
 * Created by pocketVkt on 2018/1/18.
 */

public interface AccountDetailsView extends BaseView {

    void setMainAccountHttp(int type);


    void getDataHttpFail(String msg);


}
