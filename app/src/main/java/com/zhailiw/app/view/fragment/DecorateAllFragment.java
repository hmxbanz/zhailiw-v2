package com.zhailiw.app.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhailiw.app.R;
import com.zhailiw.app.common.NLog;
import com.zhailiw.app.presenter.DecorateAllFragmentPresenter;

public class DecorateAllFragment extends BaseFragment  {
    public static DecorateAllFragment instance = null;
    private DecorateAllFragmentPresenter presenter;
    private RecyclerView recycleView;
    private SwipeRefreshLayout swiper;

    public static DecorateAllFragment getInstance() {
        if (instance == null) {
            instance = new DecorateAllFragment();
        }
        return instance;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_all;
    }

    @Override
    public void onStart() {
        super.onStart();
//        if(presenter !=null)
//        presenter.onRefresh();
    }
    @Override
    protected void initViews() {
        recycleView=  findView(R.id.recyclerView);
        swiper=  findView(R.id.swiper);
    }

    @Override
    protected void initData() {
        presenter = new DecorateAllFragmentPresenter(getContext());
        presenter.init(recycleView,swiper);
    }

}
