package com.oraclechain.pocketvkt.modules.nodevote;

import com.oraclechain.pocketvkt.base.BaseView;
import com.oraclechain.pocketvkt.bean.AccountDetailsBean;
import com.oraclechain.pocketvkt.bean.ResultTableRowBean;

/**
 * Created by pocketVkt on 2017/12/26.
 */
public interface NodeVoteView extends BaseView {

    void getAccountVoteDataHttp(ResultTableRowBean resultTableRowBean);

    void getAccountDetailsDataHttp(AccountDetailsBean accountDetailsBean);

    void getDataHttpFail(String msg);
}
