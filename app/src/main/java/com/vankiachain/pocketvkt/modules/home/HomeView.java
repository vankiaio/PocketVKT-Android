package com.vankiachain.pocketvkt.modules.home;

import com.vankiachain.pocketvkt.base.BaseView;
import com.vankiachain.pocketvkt.bean.AccountWithCoinBean;

import java.util.List;

/**
 * Created by pocketVkt on 2018/1/18.
 */

public interface HomeView extends BaseView {

    void getAccountDetailsDataHttp(List<AccountWithCoinBean> mAccountWithCoinBeen);

    void getDataHttpFail(String msg);
}
