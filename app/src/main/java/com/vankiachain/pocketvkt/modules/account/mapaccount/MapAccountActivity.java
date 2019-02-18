package com.vankiachain.pocketvkt.modules.account.mapaccount;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;

import com.google.gson.Gson;
import com.vankiachain.pocketvkt.R;
import com.vankiachain.pocketvkt.app.ActivityUtils;
import com.vankiachain.pocketvkt.app.AppManager;
import com.vankiachain.pocketvkt.app.MyApplication;
import com.vankiachain.pocketvkt.base.BaseAcitvity;
import com.vankiachain.pocketvkt.bean.AccountInfoBean;
import com.vankiachain.pocketvkt.bean.GetAccountsBean;
import com.vankiachain.pocketvkt.bean.UserBean;
import com.vankiachain.pocketvkt.blockchain.cypto.ec.VktPrivateKey;
import com.vankiachain.pocketvkt.gen.UserBeanDao;
import com.vankiachain.pocketvkt.modules.blackbox.BlackBoxMainActivity;
import com.vankiachain.pocketvkt.modules.main.MainActivity;
import com.vankiachain.pocketvkt.utils.EncryptUtil;
import com.vankiachain.pocketvkt.utils.JsonUtil;
import com.vankiachain.pocketvkt.utils.PasswordToKeyUtils;
import com.vankiachain.pocketvkt.utils.Utils;
import com.vankiachain.pocketvkt.view.ClearEditText;
import com.vankiachain.pocketvkt.view.dialog.passworddialog.PasswordCallback;
import com.vankiachain.pocketvkt.view.dialog.passworddialog.PasswordDialog;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class MapAccountActivity extends BaseAcitvity<MapAccountView, MapAccountPresenter> implements MapAccountView {


    @BindView(R.id.owner_private_key)
    ClearEditText mOwnerPrivateKey;
    @BindView(R.id.import_number)
    Button mImportNumber;

    private String mAccount_owner_private_key, mAccount_active_private_key = null;
    private String mAccount_owner_public_key, mAccount_active_public_key = null;
    private String userPassword, accountName = null;
    private UserBean userBean = new UserBean();

    private VktPrivateKey mActiveKey;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_map_account;
    }

    @Override
    public MapAccountPresenter initPresenter() {
        return new MapAccountPresenter(this);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setCenterTitle(getString(R.string.map_account_title));
    }

    @Override
    protected void initData() {
        if (!Utils.getSpUtils().getString("loginmode").equals("blackbox")) {
            userBean = MyApplication.getDaoInstant().getUserBeanDao().queryBuilder().where(UserBeanDao.Properties.Wallet_phone.eq(Utils.getSpUtils().getString("firstUser"))).build().unique();
        } else {
            userBean = MyApplication.getDaoInstant().getUserBeanDao().queryBuilder().where(UserBeanDao.Properties.Wallet_name.eq(Utils.getSpUtils().getString("firstUser"))).build().unique();
        }
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void getBlackAccountDataHttp(GetAccountsBean getAccountsBean) {
        hideProgress();
        if (getAccountsBean.getCode().equals("0") && getAccountsBean.getData().getAccount_names().size() != 0) {
            accountName = getAccountsBean.getData().getAccount_names().get(0);
            if (userBean != null && MyApplication.getInstance().getUserBean().getAccount_info() != null && MyApplication.getInstance().getUserBean().getAccount_info().contains(accountName)) {
                toast(getString(R.string.import_two_account));
            } else {
                presenter.postVktAccountData(accountName, userBean.getWallet_uid());//只是通知，不以服务端返回结果作为查询依据
                toast(getString(R.string.map_account_success));
                ArrayList<AccountInfoBean> accountInfoBeanArrayList = new ArrayList<>();
                if (MyApplication.getInstance().getUserBean().getAccount_info() != null) {
                    accountInfoBeanArrayList = JsonUtil.parseJsonToArrayList(MyApplication.getInstance().getUserBean().getAccount_info(), AccountInfoBean.class);
                }
                AccountInfoBean accountInfoBean = new AccountInfoBean();
                accountInfoBean.setAccount_name(accountName);
                accountInfoBean.setAccount_img("");
                try {
                    accountInfoBean.setAccount_active_private_key(EncryptUtil.getEncryptString(mAccount_active_private_key, userPassword));
                    accountInfoBean.setAccount_owner_private_key(EncryptUtil.getEncryptString(mAccount_owner_private_key, userPassword));
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (InvalidKeySpecException e) {
                    e.printStackTrace();
                }
                accountInfoBean.setAccount_active_public_key(mAccount_active_public_key);
                accountInfoBean.setAccount_owner_public_key(mAccount_owner_public_key);
                accountInfoBean.setIs_privacy_policy("0");
                if (accountInfoBeanArrayList.size() == 0) {
                    accountInfoBean.setIs_main_account("1");
                    presenter.setMianAccountData(accountName);//只是通知，不以服务端返回结果作为查询依据
                } else {
                    accountInfoBean.setIs_main_account("0");
                }
                if (userBean != null) {
                    if (accountInfoBeanArrayList.size() == 0) {
                        userBean.setWallet_main_account(accountInfoBean.getAccount_name());
                        userBean.setWallet_main_account_img(accountInfoBean.getAccount_img());

                    }
                    accountInfoBeanArrayList.add(accountInfoBean);
                    userBean.setAccount_info(new Gson().toJson(accountInfoBeanArrayList));
                    MyApplication.getDaoInstant().getUserBeanDao().update(userBean);
                }
                AppManager.getAppManager().finishAllActivity();
                if (!Utils.getSpUtils().getString("loginmode").equals("blackbox")) {
                    ActivityUtils.next(MapAccountActivity.this, MainActivity.class, true);
                } else {
                    ActivityUtils.next(MapAccountActivity.this, BlackBoxMainActivity.class, true);
                }
            }
        } else if (getAccountsBean.getCode().equals("0") && getAccountsBean.getData().getAccount_names().size() == 0) {
            toast(getString(R.string.no_account));
        } else {
            toast(getAccountsBean.getMsg());
        }
    }

    @Override
    public void setMainAccountHttp() {

    }

    @Override
    public void getDataHttpFail(String msg) {
        hideProgress();
    }

    @Override
    public void postVktAccountDataHttp() {

    }

    @OnClick(R.id.import_number)
    public void onViewClicked() {

        if (!TextUtils.isEmpty(mOwnerPrivateKey.getText().toString())) {
            PasswordDialog dialog = new PasswordDialog(MapAccountActivity.this, new PasswordCallback() {
                @Override
                public void sure(String password) {
                    if (MyApplication.getInstance().getUserBean().getWallet_shapwd().equals(PasswordToKeyUtils.shaCheck(password))) {
                        userPassword = password;
                        showProgress();

                        try {
                            mActiveKey = new VktPrivateKey(mOwnerPrivateKey.getText().toString().trim());
                            mAccount_owner_public_key = mActiveKey.getPublicKey().toString();
                            mAccount_active_public_key = mActiveKey.getPublicKey().toString();
                            mAccount_active_private_key = mActiveKey.toString();
                            mAccount_owner_private_key = mActiveKey.toString();
                            presenter.getAccountInfoData(mAccount_active_public_key);
                        } catch (Exception e) {
                            e.printStackTrace();
                            hideProgress();
                            toast("私钥格式错误");
                        }

                    } else {
                        toast(getResources().getString(R.string.password_error));
                    }
                }

                @Override
                public void cancle() {
                }
            });
            dialog.setCancelable(true);
            dialog.show();
        } else {
            toast(getString(R.string.input_privatekey_toast));
        }
    }
}
