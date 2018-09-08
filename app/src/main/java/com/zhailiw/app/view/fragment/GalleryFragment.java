package com.zhailiw.app.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lzy.ninegrid.NineGridView;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.zhailiw.app.R;
import com.zhailiw.app.common.NToast;
import com.zhailiw.app.presenter.GalleryFragmentPresenter;

/**
 * Created by AMing on 16/6/21.
 * Company RongCloud
 */
public class GalleryFragment extends Fragment implements View.OnClickListener  {
private static final int Blue=0x001bb4fb;
    private View view;
    public static GalleryFragment instance = null;
    private GalleryFragmentPresenter presenter;
    private TextView title;
    private RelativeLayout layout_me;
    private RecyclerView recycleView;
    private SwipeRefreshLayout swiper;

    public static GalleryFragment getInstance() {
        if (instance == null) {
            instance = new GalleryFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_gallery, null);
        initViews();
//        initData();
        presenter = new GalleryFragmentPresenter(getContext());
        presenter.init(recycleView,swiper);
        //StatusBarUtil.setTranslucent(getActivity(), StatusBarUtil.);
        //StatusBarUtil.setTranslucent(getActivity(),0);
        NineGridView.setImageLoader(new GlideImageLoader());
        return view;
    }

    /** Glide 加载 */
    private class GlideImageLoader implements NineGridView.ImageLoader {
        @Override
        public void onDisplayImage(Context context, ImageView imageView, String url) {
            Glide.with(context).load(url)//
                    //.placeholder(R.drawable.ic_default_color)//
                    //.error(R.drawable.ic_default_color)//
                    //.diskCacheStrategy(DiskCacheStrategy.ALL)//
                    .into(imageView);
        }

        @Override
        public Bitmap getCacheImage(String url) {
            return null;
        }
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
        ((TextView)view.findViewById(R.id.txt_title)).setText("灵感图库");
        view.findViewById(R.id.layout_back).setVisibility(View.INVISIBLE);
//        //简单使用
//        banner = (Banner) view.findViewById(R.id.banner);
//        banner.setImageLoader(new GlideImageLoader());//设置图片加载器
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
       // presenter.unbindService();
    }
    @Override
    public void onClick(View v) {
      presenter.onMeClick(v);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == GalleryFragmentPresenter.REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    NToast.longToast(getActivity(), "解析结果:"+result);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    NToast.longToast(getActivity(), "解析二维码失败");
                }
            }
        }
    }
}
