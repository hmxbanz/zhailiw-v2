package com.zhailiw.app.view.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhailiw.app.R;
import com.zhailiw.app.common.NLog;
import com.zhailiw.app.common.PhotoUtils;
import com.zhailiw.app.presenter.Me2Presenter;
import com.zhailiw.app.presenter.MineFragmentPresenter;
import com.zhailiw.app.view.activity.AboutActivity;
import com.zhailiw.app.view.activity.AddressActivity;
import com.zhailiw.app.view.activity.FavorActivity;
import com.zhailiw.app.view.activity.MeActivity;
import com.zhailiw.app.view.activity.OrderActivity;
import com.zhailiw.app.view.activity.ShopCarActivity;
import com.zhailiw.app.widget.SelectableRoundedImageView;
import com.zhailiw.app.widget.progressBar.MaterialProgressBar;


/**
 * Created by AMing on 16/6/21.
 * Company RongCloud
 */
public class MeFragment extends Fragment implements View.OnClickListener {
    public static MeFragment mFragment = null;
    private View view;
    private Me2Presenter presenter;
    private ImageView imgRight;
    private TabLayout tabLayout;
    private ViewPager viewpager;

    public static MeFragment getInstance() {
        if (mFragment == null) {
            mFragment = new MeFragment();
        }
        return mFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_me, null);
        initViews();
        presenter = new Me2Presenter(getContext());
        presenter.init(tabLayout,viewpager);
        //initData();
//        compareVersion();
        NLog.d("fragment-----","onCreateView");
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        NLog.d("fragment-----","onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        NLog.d("fragment-----","onResume");
    }

    private void initViews() {
        TextView txtTitle = (TextView) view.findViewById(R.id.txt_title);
        txtTitle.setText("我的装修");
        view.findViewById(R.id.img_left).setVisibility(View.INVISIBLE);
        imgRight=view.findViewById(R.id.img_right);
        imgRight.setVisibility(View.VISIBLE);
        imgRight.setOnClickListener(this);
        tabLayout =  view.findViewById(R.id.tabLayout);
        viewpager =  view.findViewById(R.id.viewpager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.layout_buy_shop:
                break;
            case R.id.img_right:
                MeActivity.StartActivity(getContext());
                break;
        }
    }



}
