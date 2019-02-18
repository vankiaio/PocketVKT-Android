package com.vankiachain.pocketvkt.view.textwatcher;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;

import com.vankiachain.pocketvkt.utils.BigDecimalUtil;
import com.vankiachain.pocketvkt.utils.ToastUtils;

/**
 * Created by pocketVkt on 2018/2/27.
 */

public class NodeVoteTextWatcher implements TextWatcher {
    private EditText editText;
    private SeekBar mSeekBar;
    private String vktToatalAmount;


    public NodeVoteTextWatcher(EditText et, SeekBar seekBar, String vktToatalAmount) {
        editText = et;
        mSeekBar = seekBar;
        mSeekBar.setFocusable(true);
        mSeekBar.setFocusableInTouchMode(true);
        this.vktToatalAmount = vktToatalAmount;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }


    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (BigDecimalUtil.greaterThan(BigDecimalUtil.toBigDecimal(s.toString()), BigDecimalUtil.toBigDecimal(vktToatalAmount))) {
            ToastUtils.showLongToast("您最多可输入" + vktToatalAmount + "VKT");
            editText.setText(vktToatalAmount);
            editText.setSelection(vktToatalAmount.length()); //光标移到最后
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
       /* if (!TextUtils.isEmpty(s.toString())) {
            for (int i = 0; i < mAccountVoteHistoryBeans.size(); i++) {
                mAccountVoteHistoryBeans.get(i).setNumber(BigDecimalUtil.multiply(new BigDecimal(Double.parseDouble(s.toString()) * 10000), new BigDecimal(voteWeight), 4) + "");
            }
            mCommonAdapter.notifyDataSetChanged();
        }*/
    }


}