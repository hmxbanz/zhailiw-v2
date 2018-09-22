package com.zhailiw.app.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.zhailiw.app.Adapter.DiaryDetailAdapter;
import com.zhailiw.app.Const;
import com.zhailiw.app.R;
import com.zhailiw.app.common.NToast;
import com.zhailiw.app.listener.AlertDialogCallBack;
import com.zhailiw.app.server.HttpException;
import com.zhailiw.app.server.async.OnDataListener;
import com.zhailiw.app.server.response.CommonResponse;
import com.zhailiw.app.server.response.DecorateDetailResponse;
import com.zhailiw.app.server.response.DecorateListResponse;
import com.zhailiw.app.view.activity.DiaryDetailActivity;
import com.zhailiw.app.view.activity.DiaryNewActivity;
import com.zhailiw.app.widget.DialogWithYesOrNoUtils;
import com.zhailiw.app.widget.LoadDialog;

import java.util.ArrayList;
import java.util.List;

public class DiaryDetailPresenter extends BasePresenter implements OnDataListener, View.OnClickListener,DiaryDetailAdapter.ItemClickListener {
    private static final int GETDIAYDETAIL = 1,GETDIAYDETAILFORGROGRESS=2,SETISTOP = 3,SETDELETE=4,SETSTATE=5,SETPROCESSSTATE=6;
    private final int processId,progressId,fromType,progressState;
    private RecyclerView recyclerView;
    private DiaryDetailActivity activity;
    private int diaryType;
    private DiaryDetailAdapter dataAdapter;
    private List<DecorateListResponse.DataBean> list = new ArrayList<>();
    private GridLayoutManager gridLayoutManager;
    private View layoutEmpty;
    private DecorateListResponse.DataBean itemData;
    private Button btnSubmit;

    public DiaryDetailPresenter(Context context){
        super(context);
        activity = (DiaryDetailActivity) context;
        Intent intent=activity.getIntent();
        processId=intent.getIntExtra("processId",0);
        progressId=intent.getIntExtra("progressId",0);
        fromType=intent.getIntExtra("fromType",0);
        progressState=intent.getIntExtra("progressState",0);
    }

    public void init() {
        this.recyclerView = activity.findViewById(R.id.recyclerView);
        this.layoutEmpty = activity.findViewById(R.id.layout_empty);
        this.btnSubmit=activity.findViewById(R.id.btn_new_diary);
        this.btnSubmit.setOnClickListener(this);

        dataAdapter=new DiaryDetailAdapter(activity);
        dataAdapter.setListItems(list);
        dataAdapter.setOnItemClickListener(this);
        if((roleId==13 ||roleId==16) && progressId!=0)
        {
            this.btnSubmit.setText("确认施工完成!");
            dataAdapter.setRoleId(this.roleId);
        }

        if(progressState ==329)//已完成
        {
            this.btnSubmit.setText("已完成!");
            this.btnSubmit.setOnClickListener(null);
        }

        if(roleId==15)//
        {
            dataAdapter.setRoleId(this.roleId);
            this.btnSubmit.setVisibility(View.INVISIBLE);
        }


        gridLayoutManager=new GridLayoutManager(context,1);
        this.recyclerView.setLayoutManager(gridLayoutManager);
        this.recyclerView.setAdapter(dataAdapter);


        checkRequestType();
    }

    private void checkRequestType() {
        if(progressId==0) {
            LoadDialog.show(context);
            atm.request(GETDIAYDETAIL, this);
        }
        else
        {
            LoadDialog.show(context);
            atm.request(GETDIAYDETAILFORGROGRESS, this);
        }
    }

    @Override
    public Object doInBackground(int requestCode, String parameter) throws HttpException {
        switch (requestCode) {
            case GETDIAYDETAIL:
                return userAction.getDecorateList(processId+"");
            case GETDIAYDETAILFORGROGRESS:
                return userAction.getProgressDiary(progressId+"");
            case SETISTOP:
                return userAction.updateWorkLogState(itemData.getProcessDetailID()+"","1");
            case SETDELETE:
                return userAction.updateWorkLogState(itemData.getProcessDetailID()+"","3");
            case SETSTATE:
                return userAction.setProgressState(progressId+"","329");
            case SETPROCESSSTATE:
                return userAction.setProcessState(processId+"","329");
        }
        return null;
    }

    @Override
    public void onSuccess(int requestCode, Object result) {
        LoadDialog.dismiss(context);
        if (result == null) return;
        switch (requestCode) {
            case GETDIAYDETAIL:
            case GETDIAYDETAILFORGROGRESS:
                DecorateListResponse decorateListResponse = (DecorateListResponse) result;
                if (decorateListResponse.getState() == Const.SUCCESS) {
                    if (decorateListResponse.getData().size() == 0) {

                    } else {
                        list.addAll(decorateListResponse.getData());
                        dataAdapter.notifyDataSetChanged();
                        layoutEmpty.setVisibility(View.INVISIBLE);
                    }
                } else
                    NToast.shortToast(context, decorateListResponse.getMsg());

                break;
            case SETISTOP:
            case SETDELETE:
                CommonResponse commonResponse = (CommonResponse) result;
                if (commonResponse.getState() == Const.SUCCESS) {
                    list.clear();
                    checkRequestType();
                }
                    NToast.shortToast(context, commonResponse.getMsg());

                break;
            case SETSTATE:
            case SETPROCESSSTATE:
                CommonResponse commonResponse2 = (CommonResponse) result;
                if (commonResponse2.getState() == Const.SUCCESS) {
                    this.btnSubmit.setEnabled(false);
                }
                NToast.shortToast(context, commonResponse2.getMsg());

                break;

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_new_diary:
                if ((roleId == 13 || roleId==16) && progressId!=0) {
                    DialogWithYesOrNoUtils.getInstance().showDialog(context, "是否确认", new AlertDialogCallBack() {
                        @Override
                        public void executeEvent() {
                            if(processId==0)
                                atm.request(SETSTATE, DiaryDetailPresenter.this);
                            else
                                atm.request(SETPROCESSSTATE, DiaryDetailPresenter.this);
                        }
                    });
                    return;
                }
                if(processId==0)
                    DiaryNewActivity.StartActivity(context,0,progressId,fromType);
                else
                    DiaryNewActivity.StartActivity(context,processId,0,fromType);
                break;
        }
    }

    @Override
    public void onItemClick(View v, DecorateDetailResponse.DataBean item) {

    }

     @Override
    public void onDelClick(DecorateListResponse.DataBean data) {
        this.itemData=data;
         DialogWithYesOrNoUtils.getInstance().showDialog(context, "是否作废", new AlertDialogCallBack() {
             @Override
             public void executeEvent() {
                 atm.request(SETDELETE, DiaryDetailPresenter.this);
             }
         });
    }

    @Override
    public void onToTopClick(DecorateListResponse.DataBean data) {
        this.itemData=data;
        DialogWithYesOrNoUtils.getInstance().showDialog(context, "是否顶置", new AlertDialogCallBack() {
            @Override
            public void executeEvent() {
                atm.request(SETISTOP, DiaryDetailPresenter.this);
            }
        });
    }
}