package com.zhailiw.app.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zhailiw.app.R;
import com.zhailiw.app.presenter.ProgressListPresenter;


public class ProgressEditActivity extends BaseActivity implements View.OnClickListener {
    private ProgressListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_edit);
        initViews();
        presenter = new ProgressListPresenter(this);
        presenter.init();
    }

    public static void StartActivity(Context context,int processId) {
        Intent intent = new Intent(context, ProgressEditActivity.class);
        intent.putExtra("processId",processId);
        context.startActivity(intent);
    }

    private void initViews() {
        findViewById(R.id.layout_back).setOnClickListener(this);
        findViewById(R.id.btn_new_progress).setOnClickListener(this);
        ((TextView)findViewById(R.id.txt_title)).setText("施工流程");
           }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.btn_new_progress:
                presenter.toProgressNewActivity();
        }
    }

}
