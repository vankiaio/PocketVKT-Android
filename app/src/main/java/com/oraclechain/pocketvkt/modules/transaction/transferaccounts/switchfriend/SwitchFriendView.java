package com.oraclechain.pocketvkt.modules.transaction.transferaccounts.switchfriend;

import com.oraclechain.pocketvkt.base.BaseView;
import com.oraclechain.pocketvkt.bean.FriendsListInfoBean;

import java.util.List;

/**
 * Created by pocketVkt on 2017/12/26.
 * 获取friendslist
 */

public interface SwitchFriendView extends BaseView {

    void getDataHttp(List<FriendsListInfoBean> dataBeanList);


    void getDataHttpFail(String msg);
}
