package com.zhailiw.app.presenter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zhailiw.app.Const;
import com.zhailiw.app.R;
import com.zhailiw.app.common.NToast;
import com.zhailiw.app.server.HttpException;
import com.zhailiw.app.server.async.OnDataListener;
import com.zhailiw.app.server.response.CommonResponse;
import com.zhailiw.app.view.activity.DecoratePeopleActivity;
import com.zhailiw.app.widget.LoadDialog;

public class DecoratePeoplePresenter extends BasePresenter implements OnDataListener,View.OnClickListener {
    private static final int ADDDECORATEPEOPLE = 1;
    private final int type,decorateId;
    private final String realName,cellphone;
    private DecoratePeopleActivity activity;
    private TextView txtTypeName;
    private EditText editName,editCellphone;

    public DecoratePeoplePresenter(Context context){
        super(context);
        activity = (DecoratePeopleActivity) context;
        Intent intent=activity.getIntent();
        type = intent.getIntExtra("type", 0);
        decorateId = intent.getIntExtra("decorateId", 0);
        realName = intent.getStringExtra("name" );
        cellphone = intent.getStringExtra("cellphone" );
    }

    public void init() {
        this.txtTypeName=activity.findViewById(R.id.txt_type_name);
        this.editName=activity.findViewById(R.id.edit_name);
        this.editCellphone=activity.findViewById(R.id.edit_cellphone);
        activity.findViewById(R.id.btn_submit).setOnClickListener(this);
        if(!TextUtils.isEmpty(realName))
        this.editName.setText(realName);
        if(!TextUtils.isEmpty(cellphone))
            this.editCellphone.setText(cellphone);

        switch (type) {
            case 1:
                this.txtTypeName.setText("设计师");
                break;
            case 2:
                this.txtTypeName.setText("监工");
                break;
            case 3:
                this.txtTypeName.setText("业务员");
                break;

        }
    }

    @Override
    public Object doInBackground(int requestCode, String id) throws HttpException {
        switch (requestCode) {
            case ADDDECORATEPEOPLE:
                return userAction.addDecoratePeople(decorateId+"",type+"",this.editName.getText().toString(),this.editCellphone.getText().toString());

        }
        return null;
    }

    @Override
    public void onSuccess(final int requestCode, Object result) {
        LoadDialog.dismiss(context);
        if (result==null)return;
        switch (requestCode) {
            case ADDDECORATEPEOPLE:
                CommonResponse commonResponse = (CommonResponse) result;
                if ( commonResponse.getState()== Const.SUCCESS) {
                    activity.finish();
                }
                NToast.shortToast(context, commonResponse.getMsg());
                break;

        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                if(TextUtils.isEmpty(this.editName.getText()))
                {
                    NToast.shortToast(context,"请填写姓名");
                    return;
                }
                if(TextUtils.isEmpty(this.editCellphone.getText().toString()))
                {
                    NToast.shortToast(context,"请填写手机码号");
                    return;
                }

                LoadDialog.show(context);
                atm.request(ADDDECORATEPEOPLE,this);
                break;
        }
    }
}