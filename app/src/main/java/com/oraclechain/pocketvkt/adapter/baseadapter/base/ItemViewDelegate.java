package com.oraclechain.pocketvkt.adapter.baseadapter.base;


/**
 * Created by pocketVkt on 2017/11/23.
 */
public interface ItemViewDelegate<T>
{

    int getItemViewLayoutId();

    boolean isForViewType(T item, int position);

    void convert(ViewHolder holder, T t, int position);

}
