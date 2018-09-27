package com.zhailiw.app.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;

import com.orhanobut.logger.Logger;
import com.zhailiw.app.Const;
import com.zhailiw.app.common.NToast;
import com.zhailiw.app.server.HttpException;
import com.zhailiw.app.server.response.LoginResponse;
import com.zhailiw.app.server.response.UserInfoResponse;
import com.zhailiw.app.view.activity.BindPhoneActivity;
import com.zhailiw.app.view.activity.DecorateActivity;
import com.zhailiw.app.view.activity.LoginFirstActivity;
import com.zhailiw.app.widget.LoadDialog;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;



public class LoginFirstPresenter extends BasePresenter  {
    private static final String TAG = LoginFirstPresenter.class.getSimpleName();
    private final BasePresenter basePresenter;
    private LoginFirstActivity activity;
    private final int LOGINWX=2;
    private final int LOGINQQ=3;
    private final int GETINFO=4;

    private String openId,bindType,nickname,headimgurl;

    public LoginFirstPresenter(Context context){
        super(context);
        activity = (LoginFirstActivity) context;
        basePresenter = BasePresenter.getInstance(context);
    }

    public void init(EditText username, EditText password) {
    }

    @Override
    public Object doInBackground(int requestCode, String id) throws HttpException {
        switch (requestCode) {
            case LOGINWX:
                return userAction.login(null,null, this.openId,"wx");
            case LOGINQQ:
                return userAction.login(null,null, this.openId,"qq");
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
                case LOGINWX:
                    bindType = "wx";
                    break;
                case LOGINQQ:
                    bindType = "qq";
                    break;
                case GETINFO:
                    UserInfoResponse userInfoResponse = (UserInfoResponse) result;
                    if ( userInfoResponse.getState()== Const.SUCCESS) {
                        setUserInfoWork(userInfoResponse.getData().getRoleID());
                        activity.finish();
                    }
                    else if(userInfoResponse.getState()== Const.FAILURE)
                    {NToast.shortToast(context, userInfoResponse.getMsg());
                    }
                    break;
            }
        LoginResponse loginResponse = (LoginResponse) result;
        if ( loginResponse.getState()== Const.SUCCESS) {
            loginWork(loginResponse.getToken());
            atm.request(GETINFO,this);
        }
        else if(loginResponse.getState()== Const.FAILURE)
        {
            BindPhoneActivity.StartActivity(activity,openId,bindType);
        }
        NToast.shortToast(context, loginResponse.getMsg());
    }
    public void wxLogin() {
        LoadDialog.show(context);
        final Platform weixin = ShareSDK.getPlatform(Wechat.NAME);

        //设置监听回调
        weixin.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, final HashMap<String, Object> hashMap) {
                LoadDialog.dismiss(context);
                Log.d(TAG, " _Weixin: -->> onComplete: Platform:" + platform.toString());
                Log.d(TAG, " _Weixin: -->> onComplete: hashMap:" + hashMap);
                //weixin.removeAccount(true); //移除授权状态和本地缓存，下次授权会重新授权
                //当前线程不能执行UI操作，需要放到主线程中去
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showUser_WeiXin(hashMap);
                    }
                });
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                LoadDialog.dismiss(context);
                if(i==8){
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            NToast.shortToast(context,"手机没有安装微信");
                        }
                    });
                }

                Log.d(TAG, " _Weixin: -->> onError:  " + throwable.toString());
                throwable.printStackTrace();
                weixin.removeAccount(true);
            }

            @Override
            public void onCancel(Platform platform, int i) {
                NToast.shortToast(context,"取消了");
            }
        });

        //授权并获取用户信息
        weixin.showUser(null);

    }
    private void showUser_WeiXin(HashMap<String, Object> hashMap) {
        openId =(String)hashMap.get("openid");
        nickname = (String) hashMap.get("nickname");
        headimgurl = (String) hashMap.get("headimgurl");
        Logger.d(hashMap.toString());
        Logger.d("nickname:"+nickname);
        Logger.d("headimgurl:"+headimgurl);
        atm.request(LOGINWX,this);
    }

    public void qqLogin() {
        LoadDialog.show(context);
        //获取QQ平台手动授权
        final Platform qq = ShareSDK.getPlatform(QQ.NAME);
        //设置回调监听
        qq.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int action, final HashMap<String, Object> hashMap) {
                LoadDialog.dismiss(context);
                Log.d(TAG, " _QQ: -->> onComplete: Platform:" + platform.toString());
                Log.d(TAG, " _QQ: -->> onComplete: hashMap:" + hashMap);
                if (action == Platform.ACTION_USER_INFOR) {
                    final PlatformDb platDB = platform.getDb();//获取数平台数据DB
                    //通过DB获取各种数据
                    platDB.getToken();
                    platDB.getUserGender();
                    platDB.getUserIcon();
                    platDB.getUserId();
                    platDB.getUserName();

                    //当前线程不能执行UI操作，需要放到主线程中去
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showUser_QQ(platDB,hashMap);
                        }
                    });
                }
            }
            @Override
            public void onError(Platform platform, int action, Throwable throwable) {
                LoadDialog.dismiss(context);
                Log.d(TAG, " _QQ: -->> onError:  " + throwable.toString());
                throwable.printStackTrace();
            }
            @Override
            public void onCancel(Platform platform, int i) {
                qq.removeAccount(true);
            }
        });
        //单独授权,进入输入用户名和密码界面
        /*qq.authorize();*/
        //授权并获得用户信息
        qq.showUser(null);


    }
    private void showUser_QQ(PlatformDb platDB, HashMap<String, Object> hashMap) {
        openId =platDB.getUserId();
        nickname = (String) hashMap.get("nickname");
        headimgurl=platDB.getUserIcon();
        headimgurl=headimgurl.substring(0,headimgurl.lastIndexOf('/'))+"/100";
        String url = (String) hashMap.get("figureurl_qq_1");
        Logger.d("nickname:"+nickname);
        Logger.d("headimgurl:"+headimgurl);

        atm.request(LOGINQQ,this);
    }
//
//    /**
//     * 新浪授权
//     */
//    public void weiboLogin() {
//        LoadDialog.show(context);
//        //获取具体的平台手动授权
//        final Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
//        //设置回调监听
//        weibo.setPlatformActionListener(new PlatformActionListener() {
//            @Override
//            public void onComplete(Platform platform, int i, final HashMap<String, Object> hashMap) {
//                Log.d(TAG, " _XinLang: -->> onComplete: Platform:" + platform.toString());
//                Log.d(TAG, " _XinLang: -->> onComplete: hashMap:" + hashMap);
////获取微博平台手动授权
//                final Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
//
//                //当前线程不能执行UI操作，需要放到主线程中去
//                activity.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        showUser_XinLang(hashMap,weibo);
//                    }
//                });
//            }
//
//            @Override
//            public void onError(Platform platform, int i, Throwable throwable) {
//                Log.d(TAG, " _XinLang: -->> onError:  " + throwable.toString());
//                throwable.printStackTrace();
//            }
//
//            @Override
//            public void onCancel(Platform platform, int i) {
//                weibo.removeAccount(true);
//            }
//        });
//        //authorize与showUser单独调用一个即可，
//        //单独授权,进入输入用户名和密码界面
//        /*weibo.authorize();*/
//        //授权并获取用户信息
//        weibo.showUser(null);
//        //移除授权
//        /*weibo.removeAccount(true);*/
//    }
//    public void showUser_XinLang(HashMap<String, Object> params, Platform weibo) {
//        openId =weibo.getDb().getToken();
//        LoadDialog.show(context);
//        atm.request(UPLOADWBOPENID,this);
//        nickname=weibo.getDb().getUserName();
//        headimgurl=weibo.getDb().getUserIcon();
//        Logger.d("nickname:"+nickname);
//        Logger.d("headimgurl:"+headimgurl);
//        editor.putString("openId", openId);
//        editor.putString("loginType", "wb");
//        editor.apply();
//    }

    private void loginWork2(String access_key)
    {
//        editor.putString(XtdConst.ACCESS_TOKEN, access_key);
//        editor.putString(XtdConst.LOGIN_USERNAME, mUsername.getText().toString());
//        editor.putString(XtdConst.LOGING_PASSWORD, mPassword.getText().toString());
//        editor.putBoolean(XtdConst.ISLOGIN, true);
//        editor.apply();
//        basePresenter.initData();
//        getInfo();
//        rid = JPushInterface.getRegistrationID(context.getApplicationContext());
//        BroadcastManager.getInstance(context).sendBroadcast(MineFragmentPresenter.UPDATEUNREAD, "loadAvator");
//        BroadcastManager.getInstance(context).sendBroadcast(GalleryFragmentPresenter.LOADDEVICE, "loadDevice");

    }

//    public void getInfo(){
//        basePresenter.initData();
//        if(basePresenter.isLogin){
//            LoadDialog.show(context);
//            atm.request(GETINFO,this);
//        }
//    }

}
