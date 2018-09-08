package com.zhailiw.app.presenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zhailiw.app.Const;
import com.zhailiw.app.R;
import com.zhailiw.app.common.NToast;
import com.zhailiw.app.loader.GlideImageLoader;
import com.zhailiw.app.server.HttpException;
import com.zhailiw.app.server.async.OnDataListener;
import com.zhailiw.app.server.broadcast.BroadcastManager;
import com.zhailiw.app.server.response.UserInfoResponse;
import com.zhailiw.app.view.activity.MainActivity;
import com.zhailiw.app.widget.LoadDialog;
import com.zhailiw.app.widget.SelectableRoundedImageView;


public class MineFragmentPresenter extends BasePresenter implements OnDataListener {
    private static final int GETINFO = 2;
    private static final int GETMSGCOUNT = 3;
    public static final String UPDATEUNREAD = "updateUnread";
    private final MainActivity activity;
    private GlideImageLoader glideImageLoader;
    private SelectableRoundedImageView avator;
    private TextView nickName;

    public MineFragmentPresenter(Context context){
        super(context);
        glideImageLoader = new GlideImageLoader();
        activity=(MainActivity)context;
    }

    public void init(SelectableRoundedImageView selectableRoundedImageView, TextView nickName) {
        this.avator = selectableRoundedImageView;
        this.nickName = nickName;
        BroadcastManager.getInstance(context).addAction(MineFragmentPresenter.UPDATEUNREAD, new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String command = intent.getAction();
                String s=intent.getStringExtra("String");
                if (!TextUtils.isEmpty(command)) {
                    switch (s){
                        case "updateUnread":
                            break;
                        case "loadAvator":
                            getInfo();
                            break;
                        default:
                    }
                }
            }
        });

    }
    public void getInfo(){
        atm.request(GETINFO,this);
    }

    @Override
    public Object doInBackground(int requestCode, String parameter) throws HttpException {
        switch (requestCode) {
            case GETINFO:
                return userAction.getInfo();
        }
        return null;
    }

    @Override
    public void onSuccess(int requestCode, Object result) {
        LoadDialog.dismiss(context);
        if (result==null)return;
        switch (requestCode) {
            case GETINFO:
                UserInfoResponse userInfoResponse = (UserInfoResponse) result;
                if (userInfoResponse.getState() == Const.SUCCESS) {
                    UserInfoResponse.DataBean entity = userInfoResponse.getData();
                    //Glide.with(context).load(Const.IMGURI+entity.getPhotoSmall()).skipMemoryCache(true).diskCacheStrategy( DiskCacheStrategy.NONE ).into(this.avator);
                    Glide.with(context).load(Const.IMGURI+entity.getPhotoSmall()).into(this.avator);
                    this.nickName.setText(entity.getNickName());
                    ((TextView)activity.findViewById(R.id.txt_member)).setText(entity.getLevelName().toString());
                }
                else
                NToast.shortToast(context, userInfoResponse.getMsg());
                break;
        }
    }


}