package com.zhailiw.app.presenter;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.zhailiw.app.Adapter.FavorAdapter;
import com.zhailiw.app.Adapter.StyleAdapter;
import com.zhailiw.app.Const;
import com.zhailiw.app.R;
import com.zhailiw.app.common.NToast;
import com.zhailiw.app.listener.EndlessRecyclerOnScrollListener;
import com.zhailiw.app.server.HttpException;
import com.zhailiw.app.server.async.OnDataListener;
import com.zhailiw.app.server.response.FavorResponse;
import com.zhailiw.app.server.response.StyleResponse;
import com.zhailiw.app.view.activity.FavorActivity;
import com.zhailiw.app.view.activity.MainActivity;
import com.zhailiw.app.view.activity.ProductDetailActivity;
import com.zhailiw.app.view.activity.ShopActivity;
import com.zhailiw.app.widget.LoadDialog;

import java.util.ArrayList;
import java.util.List;


public class FavorFragmentPresenter extends BasePresenter implements FavorAdapter.ItemClickListener,OnDataListener,SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = FavorFragmentPresenter.class.getSimpleName();
    private static final int GETFAVOR = 1;
    private final List<FavorResponse.DataBean> list=new ArrayList<>();
    private RecyclerView recyclerView;
    private FavorAdapter dataAdapter;
    private GridLayoutManager gridLayoutManager;
    private FavorActivity activity;
    private SwipeRefreshLayout swiper;
    private int pageIndex=1,totalPages;
    private EndlessRecyclerOnScrollListener onScrollListener;

    public FavorFragmentPresenter(Context context){
        super(context);
        activity = (FavorActivity) context;
        dataAdapter = new FavorAdapter(this.context);
        dataAdapter.setListItems(list);
        dataAdapter.setOnItemClickListener(this);
        gridLayoutManager=new GridLayoutManager(context,1);
        View footerView=LayoutInflater.from(context).inflate(R.layout.recyclerview_footer,null);
        dataAdapter.setFooterView(footerView);
    }

    public void init(View view) {
        this.recyclerView=view.findViewById(R.id.recyclerView);
        this.swiper=view.findViewById(R.id.swiper);
        this.swiper.setOnRefreshListener(this);
        this.recyclerView.setAdapter(dataAdapter);
        this.recyclerView.setNestedScrollingEnabled(false);
        this.recyclerView.setLayoutManager(gridLayoutManager);
        onScrollListener=new EndlessRecyclerOnScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                Logger.d("GETGALLERY currentPage:%s", currentPage);
                pageIndex = currentPage;
                if(pageIndex<=totalPages) {
                    dataAdapter.onLoading();
                    atm.request(GETFAVOR, FavorFragmentPresenter.this);
                }
                else
                {
                    dataAdapter.onLoadingDone();
                }
            }
        };
        this.recyclerView.addOnScrollListener(onScrollListener);
        atm.request(GETFAVOR,FavorFragmentPresenter.this);
    }

    @Override
    public Object doInBackground(int requestCode, String parameter) throws HttpException {
        switch (requestCode) {
            case GETFAVOR:
                return userAction.getFavors(pageIndex+"");
        }
        return null;
    }

    @Override
    public void onSuccess(int requestCode, Object result) {
        this.swiper.setRefreshing(false);
        LoadDialog.dismiss(context);
        if (result==null)return;
        switch (requestCode) {
            case GETFAVOR:
                FavorResponse response = (FavorResponse) result;
                if (response.getState() == Const.SUCCESS) {
                    if (response.getData().size() == 0) {
                    }
                    else {
                        list.addAll(response.getData());
                        dataAdapter.notifyDataSetChanged();
                    }
                    dataAdapter.onLoadingEnd();
                }else {
                    NToast.shortToast(context, response.getMsg());
                }

                break;
        }
    }

    @Override
    public void onRefresh() {
        pageIndex=1;
        this.onScrollListener.reset();
        list.clear();
        dataAdapter.notifyDataSetChanged();
        atm.request(GETFAVOR,FavorFragmentPresenter.this);
    }


    @Override
    public void onItemClick(int position, FavorResponse.DataBean item) {
        ProductDetailActivity.StartActivity(activity,item.getProductId()+"");
    }
}