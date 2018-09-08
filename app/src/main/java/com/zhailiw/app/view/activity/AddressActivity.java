package com.zhailiw.app.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zhailiw.app.R;
import com.zhailiw.app.presenter.AddressPresenter;

public class AddressActivity extends BaseActivity implements View.OnClickListener {
    private AddressPresenter presenter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swiper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        initViews();
        presenter = new AddressPresenter(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.init(recyclerView,swiper);
    }

    private void initViews() {
        ((TextView)findViewById(R.id.txt_title)).setText("管理收货地址");
        findViewById(R.id.layout_back).setOnClickListener(this);
        recyclerView = findViewById(R.id.recyclerView);
        swiper = findViewById(R.id.swiper);
        findViewById(R.id.btn_add_address).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.btn_add_address:
                AddAddressActivity.StartActivity(this);
                break;

        }

    }

    public static void StartActivityForResult(Context context) {
        Intent intent = new Intent(context, AddressActivity.class);
        ((OrderDetailActivity)context).startActivityForResult(intent, 1);
    }
}