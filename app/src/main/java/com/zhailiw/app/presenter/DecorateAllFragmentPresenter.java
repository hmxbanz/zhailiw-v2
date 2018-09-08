package com.zhailiw.app.presenter;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhailiw.app.Adapter.DecorateAllAdapter;
import com.zhailiw.app.Const;
import com.zhailiw.app.R;
import com.zhailiw.app.common.NToast;
import com.zhailiw.app.server.HttpException;
import com.zhailiw.app.server.async.OnDataListener;
import com.zhailiw.app.server.response.DecorateAllResponse;
import com.zhailiw.app.view.activity.DecorateActivity;
import com.zhailiw.app.widget.LoadDialog;

import java.util.ArrayList;
import java.util.List;


public class DecorateAllFragmentPresenter extends BasePresenter implements DecorateAllAdapter.ItemClickListener,OnDataListener,SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = DecorateAllFragmentPresenter.class.getSimpleName();
    private static final int GETDECORATEALL = 1;
    private final List<DecorateAllResponse.DataBean> list=new ArrayList<>();
    private RecyclerView recycleView;
    private SwipeRefreshLayout swiper;
    private DecorateAllAdapter dataAdapter;
    private GridLayoutManager gridLayoutManager;
    private DecorateActivity activity;
    private int pageIndex=1,totalPages;

    public DecorateAllFragmentPresenter(Context context){
        super(context);
        activity = (DecorateActivity) context;
        dataAdapter = new DecorateAllAdapter(this.context);
        dataAdapter.setListItems(list);
        dataAdapter.setOnItemClickListener(this);
    }

    public void init(RecyclerView recyclerView, SwipeRefreshLayout swiper) {
        this.recycleView =recyclerView;
        this.swiper=swiper;
        this.swiper.setOnRefreshListener(this);
        this.recycleView.setAdapter(dataAdapter);
        this.recycleView.setNestedScrollingEnabled(false);
        gridLayoutManager=new GridLayoutManager(context,1);
        this.recycleView.setLayoutManager(gridLayoutManager);
    }

    @Override
    public Object doInBackground(int requestCode, String parameter) throws HttpException {
        switch (requestCode) {
            case GETDECORATEALL:
                return userAction.getDecorate(pageIndex+"",null);
        }
        return null;
    }

    @Override
    public void onSuccess(int requestCode, Object result) {
        LoadDialog.dismiss(context);
        if (result==null)return;
        switch (requestCode) {
            case GETDECORATEALL:
                this.swiper.setRefreshing(false);
                DecorateAllResponse decorateAllResponse = (DecorateAllResponse) result;
                if (decorateAllResponse.getState() == Const.SUCCESS) {
                        totalPages=decorateAllResponse.getTotalPages();
                        if (decorateAllResponse.getData().size() == 0) {
                        }
                        else {
                            activity.findViewById(R.id.layout_empty).setVisibility(View.INVISIBLE);
                            list.addAll(decorateAllResponse.getData());
                            dataAdapter.notifyDataSetChanged();
                        }
                    }else
                        NToast.shortToast(context, decorateAllResponse.getMsg());

                    break;
        }
    }

    @Override
    public void onRefresh() {
        list.clear();
        LoadDialog.show(context);
        atm.request(GETDECORATEALL,DecorateAllFragmentPresenter.this);
    }


    @Override
    public void onItemClick(View v, DecorateAllResponse.DataBean item) {
        switch (v.getId()) {
//            case R.id.btn_pay :
//                OrderDetailActivity.StartActivity(activity,item.getOrderID()+"");
//                break;
//            case R.id.btn_cancel:
//                this.removeOrderId=item.getOrderID();
//                int index=0;
//                for (int i=0;i<list.size();i++) {
//                    if(list.get(i).getOrderID()==this.removeOrderId) {
//                        index = i;
//                        break;
//                    }
//                }
//                list.remove(index);
//                dataAdapter.notifyDataSetChanged();
//                atm.request(REMOVEORDER,this);
//                break;
        }

    }

}