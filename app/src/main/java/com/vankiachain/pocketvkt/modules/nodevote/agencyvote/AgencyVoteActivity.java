package com.vankiachain.pocketvkt.modules.nodevote.agencyvote;

import android.os.Bundle;

import com.vankiachain.pocketvkt.R;
import com.vankiachain.pocketvkt.base.BaseActivity;
import com.vankiachain.pocketvkt.modules.normalvp.NormalPresenter;
import com.vankiachain.pocketvkt.modules.normalvp.NormalView;

/**
 * 代理投票
 */
public class AgencyVoteActivity extends BaseActivity<NormalView, NormalPresenter> implements NormalView {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_agency_vote;
    }

    @Override
    public NormalPresenter initPresenter() {
        return new NormalPresenter();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setCenterTitle(getString(R.string.go_entrust));
    }

    @Override
    protected void initData() {

    }

    @Override
    public void initEvent() {

    }
}
