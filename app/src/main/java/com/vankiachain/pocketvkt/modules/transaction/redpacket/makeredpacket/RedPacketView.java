package com.vankiachain.pocketvkt.modules.transaction.redpacket.makeredpacket;

import com.vankiachain.pocketvkt.base.BaseView;
import com.vankiachain.pocketvkt.bean.CoinRateBean;
import com.vankiachain.pocketvkt.bean.RedPacketHistoryBean;
import com.vankiachain.pocketvkt.bean.SendRedPacketBean;

import java.util.List;

/**
 * Created by pocketVkt on 2017/12/26.
 */
public interface RedPacketView extends BaseView {
    void getCoinRateDataHttp(CoinRateBean.DataBean coinRateBean);

    void getRedPacketHistoryDataHttp(List<RedPacketHistoryBean.DataBean> dataBeanList);

    void sendRedPacketDataHttp(SendRedPacketBean.DataBean sendRedPacketBean);


    void getDataHttpFail(String msg);
}
