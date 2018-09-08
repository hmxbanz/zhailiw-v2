package com.zhailiw.app.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zhailiw.app.R;
import com.zhailiw.app.presenter.DecorateDetailPresenter;

public class DecorateDetailActivity extends BaseActivity implements View.OnClickListener {

    private DecorateDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decorate_detail);
        initViews();
        presenter = new DecorateDetailPresenter(this);
        presenter.init();
    }
    public static void StartActivity(Context context, int houseId) {
        Intent intent = new Intent(context, DecorateDetailActivity.class);
        intent.putExtra("houseId", houseId);
        context.startActivity(intent);
    }

    private void initViews() {
        findViewById(R.id.layout_back).setOnClickListener(this);
        ((TextView)findViewById(R.id.txt_title)).setText("装修进程");
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onRefresh();
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
