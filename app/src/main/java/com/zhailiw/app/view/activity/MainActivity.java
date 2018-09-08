package com.zhailiw.app.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhailiw.app.R;
import com.zhailiw.app.presenter.MainPresenter;
import com.zhailiw.app.view.fragment.GalleryFragment;
import com.zhailiw.app.view.fragment.MineFragment;
import com.zhailiw.app.view.fragment.ShopFragment;

import java.util.ArrayList;
import java.util.List;

import static com.zhailiw.app.common.CommonTools.checkDeviceHasNavigationBar;

public class MainActivity extends BaseActivity {
    private ViewPager viewPager;
    private List<Fragment> mFragments;
    private ImageView mImageHome, mImageShop, mImageMe;
    private TextView mTextHome,mTextShop,mTextMe;
    private MainPresenter mainPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initMianViewPager();
        changeTextViewColor();
        changeSelectedTabState(0);
        mainPresenter = new MainPresenter(this);
        mainPresenter.init(viewPager);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        mainPresenter.reStart(intent);
    }

    private void initViews() {
        if(checkDeviceHasNavigationBar(this)){
            View rootView=findViewById(R.id.root_view);
            //LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) rootView.getLayoutParams();
            //lp.setMargins(0,0, 0, 0);
            rootView.setPadding(0,0,0,paddingParams);
        }
        RelativeLayout homeLayout, shopLayout,meLayout;
        homeLayout =  findViewById(R.id.tab_layout_home);
        shopLayout =  findViewById(R.id.tab_layout_shop);
        meLayout =  findViewById(R.id.tab_layout_me);
        mImageHome = findViewById(R.id.tab_img_home);
        mImageShop =  findViewById(R.id.tab_img_shop);
        mImageMe =  findViewById(R.id.tab_img_me);
        mTextHome =  findViewById(R.id.tab_text_home);
        mTextShop=findViewById(R.id.tab_text_shop);
        mTextMe = findViewById(R.id.tab_text_me);
        homeLayout.setOnClickListener(this);
        shopLayout.setOnClickListener(this);
        meLayout.setOnClickListener(this);
        //请求权限Ｍａｉｎ
        //checkPermissions();
    }

    public static void StartActivity(Context context, int position) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("position",position);
        context.startActivity(intent);
    }
    private void initMianViewPager() {
        FragmentPagerAdapter mFragmentPagerAdapter; //将 tab  页面持久在内存中
        viewPager = findViewById(R.id.main_viewpager);
        mFragments = new ArrayList<>();
        mFragments.add(GalleryFragment.getInstance());
        mFragments.add(ShopFragment.getInstance());
        mFragments.add(MineFragment.getInstance());
        mFragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }
            @Override
            public int getCount() {
                return mFragments.size();
            }
        };
        viewPager.setAdapter(mFragmentPagerAdapter);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setOnPageChangeListener(new PageChangerListener());
        //initData();
    }
    private void changeTextViewColor() {
        mImageHome.setImageDrawable(getResources().getDrawable(R.drawable.icon_design));
        mImageShop.setImageDrawable(getResources().getDrawable(R.drawable.icon_shop));
        mImageMe.setImageDrawable(getResources().getDrawable(R.drawable.icon_me));
        mTextHome.setTextColor(Color.parseColor("#abadbb"));
        mTextShop.setTextColor(Color.parseColor("#abadbb"));
        mTextMe.setTextColor(Color.parseColor("#abadbb"));
    }
    private void changeSelectedTabState(int position) {
        switch (position) {
            case 0:
                mTextHome.setTextColor(Color.parseColor("#cdab7e"));
                mImageHome.setImageDrawable(getResources().getDrawable(R.drawable.icon_design_on));
                break;
            case 1:
                mTextShop.setTextColor(Color.parseColor("#cdab7e"));
                mImageShop.setImageDrawable(getResources().getDrawable(R.drawable.icon_shop_on));
                break;
            case 2:
                mTextMe.setTextColor(Color.parseColor("#cdab7e"));
                mImageMe.setImageDrawable(getResources().getDrawable(R.drawable.icon_me_on));
                break;
        }
    }
    private class PageChangerListener implements ViewPager.OnPageChangeListener{
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }
        @Override
        public void onPageSelected(int position) {

            int index= viewPager.getCurrentItem();
//            if(index==3){
//                mainPresenter.onMeClick();
//            }
            changeTextViewColor();
            changeSelectedTabState(position);
            //GalleryFragment homeFragment= GalleryFragment.getInstance();
            //homeFragment.scrollView.smoothScrollTo(0, 0);
        }
        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tab_layout_home:
                viewPager.setCurrentItem(0, false);
                break;
            case R.id.tab_layout_shop:
                viewPager.setCurrentItem(1, false);
                break;
            case R.id.tab_layout_me:
                mainPresenter.onMeClick();
                break;
        }
    }
}
