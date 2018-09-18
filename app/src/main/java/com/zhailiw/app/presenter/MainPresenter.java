package com.zhailiw.app.presenter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.zhailiw.app.Const;
import com.zhailiw.app.common.NToast;
import com.zhailiw.app.listener.AlertDialogCallBack;
import com.zhailiw.app.server.HttpException;
import com.zhailiw.app.server.response.CommonResponse;
import com.zhailiw.app.server.response.VersionResponse;
import com.zhailiw.app.view.activity.LoginFirstActivity;
import com.zhailiw.app.view.activity.MainActivity;
import com.zhailiw.app.widget.DialogWithYesOrNoUtils;
import com.zhailiw.app.widget.LoadDialog;
import com.zhailiw.app.widget.downloadService.DownloadService;
import com.zhailiw.app.widget.permissionLibrary.PermissionsManager;
import com.zhailiw.app.widget.permissionLibrary.PermissionsResultAction;

import static com.zhailiw.app.common.CommonTools.getVersionInfo;



public class MainPresenter extends BasePresenter {
    public static final int AUTOLOGIN = 1;
    private static final int CHECKVERSION = 2;
    private static final String TAG = MainPresenter.class.getSimpleName();
    private final BasePresenter basePresenter;
    private MainActivity activity;
    private ViewPager viewPager;

    public MainPresenter(Context context){
        super(context);
        activity = (MainActivity) context;
        basePresenter = BasePresenter.getInstance(context);
    }

    public void init(ViewPager viewPager) {
        this.viewPager=viewPager;
        atm.request(CHECKVERSION,this);
        String[] Permissions=new String[]{Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE};
        //权限申请
        PermissionsManager.getInstance().requestPermissionsIfNecessaryForResult(activity,
                Permissions,
                new PermissionsResultAction() {
                    @Override
                    public void onGranted() {

                    }

                    @Override
                    public void onDenied(String permission) {
                        Toast.makeText(context, "获取权限失败，请点击后允许获取", Toast.LENGTH_SHORT).show();
                    }
                }, true);
    }

    public void onMeClick() {
        basePresenter.initData();
        if(!basePresenter.isLogin){
            activity.startActivity(new Intent(activity, LoginFirstActivity.class));
        }
        else {
            viewPager.setCurrentItem(2, false);
        }

    }
    @Override
    public Object doInBackground(int requestCode, String id) throws HttpException {
        switch (requestCode) {
//            case AUTOLOGIN:
//                return userAction.login(userName, password,null,"");

        }
        return null;
    }
    @Override
    public void onSuccess(int requestCode, Object result) {
        LoadDialog.dismiss(context);
        if (result==null)return;
        switch (requestCode) {
            case AUTOLOGIN:
                CommonResponse commonResponse = (CommonResponse) result;
                if (commonResponse.getState() == Const.SUCCESS) {
                    //loginWork(entity.getAccess_key());
                    NToast.shortToast(activity, "登录成功");
                } else {
                    NToast.shortToast(activity, "登录："+commonResponse.getMsg());
                }
                break;
        }

    }

    public void autoLogin()
    {
//        basePresenter.initData();
//        if(!basePresenter.isLogin)
//        viewPager.setCurrentItem(0);
//        openId=sp.getString(XtdConst.OPENID,"");
//        loginType=sp.getString("loginType","");
//        NLog.d(TAG, "openId"+openId);
//        NLog.d(TAG, "loginType"+loginType);
//
//        if(!TextUtils.isEmpty(userName)){
//            LoadDialog.show(activity);
//            atm.request(AUTOLOGIN,this);
//        }
//        else if(!TextUtils.isEmpty(openId)){
//            switch (loginType){
//                case "wx":
//                    atm.request(UPLOADWXOPENID,this);break;
//                case "qq":
//                    atm.request(UPLOADQQOPENID,this);break;
//                case "wb":
//                    atm.request(UPLOADWBOPENID,this);
//            }
//        }

    }


    public void onDestroy() {
//        activity.editor.putBoolean(XtdConst.ISLOGIN, false);//退出改登录标记
//        activity.editor.commit();
//        basePresenter.initData();
    }

    public void reStart(Intent intent) {
        int position=intent.getIntExtra("position",0);
        viewPager.setCurrentItem(position);
    }
}
