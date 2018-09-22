package com.zhailiw.app.presenter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.zhailiw.app.Adapter.ProgressEditAdapter;
import com.zhailiw.app.Const;
import com.zhailiw.app.R;
import com.zhailiw.app.common.NToast;
import com.zhailiw.app.listener.AlertDialogCallBack;
import com.zhailiw.app.server.HttpException;
import com.zhailiw.app.server.async.OnDataListener;
import com.zhailiw.app.server.response.CommonResponse;
import com.zhailiw.app.server.response.ProgressListResponse;
import com.zhailiw.app.view.activity.ProgressEditActivity;
import com.zhailiw.app.view.activity.ProgressNewActivity;
import com.zhailiw.app.widget.DialogWithYesOrNoUtils;
import com.zhailiw.app.widget.LoadDialog;
import com.zhailiw.app.widget.draglist.DragListView;

import java.util.ArrayList;
import java.util.List;

public class ProgressListPresenter extends BasePresenter implements OnDataListener,ProgressEditAdapter.ItemClickListener,DragListView.ItemMoveListener {
    private static final int GETPROGRESSLIST = 1,UPDATEORDER=2,DELPROGRESS=3,SETCOMMIT=4,SETCOMMITBYSELLER=5,SETCOMMITFAILURE=6;
    private final int processId;
    private ProgressEditActivity activity;
    private DragListView drag_list;
    private int StartItem,StopItem;
    private List<ProgressListResponse.DataBean> list;
    private ProgressListResponse.DataBean newNew, oldOld;
    private int progressId;

    public ProgressListPresenter(Context context){
        super(context);
        activity = (ProgressEditActivity) context;
        Intent intent=activity.getIntent();
        processId=intent.getIntExtra("processId",0);
    }

    public void init() {
        drag_list = (DragListView) activity.findViewById(R.id.drag_list);
        List<String> list=new ArrayList<>();
        for (int i=0;i<30;i++){
            list.add("test+"+i);
        }
        LoadDialog.show(context);
        atm.request(GETPROGRESSLIST,ProgressListPresenter.this);
    }

    @Override
    public Object doInBackground(int requestCode, String parameter) throws HttpException {
        switch (requestCode) {
            case GETPROGRESSLIST:
                return userAction.getProgressList(processId+"");
            case UPDATEORDER:
                return userAction.updateProgressOrder(newNew.getProgressID()+"", newNew.getOrder()+"", oldOld.getProgressID()+"", oldOld.getOrder()+"");
            case DELPROGRESS:
                return userAction.delProgress(progressId+"");
            case SETCOMMIT:
                return userAction.setProcessState(processId+"","327");//施工流程监理确认
            case SETCOMMITBYSELLER:
                return userAction.setProcessState(processId+"","319");//施工流程客户经理确认
            case SETCOMMITFAILURE:
                return userAction.setProcessState(processId+"","318");//施工流程客户经理确认
        }
        return null;
    }

    @Override
    public void onSuccess(int requestCode, Object result) {
        LoadDialog.dismiss(context);
        if (result==null)return;
        switch (requestCode) {
            case GETPROGRESSLIST:
                ProgressListResponse progressListResponse = (ProgressListResponse) result;
                if (progressListResponse.getState() == Const.SUCCESS) {
                    if (progressListResponse.getData().size() == 0) {
                    }
                    else {
                        //装入item
                        list=progressListResponse.getData();
                        ProgressEditAdapter adapter=new ProgressEditAdapter(context);
                        adapter.setListener(this);
                        adapter.addDatas(progressListResponse.getData());
                        drag_list.setAdapter(adapter);
                        drag_list.setmListener(this);
                    }
                }else
                    NToast.shortToast(context, progressListResponse.getMsg());
                break;
            case UPDATEORDER:
                CommonResponse commonResponse = (CommonResponse) result;
                if (commonResponse.getState() == Const.SUCCESS) {
                    atm.request(GETPROGRESSLIST,ProgressListPresenter.this);
                }else
                    NToast.shortToast(context, commonResponse.getMsg());
                break;
            case DELPROGRESS:
                CommonResponse commonResponse2 = (CommonResponse) result;
                if (commonResponse2.getState() == Const.SUCCESS) {
                    atm.request(GETPROGRESSLIST,ProgressListPresenter.this);
                }else
                    NToast.shortToast(context, commonResponse2.getMsg());
                break;
            case SETCOMMIT:
            case SETCOMMITBYSELLER:
            case SETCOMMITFAILURE:
                CommonResponse commonResponse3 = (CommonResponse) result;
                if (commonResponse3.getState() == Const.SUCCESS) {
                }
                    NToast.shortToast(context, commonResponse3.getMsg());
                break;
        }
    }


    @Override
    public void onStartItem(int i) {
        this.StartItem=i;
    }

    @Override
    public void onStopItem(int i) {
        this.StopItem=i;
    }

    @Override
    public void onDrop() {
        newNew =list.get(StartItem);
        oldOld =list.get(StopItem);
        NToast.shortToast(context,"施工流程顺序已修改，请确认施工日期并修改。");
        LoadDialog.show(context);
        atm.request(UPDATEORDER,ProgressListPresenter.this);
    }

    public void toProgressNewActivity() {
        ProgressNewActivity.StartActivity(context,processId, null);
    }

    @Override
    public void onTxtDelClick(View v, ProgressListResponse.DataBean entity) {
        progressId=entity.getProgressID();
        LoadDialog.show(context);
        atm.request(DELPROGRESS,ProgressListPresenter.this);
    }
    @Override
    public void onItemClick(View v, ProgressListResponse.DataBean entity) {
        progressId=entity.getProgressID();
        ProgressNewActivity.StartActivity(context,processId,entity);
    }

    public void setCommit() {
        DialogWithYesOrNoUtils dialog = DialogWithYesOrNoUtils.getInstance();
        dialog.showDialog(context, "是否确认", new AlertDialogCallBack() {
            @Override
            public void executeEvent() {
                LoadDialog.show(context);
                if(roleId==14)//监理
                    atm.request(SETCOMMIT,ProgressListPresenter.this);
                else
                    atm.request(SETCOMMITBYSELLER,ProgressListPresenter.this);
            }

            @Override
            public void onCancle() {
                super.onCancle();
                if(roleId==13 || roleId==16)//客户经理
                    atm.request(SETCOMMITFAILURE,ProgressListPresenter.this);
            }
        });
        if(roleId==13 || roleId==16)//客户经理
        {
            dialog.setCancleText("不通过");
            dialog.setConfirmText("通过");
        }
    }
}