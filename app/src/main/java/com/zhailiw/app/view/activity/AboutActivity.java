package com.zhailiw.app.view.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zhailiw.app.R;
import com.zhailiw.app.presenter.MePresenter;
import com.zhailiw.app.widget.SelectableRoundedImageView;


public class AboutActivity extends BaseActivity implements View.OnClickListener {
    private MePresenter mMePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initViews();
        //mMePresenter =new MePresenter(this);
        //mMePresenter.init();
    }
public static void StartActivity(Context c)
{
    Intent intent = new Intent(c, AboutActivity.class);
    c.startActivity(intent);
}

    public void initViews(){
        ((TextView)findViewById(R.id.txt_title)).setText("关于我们");
        findViewById(R.id.layout_back).setOnClickListener(this);
        TextView txtAbout = findViewById(R.id.txt_about);
        String info = "     宅里家居文化发展（广州）有限公司，(以下简称宅里)是一家集软装设计、家具配饰、艺术陈设为一体的专业软装设计机构。\n" +
                "\n" +
                "     用心打造室内空间的风格化整体效果并拥有专业化的项目细分设计经验，以独特的设计理念，追求品质、崇尚个性、力求极致之意、打造与众不同的视觉空间。\n" +
                "\n" +
                "     宅里作为华南地区整体软装行业的知名品牌之一，拥有专业的独立设计师团队，独立网站，APP，线下体验馆等，为用户提供一站式的居家风格搭配服务与产品供应。";
        txtAbout.setText(info);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_back:
                finish();
                break;
        }
    }


}
