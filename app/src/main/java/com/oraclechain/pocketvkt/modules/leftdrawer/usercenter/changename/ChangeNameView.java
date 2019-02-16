package com.oraclechain.pocketvkt.modules.leftdrawer.usercenter.changename;

import com.oraclechain.pocketvkt.base.BaseView;

/**
 * Created by pocketVkt on 2018/1/18.
 */

public interface ChangeNameView extends BaseView {

    void updateNameDataHttp();


    void getDataHttpFail(String msg);
}
