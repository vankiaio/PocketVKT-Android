package com.oraclechain.pocketvkt.modules.wallet.walletmanagement;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.utils.OkLogger;
import com.oraclechain.pocketvkt.base.BasePresent;
import com.oraclechain.pocketvkt.base.BaseUrl;
import com.oraclechain.pocketvkt.bean.BaseBean;
import com.oraclechain.pocketvkt.utils.JsonUtil;

/**
 * Created by pocketVkt on 2018/1/18.
 */

public class WalletManagementPresenter extends BasePresent<WalletManagementView> {
    public void setPolicyAccountData(String vktAccountName, String status, final int position) {//设置账号的状态 1：隐藏 0：显示
        OkGo.<String>post(BaseUrl.HTTP_set_policy_account)
                .params("vktAccountName", vktAccountName)
                .params("status", status)
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        OkLogger.i(response.body().toString());
                        BaseBean baseBean = (BaseBean) JsonUtil.parseStringToBean(response.body(), BaseBean.class);
                        if (baseBean != null) {
                            view.setPolicyAccountHttp(baseBean , position);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        view.getDataHttpFail(response.message());
                    }
                });

    }

}

