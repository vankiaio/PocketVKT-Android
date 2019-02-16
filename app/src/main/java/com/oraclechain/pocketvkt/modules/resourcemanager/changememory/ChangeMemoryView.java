package com.oraclechain.pocketvkt.modules.resourcemanager.changememory;

import com.oraclechain.pocketvkt.base.BaseView;
import com.oraclechain.pocketvkt.bean.AccountDetailsBean;
import com.oraclechain.pocketvkt.bean.TableResultBean;

/**
 * Created by pocketVkt on 2017/12/26.
 */
public interface ChangeMemoryView extends BaseView {


    void getAccountDetailsDataHttp(AccountDetailsBean accountDetailsBean);

    void getTableDataHttp(TableResultBean.DataBean dataBean);

    void getDataHttpFail(String msg);
}
