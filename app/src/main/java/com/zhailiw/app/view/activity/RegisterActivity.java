package com.zhailiw.app.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zhailiw.app.R;
import com.zhailiw.app.presenter.RegisterPresenter;


public class RegisterActivity extends BaseActivity implements View.OnClickListener{
    private EditText userName, password,passwordAgain,captcha;
    private RegisterPresenter mRegisterPresenter;
    private Button btnCaptcha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();
        mRegisterPresenter = new RegisterPresenter(this);
        mRegisterPresenter.init(userName,password,passwordAgain,captcha,btnCaptcha);
    }

    private void initViews() {
        findViewById(R.id.layout_back).setOnClickListener(this);

        ((TextView)findViewById(R.id.txt_title)).setText("用户注册");

        userName =findViewById(R.id.username);
        password =findViewById(R.id.password);
        passwordAgain =findViewById(R.id.password_again);
        captcha =findViewById(R.id.captcha);
        btnCaptcha =findViewById(R.id.btn_captcha);
        btnCaptcha.setOnClickListener(this);
        findViewById(R.id.btn_register).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.btn_captcha:
                mRegisterPresenter.getCaptcha();
                break;
            case R.id.btn_register:
                mRegisterPresenter.register();
                break;
        }

    }
}
