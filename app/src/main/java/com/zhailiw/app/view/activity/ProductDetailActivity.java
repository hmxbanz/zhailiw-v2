package com.zhailiw.app.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.zhailiw.app.R;
import com.zhailiw.app.presenter.ProductDetailPresenter;


public class ProductDetailActivity extends BaseActivity implements View.OnClickListener {
    private ProductDetailPresenter presenter;
    private CheckBox checkbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        initViews();
        presenter = new ProductDetailPresenter(this);
        presenter.init();
    }


    public static void StartActivity(Context context,String productId) {
        Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra("productId",productId);
        context.startActivity(intent);
    }

    private void initViews() {
        findViewById(R.id.layout_back).setOnClickListener(this);
        ((TextView)findViewById(R.id.txt_title)).setText("商品详情");
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
