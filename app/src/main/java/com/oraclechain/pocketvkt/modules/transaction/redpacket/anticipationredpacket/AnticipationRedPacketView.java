package com.oraclechain.pocketvkt.modules.transaction.redpacket.anticipationredpacket;

import com.oraclechain.pocketvkt.base.BaseView;
import com.oraclechain.pocketvkt.bean.AuthRedPacketBean;

/**
 * Created by pocketVkt on 2017/12/26.
 */
public interface AnticipationRedPacketView extends BaseView {
    void getAuthRedPacketDataHttp(AuthRedPacketBean.DataBean dataBean);

    void getDataHttpFail(String msg);
}
