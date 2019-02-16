package com.oraclechain.pocketvkt.modules.nodevote.gonodevote;

import com.oraclechain.pocketvkt.base.BaseView;
import com.oraclechain.pocketvkt.bean.ResultNodeListBean;

/**
 * Created by pocketVkt on 2017/12/26.
 */
public interface GoNodeVoteView extends BaseView {

    void getNodeListDataHttp(ResultNodeListBean resultNodeListBean);

    void getDataHttpFail(String msg);
}
