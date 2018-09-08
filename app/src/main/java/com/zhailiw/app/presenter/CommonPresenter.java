package com.zhailiw.app.presenter;

import android.content.Context;

import com.zhailiw.app.server.async.OnDataListener;
import com.zhailiw.app.widget.LoadDialog;

public class CommonPresenter extends BasePresenter implements OnDataListener {
    //private ContactsActivity activity;
    public CommonPresenter(Context context){
        super(context);
        //activity = (ContactsActivity) context;
    }

    public void init() {
        LoadDialog.show(context);
    }

}