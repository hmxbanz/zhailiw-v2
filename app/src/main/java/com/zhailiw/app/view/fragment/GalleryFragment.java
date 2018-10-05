package com.zhailiw.app.view.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lzy.ninegrid.NineGridView;
import com.zhailiw.app.R;
import com.zhailiw.app.loader.GlideImageLoaderForNineGridView;
import com.zhailiw.app.presenter.GalleryFragmentPresenter;

/**
 * Created by AMing on 16/6/21.
 * Company RongCloud
 */
public class GalleryFragment extends BaseFragment implements View.OnClickListener  {
private static final int Blue=0x001bb4fb;
    public static GalleryFragment instance = null;
    private GalleryFragmentPresenter presenter;
    private RecyclerView recycleView;
    private SwipeRefreshLayout swiper;

    public static GalleryFragment getInstance() {
        if (instance == null) {
            instance = new GalleryFragment();
        }
        return instance;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gallery;
    }

    @Override
    protected void initViews() {
        recycleView=  findView(R.id.recyclerView);
        swiper=  findView(R.id.swiper);

//        //简单使用
//        banner = (Banner) view.findViewById(R.id.banner);
//        banner.setImageLoader(new GlideImageLoader());//设置图片加载器
    }

    @Override
    protected void initData() {
        presenter = new GalleryFragmentPresenter(getContext());
        presenter.init(recycleView,swiper);
        //StatusBarUtil.setTranslucent(getActivity(), StatusBarUtil.);
        //StatusBarUtil.setTranslucent(getActivity(),0);
        NineGridView.setImageLoader(new GlideImageLoaderForNineGridView());
    }

}
