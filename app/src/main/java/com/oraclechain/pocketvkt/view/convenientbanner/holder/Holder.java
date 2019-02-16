package com.oraclechain.pocketvkt.view.convenientbanner.holder;

/**
 * Created by pocketVkt on 2017/12/7.
 * @param <T> 任何你指定的对象
 */

import android.content.Context;
import android.view.View;

public interface Holder<T>{
    View createView(Context context);
    void UpdateUI(Context context, int position, T data);
}