package com.oraclechain.pocketvkt.modules.blackbox.blackhome;

import com.oraclechain.pocketvkt.base.BaseView;
import com.oraclechain.pocketvkt.bean.AccountWithCoinBean;

import java.util.List;

/**
 * Created by pocketEos on 2018/1/18.
 */

public interface BlackBoxHomeView extends BaseView {

    void getAccountDetailsDataHttp(List<AccountWithCoinBean> mAccountWithCoinBeen);

    void getDataHttpFail(String msg);
}
