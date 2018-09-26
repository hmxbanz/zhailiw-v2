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
import android.widget.Toast;

import com.leeiidesu.permission.PermissionHelper;
import com.leeiidesu.permission.callback.OnPermissionResultListener;
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
import com.zhailiw.app.server.response.HousePeopleResponse;
import com.zhailiw.app.server.response.ProgressListResponse;
import com.zhailiw.app.view.activity.DecorateDetailActivity;
import com.zhailiw.app.widget.LoadDialog;

import java.util.ArrayList;
import java.util.List;


public class DecorateDetailPresenter extends BasePresenter implements DecorateDetailAdapter.ItemClickListener, OnDataListener, SwipeRefreshLayout.OnRefreshListener, OnPermissionResultListener {
    private static final String TAG = DecorateDetailPresenter.class.getSimpleName();
    private int houseId, processId, position;
    private DecorateDetailActivity activity;
    private RecyclerView recycleView;
    private DecorateDetailAdapter dataAdapter;
    private GridLayoutManager gridLayoutManager;
    private static final int GETDECORATE = 1, GETDECORATELIST = 2, GETPROGRESSLIST = 3,GETHOUSEPEOPLE=4;
    private final List<DecorateDetailResponse.DataBean> list = new ArrayList<>();
    private SwipeRefreshLayout swiper;
    private boolean isClick = true;
    private RecyclerView recyclerViewDataHolder;
    private DecorateDetailAdapter.DataHolder holder;
    private String phoneNum;

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
        dataAdapter.setRoleId(this.roleId);
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
            case GETHOUSEPEOPLE:
                return userAction.getHousePeoples(houseId + "");
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
                        list.addAll(decorateDetailResponse.getData());//取列表后再取联系人
                        atm.request(GETHOUSEPEOPLE,this);
                        //dataAdapter.notifyDataSetChanged();
                    }
                } else
                    NToast.shortToast(context, decorateDetailResponse.getMsg());

                break;
            case GETHOUSEPEOPLE:
                HousePeopleResponse housePeopleResponse = (HousePeopleResponse) result;
                if (housePeopleResponse.getState() == Const.SUCCESS) {
                    if (housePeopleResponse.getData().size() == 0) {
                    } else {
                        dataAdapter.setHeaderList(housePeopleResponse.getData());
                        this.recycleView.setAdapter(dataAdapter);
                    }
                } else
                    NToast.shortToast(context, housePeopleResponse.getMsg());

                break;
            case GETDECORATELIST:
                DecorateListResponse decorateListResponse = (DecorateListResponse) result;
                if (decorateListResponse.getState() == Const.SUCCESS) {
                    if (decorateListResponse.getData().size() == 0) {
                    } else {
                        //onImgArrowClick(holder, DetailRespon, decorateListResponse, position);

                        //
                        //装入item
                        DecorateDetailListAdapter listAdapter = new DecorateDetailListAdapter(context);
                        listAdapter.setParentPosition(position);
                        listAdapter.setListItems(decorateListResponse.getData());
                        recyclerViewDataHolder.setAdapter(listAdapter);
                        recyclerViewDataHolder.setNestedScrollingEnabled(false);
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 1);
                        recyclerViewDataHolder.setLayoutManager(gridLayoutManager);
                        recyclerViewDataHolder.setVisibility(View.VISIBLE);
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
    public void onImgArrowClick(DecorateDetailAdapter.DataHolder dataHolder, DecorateDetailResponse.DataBean item, int position) {
//        if (holder != dataHolder) {
//            holder = dataHolder;
//            item.setName("展开");
//            recyclerViewDataHolder = holder.getRecyclerView();
//        }
        this.processId = item.getProcessID();
        this.position = position;
        recyclerViewDataHolder = dataHolder.getRecyclerView();
        if (item.getName().contains("装修进程")) {
            item.setName("展开");
        }



        if (item.getName().contains("展开")) {
            item.setName("收起");
            dataHolder.getImgArrow().setImageDrawable(context.getResources().getDrawable(R.drawable.icon_up));
            atm.request(GETDECORATELIST, DecorateDetailPresenter.this);
            return;
        } else {
            item.setName("展开");
            dataHolder.getImgArrow().setImageDrawable(context.getResources().getDrawable(R.drawable.icon_down));
            recyclerViewDataHolder.setVisibility(View.GONE);
        }
    }

    @Override
    public void onProgressLoadding(DecorateDetailAdapter.DataHolder dataHolder, DecorateDetailResponse.DataBean item) {
        this.processId = item.getProcessID();
        holder = dataHolder;
        recyclerViewDataHolder = holder.getRecyclerView();
        atm.request(GETPROGRESSLIST, DecorateDetailPresenter.this);
    }

    @Override
    public void onPhoneClick(DecorateDetailAdapter.HeaderHolder v, DecorateDetailResponse.DataBean item, int role) {
        if(v.getTxtDesignerCellphone().getText()==null ){
            NToast.shortToast(context,"手机号未填写，拔打失败。");
            return;
        }
        if(v.getTxtWorkerCellphone().getText()==null ){
            NToast.shortToast(context,"手机号未填写，拔打失败。");
            return;
        }
        if(v.getTxtSellerCellphone().getText()==null ){
            NToast.shortToast(context,"手机号未填写，拔打失败。");
            return;
        }

            switch (role){
                case 1:
                    callPhone(v.getTxtDesignerCellphone().getText().toString().trim());
                    break;
                case 2:
                    callPhone(v.getTxtWorkerCellphone().getText().toString().trim());
                    break;
                case 3:
                    callPhone(v.getTxtSellerCellphone().getText().toString().trim());
                    break;
            }


    }

    /**
     * 拨打电话（直接拨打电话）
     *
     * @param phoneNum 电话号码
     */
    public void callPhone(String phoneNum) {
        this.phoneNum=phoneNum;
        String[] Permissions = new String[]{Manifest.permission.CALL_PHONE};
//        //权限申请
        PermissionHelper.with(activity)
                .permissions(Permissions)
                .showOnRationale("拔打电话权限", "取消", "我知道了")    //用户拒绝过但没有勾选不再提示会显示对话框
                .showOnDenied("必需拔打电话权限才能使用", "取消", "去设置") //用户勾选不再提示会显示对话框
                .listener(this)
                .request();
    }


    @Override
    public void onGranted() {
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

    @Override
    public void onFailed(ArrayList<String> deniedPermissions) {
        Toast.makeText(context, "获取权限失败，请点击后允许获取", Toast.LENGTH_SHORT).show();
    }
}
