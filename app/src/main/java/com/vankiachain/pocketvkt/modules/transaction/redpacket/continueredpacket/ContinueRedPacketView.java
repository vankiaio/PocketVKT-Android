package com.vankiachain.pocketvkt.modules.transaction.redpacket.continueredpacket;

import com.vankiachain.pocketvkt.base.BaseView;
import com.vankiachain.pocketvkt.bean.CoinRateBean;
import com.vankiachain.pocketvkt.bean.RedPacketDetailsBean;

/**
 * Created by pocketVkt on 2017/12/26.
 */
public interface ContinueRedPacketView extends BaseView {
    void getCoinRateDataHttp(CoinRateBean.DataBean coinRateBean);

    void getRedPacketDetailsDataHttp(RedPacketDetailsBean.DataBean dataBean);

    void getDataHttpFail(String msg);
}
