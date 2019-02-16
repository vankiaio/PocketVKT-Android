package com.oraclechain.pocketvkt.modules.unstake;

import com.oraclechain.pocketvkt.base.BaseView;
import com.oraclechain.pocketvkt.bean.AccountDetailsBean;

/**
 * Created by pocketVkt on 2017/12/26.
 */
public interface UnStakeView extends BaseView {

    void getAccountDetailsDataHttp(AccountDetailsBean accountDetailsBean);

    void getDataHttpFail(String msg);
}
