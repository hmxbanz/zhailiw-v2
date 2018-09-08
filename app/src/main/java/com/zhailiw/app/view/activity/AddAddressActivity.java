package com.zhailiw.app.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.zhailiw.app.R;
import com.zhailiw.app.presenter.AddAddressPresenter;

public class AddAddressActivity extends BaseActivity implements View.OnClickListener {
    private AddAddressPresenter presenter;
    private RecyclerView recyclerView;
    private CheckBox checkBox;
    private EditText contact,cellphone,address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        initViews();
        presenter = new AddAddressPresenter(this);
        presenter.init(contact,cellphone,address,checkBox);
    }

    private void initViews() {
        ((TextView)findViewById(R.id.txt_title)).setText("增加收货地址");
        ((TextView)findViewById(R.id.txt_right)).setText("保存");
        findViewById(R.id.txt_right).setOnClickListener(this);
        findViewById(R.id.layout_back).setOnClickListener(this);

        contact = findViewById(R.id.contact);
        cellphone = findViewById(R.id.cellphone);
        address = findViewById(R.id.address);
        checkBox = findViewById(R.id.checkbox);
        Drawable drawable = this.getResources().getDrawable(R.drawable.selector_checkbox);
        drawable.setBounds(0,0,50,50);
        if(Build.VERSION.SDK_INT>=21)
            drawable.setTint(getResources().getColor(R.color.mainColor));
        checkBox.setCompoundDrawables(drawable,null,null,null);

        recyclerView = findViewById(R.id.recyclerView);
    }

    public static void StartActivity(Context context) {
        Intent intent = new Intent(context, AddAddressActivity.class);
        context.startActivity(intent);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.txt_right:
                presenter.save();
                break;
        }

    }
}