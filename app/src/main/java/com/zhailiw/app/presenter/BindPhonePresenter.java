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
import com.zhailiw.app.view.activity.BindPhoneActivity;
import com.zhailiw.app.view.activity.LoginFirstActivity;
import com.zhailiw.app.widget.DialogWithYesOrNoUtils;
import com.zhailiw.app.widget.LoadDialog;


public class BindPhonePresenter extends BasePresenter {
    private static final int GETCAPTCHA = 1;
    private static final int BINDPHONE = 2;
    private final String openId,bindType;
    private BindPhoneActivity activity;
    private EditText userName,captcha;
    private Button btnCaptcha;

    public BindPhonePresenter(Context context){
        super(context);
        activity = (BindPhoneActivity) context;
        Intent intent=activity.getIntent();
        openId=intent.getStringExtra("openId");
        bindType=intent.getStringExtra("bindType");
    }

    public void init(EditText userName, EditText captcha,Button btnCaptcha) {
        this.userName=userName;
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
    public void bindPhone() {
        if(TextUtils.isEmpty(this.userName.getText()))
        {
            NToast.shortToast(context, R.string.phone_number_be_null);
            return;
        }
        if (TextUtils.isEmpty(this.captcha.getText())) {
            NToast.shortToast(context, R.string.captcha_cannot_be_null);
            return;
        }
        LoadDialog.show(activity);
        atm.request(BINDPHONE,this);
    }
    @Override
    public Object doInBackground(int requestCode, String id) throws HttpException {
        switch (requestCode) {
            case GETCAPTCHA:
                return userAction.getCaptcha(this.userName.getText().toString(),"287");//手机绑定
            case BINDPHONE:
                return userAction.bindPhone(this.userName.getText().toString(),this.captcha.getText().toString(),openId,bindType);
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
            case BINDPHONE:
                CommonResponse commonResponse2 = (CommonResponse) result;
                if (commonResponse2.getState() == Const.SUCCESS) {
                    DialogWithYesOrNoUtils.getInstance().showDialog(context, "绑定成功", new AlertDialogCallBack(){
                        @Override
                        public void executeEvent() {
                            super.executeEvent();
                            LoginFirstActivity.StartActivity(activity);
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
