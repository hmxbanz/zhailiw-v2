package com.zhailiw.app.presenter;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zhailiw.app.Adapter.StyleAdapter;
import com.zhailiw.app.Const;
import com.zhailiw.app.common.NToast;
import com.zhailiw.app.server.HttpException;
import com.zhailiw.app.server.async.OnDataListener;
import com.zhailiw.app.server.response.StyleResponse;
import com.zhailiw.app.view.activity.MainActivity;
import com.zhailiw.app.view.activity.ShopActivity;
import com.zhailiw.app.widget.LoadDialog;

import java.util.ArrayList;
import java.util.List;


public class ShopFragmentPresenter extends BasePresenter implements StyleAdapter.ItemClickListener,OnDataListener,SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = ShopFragmentPresenter.class.getSimpleName();
    private static final int GETSTYLE = 1;
    private final BasePresenter basePresenter;
    private final List<StyleResponse.DataBean> list=new ArrayList<>();
    private RecyclerView recyclerView;
    private StyleAdapter dataAdapter;
    private GridLayoutManager gridLayoutManager;
    private MainActivity activity;
    private SwipeRefreshLayout swiper;

    public ShopFragmentPresenter(Context context){
        super(context);
        basePresenter = BasePresenter.getInstance(context);
        activity = (MainActivity) context;
        dataAdapter = new StyleAdapter(this.context);
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
        atm.request(GETSTYLE,ShopFragmentPresenter.this);
    }

    @Override
    public Object doInBackground(int requestCode, String parameter) throws HttpException {
        switch (requestCode) {
            case GETSTYLE:
                return userAction.getStyles();
        }
        return null;
    }

    @Override
    public void onSuccess(int requestCode, Object result) {
        LoadDialog.dismiss(context);
        if (result==null)return;
        switch (requestCode) {
            case GETSTYLE:
                this.swiper.setRefreshing(false);
                StyleResponse response = (StyleResponse) result;
                if (response.getState() == Const.SUCCESS) {
                    if (response.getData().size() == 0) {
                    }
                    else {
                        list.addAll(response.getData());
                        dataAdapter.notifyDataSetChanged();
                    }
                }
                else
                NToast.shortToast(context, response.getMsg());

                break;
        }
    }

    @Override
    public void onRefresh() {
        list.clear();
        atm.request(GETSTYLE,ShopFragmentPresenter.this);
    }


    @Override
    public void onItemClick(int position, StyleResponse.DataBean item) {
        ShopActivity.StartActivity(activity,item);
    }
}