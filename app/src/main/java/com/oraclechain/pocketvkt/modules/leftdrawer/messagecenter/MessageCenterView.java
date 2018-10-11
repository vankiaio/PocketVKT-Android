package com.oraclechain.pocketvkt.modules.leftdrawer.messagecenter;

import com.oraclechain.pocketvkt.base.BaseView;
import com.oraclechain.pocketvkt.bean.MessageCenterBean;

import java.util.List;

/**
 * Created by pocketEos on 2017/12/26.
 * 获取friendslist
 */
public interface MessageCenterView extends BaseView {

    void getListDataHttp(List<MessageCenterBean.DataBean> messageBean);

    void getDataHttpFail(String msg);
}
