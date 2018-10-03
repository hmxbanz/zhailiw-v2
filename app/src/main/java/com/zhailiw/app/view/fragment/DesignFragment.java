package com.zhailiw.app.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhailiw.app.R;
import com.zhailiw.app.common.NLog;
import com.zhailiw.app.presenter.DesignPresenter;


/**
 * Created by AMing on 16/6/21.
 * Company RongCloud
 */
public class DesignFragment extends Fragment implements View.OnClickListener {
    public static DesignFragment mFragment = null;
    private View view;
    private DesignPresenter presenter;
    private ImageView imgRight;
    private TabLayout tabLayout;
    private ViewPager viewpager;

    public static DesignFragment getInstance() {
        if (mFragment == null) {
            mFragment = new DesignFragment();
        }
        return mFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_design, null);
        initViews();
        presenter = new DesignPresenter(getContext());
        presenter.init(tabLayout,viewpager);
        //initData();
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
        txtTitle.setText("设计");
        view.findViewById(R.id.img_left).setVisibility(View.INVISIBLE);
        tabLayout =  view.findViewById(R.id.tabLayoutDesign);
        viewpager =  view.findViewById(R.id.viewpagerDesign);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.img_right:
                break;
        }
    }

}
