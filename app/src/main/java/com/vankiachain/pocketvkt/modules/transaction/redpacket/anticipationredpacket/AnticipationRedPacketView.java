package com.vankiachain.pocketvkt.modules.transaction.redpacket.anticipationredpacket;

import com.vankiachain.pocketvkt.base.BaseView;
import com.vankiachain.pocketvkt.bean.AuthRedPacketBean;

/**
 * Created by pocketVkt on 2017/12/26.
 */
public interface AnticipationRedPacketView extends BaseView {
    void getAuthRedPacketDataHttp(AuthRedPacketBean.DataBean dataBean);

    void getDataHttpFail(String msg);
}
