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
import com.zhailiw.app.presenter.AllOrderFragmentPresenter;
public class OrderAllFragment extends Fragment  {
    private View view;
    public static OrderAllFragment instance = null;
    private AllOrderFragmentPresenter presenter;
    private RecyclerView recycleView;
    private SwipeRefreshLayout swiper;

    public static OrderAllFragment getInstance() {
        if (instance == null) {
            instance = new OrderAllFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_all, null);
        initViews();
        presenter = new AllOrderFragmentPresenter(getContext());
        presenter.init(recycleView,swiper);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onRefresh();
    }

    private void initViews() {
        recycleView=  view.findViewById(R.id.recyclerView);
        swiper=  view.findViewById(R.id.swiper);
    }

}
