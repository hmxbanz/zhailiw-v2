package com.zhailiw.app.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zhailiw.app.R;
import com.zhailiw.app.presenter.BindPhonePresenter;


public class BindPhoneActivity extends BaseActivity implements View.OnClickListener{
    private EditText userName, captcha;
    private BindPhonePresenter bindPhonePresenter;
    private TextView txtCaptcha;
    private Button btnCaptcha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_phone);
        initViews();
        bindPhonePresenter = new BindPhonePresenter(this);
        bindPhonePresenter.init(userName,captcha,btnCaptcha);
    }

    private void initViews() {
        findViewById(R.id.layout_back).setOnClickListener(this);
        ((TextView)findViewById(R.id.txt_title)).setText("手机绑定");

        userName =findViewById(R.id.username);
        captcha =findViewById(R.id.captcha);
        btnCaptcha =findViewById(R.id.btn_captcha);
        btnCaptcha.setOnClickListener(this);
        findViewById(R.id.btn_bind_phone).setOnClickListener(this);
    }

    public static void StartActivity(Context context,String openId,String bindType) {
        Intent intent = new Intent(context, BindPhoneActivity.class);
        intent.putExtra("openId", openId);
        intent.putExtra("bindType", bindType);
        context.startActivity(intent);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.btn_captcha:
                bindPhonePresenter.getCaptcha();
                break;
            case R.id.btn_bind_phone:
                bindPhonePresenter.bindPhone();
                break;
        }

    }
}
