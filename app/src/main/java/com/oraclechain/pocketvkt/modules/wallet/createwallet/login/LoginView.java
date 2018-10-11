package com.oraclechain.pocketvkt.modules.wallet.createwallet.login;

import com.oraclechain.pocketvkt.base.BaseView;
import com.oraclechain.pocketvkt.bean.CodeAuthBean;

/**
 * Created by pocketEos on 2018/1/18.
 */

public interface LoginView extends BaseView {

    void getCodeDataHttp(String message);

    void getCodeAuthDataHttp(CodeAuthBean.DataBean codeAuthBean);

    void getDataHttpFail(String msg);
}
