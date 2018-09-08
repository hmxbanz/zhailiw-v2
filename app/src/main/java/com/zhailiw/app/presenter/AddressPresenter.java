package com.zhailiw.app.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zhailiw.app.Adapter.AddressAdapter;
import com.zhailiw.app.Const;
import com.zhailiw.app.R;
import com.zhailiw.app.common.NToast;
import com.zhailiw.app.server.HttpException;
import com.zhailiw.app.server.async.OnDataListener;
import com.zhailiw.app.server.response.AddressResponse;
import com.zhailiw.app.server.response.CommonResponse;
import com.zhailiw.app.view.activity.AddressActivity;
import com.zhailiw.app.widget.LoadDialog;

import java.util.ArrayList;
import java.util.List;

public class AddressPresenter extends BasePresenter implements OnDataListener, AddressAdapter.ItemClickListener,SwipeRefreshLayout.OnRefreshListener {
    private final AddressActivity activity;
    private final AddressAdapter dataAdapter;
    private final List<AddressResponse.DataBean> list=new ArrayList<>();
    private static final int GETADDRESS=1;
    private static final int DELADDRESS=2;
    private static final int SETADDRESS=3;

    private GridLayoutManager gridLayoutManager;
    private RecyclerView recyclerView;
    private int delAddressId;
    private SwipeRefreshLayout swiper;

    public AddressPresenter(Context context){
        super(context);
        activity = (AddressActivity) context;
        dataAdapter = new AddressAdapter(this.context);
        dataAdapter.setListItems(list);
        dataAdapter.setOnItemClickListener(this);
    }

    public void init(RecyclerView recyclerView, SwipeRefreshLayout swiper) {
        LoadDialog.show(context);
        atm.request(GETADDRESS,this);
        this.swiper=swiper;
        this.swiper.setOnRefreshListener(this);
        this.recyclerView=recyclerView;
        this.recyclerView.setAdapter(dataAdapter);
        this.recyclerView.setNestedScrollingEnabled(false);
        gridLayoutManager=new GridLayoutManager(context,1);
        this.recyclerView.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void onItemClick(int position, AddressResponse.DataBean item) {
        Intent mIntent = new Intent();
        mIntent.putExtra("addressId", item.getAddressID()+"");
        activity.setResult(1,mIntent);
        activity.finish();
    }

    @Override
    public void onTxtDelete(int id, AddressResponse.DataBean item) {
        this.delAddressId=item.getAddressID();
        switch (id)
        {
            case R.id.txt_set_address:
                atm.request(SETADDRESS,this);
                break;
            case R.id.txt_delete:
                atm.request(DELADDRESS,this);
        }
    }

    @Override
    public Object doInBackground(int requestCode, String parameter) throws HttpException {
        switch (requestCode) {
            case GETADDRESS :
                return userAction.getAddress();
            case DELADDRESS :
                return userAction.delAddress(delAddressId);
            case SETADDRESS :
                return userAction.setAddress(delAddressId);
        }
        return null;
    }

    @Override
    public void onSuccess(int requestCode, Object result) {
        LoadDialog.dismiss(context);
        if (result==null)return;
        switch (requestCode) {
            case GETADDRESS:
                this.swiper.setEnabled(false);
                AddressResponse addressResponse = (AddressResponse) result;
                if (addressResponse.getState() == Const.SUCCESS) {
                    if (addressResponse.getData().size() == 0) {
                    }
                    else {
                        list.clear();
                        list.addAll(addressResponse.getData());
                        dataAdapter.notifyDataSetChanged();
                    }
                }else {
                    NToast.shortToast(context, addressResponse.getMsg());
                }
                break;
            case DELADDRESS:
                CommonResponse commonResponse = (CommonResponse) result;
                if (commonResponse.getState() == Const.SUCCESS) {
                    atm.request(GETADDRESS,this);
                }
                    NToast.shortToast(context, commonResponse.getMsg());
                break;
            case SETADDRESS:
                CommonResponse commonResponse2 = (CommonResponse) result;
                if (commonResponse2.getState() == Const.SUCCESS) {
                    atm.request(GETADDRESS,this);
                }
                NToast.shortToast(context, commonResponse2.getMsg());
                break;
        }
    }

    @Override
    public void onRefresh() {
        list.clear();
        dataAdapter.notifyDataSetChanged();
        atm.request(GETADDRESS,this);
    }
}