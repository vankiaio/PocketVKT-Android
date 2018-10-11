package com.oraclechain.pocketvkt.modules.transaction.redpacket.makeredpacket;

import com.oraclechain.pocketvkt.base.BaseView;
import com.oraclechain.pocketvkt.bean.CoinRateBean;
import com.oraclechain.pocketvkt.bean.RedPacketHistoryBean;
import com.oraclechain.pocketvkt.bean.SendRedPacketBean;

import java.util.List;

/**
 * Created by pocketEos on 2017/12/26.
 */
public interface RedPacketView extends BaseView {
    void getCoinRateDataHttp(CoinRateBean.DataBean coinRateBean);

    void getRedPacketHistoryDataHttp(List<RedPacketHistoryBean.DataBean> dataBeanList);

    void sendRedPacketDataHttp(SendRedPacketBean.DataBean sendRedPacketBean);


    void getDataHttpFail(String msg);
}
