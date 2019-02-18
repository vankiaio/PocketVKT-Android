package com.vankiachain.pocketvkt.modules.friendslist.pelist;

import com.vankiachain.pocketvkt.base.BaseView;
import com.vankiachain.pocketvkt.bean.PelistBean;

import java.util.List;

/**
 * Created by pocketVkt on 2017/12/26.
 * 获取friendslist
 */
public interface PelistView extends BaseView {

    void getListDataHttp(List<PelistBean.DataBean> pelistBean);

    void getDataHttpFail(String msg);
}
