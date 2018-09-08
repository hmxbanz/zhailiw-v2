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
import com.zhailiw.app.presenter.FavorFragmentPresenter;
import com.zhailiw.app.presenter.GalleryFragmentPresenter;

/**
 * Created by AMing on 16/6/21.
 * Company RongCloud
 */
public class FavorFragment extends Fragment {
    private View view;
    public static FavorFragment instance = null;
    private FavorFragmentPresenter presenter;

    public static FavorFragment getInstance() {
        if (instance == null) {
            instance = new FavorFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_favor, null);
        presenter = new FavorFragmentPresenter(getContext());
        presenter.init(view);
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


}
