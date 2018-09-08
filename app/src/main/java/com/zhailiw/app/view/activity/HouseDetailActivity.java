package com.zhailiw.app.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zhailiw.app.R;
import com.zhailiw.app.presenter.HouseDetailPresenter;

public class HouseDetailActivity extends BaseActivity implements View.OnClickListener {

    private HouseDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_detail);
        initViews();
        presenter = new HouseDetailPresenter(this);
        presenter.init();
    }
    public static void StartActivity(Context context,int houseId) {
        Intent intent = new Intent(context, HouseDetailActivity.class);
        intent.putExtra("houseId", houseId);
        context.startActivity(intent);
    }

    private void initViews() {
        findViewById(R.id.layout_back).setOnClickListener(this);
        findViewById(R.id.btn_decorate).setOnClickListener(this);
        ((TextView)findViewById(R.id.txt_title)).setText("装修房详情");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.btn_decorate:
                presenter.toDecorateDetail();
                break;
        }
    }
}
