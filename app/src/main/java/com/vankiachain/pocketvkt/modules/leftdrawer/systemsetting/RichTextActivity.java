package com.vankiachain.pocketvkt.modules.leftdrawer.systemsetting;

import android.os.Bundle;
import android.widget.TextView;

import com.vankiachain.pocketvkt.R;
import com.vankiachain.pocketvkt.base.BaseActivity;
import com.vankiachain.pocketvkt.modules.normalvp.NormalPresenter;
import com.vankiachain.pocketvkt.modules.normalvp.NormalView;
import com.zzhoujay.richtext.ImageHolder;
import com.zzhoujay.richtext.RichText;

import butterknife.BindView;

public class RichTextActivity extends BaseActivity<NormalView, NormalPresenter> implements NormalView {

    @BindView(R.id.details)
    TextView mDetails;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rich_text;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setCenterTitle(getIntent().getStringExtra("title"));
            RichText.fromHtml(getIntent().getStringExtra("details")) // 数据源
                    .autoFix(true) // 是否自动修复，默认true
                    .autoPlay(true) // gif图片是否自动播放
                    .scaleType(ImageHolder.ScaleType.CENTER_CROP) // 图片缩放方式
                    .size(ImageHolder.MATCH_PARENT, ImageHolder.WRAP_CONTENT) // 图片占位区域的宽高
                    .error(R.mipmap.ic_launcher) // 设置加载失败的错误图
                    .bind(RichTextActivity.this) // 绑定richText对象到某个object上，方便后面的清理
                    .into(mDetails); // 设置目标TextView

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RichText.recycle();
        RichText.clear(RichTextActivity.this);
    }
}
