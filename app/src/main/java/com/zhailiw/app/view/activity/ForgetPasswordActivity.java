package com.zhailiw.app.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zhailiw.app.R;
import com.zhailiw.app.presenter.ForgetPasswordPresenter;


public class ForgetPasswordActivity extends BaseActivity implements View.OnClickListener {
    private EditText userName, password,passwordAgain,captcha;
    private Button btnCaptcha;
    private ForgetPasswordPresenter mForgetPasswordPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        initViews();
        mForgetPasswordPresenter = new ForgetPasswordPresenter(this);
        mForgetPasswordPresenter.init(userName,password,passwordAgain,captcha,btnCaptcha);
    }

    private void initViews() {
        findViewById(R.id.layout_back).setOnClickListener(this);

        ((TextView)findViewById(R.id.txt_title)).setText("找回密码");

        userName =findViewById(R.id.username);
        password =findViewById(R.id.password);
        passwordAgain =findViewById(R.id.password_again);
        captcha =findViewById(R.id.captcha);
        btnCaptcha =findViewById(R.id.btn_captcha);
        btnCaptcha.setOnClickListener(this);
        findViewById(R.id.btn_find_pass).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.btn_captcha:
                mForgetPasswordPresenter.getCaptcha();
                break;
            case R.id.btn_find_pass:
                mForgetPasswordPresenter.resetPassword();
                break;
        }

    }
}
