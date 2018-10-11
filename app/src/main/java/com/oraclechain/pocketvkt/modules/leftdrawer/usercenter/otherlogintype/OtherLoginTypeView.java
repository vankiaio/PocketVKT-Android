package com.oraclechain.pocketvkt.modules.leftdrawer.usercenter.otherlogintype;

import com.oraclechain.pocketvkt.base.BaseView;

/**
 * Created by pocketEos on 2018/1/18.
 */

public interface OtherLoginTypeView extends BaseView {

    void unBindOtherLoginDataHttp();

    void bindOtherLoginDataHttp();

    void getDataHttpFail(String msg);
}
