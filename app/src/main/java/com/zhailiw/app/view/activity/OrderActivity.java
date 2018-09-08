package com.zhailiw.app.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zhailiw.app.R;
import com.zhailiw.app.presenter.FavorPresenter;
import com.zhailiw.app.presenter.OrderPresenter;


public class OrderActivity extends BaseActivity implements View.OnClickListener {
    private OrderPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favor);
        initViews();
        presenter = new OrderPresenter(this);
        presenter.init();
    }


    public static void StartActivity(Context context,int tabIndex) {
        Intent intent = new Intent(context, OrderActivity.class);
        intent.putExtra("tabIndex", tabIndex);
        context.startActivity(intent);
    }

    private void initViews() {
        findViewById(R.id.layout_back).setOnClickListener(this);
        ((TextView)findViewById(R.id.txt_title)).setText("我的订单");
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
