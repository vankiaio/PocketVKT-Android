package com.vankiachain.pocketvkt.modules.leftdrawer.usercenter;

import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.utils.OkLogger;
import com.vankiachain.pocketvkt.app.MyApplication;
import com.vankiachain.pocketvkt.base.BasePresent;
import com.vankiachain.pocketvkt.base.BaseUrl;
import com.vankiachain.pocketvkt.bean.UpdataPhotoBean;
import com.vankiachain.pocketvkt.utils.JsonUtil;
import com.vankiachain.pocketvkt.utils.ToastUtils;

import java.io.File;

/**
 * Created by pocketVkt on 2018/1/18.
 */

public class UserCenterPresenter extends BasePresent<UserCenterView> {
    private Context mContext;

    public UserCenterPresenter(Context context) {
        this.mContext = context;
    }

    public void headImgUploadaData(String imagePath) {
        OkGo.<String>post(BaseUrl.HTTP_headImgUploada)
                .params("uid", MyApplication.getInstance().getUserBean().getWallet_uid())
                .params("file", new File(imagePath))
                .tag(mContext)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        OkLogger.i(response.body().toString());
                        UpdataPhotoBean updataPhotoBean = (UpdataPhotoBean) JsonUtil.parseStringToBean(response.body(), UpdataPhotoBean.class);
                        if (updataPhotoBean != null && updataPhotoBean.getCode().equals("0")) {
                            view.headImgUploadaDataHttp(updataPhotoBean);
                        } else {
                            ToastUtils.showShortToast(updataPhotoBean.getMessage());
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        view.getDataHttpFail(response.message());
                    }
                });
    }

}

