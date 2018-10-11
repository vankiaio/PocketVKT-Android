package com.oraclechain.pocketvkt.modules.account.backupaccount;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.oraclechain.pocketvkt.R;
import com.oraclechain.pocketvkt.app.ActivityUtils;
import com.oraclechain.pocketvkt.app.AppManager;
import com.oraclechain.pocketvkt.app.MyApplication;
import com.oraclechain.pocketvkt.base.BaseAcitvity;
import com.oraclechain.pocketvkt.bean.AccountInfoBean;
import com.oraclechain.pocketvkt.modules.blackbox.BlackBoxMainActivity;
import com.oraclechain.pocketvkt.modules.main.MainActivity;
import com.oraclechain.pocketvkt.modules.normalvp.NormalPresenter;
import com.oraclechain.pocketvkt.modules.normalvp.NormalView;
import com.oraclechain.pocketvkt.utils.EncryptUtil;
import com.oraclechain.pocketvkt.utils.PasswordToKeyUtils;
import com.oraclechain.pocketvkt.utils.Utils;
import com.oraclechain.pocketvkt.view.dialog.passworddialog.PasswordCallback;
import com.oraclechain.pocketvkt.view.dialog.passworddialog.PasswordDialog;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BackUpKeyActivity extends BaseAcitvity<NormalView, NormalPresenter> implements NormalView {


    @BindView(R.id.switch_view)
    Switch mSwitchView;
    @BindView(R.id.go_home)
    Button mGoHome;
    @BindView(R.id.details)
    TextView mDetails;
    AccountInfoBean mAccountInfoBean = null;
    @BindView(R.id.desc)
    TextView mDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_back_up_key;
    }

    @Override
    public NormalPresenter initPresenter() {
        return new NormalPresenter();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);//当前页面防截屏录屏
        setCenterTitle(getString(R.string.pra_backup));
    }

    @Override
    protected void initData() {
        mAccountInfoBean = getIntent().getParcelableExtra("account");
    }

    @Override
    public void initEvent() {
        mSwitchView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    PasswordDialog mPasswordDialog = new PasswordDialog(BackUpKeyActivity.this, new PasswordCallback() {
                        @Override
                        public void sure(String password) {
                            if (MyApplication.getInstance().getUserBean().getWallet_shapwd().equals(PasswordToKeyUtils.shaCheck(password))) {
                                try {
                                    mDetails.setText("OWNKEY:\n" + EncryptUtil.getDecryptString(mAccountInfoBean.getAccount_owner_private_key(), password)
                                            + "\nACTIVEKEY：\n" + EncryptUtil.getDecryptString(mAccountInfoBean.getAccount_active_private_key(), password));
                                } catch (NoSuchAlgorithmException e) {
                                    e.printStackTrace();
                                } catch (InvalidKeySpecException e) {
                                    e.printStackTrace();
                                }
                                mDetails.setVisibility(View.VISIBLE);
                                mSwitchView.setVisibility(View.GONE);
                                mDesc.setVisibility(View.GONE);
                            } else {
                                toast(getResources().getString(R.string.password_error));
                            }
                        }

                        @Override
                        public void cancle() {
                        }
                    });
                    mPasswordDialog.setCancelable(true);
                    mPasswordDialog.show();
                } else {
                    mDetails.setVisibility(View.GONE);
                    mSwitchView.setVisibility(View.VISIBLE);
                    mDesc.setVisibility(View.VISIBLE);
                }
            }
        });

        mGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.getSpUtils().getString("loginmode").equals("phone")) {
                    ActivityUtils.next(BackUpKeyActivity.this, MainActivity.class, true);
                } else {
                    ActivityUtils.next(BackUpKeyActivity.this, BlackBoxMainActivity.class, true);
                }
                AppManager.getAppManager().finishAllActivity();
            }
        });
    }
}
