package com.vankiachain.pocketvkt.modules.transaction.redpacket.getredpacketdetails;

import com.vankiachain.pocketvkt.base.BaseView;
import com.vankiachain.pocketvkt.bean.RedPacketDetailsBean;

/**
 * Created by pocketVkt on 2017/12/26.
 */
public interface GetRedPacketDetailsView extends BaseView {
    void getRedPacketDetailsDataHttp(RedPacketDetailsBean.DataBean dataBean);

    void getDataHttpFail(String msg);
}
