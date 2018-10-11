package com.oraclechain.pocketvkt.modules.resourcemanager.changenet;

import com.oraclechain.pocketvkt.base.BaseView;
import com.oraclechain.pocketvkt.bean.AccountDetailsBean;

/**
 * Created by pocketEos on 2017/12/26.
 */
public interface ChangeNetView extends BaseView {


    void getAccountDetailsDataHttp(AccountDetailsBean accountDetailsBean);

    void getDataHttpFail(String msg);
}
