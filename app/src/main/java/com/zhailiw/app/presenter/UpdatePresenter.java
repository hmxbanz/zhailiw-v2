package com.zhailiw.app.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.EditText;

import com.zhailiw.app.Const;
import com.zhailiw.app.common.NToast;
import com.zhailiw.app.server.HttpException;
import com.zhailiw.app.server.broadcast.BroadcastManager;
import com.zhailiw.app.server.response.CommonResponse;
import com.zhailiw.app.view.activity.UpdateActivity;
import com.zhailiw.app.widget.LoadDialog;


public class UpdatePresenter extends BasePresenter{
    private static final int SAVE = 1;
    private UpdateActivity activity;
    private EditText nickName;

    public UpdatePresenter(Context context){
        super(context);
        this.activity = (UpdateActivity) context;
    }

    public void init() {
    }

    public void save(EditText nickName) {
        this.nickName = nickName;
        if(TextUtils.isEmpty(this.nickName.getText()))
        {
            NToast.shortToast(context,"请输入新昵称");return;
        }
        LoadDialog.show(context);
        atm.request(SAVE,this);

    }

    @Override
    public Object doInBackground(int requestCode, String parameter) throws HttpException {
        return userAction.save(this.nickName.getText().toString());
    }

    @Override
    public void onSuccess(int requestCode, Object result) {
        LoadDialog.dismiss(context);

        CommonResponse response = (CommonResponse) result;
        if (response != null && response.getState() == Const.SUCCESS) {
            BroadcastManager.getInstance(context).sendBroadcast(MePresenter.UPDATENICKNAME,this.nickName.getText().toString());
            //activity.finish();
        }
        NToast.shortToast(context,response.getMsg());

    }
}
