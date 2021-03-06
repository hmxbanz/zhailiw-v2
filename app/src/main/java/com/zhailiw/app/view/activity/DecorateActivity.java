package com.zhailiw.app.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhailiw.app.R;
import com.zhailiw.app.presenter.DecoratePresenter;
import com.zhailiw.app.view.fragment.MineFragment;

public class DecorateActivity extends BaseActivity implements View.OnClickListener {

    private DecoratePresenter presenter;
    private ImageView imgRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decorate);
        initViews();
        presenter = new DecoratePresenter(this);
        presenter.init();
    }
    public static void StartActivity(Context context) {
        Intent intent = new Intent(context, DecorateActivity.class);
        context.startActivity(intent);
    }

    private void initViews() {
        findViewById(R.id.layout_back).setOnClickListener(this);
        imgRight=findViewById(R.id.img_right);
        imgRight.setVisibility(View.VISIBLE);
        imgRight.setOnClickListener(this);
        ((TextView)findViewById(R.id.txt_title)).setText("我的装修");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.img_right:
                MeActivity.StartActivity(this);
                break;
        }
    }
}
