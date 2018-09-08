package com.zhailiw.app.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.youth.banner.Banner;
import com.zhailiw.app.Adapter.ShopAdapter;
import com.zhailiw.app.Const;
import com.zhailiw.app.R;
import com.zhailiw.app.common.NToast;
import com.zhailiw.app.listener.EndlessRecyclerOnScrollListener;
import com.zhailiw.app.loader.GlideImageLoader;
import com.zhailiw.app.server.HttpException;
import com.zhailiw.app.server.async.OnDataListener;
import com.zhailiw.app.server.response.ADResponse;
import com.zhailiw.app.server.response.ShopResponse;
import com.zhailiw.app.server.response.SystemObjResponse;
import com.zhailiw.app.view.activity.ProductDetailActivity;
import com.zhailiw.app.view.activity.ShopActivity;
import com.zhailiw.app.widget.LoadDialog;
import com.zhailiw.app.widget.progressBar.MaterialProgressBar;

import java.util.ArrayList;
import java.util.List;

public class ShopPresenter extends BasePresenter implements OnDataListener, ShopAdapter.ItemClickListener,SwipeRefreshLayout.OnRefreshListener {
    private ShopActivity activity;
    private ShopAdapter dataAdapter;
    private  List<ShopResponse.DataBean> list=new ArrayList<>();
    private static final int GETPRODUCTS=1;
    private static final int GETADS=2;
    private GridLayoutManager gridLayoutManager;
    private RecyclerView recyclerView;
    private String styleId,productTypeId;
    private EndlessRecyclerOnScrollListener onScrollListener;
    private int pageIndex=1,totalPages;
    private View footerView;
    private SwipeRefreshLayout swiper;
    private List<SystemObjResponse.SysObjBean.ChildDictionariesBean> tabs;

    public ShopPresenter(Context context){
        super(context);
        activity = (ShopActivity) context;
        dataAdapter = new ShopAdapter(this.context);
        dataAdapter.setListItems(list);
        dataAdapter.setOnItemClickListener(this);
        footerView= LayoutInflater.from(context).inflate(R.layout.recyclerview_footer,null);
        dataAdapter.setFooterView(footerView);
        SystemObjResponse.SysObjBean option = systemObj.get(1);
        tabs=option.getChildDictionaries();
        dataAdapter.setTabs(tabs);
    }

    public void init(RecyclerView recyclerView, SwipeRefreshLayout swiper) {
        Intent intent=activity.getIntent();
        this.styleId=intent.getStringExtra("styleId");
        this.swiper=swiper;
        this.swiper.setOnRefreshListener(this);
        this.recyclerView=recyclerView;
        gridLayoutManager=new GridLayoutManager(context,2);
        this.recyclerView.setLayoutManager(gridLayoutManager);
        this.recyclerView.setAdapter(dataAdapter);
        this.recyclerView.setNestedScrollingEnabled(false);
        onScrollListener=new EndlessRecyclerOnScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                Logger.d("GETPRODUCTS currentPage:%s", currentPage);
                pageIndex = currentPage;
                TextView tips=footerView.findViewById(R.id.tips);
                MaterialProgressBar progressBar=footerView.findViewById(R.id.progress_wheel);
                footerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                tips.setText(R.string.layout_dialog_loading);
                if(pageIndex<=totalPages) {
                    atm.request(GETPRODUCTS, ShopPresenter.this);
                }
                else
                {
                    progressBar.setVisibility(View.GONE);
                    tips.setText("我是有底线的");
                }
            }
        };
        this.recyclerView.addOnScrollListener(onScrollListener);
        LoadDialog.show(context);
        atm.request(GETPRODUCTS,this);
    }
    @Override
    public Object doInBackground(int requestCode, String parameter) throws HttpException {
        switch (requestCode) {
            case GETPRODUCTS :
                return userAction.getProducts(pageIndex+"",this.styleId,this.productTypeId);
            case GETADS :
                return userAction.getAds(this.styleId);
        }
        return null;
    }

    @Override
    public void onSuccess(int requestCode, Object result) {
        LoadDialog.dismiss(context);
        if (result==null)return;
        switch (requestCode) {
            case GETPRODUCTS:
                ShopResponse shopResponse = (ShopResponse) result;
                if (shopResponse.getState() == Const.SUCCESS) {
                    totalPages=shopResponse.getTotalPages();
                    if (shopResponse.getData().size() == 0) {
                    }
                    else {
                        list.addAll(shopResponse.getData());
                        dataAdapter.notifyDataSetChanged();
                        footerView.setVisibility(View.GONE);
                        this.swiper.setRefreshing(false);
                    }
                }else {
                    NToast.shortToast(context, shopResponse.getMsg());
                }
                atm.request(GETADS,this);
                break;
            case GETADS:
                ADResponse adResponse = (ADResponse) result;
                if (adResponse.getState() == Const.SUCCESS) {
                    if (adResponse.getData().size() == 0) {
                    }
                    else {
                        List<String> images = new ArrayList<>();
                        for (ADResponse.DataBean bean : adResponse.getData()) {
                            String s=Const.IMGURI+bean.getADPhoto();
                            images.add(s);
                        }
                        Banner banner=dataAdapter.getAdHolder().getBanner();
                        banner.setImageLoader(new GlideImageLoader());
                        banner.setImages(images);//设置图片集合
                        banner.start();
//                        dataAdapter.notifyDataSetChanged();
                    }
                }else {
                    NToast.shortToast(context, adResponse.getMsg());
                }
                break;
        }
    }

    @Override
    public void onItemClick(int position, ShopResponse.DataBean item) {
        ProductDetailActivity.StartActivity(activity,item.getProductId()+"");
    }

    @Override
    public void onTabExpand() {
        productTypeId=null;
        pageIndex=1;
        this.onScrollListener.reset();
        list.clear();
        dataAdapter.notifyDataSetChanged();
        atm.request(GETPRODUCTS,ShopPresenter.this);
    }

    @Override
    public void onTabItemClick(int position, SystemObjResponse.SysObjBean.ChildDictionariesBean item) {
        productTypeId=item.getId()+"";
        pageIndex=1;
        this.onScrollListener.reset();
        list.clear();
        dataAdapter.notifyDataSetChanged();
        atm.request(GETPRODUCTS,ShopPresenter.this);
    }

    @Override
    public void onRefresh() {
        pageIndex=1;
        this.onScrollListener.reset();
        list.clear();
        atm.request(GETPRODUCTS,ShopPresenter.this);
    }
}