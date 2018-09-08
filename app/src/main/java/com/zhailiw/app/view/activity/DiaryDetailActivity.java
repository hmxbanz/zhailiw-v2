package com.zhailiw.app.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.luck.picture.lib.config.PictureConfig;
import com.lzy.ninegrid.NineGridView;
import com.zhailiw.app.R;
import com.zhailiw.app.loader.GlideImageLoaderForNineGridView;
import com.zhailiw.app.presenter.DiaryDetailPresenter;

public class DiaryDetailActivity extends BaseActivity implements View.OnClickListener {

    private DiaryDetailPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_detail);
        initViews();
        presenter = new DiaryDetailPresenter(this);
        presenter.init();
        NineGridView.setImageLoader(new GlideImageLoaderForNineGridView());
    }
    public static void StartActivity(Context context,int processId,int progressId,int fromType) {
        Intent intent = new Intent(context, DiaryDetailActivity.class);
        intent.putExtra("processId", processId);
        intent.putExtra("progressId", progressId);
        intent.putExtra("fromType", fromType);
        context.startActivity(intent);
    }

    private void initViews() {
        findViewById(R.id.layout_back).setOnClickListener(this);
        ((TextView)findViewById(R.id.txt_title)).setText("信息详情");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_back:
                finish();
                break;
        }
    }

}
