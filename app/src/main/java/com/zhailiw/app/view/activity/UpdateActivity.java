package com.zhailiw.app.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zhailiw.app.R;
import com.zhailiw.app.presenter.UpdatePresenter;

public class UpdateActivity extends BaseActivity implements View.OnClickListener {
    public TextView txt_right;
    private UpdatePresenter presenter;
    private EditText nickName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        initViews();
        presenter = new UpdatePresenter(this);
        presenter.init();
    }

    private void initViews() {
        ((TextView)findViewById(R.id.txt_title)).setText("修改资料");
        ((TextView)findViewById(R.id.txt_right)).setText("保存");
        findViewById(R.id.layout_back).setOnClickListener(this);
        findViewById(R.id.txt_right).setOnClickListener(this);
        nickName = (EditText) findViewById(R.id.nick_name);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.txt_right:
                presenter.save(nickName);
                break;
        }

    }
}