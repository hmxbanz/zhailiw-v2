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
public class DesignCaseFragment extends BaseFragment  {
private static final int Blue=0x001bb4fb;
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

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_design_case;
    }

    @Override
    protected void initViews() {
        recycleView=  findView(R.id.recyclerView);
        swiper=  findView(R.id.swiper);
    }

    @Override
    protected void initData() {
        presenter = new DesignCasePresenter(getContext());
        presenter.init(recycleView,swiper);
        NineGridView.setImageLoader(new GlideImageLoaderForNineGridView());
    }

}
