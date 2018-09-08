package com.zhailiw.app.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.zhailiw.app.R;
import com.zhailiw.app.common.NToast;
import com.zhailiw.app.view.activity.DecorateActivity;
import com.zhailiw.app.view.activity.LoginFirstActivity;
import com.zhailiw.app.view.fragment.DecorateAllFragment;
import com.zhailiw.app.view.fragment.DecorateDoneFragment;

import java.util.ArrayList;



public class DecoratePresenter extends BasePresenter  {
    private static final String TAG = DecoratePresenter.class.getSimpleName();
    private final BasePresenter basePresenter;
    private final int tabIndex;
    private DecorateActivity activity;
    private ViewPager viewpager;
    private ArrayList<Fragment> fragments;
    private FragmentPagerAdapter fragmentPagerAdapter;

    public DecoratePresenter(Context context){
        super(context);
        activity = (DecorateActivity) context;
        basePresenter = BasePresenter.getInstance(context);
        basePresenter.initData();
        Intent intent=activity.getIntent();
        tabIndex=intent.getIntExtra("tabIndex",0);
    }

    public void init() {
        if(basePresenter.isLogin==false){
            LoginFirstActivity.StartActivity(context);
            activity.finish();
        }

        NToast.shortToast(context,basePresenter.roleId+"");
        TabLayout tabLayout =  activity.findViewById(R.id.tabLayout);
        viewpager =  activity.findViewById(R.id.viewpager);
        fragments = new ArrayList<>();
        fragments.add(DecorateAllFragment.getInstance());
        fragments.add(DecorateDoneFragment.getInstance());

        fragmentPagerAdapter = new FragmentPagerAdapter(activity.getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }
            @Override
            public int getCount() {
                return fragments.size();
            }
        };
        viewpager.setAdapter(fragmentPagerAdapter);
        viewpager.setCurrentItem(tabIndex);
        viewpager.setOffscreenPageLimit(2);
        viewpager.setOnPageChangeListener(new PageChangerListener());
        tabLayout.setupWithViewPager(viewpager);
        tabLayout.getTabAt(0).setText("装修中");
        tabLayout.getTabAt(1).setText("已完成");
    }
    private class PageChangerListener implements ViewPager.OnPageChangeListener{
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }
        @Override
        public void onPageSelected(int position) {
            if(position==0)
            {
                DecorateAllFragment decorateAllFragment= DecorateAllFragment.getInstance();
                decorateAllFragment.onStart();
            }
            else if(position==1)
            {
                DecorateDoneFragment decorateDoneFragment= DecorateDoneFragment.getInstance();
                decorateDoneFragment.onStart();
            }

        }
        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }
}
