package com.zhailiw.app.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.zhailiw.app.R;
import com.zhailiw.app.presenter.ShopCarPresenter;


public class ShopCarActivity extends BaseActivity implements View.OnClickListener {
    private ShopCarPresenter presenter;
    private CheckBox checkbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_car);
        initViews();
        presenter = new ShopCarPresenter(this);
        presenter.init();
    }


    public static void StartActivity(Context context) {
        Intent intent = new Intent(context, ShopCarActivity.class);
        context.startActivity(intent);
    }

    private void initViews() {
        findViewById(R.id.layout_back).setOnClickListener(this);
        ((TextView)findViewById(R.id.txt_title)).setText("购物车");
        ((TextView)findViewById(R.id.txt_right)).setText("管理");
        findViewById(R.id.txt_right).setOnClickListener(this);
        findViewById(R.id.btn_buy).setOnClickListener(this);
        this.checkbox=findViewById(R.id.checkbox);
        this.checkbox.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.txt_right:
                presenter.onRightClick();
                break;
            case R.id.btn_buy:
                presenter.onPayClick();
            case R.id.checkbox:
                presenter.onCheckAll(checkbox);
        }
    }

}
