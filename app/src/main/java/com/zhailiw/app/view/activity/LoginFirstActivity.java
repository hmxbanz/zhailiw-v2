package com.zhailiw.app.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zhailiw.app.R;
import com.zhailiw.app.presenter.LoginFirstPresenter;


public class LoginFirstActivity extends BaseActivity implements View.OnClickListener {

    private LoginFirstPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_first);
        initViews();
        presenter = new LoginFirstPresenter(this);
    }

    public static void StartActivity(Context context) {
        Intent intent = new Intent(context, LoginFirstActivity.class);
        context.startActivity(intent);
    }

    private void initViews() {
         findViewById(R.id.layout_back).setOnClickListener(this);
         findViewById(R.id.layout_login_qq).setOnClickListener(this);
         findViewById(R.id.layout_login_wx).setOnClickListener(this);
         findViewById(R.id.layout_login_phone).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.layout_login_phone:
                startActivity(new Intent(this,LoginActivity.class));
                break;
            case R.id.layout_login_wx:
                presenter.wxLogin();
                break;
            case R.id.layout_login_qq:
                presenter.qqLogin();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
