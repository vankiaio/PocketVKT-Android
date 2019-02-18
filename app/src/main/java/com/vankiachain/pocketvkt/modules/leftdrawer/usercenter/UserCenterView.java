package com.vankiachain.pocketvkt.modules.leftdrawer.usercenter;

import com.vankiachain.pocketvkt.base.BaseView;
import com.vankiachain.pocketvkt.bean.UpdataPhotoBean;

/**
 * Created by pocketVkt on 2018/1/18.
 */

public interface UserCenterView extends BaseView {

    void headImgUploadaDataHttp(UpdataPhotoBean updataPhotoBean);


    void getDataHttpFail(String msg);
}
