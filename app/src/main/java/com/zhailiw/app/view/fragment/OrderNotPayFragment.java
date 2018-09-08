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
import com.zhailiw.app.presenter.NotPayOrderFragmentPresenter;

/**
 * Created by AMing on 16/6/21.
 * Company RongCloud
 */
public class OrderNotPayFragment extends Fragment {
    private View view;
    public static OrderNotPayFragment instance = null;
    private NotPayOrderFragmentPresenter presenter;
    private RecyclerView recycleView;
    private SwipeRefreshLayout swiper;

    public static OrderNotPayFragment getInstance() {
        if (instance == null) {
            instance = new OrderNotPayFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_not_pay, null);
        initViews();
        presenter = new NotPayOrderFragmentPresenter(getContext());
        presenter.init(recycleView, swiper);
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        presenter.onRefresh();
    }


    private void initViews() {
        recycleView = view.findViewById(R.id.recyclerView);
        swiper = view.findViewById(R.id.swiper);
    }
}
