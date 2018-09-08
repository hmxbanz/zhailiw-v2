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
import com.zhailiw.app.presenter.GalleryFragmentPresenter;
import com.zhailiw.app.presenter.OrderDoneFragmentPresenter;

/**
 * Created by AMing on 16/6/21.
 * Company RongCloud
 */
public class OrderDoneFragment extends Fragment implements View.OnClickListener  {
    private View view;
    public static OrderDoneFragment instance = null;
    private OrderDoneFragmentPresenter presenter;
    private RecyclerView recycleView;
    private SwipeRefreshLayout swiper;

    public static OrderDoneFragment getInstance() {
        if (instance == null) {
            instance = new OrderDoneFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_order_done, null);
        initViews();
        presenter = new OrderDoneFragmentPresenter(getContext());
        presenter.init(recycleView,swiper);
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void initViews() {
        recycleView=  view.findViewById(R.id.recyclerView);
        swiper=  view.findViewById(R.id.swiper);
    }

    @Override
    public void onClick(View v) {
    }
}
