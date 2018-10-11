package com.oraclechain.pocketvkt.modules.friendslist.pelist;

import com.oraclechain.pocketvkt.base.BaseView;
import com.oraclechain.pocketvkt.bean.PelistBean;

import java.util.List;

/**
 * Created by pocketEos on 2017/12/26.
 * 获取friendslist
 */
public interface PelistView extends BaseView {

    void getListDataHttp(List<PelistBean.DataBean> pelistBean);

    void getDataHttpFail(String msg);
}
