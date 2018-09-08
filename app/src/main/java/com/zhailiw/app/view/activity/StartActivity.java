package com.zhailiw.app.view.activity;

import android.os.Bundle;

import com.zhailiw.app.R;
import com.zhailiw.app.presenter.StartPresenter;

/**
 * Created by Administrator on 2015/10/3.
 */
public class StartActivity extends BaseActivity  {
    private StartPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        presenter = new StartPresenter(this);
        presenter.init();
        //setHeadVisibility(View.GONE);
    }

}
