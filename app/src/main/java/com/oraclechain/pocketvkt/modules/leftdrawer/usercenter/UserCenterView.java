package com.oraclechain.pocketvkt.modules.leftdrawer.usercenter;

import com.oraclechain.pocketvkt.base.BaseView;
import com.oraclechain.pocketvkt.bean.UpdataPhotoBean;

/**
 * Created by pocketEos on 2018/1/18.
 */

public interface UserCenterView extends BaseView {

    void headImgUploadaDataHttp(UpdataPhotoBean updataPhotoBean);


    void getDataHttpFail(String msg);
}
