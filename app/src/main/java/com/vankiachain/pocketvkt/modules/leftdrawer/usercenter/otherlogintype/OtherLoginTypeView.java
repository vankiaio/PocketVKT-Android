package com.vankiachain.pocketvkt.modules.leftdrawer.usercenter.otherlogintype;

import com.vankiachain.pocketvkt.base.BaseView;

/**
 * Created by pocketVkt on 2018/1/18.
 */

public interface OtherLoginTypeView extends BaseView {

    void unBindOtherLoginDataHttp();

    void bindOtherLoginDataHttp();

    void getDataHttpFail(String msg);
}
