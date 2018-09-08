package com.zhailiw.app.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zhailiw.app.R;
import com.zhailiw.app.presenter.MePresenter;
import com.zhailiw.app.widget.SelectableRoundedImageView;


public class MeActivity extends BaseActivity implements View.OnClickListener {
    private MePresenter mePresenter;

    public static final int REQUEST_CODE_ASK_PERMISSIONS = 101;
    private Uri selectUri;
    private SelectableRoundedImageView selectableRoundedImageView;
    private TextView txtNickName,txtBirthday,txtCellphone,txtWeixin,txtQQ,txtSex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        initViews();
        mePresenter =new MePresenter(this);
        mePresenter.init(selectableRoundedImageView, txtNickName,txtBirthday,txtCellphone,txtWeixin,txtQQ,txtSex);
    }

    public void initViews(){
        ((TextView)findViewById(R.id.txt_title)).setText("人个信息");
        findViewById(R.id.layout_back).setOnClickListener(this);
        selectableRoundedImageView= findViewById(R.id.img_avatar);
        selectableRoundedImageView.setOnClickListener(this);
        findViewById(R.id.layout_nickname).setOnClickListener(this);
        findViewById(R.id.layout_birthday).setOnClickListener(this);
        txtNickName =  findViewById(R.id.txt_nickname);
        txtBirthday =  findViewById(R.id.txt_birthday);
        txtCellphone =  findViewById(R.id.txt_cellphone);
        txtWeixin =  findViewById(R.id.txt_weixin);
        txtQQ =  findViewById(R.id.txt_qq);
        findViewById(R.id.btn_login_off).setOnClickListener(this);
        txtBirthday.setOnClickListener(this);
        txtSex =  findViewById(R.id.txt_sex);
        txtSex.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.img_avatar:
                mePresenter.showPhotoDialog();
                break;
            case R.id.layout_nickname:
                startActivity(new Intent(this,UpdateActivity.class));
                break;
            case R.id.btn_login_off:
                mePresenter.loginOff();
                break;
            case R.id.txt_birthday:
                mePresenter.onYearMonthDayPicker(txtBirthday);
                break;
            case R.id.txt_sex:
                mePresenter.onSexPicker(txtSex);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mePresenter.onActivityResult(requestCode, resultCode, data);
    }


}
