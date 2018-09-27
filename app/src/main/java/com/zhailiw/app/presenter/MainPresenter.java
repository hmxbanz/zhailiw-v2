package com.zhailiw.app.presenter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;

import com.leeiidesu.permission.PermissionHelper;
import com.leeiidesu.permission.callback.OnPermissionResultListener;
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

import java.util.ArrayList;

import static com.zhailiw.app.common.CommonTools.getVersionInfo;


public class MainPresenter extends BasePresenter implements OnPermissionResultListener {
    public static final int AUTOLOGIN = 1;
    private static final int CHECKVERSION = 2;
    private static final String TAG = MainPresenter.class.getSimpleName();
    private final BasePresenter basePresenter;
    private MainActivity activity;
    private ViewPager viewPager;

    private String apkUrl;

    public MainPresenter(Context context){
        super(context);
        activity = (MainActivity) context;
        basePresenter = BasePresenter.getInstance(context);
    }

    public void init(ViewPager viewPager) {
        this.viewPager=viewPager;
        atm.request(CHECKVERSION,this);
//        String[] Permissions=new String[]{
//                Manifest.permission.ACCESS_FINE_LOCATION,
//                Manifest.permission.READ_EXTERNAL_STORAGE};
//        //权限申请
//        PermissionHelper.with(activity)
//                .permissions(Permissions)
//                .showOnRationale("存储权限", "取消", "我知道了")    //用户拒绝过但没有勾选不再提示会显示对话框
//                .showOnDenied("必需拔打存储权限才能更新", "取消", "去设置") //用户勾选不再提示会显示对话框
//                .request();
    }

    public void onMeClick() {
        basePresenter.initData();
        if(!basePresenter.isLogin){
            activity.startActivity(new Intent(activity, LoginFirstActivity.class));
        }
        else {
            viewPager.setCurrentItem(3, false);
        }

    }
    @Override
    public Object doInBackground(int requestCode, String id) throws HttpException {
        switch (requestCode) {
            case CHECKVERSION:
                return userAction.checkVersion();
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
            case CHECKVERSION:
                VersionResponse versionResponse = (VersionResponse) result;
                if (versionResponse.getState() == Const.SUCCESS) {
                    final VersionResponse.ResultEntity entity=versionResponse.getAndroid();
                    String[] versionInfo = getVersionInfo(activity);
                    int versionCode = Integer.parseInt(versionInfo[0]);
                    if(entity.getVersionCode()>versionCode)
                    {
                        DialogWithYesOrNoUtils dialog=DialogWithYesOrNoUtils.getInstance();
                        dialog.showDialog(activity, "发现新版本:"+entity.getVersionName(), new AlertDialogCallBack(){
                            @Override
                            public void executeEvent() {
                                goToDownload(entity.getDownloadUrl());
                            }
                        });
                        dialog.setContent(entity.getVersionInfo());
                    }
                }else {
                    NToast.shortToast(activity, "版本检测："+versionResponse.getMsg());
                }
                break;
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

    private void goToDownload(final String apkUrl) {
        this.apkUrl=apkUrl;
        //权限申请
        String[] Permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        PermissionHelper.with(activity)
                .permissions(Permissions)
                .showOnRationale("存储权限", "取消", "我知道了")    //用户拒绝过但没有勾选不再提示会显示对话框
                .showOnDenied("必需拔打存储权限才能更新", "取消", "去设置") //用户勾选不再提示会显示对话框
                .listener(this)
                .request();
    }

    @Override
    public void onGranted() {
        Intent intent=new Intent(activity,DownloadService.class);
        intent.putExtra("url", apkUrl);
        activity.startService(intent);
    }

    @Override
    public void onFailed(ArrayList<String> deniedPermissions) {

    }
}
