package com.zhailiw.app.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zhailiw.app.R;
import com.zhailiw.app.common.CommonTools;
import com.zhailiw.app.presenter.ProgressNewPresenter;
import com.zhailiw.app.server.response.ProgressListResponse;

import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.util.CompatUtils;
import cn.qqtheme.framework.util.ConvertUtils;

public class ProgressNewActivity extends BaseActivity implements View.OnClickListener {

    private ProgressNewPresenter presenter;
    private TextView txtStartDate,txtEndDate;
    private EditText editName;
    private String startDate,endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_new);
        initViews();
        presenter = new ProgressNewPresenter(this);
        presenter.init(editName,txtStartDate,txtEndDate);
    }
    public static void StartActivity(Context context, int processId, ProgressListResponse.DataBean entity) {
        Intent intent = new Intent(context, ProgressNewActivity.class);
        intent.putExtra("processId", processId);
        if(entity !=null)
        {
            intent.putExtra("progressId", entity.getProgressID());
            intent.putExtra("name",entity.getName());
            intent.putExtra("startDate", CommonTools.formatDateTime2(entity.getStartDate()));
            intent.putExtra("endDate",CommonTools.formatDateTime2(entity.getEndDate()));
        }

        context.startActivity(intent);
    }

    private void initViews() {
        findViewById(R.id.layout_back).setOnClickListener(this);
        findViewById(R.id.img_start_down).setOnClickListener(this);
        findViewById(R.id.img_end_down).setOnClickListener(this);
        findViewById(R.id.btn_new_progress).setOnClickListener(this);

        txtStartDate=findViewById(R.id.txt_start_date);
        txtEndDate=findViewById(R.id.txt_end_date);
        txtStartDate.setOnClickListener(this);
        txtEndDate.setOnClickListener(this);
        editName=findViewById(R.id.edit_name);
        ((TextView)findViewById(R.id.txt_title)).setText("新建施工流程");
    }

    public void onYearMonthDayPicker(final int id) {
        final DatePicker picker = new DatePicker(this);
        picker.setCanceledOnTouchOutside(true);
        picker.setUseWeight(true);
        picker.setTopPadding(ConvertUtils.toPx(this, 10));
        picker.setRangeEnd(2020, 1, 11);
        picker.setRangeStart(1960, 8, 29);
        picker.setSelectedItem(2018, 1, 1);
        picker.setResetWhileWheel(false);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                //showToast(year + "-" + month + "-" + day);

                if(id==R.id.img_start_down){
                    startDate=year + "-" + month + "-" + day;
                    txtStartDate.setText(year + "-" + month + "-" + day);
                }
                else
                {
                    endDate=year + "-" + month + "-" + day;
                    txtEndDate.setText(year + "-" + month + "-" + day);
                }

            }
        });
        picker.setOnWheelListener(new DatePicker.OnWheelListener() {
            @Override
            public void onYearWheeled(int index, String year) {
                picker.setTitleText(year + "-" + picker.getSelectedMonth() + "-" + picker.getSelectedDay());
            }

            @Override
            public void onMonthWheeled(int index, String month) {
                picker.setTitleText(picker.getSelectedYear() + "-" + month + "-" + picker.getSelectedDay());
            }

            @Override
            public void onDayWheeled(int index, String day) {
                picker.setTitleText(picker.getSelectedYear() + "-" + picker.getSelectedMonth() + "-" + day);
            }
        });
        picker.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.img_start_down:
            case R.id.img_end_down:
                onYearMonthDayPicker(v.getId());
                break;
            case R.id.btn_new_progress:
                presenter.submit(editName.getText().toString(),startDate,endDate);
                break;
        }
    }
}
