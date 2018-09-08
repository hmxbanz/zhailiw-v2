package com.zhailiw.app.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zhailiw.app.R;
import com.zhailiw.app.presenter.ShopPresenter;
import com.zhailiw.app.server.response.StyleResponse;


public class ShopActivity extends BaseActivity implements View.OnClickListener {

    private ShopPresenter presenter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swiper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        initViews();
        presenter =new ShopPresenter(this);
        presenter.init(recyclerView,swiper);
    }

    private void initViews() {
        findViewById(R.id.layout_back).setOnClickListener(this);
        ((TextView)findViewById(R.id.txt_title)).setText("商品列表");
        this.recyclerView = findViewById(R.id.recyclerView);
        this.swiper = findViewById(R.id.swiper);

           }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_back:
                finish();
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public static void StartActivity(Context context, StyleResponse.DataBean item) {
        Intent intent = new Intent(context, ShopActivity.class);
        intent.putExtra("styleId",item.getClassifyID()+"");
        context.startActivity(intent);
    }
}
