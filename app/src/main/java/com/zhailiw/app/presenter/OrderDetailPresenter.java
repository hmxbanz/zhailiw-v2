package com.zhailiw.app.presenter;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhailiw.app.Adapter.OrderDetailAdapter;
import com.zhailiw.app.Const;
import com.zhailiw.app.R;
import com.zhailiw.app.common.NLog;
import com.zhailiw.app.common.NToast;
import com.zhailiw.app.server.HttpException;
import com.zhailiw.app.server.response.CommonResponse;
import com.zhailiw.app.server.response.DefaultAddressResponse;
import com.zhailiw.app.server.response.OrderDetailResponse;
import com.zhailiw.app.view.activity.AddressActivity;
import com.zhailiw.app.view.activity.OrderDetailActivity;
import com.zhailiw.app.widget.DialogPay;
import com.zhailiw.app.widget.LoadDialog;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class OrderDetailPresenter extends BasePresenter implements View.OnClickListener, DialogPay.DialogPopListener {
    private static final String TAG = FavorPresenter.class.getSimpleName();
    private final OrderDetailAdapter dataAdapter;
    private final List<OrderDetailResponse.DataBean.OrderListBean> list=new ArrayList<OrderDetailResponse.DataBean.OrderListBean>();
    private OrderDetailActivity activity;
    private final int GETORDERDETAIL=1, GETADDRESS =2,SETADDRESS=3;
    private String orderId;
    private TextView txtContact,txtAddress,txtOrderNo,txtOrderTotal;
    private LinearLayout layoutAddress;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private IWXAPI api;
    private OrderDetailResponse orderDetailResponse;
    private DialogPay dialog;
    private String addressId;
    private boolean isAddressSelect;

    public OrderDetailPresenter(Context context){
        super(context);
        activity = (OrderDetailActivity) context;
        dataAdapter = new OrderDetailAdapter(this.context);
        dataAdapter.setListItems(list);
        Intent intent = activity.getIntent();
        orderId=intent.getStringExtra("orderId");
    }

    public void init() {
        api = WXAPIFactory.createWXAPI(context, null);
        api.registerApp(Const.APPID);
        this.txtContact = activity.findViewById(R.id.txt_contact);
        this.layoutAddress = activity.findViewById(R.id.layout_address);
        this.txtAddress = activity.findViewById(R.id.txt_address);
        this.txtOrderNo = activity.findViewById(R.id.txt_order_no);
        this.txtOrderTotal = activity.findViewById(R.id.txt_order_total);
        this.layoutAddress.setOnClickListener(this);
        activity.findViewById(R.id.btn_pay).setOnClickListener(this);
        this.recyclerView = activity.findViewById(R.id.recyclerView);
        this.recyclerView.setAdapter(dataAdapter);
        this.recyclerView.setNestedScrollingEnabled(false);
        gridLayoutManager=new GridLayoutManager(context,1);
        this.recyclerView.setLayoutManager(gridLayoutManager);
        atm.request(GETORDERDETAIL,this);
    }

    @Override
    public Object doInBackground(int requestCode, String parameter) throws HttpException {
        switch (requestCode) {
            case GETORDERDETAIL:
                return userAction.getOrderDetail(orderId);
            case GETADDRESS:
                return userAction.getOrderAddress(this.addressId+"");
            case SETADDRESS:
                return userAction.setOrderAddress(this.orderId,this.addressId+"");
        }
        return null;
    }

    @Override
    public void onSuccess(int requestCode, Object result) {
        LoadDialog.dismiss(context);
        if (result == null)     return;
        switch (requestCode) {
            case GETORDERDETAIL:
                orderDetailResponse = (OrderDetailResponse) result;
                OrderDetailResponse.DataBean data = orderDetailResponse.getData();
                if (orderDetailResponse.getState() == Const.SUCCESS) {
                    TextPaint tp=this.txtOrderTotal.getPaint();
                    tp.setFakeBoldText(true);
                    this.txtOrderNo.setText("订单号："+data.getOrderNo());
                    this.txtOrderTotal.setText("订单总价："+data.getTotal()+"元");
                    list.addAll(data.getOrderList());
                    dataAdapter.notifyDataSetChanged();
                    if(data.getCellphone()==null)
                    {
                        this.txtContact.setText("请选择收货地址");
                        this.txtAddress.setVisibility(View.GONE);
                        this.isAddressSelect=false;
                    }
                    else
                    {
                        this.isAddressSelect=true;
                        this.txtAddress.setVisibility(View.VISIBLE);
                        this.txtContact.setText("收货人："+data.getContact()+" 手机："+data.getCellphone());
                        this.txtAddress.setText("送货地址："+data.getAddress());
                    }

                }
                else
                    NToast.shortToast(context, orderDetailResponse.getMsg());
                break;
            case GETADDRESS:
                DefaultAddressResponse defaultResponse = (DefaultAddressResponse) result;
                if(defaultResponse.getState()==Const.SUCCESS)
                {
                    this.isAddressSelect=true;
                    this.txtAddress.setVisibility(View.VISIBLE);
                    this.txtContact.setText("收货人："+defaultResponse.getDefaultAddress().getContact()+" 手机："+defaultResponse.getDefaultAddress().getCellphone());
                    this.txtAddress.setText("送货地址："+defaultResponse.getDefaultAddress().getAddress());
                    atm.request(SETADDRESS,this);
                }
                else
                    NToast.shortToast(context, orderDetailResponse.getMsg());

                break;
            case SETADDRESS:
                CommonResponse commonResponse = (CommonResponse) result;
                if (commonResponse.getState() == Const.SUCCESS) {

                }
                else
                    NToast.shortToast(context, commonResponse.getMsg());
                break;
        }
 }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_pay:
                if(!isAddressSelect){
                    NToast.shortToast(context, "请选择收货地址！");
                return;}
                showPhotoDialog(v);
                break;
            case R.id.layout_address:
                AddressActivity.StartActivityForResult(context);
                break;
        }
    }

    /**
     * 弹出底部框
     */
    @TargetApi(23)
    public void showPhotoDialog(View v) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        dialog = new DialogPay(context);
        dialog.setListener(this);
        dialog.show();
        dialog.setData(orderDetailResponse);
    }

    @Override
    public void onSubmit(int type) {
        switch (type) {
            case 1:
                PayReq request = new PayReq();
                request.appId = orderDetailResponse.getWxPayStr().getAppid();
                request.partnerId = orderDetailResponse.getWxPayStr().getPartnerid();
                request.prepayId= orderDetailResponse.getWxPayStr().getPrepayid();
                request.packageValue = "Sign=WXPay";
                request.nonceStr= orderDetailResponse.getWxPayStr().getNoncestr();
                request.timeStamp= orderDetailResponse.getWxPayStr().getTimestamp();
                request.sign= orderDetailResponse.getWxPayStr().getSign();
                NLog.e("结果：",api.sendReq(request));
                break;
            case 2:
                NToast.shortToast(context,"暂时不支持支付宝！");
                break;
        }

    }

    public void getAddress(String addressId) {
        this.addressId=addressId;
        atm.request(GETADDRESS,this);
    }
}


