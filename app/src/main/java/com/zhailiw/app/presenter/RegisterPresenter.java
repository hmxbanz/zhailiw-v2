package com.zhailiw.app.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.zhailiw.app.Const;
import com.zhailiw.app.R;
import com.zhailiw.app.common.NToast;
import com.zhailiw.app.listener.AlertDialogCallBack;
import com.zhailiw.app.server.HttpException;
import com.zhailiw.app.server.response.CommonResponse;
import com.zhailiw.app.view.activity.LoginActivity;
import com.zhailiw.app.view.activity.RegisterActivity;
import com.zhailiw.app.widget.DialogWithYesOrNoUtils;
import com.zhailiw.app.widget.LoadDialog;


public class RegisterPresenter extends BasePresenter {
    private static final int GETCAPTCHA = 1;
    private static final int REGISTER = 2;
    private String nickname;
    private final String headimgurl;
    private RegisterActivity activity;
    private EditText userName,password,passwordAgain,captcha;
    private Button btnCaptcha;

    public RegisterPresenter(Context context){
        super(context);
        activity = (RegisterActivity) context;
        Intent fromIntent=((RegisterActivity) context).getIntent();
        nickname = fromIntent.getStringExtra("nickname");
        headimgurl = fromIntent.getStringExtra("headimgurl");
    }

    public void init(EditText userName, EditText password, EditText passwordAgain, EditText captcha, Button btnCaptcha) {
        this.userName=userName;
        this.password=password;
        this.passwordAgain=passwordAgain;
        this.captcha=captcha;
        this.btnCaptcha=btnCaptcha;
    }

    public void getCaptcha() {
        if(TextUtils.isEmpty(this.userName.getText()))
        {
            NToast.shortToast(context, R.string.phone_number_be_null);
            return;
        }
        timer.start();//开始倒计时60秒
        LoadDialog.show(activity);
        atm.request(GETCAPTCHA,this);
    }
    public void register() {
        if(TextUtils.isEmpty(this.userName.getText()))
        {
            NToast.shortToast(context, R.string.phone_number_be_null);
            return;
        }
        if (TextUtils.isEmpty(this.password.getText())) {
            NToast.shortToast(context, R.string.password_be_null);
            return;
        }
        if (this.password.getText().toString().contains(" ")) {
            NToast.shortToast(context, R.string.password_cannot_contain_spaces);
            return;
        }
        if (TextUtils.isEmpty(this.captcha.getText())) {
            NToast.shortToast(context, R.string.captcha_cannot_be_null);
            return;
        }

        LoadDialog.show(activity);
        atm.request(REGISTER,this);

    }
    @Override
    public Object doInBackground(int requestCode, String id) throws HttpException {
        switch (requestCode) {
            case GETCAPTCHA:
                return userAction.getCaptcha(this.userName.getText().toString(),"286");//手机注册
            case REGISTER:
                return userAction.register(
                        this.userName.getText().toString(),
                        this.password.getText().toString(),
                        "昵称",
                        this.captcha.getText().toString());
        }
        return null;
    }

    @Override
    public void onSuccess(int requestCode, Object result) {
        LoadDialog.dismiss(context);
        switch (requestCode) {
            case GETCAPTCHA:
                CommonResponse commonResponse = (CommonResponse) result;
                NToast.shortToast(context, commonResponse.getMsg());
                break;
            case REGISTER:
                CommonResponse commonResponse2 = (CommonResponse) result;
                if (commonResponse2.getState() == Const.SUCCESS) {
                    DialogWithYesOrNoUtils.getInstance().showDialog(context, "注册成功", new AlertDialogCallBack(){
                        @Override
                        public void executeEvent() {
                            super.executeEvent();
                            LoginActivity.StartActivity(activity);
                        }
                    });

                    NToast.shortToast(context, commonResponse2.getMsg());
                    break;
                }
        }
    }
    private CountDownTimer timer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            btnCaptcha.setEnabled(false);
            btnCaptcha.setText((millisUntilFinished / 1000) + "秒后可重发");
        }
        @Override
        public void onFinish() {
            btnCaptcha.setEnabled(true);
            btnCaptcha.setText("获取验证码");
        }
    };
}
