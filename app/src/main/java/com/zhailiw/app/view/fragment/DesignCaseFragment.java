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

import com.lzy.ninegrid.NineGridView;
import com.zhailiw.app.R;
import com.zhailiw.app.loader.GlideImageLoaderForNineGridView;
import com.zhailiw.app.presenter.DesignCasePresenter;
import com.zhailiw.app.presenter.GalleryFragmentPresenter;

/**
 * Created by AMing on 16/6/21.
 * Company RongCloud
 */
public class DesignCaseFragment extends Fragment implements View.OnClickListener  {
private static final int Blue=0x001bb4fb;
    private View view;
    public static DesignCaseFragment instance = null;
    private DesignCasePresenter presenter;
    private RecyclerView recycleView;
    private SwipeRefreshLayout swiper;

    public static DesignCaseFragment getInstance() {
        if (instance == null) {
            instance = new DesignCaseFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_design_case, null);
        initViews();
//        initData();
        presenter = new DesignCasePresenter(getContext());
        presenter.init(recycleView,swiper);
        //StatusBarUtil.setTranslucent(getActivity(), StatusBarUtil.);
        //StatusBarUtil.setTranslucent(getActivity(),0);
        NineGridView.setImageLoader(new GlideImageLoaderForNineGridView());
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
    public void onDestroy() {
        super.onDestroy();
    }
    public void onClick(View v) {
    }

}
