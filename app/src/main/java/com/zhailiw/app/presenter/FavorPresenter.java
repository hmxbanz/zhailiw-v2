package com.zhailiw.app.presenter;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.zhailiw.app.R;
import com.zhailiw.app.view.activity.FavorActivity;
import com.zhailiw.app.view.fragment.ArticleFragment;
import com.zhailiw.app.view.fragment.FavorFragment;

import java.util.ArrayList;

public class FavorPresenter extends BasePresenter  {
    private static final String TAG = FavorPresenter.class.getSimpleName();
    private final BasePresenter basePresenter;
    private FavorActivity activity;
    private ViewPager viewpager;
    private ArrayList<Fragment> fragments;
    private FragmentPagerAdapter fragmentPagerAdapter;

    public FavorPresenter(Context context){
        super(context);
        activity = (FavorActivity) context;
        basePresenter = BasePresenter.getInstance(context);
    }

    public void init() {
        TabLayout tabLayout = (TabLayout) activity.findViewById(R.id.tabLayout);
////        tabLayout.addTab(tabLayout.newTab().setText("视频"), true);//添加 Tab,默认选中
////        tabLayout.addTab(tabLayout.newTab().setText("音频"),false);//添加 Tab,默认不选中
        viewpager = (ViewPager) activity.findViewById(R.id.viewpager);
        fragments = new ArrayList<>();
        fragments.add(FavorFragment.getInstance());
        fragments.add(ArticleFragment.getInstance());

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
        viewpager.setCurrentItem(0);
        viewpager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewpager);
        tabLayout.getTabAt(0).setText("东西");
        tabLayout.getTabAt(1).setText("内容");
    }

}
