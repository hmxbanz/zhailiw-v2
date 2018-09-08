package com.zhailiw.app.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.zhailiw.app.R;
import com.zhailiw.app.presenter.LoginPresenter;


public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText mUsername,mPassword;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        presenter = new LoginPresenter(this);
        presenter.init(mUsername,mPassword);
    }


    public static void StartActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
//        intent.putExtra("openId",openId);
//        intent.putExtra("type",type);
        context.startActivity(intent);
    }

    private void initViews() {
        findViewById(R.id.layout_back).setOnClickListener(this);
        findViewById(R.id.layout_login_qq).setOnClickListener(this);
        findViewById(R.id.layout_login_wx).setOnClickListener(this);
        mUsername =  findViewById(R.id.username);
        mPassword =  findViewById(R.id.password);
        findViewById(R.id.btn_login).setOnClickListener(this);
        findViewById(R.id.text_forget_password).setOnClickListener(this);
        findViewById(R.id.txt_register).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.txt_register:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
            case R.id.btn_login:
                presenter.login("normal");
                break;
            case R.id.text_forget_password:
                startActivity(new Intent(this,ForgetPasswordActivity.class));
                break;
            case R.id.layout_login_wx:
                LoginFirstActivity.StartActivity(this);
                break;
            case R.id.layout_login_qq:
                LoginFirstActivity.StartActivity(this);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
