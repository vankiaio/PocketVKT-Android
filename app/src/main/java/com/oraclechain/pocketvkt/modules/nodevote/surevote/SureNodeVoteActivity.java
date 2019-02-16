package com.oraclechain.pocketvkt.modules.nodevote.surevote;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.oraclechain.pocketvkt.R;
import com.oraclechain.pocketvkt.adapter.AdapterManger;
import com.oraclechain.pocketvkt.adapter.baseadapter.CommonAdapter;
import com.oraclechain.pocketvkt.app.ActivityUtils;
import com.oraclechain.pocketvkt.app.AppManager;
import com.oraclechain.pocketvkt.app.MyApplication;
import com.oraclechain.pocketvkt.base.BaseAcitvity;
import com.oraclechain.pocketvkt.bean.AccountVoteHistoryBean;
import com.oraclechain.pocketvkt.bean.DelegatebwMessageBean;
import com.oraclechain.pocketvkt.bean.RegproxyMessageBean;
import com.oraclechain.pocketvkt.bean.ResponseBean;
import com.oraclechain.pocketvkt.bean.ResultNodeListBean;
import com.oraclechain.pocketvkt.bean.ResultTableRowBean;
import com.oraclechain.pocketvkt.bean.ResultVoteWeightBean;
import com.oraclechain.pocketvkt.bean.VoteproducerMessageBean;
import com.oraclechain.pocketvkt.blockchain.PushDatamanger;
import com.oraclechain.pocketvkt.modules.leftdrawer.systemsetting.RichTextActivity;
import com.oraclechain.pocketvkt.modules.nodevote.gonodevote.GoNodeVoteActivity;
import com.oraclechain.pocketvkt.utils.BigDecimalUtil;
import com.oraclechain.pocketvkt.utils.FilesUtils;
import com.oraclechain.pocketvkt.utils.KeyBoardUtil;
import com.oraclechain.pocketvkt.utils.PasswordToKeyUtils;
import com.oraclechain.pocketvkt.utils.StringUtils;
import com.oraclechain.pocketvkt.utils.Utils;
import com.oraclechain.pocketvkt.view.RecycleViewDivider;
import com.oraclechain.pocketvkt.view.dialog.passworddialog.PasswordCallback;
import com.oraclechain.pocketvkt.view.dialog.passworddialog.PasswordDialog;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * The type Sure vote activity.
 */
public class SureNodeVoteActivity extends BaseAcitvity<SureNodeVoteView, SureNodeVotePresenter> implements SureNodeVoteView {

    SeekBar mMseekbar;
    EditText mVoteVktAmount;
    TextView mInputNumber;
    TextView vote_amount;
    TextView mBelocked;
    @BindView(R.id.vote_details_recycleview)
    XRecyclerView mVoteDetailsRecycleview;
    @BindView(R.id.sure_vote)
    TextView mSureVote;
    String vktAmount, stakeAmount;
    String voteWeight = "0";
    String mpassword = "";
    private ArrayList<ResultNodeListBean.DataBeanX.DataBean> mSelectNode = new ArrayList<>();
    private List<AccountVoteHistoryBean> mAccountVoteHistoryBeans = new ArrayList<>();
    private CommonAdapter mCommonAdapter;
    private String account;
    private ResultTableRowBean mResultTableRowBean = new ResultTableRowBean();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sure_vote;
    }

    @Override
    public SureNodeVotePresenter initPresenter() {
        return new SureNodeVotePresenter(this);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setCenterTitle(getString(R.string.node_vote));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        layoutManager.setSmoothScrollbarEnabled(true);
        mVoteDetailsRecycleview.setLayoutManager(layoutManager);
        mVoteDetailsRecycleview.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL, 1, getResources().getColor(R.color.white_line)));
        mVoteDetailsRecycleview.setPullRefreshEnabled(false);
        mVoteDetailsRecycleview.setLoadingMoreEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View contentView = mInflater.inflate(R.layout.sure_nodevote_header, null);
        mMseekbar = contentView.findViewById(R.id.mseekbar);
        mMseekbar.setEnabled(true);
        mMseekbar.setFocusable(true);
        mMseekbar.setFocusableInTouchMode(true);
        mVoteVktAmount = contentView.findViewById(R.id.vote_vkt_amount);
        mInputNumber = contentView.findViewById(R.id.input_number);
        mBelocked = contentView.findViewById(R.id.be_locked);
        vote_amount = contentView.findViewById(R.id.vote_amount);
        mVoteDetailsRecycleview.addHeaderView(contentView);
        vktAmount = getIntent().getStringExtra("amount");
        stakeAmount = getIntent().getStringExtra("stakedAmount");


        mVoteVktAmount.setText("0");
        vote_amount.setText(stakeAmount+" VKT");
    }

    @Override
    protected void initData() {
        account = getIntent().getStringExtra("account");

        presenter.getNowVoteWeightData();

        presenter.getAccountVoteData(account);

        mSelectNode = getIntent().getParcelableArrayListExtra("select");

        mCommonAdapter = AdapterManger.getAccountVoteAdapter(this, mAccountVoteHistoryBeans);
        mVoteDetailsRecycleview.setAdapter(mCommonAdapter);
    }

    @Override
    public void initEvent() {

        mMseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mVoteVktAmount.setFocusable(false);
                mVoteVktAmount.setFocusableInTouchMode(false);
                mVoteVktAmount.setClickable(false);
                if (KeyBoardUtil.isSoftInputShow(SureNodeVoteActivity.this)) {
                    KeyBoardUtil.getInstance(SureNodeVoteActivity.this).hide();
                }
                BigDecimal bigDecimal = BigDecimalUtil.multiply(BigDecimalUtil.divide(new BigDecimal(progress), new BigDecimal(100), 2), new BigDecimal(vktAmount), 0);
                mVoteVktAmount.setText(bigDecimal + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                for (int i = 0; i < mAccountVoteHistoryBeans.size(); i++) {
                    mAccountVoteHistoryBeans.get(i).setNumber(BigDecimalUtil.multiply(BigDecimalUtil.multiply(BigDecimalUtil.add(new BigDecimal(mVoteVktAmount.getText().toString()), new BigDecimal(stakeAmount), 4), new BigDecimal("10000"), 4), new BigDecimal(voteWeight), 4) + "");
                }
                mCommonAdapter.notifyDataSetChanged();
            }
        });
        mInputNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVoteVktAmount.setFocusable(true);
                mVoteVktAmount.setFocusableInTouchMode(true);
                mVoteVktAmount.setClickable(true);
                mVoteVktAmount.requestFocus();
                mVoteVktAmount.setSelection(mVoteVktAmount.getText().toString().length());//将光标移至文字末尾
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    public void run() {
                        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                }, 200); // 0.2秒后自动弹出软键盘
            }
        });

//        mVoteVktAmount.addTextChangedListener(new NodeVoteTextWatcher(mVoteVktAmount, mMseekbar, "10"));


        mBelocked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("details", FilesUtils.readAssetsTxt(SureNodeVoteActivity.this, "belocked_info"));
                bundle.putString("title", "锁定说明");
                ActivityUtils.next(SureNodeVoteActivity.this, RichTextActivity.class, bundle);
            }
        });
    }

    @OnClick(R.id.sure_vote)
    public void onViewClicked() {
        vktAmount = mVoteVktAmount.getText().toString().trim();
        if (!mResultTableRowBean.getCode().equals("0") & mVoteVktAmount.getText().toString().trim().equals("0") && !BigDecimalUtil.greaterThan(new BigDecimal(stakeAmount), new BigDecimal(0))) {
            toast(getString(R.string.vkt_zero_toast));
            return;
        }
        PasswordDialog mPasswordDialog = new PasswordDialog(SureNodeVoteActivity.this, new PasswordCallback() {
            @Override
            public void sure(final String password) {
                if (MyApplication.getInstance().getUserBean().getWallet_shapwd().equals(PasswordToKeyUtils.shaCheck(password))) {
                    showProgress();
                    mpassword = password;
                    DelegatebwMessageBean delegatebwMessageBean = new DelegatebwMessageBean();
                    delegatebwMessageBean.setFrom(account);
                    delegatebwMessageBean.setReceiver(account);
                    BigDecimal bigIntegerAmount = BigDecimalUtil.multiply(BigDecimalUtil.toBigDecimal(mVoteVktAmount.getText().toString().trim()), new BigDecimal(1), 4);
                    delegatebwMessageBean.setStake_cpu_quantity(StringUtils.addZero(BigDecimalUtil.divide(bigIntegerAmount, new BigDecimal(2), 4) + "") + " VKT");
                    delegatebwMessageBean.setStake_net_quantity(StringUtils.addZero(BigDecimalUtil.divide(bigIntegerAmount, new BigDecimal(2), 4) + "") + " VKT");

                    VoteproducerMessageBean voteproducerMessageBean = new VoteproducerMessageBean();
                    voteproducerMessageBean.setVoter(account);
                    String[] producers = new String[mAccountVoteHistoryBeans.size()];
                    for (int i = 0; i < mAccountVoteHistoryBeans.size(); i++) {
                        producers[i] = mAccountVoteHistoryBeans.get(i).getProducers();
                    }
                    voteproducerMessageBean.setProducers(Arrays.asList(StringUtils.stringSort(producers)));
                    voteproducerMessageBean.setProxy("");

                    if (!mResultTableRowBean.getCode().equals("0") && BigDecimalUtil.greaterThan(new BigDecimal(vktAmount), new BigDecimal(0))) {//未注册到投票系统 只要余额大于0都要进行质押 投票系统 质押 投票
                        new PushDatamanger(SureNodeVoteActivity.this, password, new PushDatamanger.Callback() {
                            @Override
                            public void getResult(String result) {
                                new PushDatamanger(SureNodeVoteActivity.this, password, new PushDatamanger.Callback() {
                                    @Override
                                    public void getResult(String result) {

                                        if (result.contains("transaction_id")) {
                                            new PushDatamanger(SureNodeVoteActivity.this, password, new PushDatamanger.Callback() {
                                                @Override
                                                public void getResult(String result) {
                                                    if (result.contains("transaction_id")) {
                                                        if (Utils.getSpUtils().getString("loginmode", "").equals("phone")) {
                                                            presenter.getcomplete_taskData();//通知完成投票
                                                        }else {
                                                            toast(getString(R.string.node_vote_success));
                                                            finish();
                                                            AppManager.getAppManager().finishActivity(GoNodeVoteActivity.class);
                                                        }
                                                    }
                                                }
                                            }).pushAction("vktio", "voteproducer",
                                                    new Gson().toJson(voteproducerMessageBean), account);
                                        }
                                    }
                                }).pushAction("vktio", "delegatebw",
                                        new Gson().toJson(delegatebwMessageBean), account);
                            }
                        }).pushAction("vktio", "regproxy", new Gson().toJson(new RegproxyMessageBean(account, "1")), account);
                    } else if (!mResultTableRowBean.getCode().equals("0") && BigDecimalUtil.greaterThan(new BigDecimal(stakeAmount), new BigDecimal(0)) && !BigDecimalUtil.greaterThan(new BigDecimal(vktAmount), new BigDecimal(0))) {//未注册到投票系统 质押资产！=0 余额=0 先注册到投票系统再进行投票
                        new PushDatamanger(SureNodeVoteActivity.this, password, new PushDatamanger.Callback() {
                            @Override
                            public void getResult(String result) {
                                if (result.contains("transaction_id")) {
                                    new PushDatamanger(SureNodeVoteActivity.this, password, new PushDatamanger.Callback() {
                                        @Override
                                        public void getResult(String result) {
                                            if (result.contains("transaction_id")) {
                                                if (Utils.getSpUtils().getString("loginmode", "").equals("phone")) {
                                                    presenter.getcomplete_taskData();//通知完成投票
                                                }else {
                                                    toast(getString(R.string.node_vote_success));
                                                    finish();
                                                    AppManager.getAppManager().finishActivity(GoNodeVoteActivity.class);
                                                }
                                            }
                                        }
                                    }).pushAction("vktio", "voteproducer",
                                            new Gson().toJson(voteproducerMessageBean), account);
                                }
                            }
                        }).pushAction("vktio", "regproxy", new Gson().toJson(new RegproxyMessageBean(account, "1")), account);
                    } else if (!BigDecimalUtil.greaterThan(new BigDecimal(stakeAmount), new BigDecimal(0)) && !BigDecimalUtil.greaterThan(new BigDecimal(vktAmount), new BigDecimal(0))) {//余额为0 ，质押为0
                        toast(getString(R.string.vkt_zero_toast));
                    } else if (mResultTableRowBean.getCode().equals("0") && !BigDecimalUtil.greaterThan(new BigDecimal(vktAmount), new BigDecimal(0)) && BigDecimalUtil.greaterThan(new BigDecimal(stakeAmount), new BigDecimal(0))) {//检查余额为小于等于0，质押大于0，不用质押直接投票,
                        new PushDatamanger(SureNodeVoteActivity.this, password, new PushDatamanger.Callback() {
                            @Override
                            public void getResult(String result) {
                                if (result.contains("transaction_id")) {
                                    if (Utils.getSpUtils().getString("loginmode", "").equals("phone")) {
                                        presenter.getcomplete_taskData();//通知完成投票
                                    }else
                                    toast(getString(R.string.node_vote_success));
                                    finish();
                                    AppManager.getAppManager().finishActivity(GoNodeVoteActivity.class);
                                }
                            }
                        }).pushAction("vktio", "voteproducer",
                                new Gson().toJson(voteproducerMessageBean), account);
                    } else if (mResultTableRowBean.getCode().equals("0") && BigDecimalUtil.greaterThan(new BigDecimal(vktAmount), new BigDecimal(0))) {//账户余额大于0，质押还能进行投票进行~
                        new PushDatamanger(SureNodeVoteActivity.this, password, new PushDatamanger.Callback() {
                            @Override
                            public void getResult(String result) {

                                if (result.contains("transaction_id")) {
                                    new PushDatamanger(SureNodeVoteActivity.this, password, new PushDatamanger.Callback() {
                                        @Override
                                        public void getResult(String result) {
                                            if (result.contains("transaction_id")) {
                                                if (Utils.getSpUtils().getString("loginmode", "").equals("phone")) {
                                                    presenter.getcomplete_taskData();//通知完成投票
                                                }else {
                                                    toast(getString(R.string.node_vote_success));
                                                    finish();
                                                    AppManager.getAppManager().finishActivity(GoNodeVoteActivity.class);
                                                }
                                            }
                                        }
                                    }).pushAction("vktio", "voteproducer",
                                            new Gson().toJson(voteproducerMessageBean), account);
                                }
                            }
                        }).pushAction("vktio", "delegatebw",
                                new Gson().toJson(delegatebwMessageBean), account);
                    }
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


    @Override
    public void getNowVoteWeightDataHttp(ResultVoteWeightBean resultVoteWeightBean) {
        if (resultVoteWeightBean.getCode().equals("0")) {
            voteWeight = resultVoteWeightBean.getData();
            for (ResultNodeListBean.DataBeanX.DataBean rowsBean : mSelectNode) {
                AccountVoteHistoryBean accountVoteHistoryBean = new AccountVoteHistoryBean();
                accountVoteHistoryBean.setProducers(rowsBean.getOwner());
                accountVoteHistoryBean.setNumber(BigDecimalUtil.multiply(new BigDecimal(Double.parseDouble(stakeAmount) * 10000), new BigDecimal(voteWeight), 4) + "");
                mAccountVoteHistoryBeans.add(accountVoteHistoryBean);
            }
            mCommonAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getAccountVoteDataHttp(ResultTableRowBean resultTableRowBean) {
        mResultTableRowBean = resultTableRowBean;
    }

    @Override
    public void postVoteTask(ResponseBean<String> data) {
        hideProgress();
        toast(getString(R.string.node_vote_success));
        finish();
        AppManager.getAppManager().finishActivity(GoNodeVoteActivity.class);
    }

    @Override
    public void getDataHttpFail(String msg) {
        hideProgress();
    }


}
