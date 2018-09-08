package com.zhailiw.app.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.zhailiw.app.R;
import com.zhailiw.app.presenter.OrderDetailPresenter;


public class OrderDetailActivity extends BaseActivity implements View.OnClickListener {
    private OrderDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        initViews();
        presenter = new OrderDetailPresenter(this);
        presenter.init();
    }


    public static void StartActivity(Context context,String orderId) {
        Intent intent = new Intent(context, OrderDetailActivity.class);
        intent.putExtra("orderId", orderId);
        context.startActivity(intent);
    }

    private void initViews() {
        findViewById(R.id.layout_back).setOnClickListener(this);
        ((TextView)findViewById(R.id.txt_title)).setText("订单详情");
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String addressId = "";
        if(data!=null)
         addressId = data.getStringExtra("addressId");
        switch (requestCode) {
            case 1:
                presenter.getAddress(addressId);
                break;
            case 2:
                break;
            default:
                break;
        }
    }

}
