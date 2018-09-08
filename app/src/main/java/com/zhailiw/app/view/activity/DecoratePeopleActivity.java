package com.zhailiw.app.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zhailiw.app.R;
import com.zhailiw.app.presenter.DecoratePeoplePresenter;
import com.zhailiw.app.server.response.DecorateDetailResponse;

public class DecoratePeopleActivity extends BaseActivity implements View.OnClickListener {

    private DecoratePeoplePresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decorate_people);
        initViews();
        presenter = new DecoratePeoplePresenter(this);
        presenter.init();
    }
    public static void StartActivity(Context context, int type, int decorateId, String name,String cellphone) {
        Intent intent = new Intent(context, DecoratePeopleActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("decorateId", decorateId);
        intent.putExtra("name", name);
        intent.putExtra("cellphone", cellphone);
        context.startActivity(intent);
    }

    private void initViews() {
        findViewById(R.id.layout_back).setOnClickListener(this);
        ((TextView)findViewById(R.id.txt_title)).setText("添加项目相关人员");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.img_start_down:
            case R.id.img_end_down:
                break;
            case R.id.btn_new_progress:
                break;
        }
    }


}
