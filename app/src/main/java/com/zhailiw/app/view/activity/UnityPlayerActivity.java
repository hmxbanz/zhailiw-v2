package com.zhailiw.app.view.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.zhailiw.app.R;
import com.zhailiw.app.widget.permissionLibrary.PermissionsManager;
import com.zhailiw.app.widget.permissionLibrary.PermissionsResultAction;

public class UnityPlayerActivity extends BaseActivity
{
    private static final int QRSCANNING = 1;
    private String qrCode,IMEI;

    private static final String TAG = UnityPlayerActivity.class.getSimpleName();
    @Override protected void onCreate (Bundle savedInstanceState)
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        getWindow().setFormat(PixelFormat.RGBX_8888); // <--- This makes xperia play happy
        setContentView(R.layout.activity_main);
    }

    public void init() {
        String[] Permissions=new String[]{Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};
        //权限申请
        PermissionsManager.getInstance().requestPermissionsIfNecessaryForResult(this,
                Permissions,
                new PermissionsResultAction() {
                    @Override
                    public void onGranted() {
                    }
                    @Override
                    public void onDenied(String permission) {
                        Toast.makeText(UnityPlayerActivity.this, "Permission Denied!", Toast.LENGTH_SHORT).show();
                    }
                }, true);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        switch (requestCode) {
            case QRSCANNING:
                //处理扫描结果（在界面上显示）
                if (null != data) {
                    Bundle bundle = data.getExtras();
                    if (bundle == null) {
                        return;
                    }
                    if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                        qrCode = bundle.getString(CodeUtils.RESULT_STRING);
                        Toast.makeText(this, "result:" + qrCode, Toast.LENGTH_LONG).show();
                    } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                        Toast.makeText(this, "error", Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

}
