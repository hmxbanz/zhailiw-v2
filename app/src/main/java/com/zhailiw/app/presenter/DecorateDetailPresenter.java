package com.zhailiw.app.presenter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zhailiw.app.Adapter.DecorateDetailAdapter;
import com.zhailiw.app.Adapter.DecorateDetailListAdapter;
import com.zhailiw.app.Adapter.ProgressListAdapter;
import com.zhailiw.app.Const;
import com.zhailiw.app.R;
import com.zhailiw.app.common.NToast;
import com.zhailiw.app.server.HttpException;
import com.zhailiw.app.server.async.OnDataListener;
import com.zhailiw.app.server.response.DecorateDetailResponse;
import com.zhailiw.app.server.response.DecorateListResponse;
import com.zhailiw.app.server.response.ProgressListResponse;
import com.zhailiw.app.view.activity.DecorateDetailActivity;
import com.zhailiw.app.widget.LoadDialog;
import com.zhailiw.app.widget.permissionLibrary.PermissionsManager;
import com.zhailiw.app.widget.permissionLibrary.PermissionsResultAction;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;


public class DecorateDetailPresenter extends BasePresenter implements DecorateDetailAdapter.ItemClickListener, OnDataListener, SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = DecorateDetailPresenter.class.getSimpleName();
    private int houseId, processId, position;
    private DecorateDetailActivity activity;
    private RecyclerView recycleView;
    private DecorateDetailAdapter dataAdapter;
    private GridLayoutManager gridLayoutManager;
    private static final int GETDECORATE = 1, GETDECORATELIST = 2, GETPROGRESSLIST = 3;
    private final List<DecorateDetailResponse.DataBean> list = new ArrayList<>();
    private SwipeRefreshLayout swiper;
    private boolean isClick = true;
    private RecyclerView recyclerViewDataHolder;
    private DecorateDetailResponse.DataBean DetailRespon;
    private DecorateDetailAdapter.DataHolder holder;

    public DecorateDetailPresenter(Context context) {
        super(context);
        activity = (DecorateDetailActivity) context;
        Intent intent = activity.getIntent();
        houseId = intent.getIntExtra("houseId", 0);
    }

    public void init() {
        this.recycleView = activity.findViewById(R.id.recyclerView);
        this.swiper = activity.findViewById(R.id.swiper);
        ;
        this.swiper.setOnRefreshListener(this);
        dataAdapter = new DecorateDetailAdapter(activity);
        dataAdapter.setListItems(list);
        View header = LayoutInflater.from(activity).inflate(R.layout.listitem_decorate_detail_header, null);
        dataAdapter.setHeaderView(header);
        dataAdapter.setOnItemClickListener(this);
        gridLayoutManager = new GridLayoutManager(context, 1);
        this.recycleView.setLayoutManager(gridLayoutManager);

        //onRefresh();
    }

    @Override
    public void onRefresh() {
        list.clear();
        atm.request(GETDECORATE, DecorateDetailPresenter.this);
    }

    @Override
    public Object doInBackground(int requestCode, String parameter) throws HttpException {
        switch (requestCode) {
            case GETDECORATE:
                return userAction.getDecorateDetail(houseId + "");
            case GETDECORATELIST:
                return userAction.getDecorateList(processId + "");
            case GETPROGRESSLIST:
                return userAction.getProgressList(processId + "");
        }
        return null;
    }

    @Override
    public void onSuccess(int requestCode, Object result) {
        LoadDialog.dismiss(context);
        this.swiper.setRefreshing(false);
        if (result == null) return;
        switch (requestCode) {
            case GETDECORATE:
                DecorateDetailResponse decorateDetailResponse = (DecorateDetailResponse) result;
                if (decorateDetailResponse.getState() == Const.SUCCESS) {
                    if (decorateDetailResponse.getData().size() == 0) {
                    } else {
                        list.addAll(decorateDetailResponse.getData());
                        this.recycleView.setAdapter(dataAdapter);
                        //dataAdapter.notifyDataSetChanged();
                    }
                } else
                    NToast.shortToast(context, decorateDetailResponse.getMsg());

                break;
            case GETDECORATELIST:
                DecorateListResponse decorateListResponse = (DecorateListResponse) result;
                if (decorateListResponse.getState() == Const.SUCCESS) {
                    if (decorateListResponse.getData().size() == 0) {
                    } else {
                        onImgArrowClick(holder, DetailRespon, decorateListResponse, position);
                    }
                } else
                    NToast.shortToast(context, decorateListResponse.getMsg());

                break;
            case GETPROGRESSLIST:
                ProgressListResponse progressListResponse = (ProgressListResponse) result;
                if (progressListResponse.getState() == Const.SUCCESS) {
                    if (progressListResponse.getData().size() == 0) {
                    } else {
                        //装入item
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 1);
                        recyclerViewDataHolder.setLayoutManager(gridLayoutManager);
                        ProgressListAdapter listAdapter = new ProgressListAdapter(context);
                        listAdapter.setListItems(progressListResponse.getData());
                        listAdapter.notifyDataSetChanged();
                        recyclerViewDataHolder.setAdapter(listAdapter);
                        recyclerViewDataHolder.setNestedScrollingEnabled(false);
                        holder.getTxtTips().setVisibility(View.GONE);
                    }
                } else
                    NToast.shortToast(context, progressListResponse.getMsg());

                break;
        }
    }

    @Override
    public void onItemClick(View v, DecorateDetailResponse.DataBean item) {

    }

    @Override
    public void onImgArrowClick(DecorateDetailAdapter.DataHolder dataHolder, DecorateDetailResponse.DataBean item, DecorateListResponse listResponse, int position) {
        if (holder != dataHolder) {
            isClick = true;
        }
        this.processId = item.getProcessID();
        this.position = position;
        holder = dataHolder;
        recyclerViewDataHolder = holder.getRecyclerView();
        DetailRespon = item;
        if (listResponse == null) {
            atm.request(GETDECORATELIST, DecorateDetailPresenter.this);
            return;
        }

        if (isClick == true) {
            dataHolder.getImgArrow().setImageDrawable(context.getResources().getDrawable(R.drawable.icon_up));
            isClick = false;
            recyclerViewDataHolder.setVisibility(View.VISIBLE);
        } else {
            dataHolder.getImgArrow().setImageDrawable(context.getResources().getDrawable(R.drawable.icon_down));
            isClick = true;
            recyclerViewDataHolder.setVisibility(View.GONE);
        }

        //装入item
        DecorateDetailListAdapter listAdapter = new DecorateDetailListAdapter(context);
        listAdapter.setParentPosition(position);
        listAdapter.setListItems(listResponse.getData());
        listAdapter.notifyDataSetChanged();
        recyclerViewDataHolder.setAdapter(listAdapter);
        recyclerViewDataHolder.setNestedScrollingEnabled(false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 1);
        recyclerViewDataHolder.setLayoutManager(gridLayoutManager);

    }

    @Override
    public void onProcessLoadding(DecorateDetailAdapter.DataHolder dataHolder, DecorateDetailResponse.DataBean item) {
        this.processId = item.getProcessID();
        holder = dataHolder;
        recyclerViewDataHolder = holder.getRecyclerView();
        atm.request(GETPROGRESSLIST, DecorateDetailPresenter.this);
    }

    @Override
    public void onPhoneClick(View v, DecorateDetailResponse.DataBean item,int role) {
        if(item.getDesingerCellPhone()==null || item.getWorkerCellPhone()==null || item.getSellerCellPhone()==null){
            NToast.shortToast(context,"手机号未填写，拔打失败。");
            return;
        }

        if(role==1)
            callPhone(item.getDesingerCellPhone().trim());
        else if(role==2)
            callPhone(item.getWorkerCellPhone().trim());
        else if(role==3)
            callPhone(item.getSellerCellPhone().trim());
    }

    /**
     * 拨打电话（直接拨打电话）
     *
     * @param phoneNum 电话号码
     */
    public void callPhone(String phoneNum) {
        String[] Permissions = new String[]{Manifest.permission.CALL_PHONE};
        //权限申请
        PermissionsManager.getInstance().requestPermissionsIfNecessaryForResult(activity,
                Permissions,
                new PermissionsResultAction() {
                    @Override
                    public void onGranted() {

                    }

                    @Override
                    public void onDenied(String permission) {
                        Toast.makeText(context, "获取权限失败，请点击后允许获取", Toast.LENGTH_SHORT).show();
                    }
                }, true);

        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        context.startActivity(intent);
    }



}