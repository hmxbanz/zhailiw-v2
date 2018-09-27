package com.zhailiw.app.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.zhailiw.app.Const;
import com.zhailiw.app.common.CommonTools;
import com.zhailiw.app.server.HttpException;
import com.zhailiw.app.server.response.SystemObjResponse;
import com.zhailiw.app.view.activity.MainActivity;
import com.zhailiw.app.view.activity.StartActivity;
import com.zhailiw.app.widget.ACache;
import com.zhailiw.app.widget.LoadDialog;


public class StartPresenter extends BasePresenter  {
    private static final int GETSYSTEMOBJ = 1036;
    private static final int GETCITIES = 1037;
    private StartActivity activity;
    private Handler hand = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (CommonTools.isFristRun(activity)) {
                Intent intent = new Intent(activity, MainActivity.class);
                activity.startActivity(intent);
                activity.finish();
            }
            else
            {
                Intent intent = new Intent(activity, MainActivity.class);
                activity.startActivity(intent);
                activity.finish();
            }
        }
    };

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

        }
    }


}
