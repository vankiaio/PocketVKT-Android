package com.oraclechain.pocketvkt.modules.wallet.walletmanagement;

import com.oraclechain.pocketvkt.base.BaseView;
import com.oraclechain.pocketvkt.bean.BaseBean;

/**
 * Created by pocketEos on 2018/1/18.
 */

public interface WalletManagementView extends BaseView {

    void setPolicyAccountHttp(BaseBean baseBean, int position);


    void getDataHttpFail(String msg);
}
