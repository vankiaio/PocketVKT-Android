package com.oraclechain.pocketvkt.modules.nodevote.surevote;

import com.oraclechain.pocketvkt.base.BaseView;
import com.oraclechain.pocketvkt.bean.ResponseBean;
import com.oraclechain.pocketvkt.bean.ResultTableRowBean;
import com.oraclechain.pocketvkt.bean.ResultVoteWeightBean;

/**
 * Created by pocketVkt on 2017/12/26.
 */
public interface SureNodeVoteView extends BaseView {

    void getNowVoteWeightDataHttp(ResultVoteWeightBean resultVoteWeightBean);

    void getAccountVoteDataHttp(ResultTableRowBean resultTableRowBean);

    void postVoteTask(ResponseBean<String> data);

    void getDataHttpFail(String msg);
}
