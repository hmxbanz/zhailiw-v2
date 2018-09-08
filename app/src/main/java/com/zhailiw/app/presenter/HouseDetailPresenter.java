package com.zhailiw.app.presenter;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.zhailiw.app.Const;
import com.zhailiw.app.R;
import com.zhailiw.app.common.NToast;
import com.zhailiw.app.server.HttpException;
import com.zhailiw.app.server.async.OnDataListener;
import com.zhailiw.app.server.response.HouseDetailResponse;
import com.zhailiw.app.view.activity.DecorateDetailActivity;
import com.zhailiw.app.view.activity.HouseDetailActivity;
import com.zhailiw.app.widget.LoadDialog;

public class HouseDetailPresenter extends BasePresenter implements OnDataListener {
    private static final int GETHOUSEDETAIL = 1;
    private final int houseId;
    private HouseDetailActivity activity;
    public HouseDetailPresenter(Context context){
        super(context);
        activity = (HouseDetailActivity) context;
        Intent intent=activity.getIntent();
        houseId=intent.getIntExtra("houseId",0);
    }

    public void init() {
        LoadDialog.show(context);
        atm.request(GETHOUSEDETAIL,HouseDetailPresenter.this);
    }


    public void toDecorateDetail() {
        DecorateDetailActivity.StartActivity(context,houseId);
    }


    @Override
    public Object doInBackground(int requestCode, String parameter) throws HttpException {
        switch (requestCode) {
            case GETHOUSEDETAIL:
                return userAction.getHouseDetail(houseId+"");
               }
        return null;
    }

    @Override
    public void onSuccess(int requestCode, Object result) {
        LoadDialog.dismiss(context);
        if (result==null)return;
        switch (requestCode) {
            case GETHOUSEDETAIL:
                HouseDetailResponse houseDetailResponse = (HouseDetailResponse) result;
                if (houseDetailResponse.getState() == Const.SUCCESS) {
                    ((TextView)activity.findViewById(R.id.txt_name)).setText(houseDetailResponse.getData().getHouseName());
                    ((TextView)activity.findViewById(R.id.txt_size)).setText(houseDetailResponse.getData().getSize());
                    ((TextView)activity.findViewById(R.id.txt_style)).setText(houseDetailResponse.getData().getHouseTypeName());
                    ((TextView)activity.findViewById(R.id.txt_budget)).setText(houseDetailResponse.getData().getBudget()+"元");
                    ((TextView)activity.findViewById(R.id.txt_address)).setText(houseDetailResponse.getData().getAddress());
                    ((TextView)activity.findViewById(R.id.txt_remark)).setText(houseDetailResponse.getData().getRemark());
                }else
                    NToast.shortToast(context, houseDetailResponse.getMsg());

                break;


        }
    }

}