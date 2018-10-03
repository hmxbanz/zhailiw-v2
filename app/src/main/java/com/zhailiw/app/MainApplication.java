package com.zhailiw.app;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lzy.ninegrid.NineGridView;
import com.mob.MobSDK;
import com.zhailiw.app.common.NLog;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        NLog.setDebug(true);//开log日志
        MobSDK.init(this);
        //ZXingLibrary.initDisplayOpinion(this);
    }
}
