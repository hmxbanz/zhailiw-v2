package com.zhailiw.app.presenter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.google.gson.Gson;
import com.leeiidesu.permission.PermissionHelper;
import com.leeiidesu.permission.callback.OnPermissionResultListener;
import com.orhanobut.logger.Logger;
import com.zhailiw.app.Const;
import com.zhailiw.app.common.CommonTools;
import com.zhailiw.app.common.NToast;
import com.zhailiw.app.listener.AlertDialogCallBack;
import com.zhailiw.app.server.HttpException;
import com.zhailiw.app.server.response.SystemObjResponse;
import com.zhailiw.app.server.response.VersionResponse;
import com.zhailiw.app.view.activity.DecorateActivity;
import com.zhailiw.app.view.activity.GuideActivity;
import com.zhailiw.app.view.activity.LoginFirstActivity;
import com.zhailiw.app.view.activity.MainActivity;
import com.zhailiw.app.view.activity.StartActivity;
import com.zhailiw.app.widget.ACache;
import com.zhailiw.app.widget.DialogWithYesOrNoUtils;
import com.zhailiw.app.widget.LoadDialog;
import com.zhailiw.app.widget.downloadService.DownloadService;
import com.zhailiw.app.widget.permissionLibrary.PermissionsManager;
import com.zhailiw.app.widget.permissionLibrary.PermissionsResultAction;

import java.util.ArrayList;

import static com.zhailiw.app.common.CommonTools.getVersionInfo;


public class StartPresenter extends BasePresenter implements OnPermissionResultListener {
    private static final int GETSYSTEMOBJ = 1036;
    private static final int GETCITIES = 1037;
    private static final int CHECKVERSION = 1038;
    private StartActivity activity;
    private Handler hand = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (CommonTools.isFristRun(activity)) {
                Intent intent = new Intent(activity, LoginFirstActivity.class);
                activity.startActivity(intent);
                activity.finish();
            }
            else
            {
                Intent intent = new Intent(activity, DecorateActivity.class);
                activity.startActivity(intent);
                activity.finish();
            }
        }
    };
    private String apkUrl;

    public StartPresenter(Context context) {
        super(context);
        activity = (StartActivity) context;
    }

    public void init() {
        String systemObjCache = aCache.getAsString("SystemObjCache");
        Logger.d("systemObjCache %s:", systemObjCache);
        if (systemObjCache==null || ("null").equals(systemObjCache)) {
            getSystemObj();
            return;
        }

        //加载系统数据成功后准备跳转
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Message msg = hand.obtainMessage();
                hand.sendMessage(msg);
            }
        }.start();
    }

    public void getSystemObj() {
        LoadDialog.show(context);
        atm.request(GETSYSTEMOBJ, this);
    }

    @Override
    public Object doInBackground(int requestCode, String parameter) throws HttpException {

        switch (requestCode) {
            case GETSYSTEMOBJ:
                return userAction.getSystemObj();
            case CHECKVERSION:
                return userAction.checkVersion();
        }
        return null;
    }

    @Override
    public void onSuccess(int requestCode, Object result) {
        LoadDialog.dismiss(context);
        switch (requestCode) {
            case GETSYSTEMOBJ:
                SystemObjResponse systemObjResponse = (SystemObjResponse) result;
                if (systemObjResponse.getState() == Const.SUCCESS) {
                    String cache = new Gson().toJson(systemObjResponse.getSysObj());
                    aCache.put("SystemObjCache", cache, 7 * ACache.TIME_DAY);
                //加载系统数据成功后准备跳转
                    new Thread() {
                        public void run() {
                            try {
                                Thread.sleep(1500);
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            Message msg = hand.obtainMessage();
                            hand.sendMessage(msg);
                        }
                    }.start();
                }
                break;
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
        }
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
