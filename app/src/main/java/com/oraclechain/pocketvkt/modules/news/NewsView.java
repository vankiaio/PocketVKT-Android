package com.oraclechain.pocketvkt.modules.news;

import com.oraclechain.pocketvkt.base.BaseView;
import com.oraclechain.pocketvkt.bean.CoinBean;
import com.oraclechain.pocketvkt.bean.NewsBean;

import java.util.List;

/**
 * Created by pocketVkt on 2017/12/26.
 * 获取friendslist
 */

public interface NewsView extends BaseView {

    void getNewsDataHttp(List<NewsBean.DataBean> newsBean);

    void getBannerDataHttp(List<NewsBean.DataBean> newsBean);

    void getCoinTypeDataHttp(List<CoinBean.DataBean> coinBeen);

    void getDataHttpFail(String msg);
}
