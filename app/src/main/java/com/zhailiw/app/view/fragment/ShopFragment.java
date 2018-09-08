package com.zhailiw.app.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhailiw.app.R;
import com.zhailiw.app.presenter.ShopFragmentPresenter;

/**
 * Created by AMing on 16/6/21.
 * Company RongCloud
 */
public class ShopFragment extends Fragment implements View.OnClickListener  {
private static final int Blue=0x001bb4fb;
    private View view;
    public static ShopFragment instance = null;
    private ShopFragmentPresenter presenter;
    private TextView title;
    private RelativeLayout layout_me;
    private RecyclerView recycleView;
    private SwipeRefreshLayout swiper;

    public static ShopFragment getInstance() {
        if (instance == null) {
            instance = new ShopFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_gallery, null);
        initViews();
//        initData();
        presenter = new ShopFragmentPresenter(getContext());
        presenter.init(recycleView,swiper);
        //StatusBarUtil.setTranslucent(getActivity(), StatusBarUtil.);
        //StatusBarUtil.setTranslucent(getActivity(),0);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        //presenter.loadData();
    }

    private void initViews() {
        recycleView=  view.findViewById(R.id.recyclerView);
        swiper=  view.findViewById(R.id.swiper);
        ((TextView)view.findViewById(R.id.txt_title)).setText("家居商城");
        view.findViewById(R.id.layout_back).setVisibility(View.INVISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
       // presenter.unbindService();
    }
    @Override
    public void onClick(View v) {

    }

}
