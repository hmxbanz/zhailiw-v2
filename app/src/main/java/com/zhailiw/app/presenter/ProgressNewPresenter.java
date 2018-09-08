package com.zhailiw.app.presenter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.zhailiw.app.Const;
import com.zhailiw.app.common.NToast;
import com.zhailiw.app.server.HttpException;
import com.zhailiw.app.server.async.OnDataListener;
import com.zhailiw.app.server.response.CommonResponse;
import com.zhailiw.app.view.activity.ProgressEditActivity;
import com.zhailiw.app.view.activity.ProgressNewActivity;
import com.zhailiw.app.widget.LoadDialog;

public class ProgressNewPresenter extends BasePresenter implements OnDataListener {
    private static final int PROGRESSNEW = 1,UPDATEPROGRESS=2;
    private final int processId,progressId;
    private String mode;
    private ProgressNewActivity activity;
    private String name,startDate,endDate;


    public ProgressNewPresenter(Context context){
        super(context);
        activity = (ProgressNewActivity) context;
        Intent intent=activity.getIntent();
        processId=intent.getIntExtra("processId",0);
        progressId=intent.getIntExtra("progressId",0);
        name=intent.getStringExtra("name");
        startDate=intent.getStringExtra("startDate");
        endDate=intent.getStringExtra("endDate");
        if (!TextUtils.isEmpty(name)) {
            mode="edit";
        }
    }

    public void init(EditText editName, TextView txtStartDate, TextView txtEndDate) {
        if(mode!=null) {
            editName.setText(name);
            txtStartDate.setText(startDate);
            txtEndDate.setText(endDate);
        }
    }

    @Override
    public Object doInBackground(int requestCode, String parameter) throws HttpException {
        switch (requestCode) {
            case PROGRESSNEW:
                return userAction.addProgress(processId,name,startDate,endDate);
            case UPDATEPROGRESS:
                return userAction.updateProgress(progressId,name,startDate,endDate);
        }
        return null;
    }

    @Override
    public void onSuccess(int requestCode, Object result) {
        LoadDialog.dismiss(context);
        if (result==null)return;
        switch (requestCode) {
            case PROGRESSNEW:
                CommonResponse commonResponse = (CommonResponse) result;
                if (commonResponse.getState() == Const.SUCCESS) {
                    ProgressEditActivity.StartActivity(context,processId);
                }else
                    NToast.shortToast(context, commonResponse.getMsg());

                break;
            case UPDATEPROGRESS:
                CommonResponse commonResponse2 = (CommonResponse) result;
                if (commonResponse2.getState() == Const.SUCCESS) {
                    ProgressEditActivity.StartActivity(context,processId);
                }else
                    NToast.shortToast(context, commonResponse2.getMsg());

                break;
        }
    }

    public void submit(String name, String startDate, String endDate) {
        if(TextUtils.isEmpty(name))
        {
            NToast.shortToast(context, "名称不能为空");return;
        }

        this.name=name;
        if(startDate!=null)
        this.startDate=startDate;
        if(endDate!=null)
        this.endDate=endDate;

        if(mode !=null) {
            LoadDialog.show(context);
            atm.request(UPDATEPROGRESS,this);
        }
        else
        {
            if(TextUtils.isEmpty(startDate))
            {
                NToast.shortToast(context, "开始时间不能为空");return;
            }
            if(TextUtils.isEmpty(endDate))
            {
                NToast.shortToast(context, "结束时间不能为空");return;
            }

            LoadDialog.show(context);
            atm.request(PROGRESSNEW,this);
        }

    }
}