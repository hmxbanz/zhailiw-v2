package com.zhailiw.app.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.zhailiw.app.R;
import com.zhailiw.app.presenter.DiaryNewPresenter;

import java.util.ArrayList;
import java.util.List;

import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.util.ConvertUtils;

public class DiaryNewActivity extends BaseActivity implements View.OnClickListener {

    private DiaryNewPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_new);
        initViews();
        presenter = new DiaryNewPresenter(this);
        presenter.init();
    }
    public static void StartActivity(Context context,int processId,int progressId,int type) {
        Intent intent = new Intent(context, DiaryNewActivity.class);
        intent.putExtra("processId", processId);
        intent.putExtra("progressId", progressId);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    private void initViews() {
        findViewById(R.id.layout_back).setOnClickListener(this);
        ((TextView)findViewById(R.id.txt_title)).setText("上传");
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                        presenter.onReturn(data);
                    break;
            }
        }
    }

}
