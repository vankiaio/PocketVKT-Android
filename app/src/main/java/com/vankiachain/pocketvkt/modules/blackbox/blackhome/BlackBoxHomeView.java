package com.vankiachain.pocketvkt.modules.blackbox.blackhome;

import com.vankiachain.pocketvkt.base.BaseView;
import com.vankiachain.pocketvkt.bean.AccountWithCoinBean;

import java.util.List;

/**
 * Created by pocketVkt on 2018/1/18.
 */

public interface BlackBoxHomeView extends BaseView {

    void getAccountDetailsDataHttp(List<AccountWithCoinBean> mAccountWithCoinBeen);

    void getDataHttpFail(String msg);
}
