package com.zhailiw.app.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.zhailiw.app.R;
import com.zhailiw.app.view.activity.MainActivity;
import com.zhailiw.app.view.activity.OrderActivity;
import com.zhailiw.app.view.fragment.OrderAllFragment;
import com.zhailiw.app.view.fragment.OrderDoneFragment;
import com.zhailiw.app.view.fragment.OrderNotPayFragment;
import com.zhailiw.app.view.fragment.OrderPayFragment;

import java.util.ArrayList;


public class OrderPresenter extends BasePresenter  {
    private static final String TAG = OrderPresenter.class.getSimpleName();
    private final BasePresenter basePresenter;
    private final int tabIndex;
    private OrderActivity activity;
    private ViewPager viewpager;
    private ArrayList<Fragment> fragments;
    private FragmentPagerAdapter fragmentPagerAdapter;

    public OrderPresenter(Context context){
        super(context);
        activity = (OrderActivity) context;
        basePresenter = BasePresenter.getInstance(context);
        Intent intent=activity.getIntent();
        tabIndex=intent.getIntExtra("tabIndex",0);
    }

    public void init() {
        TabLayout tabLayout =  activity.findViewById(R.id.tabLayout);
        viewpager =  activity.findViewById(R.id.viewpager);
        fragments = new ArrayList<>();
        fragments.add(OrderAllFragment.getInstance());
        fragments.add(OrderNotPayFragment.getInstance());
        fragments.add(OrderPayFragment.getInstance());
        fragments.add(OrderDoneFragment.getInstance());

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
        tabLayout.getTabAt(0).setText("全部");
        tabLayout.getTabAt(1).setText("未付款");
        tabLayout.getTabAt(2).setText("已付款");
        tabLayout.getTabAt(3).setText("已完成");
    }
    private class PageChangerListener implements ViewPager.OnPageChangeListener{
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }
        @Override
        public void onPageSelected(int position) {
            if(position==0)
            {
                OrderAllFragment orderAllFragment= OrderAllFragment.getInstance();
                orderAllFragment.onStart();
            }
            else if(position==1)
            {
                OrderNotPayFragment orderNotPayFragment= OrderNotPayFragment.getInstance();
                orderNotPayFragment.onStart();
            }

        }
        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }
}
