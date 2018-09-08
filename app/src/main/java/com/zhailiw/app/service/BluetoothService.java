package com.zhailiw.app.service;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;

public class BluetoothService extends Service {
    private static final long SCANTIME = 3000;
    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void runOnMainThread(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
        }
    }

}
