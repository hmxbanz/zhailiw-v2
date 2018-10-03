package com.zhailiw.app.presenter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.leeiidesu.permission.PermissionHelper;
import com.leeiidesu.permission.callback.OnPermissionResultListener;
import com.zhailiw.app.Const;
import com.zhailiw.app.R;
import com.zhailiw.app.common.NToast;
import com.zhailiw.app.listener.AlertDialogCallBack;
import com.zhailiw.app.server.HttpException;
import com.zhailiw.app.server.response.VersionResponse;
import com.zhailiw.app.view.activity.MainActivity;
import com.zhailiw.app.view.fragment.DecorateAllFragment;
import com.zhailiw.app.view.fragment.DecorateDoneFragment;
import com.zhailiw.app.view.fragment.DesignCaseFragment;
import com.zhailiw.app.view.fragment.DesignerFragment;
import com.zhailiw.app.view.fragment.GalleryFragment;
import com.zhailiw.app.view.fragment.ShopFragment;
import com.zhailiw.app.widget.DialogWithYesOrNoUtils;
import com.zhailiw.app.widget.LoadDialog;
import com.zhailiw.app.widget.downloadService.DownloadService;

import java.util.ArrayList;

import static com.zhailiw.app.common.CommonTools.getVersionInfo;


public class DesignPresenter extends BasePresenter implements OnPermissionResultListener {
    private static final String TAG = DesignPresenter.class.getSimpleName();
    private static final int CHECKVERSION = 1038;
    private final BasePresenter basePresenter;
    //    private final int tabIndex;
    private MainActivity activity;
    private ViewPager viewpager;
    private ArrayList<Fragment> fragments;
    private FragmentPagerAdapter fragmentPagerAdapter;

    private String apkUrl;

    public DesignPresenter(Context context){
        super(context);
        activity = (MainActivity) context;
        basePresenter = BasePresenter.getInstance(context);
        basePresenter.initData();
//        Intent intent=activity.getIntent();
//        tabIndex=intent.getIntExtra("tabIndex",0);
    }

    public void init(TabLayout tabLayout, ViewPager viewpager) {
        fragments = new ArrayList<>();
        fragments.add(GalleryFragment.getInstance());
        fragments.add(DesignCaseFragment.getInstance());
        fragments.add(DesignerFragment.getInstance());
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
        tabLayout.getTabAt(0).setText("设计案例");
        tabLayout.getTabAt(1).setText("装修案例");
        tabLayout.getTabAt(2).setText("设计师");
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
                VersionResponse versionResponse = (VersionResponse) result;
                if (versionResponse.getState() == Const.SUCCESS) {
                    final VersionResponse.ResultEntity entity=versionResponse.getAndroid();
                    String[] versionInfo = getVersionInfo(activity);
                    int versionCode = Integer.parseInt(versionInfo[0]);
                    if(entity.getVersionCode()>versionCode)
                    {
                        DialogWithYesOrNoUtils dialog=DialogWithYesOrNoUtils.getInstance();
                        dialog.showDialog(activity, "发现新版本:"+entity.getVersionName(), new AlertDialogCallBack(){
                            @Override
                            public void executeEvent() {
                                goToDownload(entity.getDownloadUrl());
                            }
                        });
                        dialog.setContent(entity.getVersionInfo());
                    }
                }else {
                    NToast.shortToast(activity, "版本检测："+versionResponse.getMsg());
                }
                break;
        }
    }
    private void goToDownload(final String apkUrl) {
        this.apkUrl=apkUrl;
        //权限申请
        String[] Permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        PermissionHelper.with(activity)
                .permissions(Permissions)
                .showOnRationale("存储权限", "取消", "我知道了")    //用户拒绝过但没有勾选不再提示会显示对话框
                .showOnDenied("必需拔打存储权限才能更新", "取消", "去设置") //用户勾选不再提示会显示对话框
                .listener(this)
                .request();
    }

    @Override
    public void onGranted() {
        Intent intent=new Intent(activity,DownloadService.class);
        intent.putExtra("url", apkUrl);
        activity.startService(intent);
    }

    @Override
    public void onFailed(ArrayList<String> deniedPermissions) {

    }
}
