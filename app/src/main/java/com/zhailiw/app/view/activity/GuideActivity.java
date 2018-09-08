package com.zhailiw.app.view.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.zhailiw.app.Adapter.GuideAdapter;
import com.zhailiw.app.R;
import com.zhailiw.app.listener.OnPageScrolledListener;
import com.zhailiw.app.widget.scaleIndicator.ScaleIndicatorLayout;


public class GuideActivity extends BaseActivity {
    private ViewPager viewPager;
    private ScaleIndicatorLayout indicator;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
    }
    private void initView()  {
        //setHeadVisibility(View.GONE);
        indicator =(ScaleIndicatorLayout) findViewById(R.id.indicator);
        this.viewPager = ((ViewPager)findViewById(R.id.GuiderViewPager));
        GuideAdapter GuideAdapter = new GuideAdapter(getSupportFragmentManager(), this);
        this.viewPager.setAdapter(GuideAdapter);
        OnPageScrolledListener listener = new OnPageScrolledListener() {
            @Override
            public void onPageScrolled(int curItem, int nextItem, float radio) {
                indicator.setRadio(curItem, 1f - radio);
                indicator.setRadio(nextItem, radio);
            }
            @Override
            public void onPageSelected(int position) {
                indicator.setVisibility(position == 2 ? View.GONE : View.VISIBLE);
            }
        };
        listener.setViewPager(this.viewPager);
        int curIndex = 0;
        this.viewPager.setCurrentItem(curIndex);
        indicator.setRadio(curIndex, 1f);
    }
    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

}
