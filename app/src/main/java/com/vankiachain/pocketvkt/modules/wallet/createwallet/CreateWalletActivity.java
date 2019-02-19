package com.vankiachain.pocketvkt.modules.wallet.createwallet;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.vankiachain.pocketvkt.R;
import com.vankiachain.pocketvkt.app.ActivityUtils;
import com.vankiachain.pocketvkt.app.MyApplication;
import com.vankiachain.pocketvkt.base.BaseActivity;
import com.vankiachain.pocketvkt.bean.UserBean;
import com.vankiachain.pocketvkt.gen.UserBeanDao;
import com.vankiachain.pocketvkt.modules.account.createaccount.CreateAccountActivity;
import com.vankiachain.pocketvkt.modules.normalvp.NormalPresenter;
import com.vankiachain.pocketvkt.modules.normalvp.NormalView;
import com.vankiachain.pocketvkt.modules.wallet.importwallet.ImportWalletActivity;
import com.vankiachain.pocketvkt.utils.EncryptUtil;
import com.vankiachain.pocketvkt.utils.PasswordToKeyUtils;
import com.vankiachain.pocketvkt.utils.Utils;
import com.vankiachain.pocketvkt.view.ClearEditText;

import butterknife.BindView;
import butterknife.OnClick;

public class CreateWalletActivity extends BaseActivity<NormalView, NormalPresenter> implements NormalView {


    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.password)
    ClearEditText mPassword;
    @BindView(R.id.confirm_password)
    ClearEditText mConfirmPassword;
    @BindView(R.id.create_wallet)
    Button mCreateWallet;
    @BindView(R.id.go_import_wallet)
    TextView mGoImportWallet;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_creat_wallet;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setCenterTitle(getString(R.string.creat_wallet));
    }

    @Override
    protected void initData() {

    }

    @Override
    public void initEvent() {
        mGoImportWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.next(CreateWalletActivity.this, ImportWalletActivity.class);
            }
        });
    }

    @Override
    public NormalPresenter initPresenter() {
        return new NormalPresenter();
    }


    @OnClick(R.id.create_wallet)
    public void onViewClicked() {
        if (TextUtils.isEmpty(mPassword.getText().toString()) || TextUtils.isEmpty(mConfirmPassword.getText().toString())) {
            toast(getString(R.string.input_pwd_toast));
        } else if (mPassword.getText().toString() != null && mConfirmPassword.getText().toString() != null && mConfirmPassword.getText().toString().equals(mPassword.getText().toString())) {
            UserBean userBean = MyApplication.getDaoInstant().getUserBeanDao().queryBuilder().where(UserBeanDao.Properties.Wallet_phone.eq(Utils.getSpUtils().getString("firstUser"))).build().unique();
            if (userBean != null) {
                String randomString = EncryptUtil.getRandomString(32);
                userBean.setWallet_shapwd(PasswordToKeyUtils.shaEncrypt(randomString+mPassword.getText().toString().trim()));
                MyApplication.getDaoInstant().getUserBeanDao().update(userBean);
                MyApplication.getInstance().getUserBean().setWallet_shapwd(PasswordToKeyUtils.shaEncrypt(randomString+mPassword.getText().toString().trim()));
            }
            Bundle bundle = new Bundle();
            bundle.putInt("type", 1);
            ActivityUtils.next(CreateWalletActivity.this, CreateAccountActivity.class, bundle);
        } else {
            toast(getString(R.string.two_pwd));
        }
    }


}
