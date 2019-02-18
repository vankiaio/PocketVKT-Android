package com.vankiachain.pocketvkt.modules.nodevote.gonodevote;

import com.vankiachain.pocketvkt.base.BaseView;
import com.vankiachain.pocketvkt.bean.ResultNodeListBean;

/**
 * Created by pocketVkt on 2017/12/26.
 */
public interface GoNodeVoteView extends BaseView {

    void getNodeListDataHttp(ResultNodeListBean resultNodeListBean);

    void getDataHttpFail(String msg);
}
