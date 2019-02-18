package com.vankiachain.pocketvkt.modules.friendslist.myfriendslist;

import com.vankiachain.pocketvkt.base.BaseView;
import com.vankiachain.pocketvkt.bean.User;

import java.util.ArrayList;

/**
 * Created by pocketVkt on 2017/12/26.
 * 获取friendslist
 */

public interface FriendsListView extends BaseView {

    void getDataHttp(ArrayList<User> mDataBeanArrayList);


    void getDataHttpFail(String msg);
}
