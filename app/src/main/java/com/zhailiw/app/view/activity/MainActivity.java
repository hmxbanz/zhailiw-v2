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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhailiw.app.R;
import com.zhailiw.app.presenter.MainPresenter;
import com.zhailiw.app.view.fragment.CompanyFragment;
import com.zhailiw.app.view.fragment.DesignerFragment;
import com.zhailiw.app.view.fragment.MeFragment;
import com.zhailiw.app.view.fragment.MineFragment;
import com.zhailiw.app.view.fragment.WorkFragment;

import java.util.ArrayList;
import java.util.List;

import static com.zhailiw.app.common.CommonTools.checkDeviceHasNavigationBar;

public class MainActivity extends BaseActivity {
    private ViewPager viewPager;
    private List<Fragment> mFragments;
    private ImageView mImageDesign, mImageCompnay, mImageArt,mImageMe;
    private TextView mTextDesign, mTextCompany,mTextArt,mTextMe;
    private MainPresenter mainPresenter;
    private RelativeLayout designLayout, companyLayout,artLayout,meLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initMianViewPager();
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
        designLayout =  findViewById(R.id.tab_layout_design);
        companyLayout =  findViewById(R.id.tab_layout_company);
        artLayout =  findViewById(R.id.tab_layout_art);
        meLayout =  findViewById(R.id.tab_layout_me);
        mImageDesign = findViewById(R.id.tab_img_design);
        mImageCompnay =  findViewById(R.id.tab_img_company);
        mImageArt =  findViewById(R.id.tab_img_art);
        mImageMe =  findViewById(R.id.tab_img_me);
        mTextDesign =  findViewById(R.id.tab_text_design);
        mTextCompany =findViewById(R.id.tab_text_company);
        mTextArt =findViewById(R.id.tab_text_art);
        mTextMe = findViewById(R.id.tab_text_me);
        designLayout.setOnClickListener(this);
        companyLayout.setOnClickListener(this);
        artLayout.setOnClickListener(this);
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
        mFragments.add(DesignerFragment.getInstance());
        mFragments.add(CompanyFragment.getInstance());
        mFragments.add(WorkFragment.getInstance());
        mFragments.add(MeFragment.getInstance());
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
        viewPager.setOffscreenPageLimit(4);
        viewPager.setOnPageChangeListener(new PageChangerListener());
        //initData();
    }
    private void changeTextViewColor() {
        mImageDesign.setImageDrawable(getResources().getDrawable(R.drawable.icon_design));
        mImageCompnay.setImageDrawable(getResources().getDrawable(R.drawable.icon_company));
        mImageArt.setImageDrawable(getResources().getDrawable(R.drawable.icon_art));
        mImageMe.setImageDrawable(getResources().getDrawable(R.drawable.icon_me));
        mTextDesign.setTextColor(Color.parseColor("#464646"));
        mTextCompany.setTextColor(Color.parseColor("#464646"));
        mTextArt.setTextColor(Color.parseColor("#464646"));
        mTextMe.setTextColor(Color.parseColor("#464646"));
        designLayout.setBackgroundColor(Color.parseColor("#f9f9f9"));
        companyLayout.setBackgroundColor(Color.parseColor("#f9f9f9"));
        artLayout.setBackgroundColor(Color.parseColor("#f9f9f9"));
        meLayout.setBackgroundColor(Color.parseColor("#f9f9f9"));
    }
    private void changeSelectedTabState(int position) {
        changeTextViewColor();
        switch (position) {
            case 0:
                mTextDesign.setTextColor(Color.parseColor("#ffffff"));
                mImageDesign.setImageDrawable(getResources().getDrawable(R.drawable.icon_design_on));
                designLayout.setBackgroundColor(getResources().getColor(R.color.mainColor));
                break;
            case 1:
                mTextCompany.setTextColor(Color.parseColor("#ffffff"));
                mImageCompnay.setImageDrawable(getResources().getDrawable(R.drawable.icon_company_on));
                companyLayout.setBackgroundColor(getResources().getColor(R.color.mainColor));
                break;
            case 2:
                mTextArt.setTextColor(Color.parseColor("#ffffff"));
                mImageArt.setImageDrawable(getResources().getDrawable(R.drawable.icon_art_on));
                artLayout.setBackgroundColor(getResources().getColor(R.color.mainColor));
                break;
            case 3:
                mTextMe.setTextColor(Color.parseColor("#ffffff"));
                mImageMe.setImageDrawable(getResources().getDrawable(R.drawable.icon_me_on));
                meLayout.setBackgroundColor(getResources().getColor(R.color.mainColor));
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
            case R.id.tab_layout_design:
                viewPager.setCurrentItem(0, false);
                break;
            case R.id.tab_layout_company:
                viewPager.setCurrentItem(1, false);
                break;
            case R.id.tab_layout_art:
                viewPager.setCurrentItem(2, false);
                break;
            case R.id.tab_layout_me:
                viewPager.setCurrentItem(3, false);
                mainPresenter.onMeClick();
                break;
        }
    }
}
