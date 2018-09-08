package com.zhailiw.app.presenter;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhailiw.app.Adapter.AllOrderAdapter;
import com.zhailiw.app.Const;
import com.zhailiw.app.R;
import com.zhailiw.app.common.NToast;
import com.zhailiw.app.server.HttpException;
import com.zhailiw.app.server.async.OnDataListener;
import com.zhailiw.app.server.response.ShopCarResponse;
import com.zhailiw.app.view.activity.OrderActivity;
import com.zhailiw.app.widget.LoadDialog;

import java.util.ArrayList;
import java.util.List;


public class OrderDoneFragmentPresenter extends BasePresenter implements AllOrderAdapter.ItemClickListener,OnDataListener,SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = OrderDoneFragmentPresenter.class.getSimpleName();
    private static final int GETALLORDER = 1;
    private final List<ShopCarResponse.DataBean> list=new ArrayList<>();
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swiper;
    private AllOrderAdapter dataAdapter;
    private GridLayoutManager gridLayoutManager;
    private OrderActivity activity;
    private int pageIndex=1,totalPages;

    public OrderDoneFragmentPresenter(Context context){
        super(context);
        activity = (OrderActivity) context;
        dataAdapter = new AllOrderAdapter(this.context);
        dataAdapter.setListItems(list);
        dataAdapter.setOnItemClickListener(this);
    }

    public void init(RecyclerView recyclerView, SwipeRefreshLayout swiper) {
        this.recyclerView=recyclerView;
        this.swiper=swiper;
        this.swiper.setOnRefreshListener(this);
        this.recyclerView.setAdapter(dataAdapter);
        this.recyclerView.setNestedScrollingEnabled(false);
        gridLayoutManager=new GridLayoutManager(context,1);
        this.recyclerView.setLayoutManager(gridLayoutManager);
        atm.request(GETALLORDER,OrderDoneFragmentPresenter.this);
    }

    @Override
    public Object doInBackground(int requestCode, String parameter) throws HttpException {
        switch (requestCode) {
            case GETALLORDER:
                return userAction.getMyOrder(pageIndex+"","298","278");
        }
        return null;
    }

    @Override
    public void onSuccess(int requestCode, Object result) {
        LoadDialog.dismiss(context);
        if (result==null)return;
        switch (requestCode) {
            case GETALLORDER:
                this.swiper.setRefreshing(false);
                ShopCarResponse shopCarResponse = (ShopCarResponse) result;
                if (shopCarResponse.getState() == Const.SUCCESS) {
                        totalPages=shopCarResponse.getTotalPages();
                        if (shopCarResponse.getData().size() == 0) {
                        }
                        else {
                            list.addAll(shopCarResponse.getData());
                            dataAdapter.notifyDataSetChanged();
                        }
                    }else
                        NToast.shortToast(context, shopCarResponse.getMsg());

                    break;
        }
    }

    @Override
    public void onRefresh() {
        list.clear();
        atm.request(GETALLORDER,OrderDoneFragmentPresenter.this);
    }


    @Override
    public void onItemClick(View v, ShopCarResponse.DataBean item) {
        switch (v.getId()) {
            case R.id.btn_pay :
                break;
        }

    }

}