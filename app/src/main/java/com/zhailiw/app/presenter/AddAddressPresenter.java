package com.zhailiw.app.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.CheckBox;
import android.widget.EditText;

import com.zhailiw.app.Const;
import com.zhailiw.app.R;
import com.zhailiw.app.common.NToast;
import com.zhailiw.app.server.HttpException;
import com.zhailiw.app.server.async.OnDataListener;
import com.zhailiw.app.server.response.AddressResponse;
import com.zhailiw.app.server.response.CommonResponse;
import com.zhailiw.app.view.activity.AddAddressActivity;
import com.zhailiw.app.widget.LoadDialog;

public class AddAddressPresenter extends BasePresenter implements OnDataListener {
    private final AddAddressActivity activity;
    private static final int ADDADDRESS =1;
    private EditText contact,cellphone,address;
    private CheckBox checkBox;

    public AddAddressPresenter(Context context){
        super(context);
        activity = (AddAddressActivity) context;
    }
    public void init(EditText contact, EditText cellphone, EditText address, CheckBox checkBox) {
        this.contact=contact;
        this.cellphone=cellphone;
        this.address=address;
        this.checkBox=checkBox;
    }
    @Override
    public Object doInBackground(int requestCode, String parameter) throws HttpException {
        switch (requestCode) {
            case ADDADDRESS:
                return userAction.addAddress(
                        contact.getText().toString(),
                        cellphone.getText().toString(),
                        address.getText().toString(),
                        checkBox.isChecked()
                );
        }
        return null;
    }

    @Override
    public void onSuccess(int requestCode, Object result) {
        LoadDialog.dismiss(context);
        if (result==null)return;
        switch (requestCode) {
            case ADDADDRESS:
                CommonResponse commonResponse = (CommonResponse) result;
                if (commonResponse.getState() == Const.SUCCESS) {
                }
                    NToast.shortToast(context, commonResponse.getMsg());
                break;
        }
    }


    public void save() {
        if(TextUtils.isEmpty(contact.getText().toString()))
        {
            NToast.shortToast(context, "联系人不能为空");
            return;
        }
        if (TextUtils.isEmpty(cellphone.getText().toString())) {
            NToast.shortToast(context, "联系电话不能为空");
            return;
        }
        if (TextUtils.isEmpty(address.getText().toString())) {
            NToast.shortToast(context, "地址不能为空");
            return;
        }
        LoadDialog.show(context);
        atm.request(ADDADDRESS,this);
    }
}