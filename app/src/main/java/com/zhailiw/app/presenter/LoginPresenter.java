package com.zhailiw.app.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.EditText;

import com.zhailiw.app.Const;
import com.zhailiw.app.R;
import com.zhailiw.app.common.NToast;
import com.zhailiw.app.server.HttpException;
import com.zhailiw.app.server.response.LoginResponse;
import com.zhailiw.app.server.response.UserInfoResponse;
import com.zhailiw.app.view.activity.DecorateActivity;
import com.zhailiw.app.view.activity.LoginActivity;
import com.zhailiw.app.view.activity.MainActivity;
import com.zhailiw.app.widget.LoadDialog;


public class LoginPresenter extends BasePresenter  {
    private static final String TAG = LoginPresenter.class.getSimpleName();
    private static final int LOGIN = 1,GETINFO=2;
    private final BasePresenter basePresenter;
    private LoginActivity activity;
    private EditText mUsername;
    private EditText mPassword;
    private String loginType;

    public LoginPresenter(Context context){
        super(context);
        activity = (LoginActivity) context;
        basePresenter = BasePresenter.getInstance(context);
    }

    public void init(EditText username, EditText password) {
        this.mUsername=username;
        this.mPassword=password;
    }

    public void login(String type) {
        this.loginType=type;

        if(TextUtils.isEmpty(mUsername.getText().toString()))
        {
            NToast.shortToast(context, R.string.phone_number_be_null);
            return;
        }
        if (TextUtils.isEmpty(mPassword.getText().toString())) {
            NToast.shortToast(context, R.string.password_be_null);
            return;
        }
        if (mPassword.getText().toString().contains(" ")) {
            NToast.shortToast(context, R.string.password_cannot_contain_spaces);
            return;
        }

            LoadDialog.show(context);
            atm.request(LOGIN,this);
    }

    @Override
    public Object doInBackground(int requestCode, String id) throws HttpException {
        switch (requestCode) {
            case LOGIN:
                return userAction.login(mUsername.getText().toString(), mPassword.getText().toString(),null,this.loginType);
            case GETINFO:
                return userAction.getInfo();

        }
        return null;
    }
    @Override
    public void onSuccess(final int requestCode, Object result) {
        LoadDialog.dismiss(context);
        if (result==null)return;
            switch (requestCode) {
                case LOGIN:
                    LoginResponse loginResponse = (LoginResponse) result;
                    if ( loginResponse.getState()== Const.SUCCESS) {
                        loginWork(loginResponse.getToken());
                        atm.request(GETINFO,this);
                    }
                    NToast.shortToast(context, loginResponse.getMsg());
                    break;
                case GETINFO:
                    UserInfoResponse userInfoResponse = (UserInfoResponse) result;
                    if ( userInfoResponse.getState()== Const.SUCCESS) {
                        setUserInfoWork(userInfoResponse.getData().getRoleID());
                        MainActivity.StartActivity(activity,0);
                        activity.finish();
                    }
                    else if(userInfoResponse.getState()== Const.FAILURE)
                    {NToast.shortToast(context, userInfoResponse.getMsg());
                    }
                    break;
            }
    }


    public void getInfo(){
        basePresenter.initData();
        if(basePresenter.isLogin){
            LoadDialog.show(context);
            //atm.request(GETINFO,this);
        }
    }

}
