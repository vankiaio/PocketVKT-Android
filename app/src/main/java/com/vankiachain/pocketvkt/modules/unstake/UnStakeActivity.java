package com.vankiachain.pocketvkt.modules.unstake;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.vankiachain.pocketvkt.R;
import com.vankiachain.pocketvkt.app.MyApplication;
import com.vankiachain.pocketvkt.base.BaseActivity;
import com.vankiachain.pocketvkt.bean.AccountDetailsBean;
import com.vankiachain.pocketvkt.bean.UnstakeBean;
import com.vankiachain.pocketvkt.blockchain.PushDatamanger;
import com.vankiachain.pocketvkt.utils.BigDecimalUtil;
import com.vankiachain.pocketvkt.utils.KeyBoardUtil;
import com.vankiachain.pocketvkt.utils.PasswordToKeyUtils;
import com.vankiachain.pocketvkt.utils.RegexUtil;
import com.vankiachain.pocketvkt.utils.StringUtils;
import com.vankiachain.pocketvkt.view.dialog.passworddialog.PasswordCallback;
import com.vankiachain.pocketvkt.view.dialog.passworddialog.PasswordDialog;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.OnClick;

public class UnStakeActivity extends BaseActivity<UnStakeView, UnStakePresenter> implements UnStakeView {


    @BindView(R.id.unstark_cpu)
    TextView mUnstarkCpu;
    @BindView(R.id.unstark_net)
    TextView mUnstarkNet;
    @BindView(R.id.go_unstark)
    Button mGoUnstark;
    private String cpu, net;
    private String lowStake = "1";//最低剩余抵押金额

    @Override
    protected int getLayoutId() {
        return R.layout.activity_un_stake;
    }

    @Override
    public UnStakePresenter initPresenter() {
        return new UnStakePresenter(this);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setCenterTitle(getString(R.string.stake_vkt));
    }

    @Override
    protected void initData() {
        showProgress();
        presenter.getAccountVoteData(getIntent().getStringExtra("account"));
    }

    @Override
    public void initEvent() {

    }


    @Override
    public void getAccountDetailsDataHttp(AccountDetailsBean accountDetailsBean) {
        hideProgress();
        if (BigDecimalUtil.greaterThan(BigDecimalUtil.toBigDecimal(lowStake), new BigDecimal(RegexUtil.subZeroAndDot(accountDetailsBean.getVkt_cpu_weight())))) {//已质押的VKT数量小于最低质押数量
            mGoUnstark.setClickable(false);
            mGoUnstark.setText(R.string.dont_unstake);
            mGoUnstark.setBackgroundColor(getResources().getColor(R.color.gray_color));
            mUnstarkCpu.setText("0 VKT / " + RegexUtil.subZeroAndDot(accountDetailsBean.getVkt_cpu_weight()) + " VKT ");
            mUnstarkNet.setText("0 VKT / " + RegexUtil.subZeroAndDot(accountDetailsBean.getVkt_net_weight()) + " VKT");
            return;
        } else {
            mGoUnstark.setBackgroundColor(getResources().getColor(R.color.blue_button));
            mGoUnstark.setClickable(true);
            mGoUnstark.setText(R.string.sure_stake);
            cpu = BigDecimalUtil.minus(new BigDecimal(RegexUtil.subZeroAndDot(accountDetailsBean.getVkt_cpu_weight())), new BigDecimal(lowStake), 4) + "";
            net = BigDecimalUtil.minus(new BigDecimal(RegexUtil.subZeroAndDot(accountDetailsBean.getVkt_net_weight())), new BigDecimal(lowStake), 4) + "";
            mUnstarkCpu.setText(cpu + " VKT / " + RegexUtil.subZeroAndDot(accountDetailsBean.getVkt_cpu_weight()) + " VKT");
            mUnstarkNet.setText(net + " VKT / " + RegexUtil.subZeroAndDot(accountDetailsBean.getVkt_net_weight()) + " VKT");
        }

    }

    @Override
    public void getDataHttpFail(String msg) {
        hideProgress();
        toast(msg);
    }

    @OnClick(R.id.go_unstark)
    public void onViewClicked() {
        if (KeyBoardUtil.isSoftInputShow(this)) {
            KeyBoardUtil.getInstance(this).hide();
        }
        UnstakeBean unstakeBean = new UnstakeBean();
        unstakeBean.setFrom(getIntent().getStringExtra("account"));
        unstakeBean.setReceiver(getIntent().getStringExtra("account"));
        unstakeBean.setUnstake_cpu_quantity(StringUtils.addZero(cpu) + " VKT");
        unstakeBean.setUnstake_net_quantity(StringUtils.addZero(net) + " VKT");
        PasswordDialog mPasswordDialog = new PasswordDialog(this, new PasswordCallback() {
            @Override
            public void sure(String password) {
                if (MyApplication.getInstance().getUserBean().getWallet_shapwd().equals(PasswordToKeyUtils.shaCheck(password))) {

                    new PushDatamanger(UnStakeActivity.this, password, new PushDatamanger.Callback() {
                        @Override
                        public void getResult(String result) {
                            if (result.contains("transaction_id")) {
                                toast(getString(R.string.stake_success));
                                finish();
                            } else {
                                toast(getString(R.string.stake_fail));
                            }
                        }
                    }).pushAction("vktio", "undelegatebw",
                            new Gson().toJson(unstakeBean), getIntent().getStringExtra("account"));
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
    }
}
