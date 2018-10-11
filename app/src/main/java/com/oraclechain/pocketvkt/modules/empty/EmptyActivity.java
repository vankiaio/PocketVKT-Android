package com.oraclechain.pocketvkt.modules.empty;

import android.os.Bundle;

import com.oraclechain.pocketvkt.R;
import com.oraclechain.pocketvkt.base.BaseAcitvity;
import com.oraclechain.pocketvkt.modules.normalvp.NormalPresenter;
import com.oraclechain.pocketvkt.modules.normalvp.NormalView;

public class EmptyActivity extends BaseAcitvity<NormalView, NormalPresenter> implements NormalView {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_empty;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setCenterTitle("红包");
    }

    @Override
    protected void initData() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    public NormalPresenter initPresenter() {
        return new NormalPresenter();
    }
}
