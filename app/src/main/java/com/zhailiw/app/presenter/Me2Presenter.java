package com.zhailiw.app.presenter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.leeiidesu.permission.PermissionHelper;
import com.leeiidesu.permission.callback.OnPermissionResultListener;
import com.zhailiw.app.Const;
import com.zhailiw.app.R;
import com.zhailiw.app.common.NToast;
import com.zhailiw.app.listener.AlertDialogCallBack;
import com.zhailiw.app.server.HttpException;
import com.zhailiw.app.server.response.VersionResponse;
import com.zhailiw.app.view.activity.DecorateActivity;
import com.zhailiw.app.view.activity.LoginFirstActivity;
import com.zhailiw.app.view.activity.MainActivity;
import com.zhailiw.app.view.fragment.DecorateAllFragment;
import com.zhailiw.app.view.fragment.DecorateDoneFragment;
import com.zhailiw.app.widget.DialogWithYesOrNoUtils;
import com.zhailiw.app.widget.LoadDialog;
import com.zhailiw.app.widget.downloadService.DownloadService;

import java.util.ArrayList;

import static com.zhailiw.app.common.CommonTools.getVersionInfo;


public class Me2Presenter extends BasePresenter {
    private static final String TAG = Me2Presenter.class.getSimpleName();
    private static final int CHECKVERSION = 1038;
    private final BasePresenter basePresenter;
//    private final int tabIndex;
    private MainActivity activity;
    private ViewPager viewpager;
    private ArrayList<Fragment> fragments;
    private FragmentPagerAdapter fragmentPagerAdapter;

    private String apkUrl;

    public Me2Presenter(Context context){
        super(context);
        activity = (MainActivity) context;
        basePresenter = BasePresenter.getInstance(context);
        basePresenter.initData();
//        Intent intent=activity.getIntent();
//        tabIndex=intent.getIntExtra("tabIndex",0);
    }

    public void init(TabLayout tabLayout, ViewPager viewpager) {
//        if(basePresenter.isLogin==false){
//            LoginFirstActivity.StartActivity(context);
//            activity.finish();
//        }
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
        this.viewpager=viewpager;
        this.viewpager.setAdapter(fragmentPagerAdapter);
        this.viewpager.setCurrentItem(0);
        this.viewpager.setOffscreenPageLimit(2);
        this.viewpager.setOnPageChangeListener(new PageChangerListener());
        tabLayout.setupWithViewPager(this.viewpager);
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


    @Override
    public Object doInBackground(int requestCode, String parameter) throws HttpException {
        switch (requestCode) {
            case CHECKVERSION:
                return userAction.checkVersion();
        }
        return null;
    }

    @Override
    public void onSuccess(int requestCode, Object result) {
        LoadDialog.dismiss(context);
        if (result==null)return;
        switch (requestCode) {
            case CHECKVERSION:

                break;
        }
    }

}
