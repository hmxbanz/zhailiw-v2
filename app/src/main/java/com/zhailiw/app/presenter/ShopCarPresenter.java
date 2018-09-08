package com.zhailiw.app.presenter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.zhailiw.app.Adapter.ShopCarAdapter;
import com.zhailiw.app.Const;
import com.zhailiw.app.R;
import com.zhailiw.app.common.NToast;
import com.zhailiw.app.server.HttpException;
import com.zhailiw.app.server.response.AddOrderResponse;
import com.zhailiw.app.server.response.CommonResponse;
import com.zhailiw.app.server.response.ShopCarResponse;
import com.zhailiw.app.view.activity.OrderDetailActivity;
import com.zhailiw.app.view.activity.ProductDetailActivity;
import com.zhailiw.app.view.activity.ShopCarActivity;
import com.zhailiw.app.widget.LoadDialog;

import java.util.ArrayList;
import java.util.List;


public class ShopCarPresenter extends BasePresenter implements ShopCarAdapter.ItemClickListener, SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = ShopCarPresenter.class.getSimpleName();
    private static final int GETSHOPCAR = 1;
    private static final int UPDATEBUYSHOP = 2;
    private static final int DELETEBUYSHOP = 3;
    private static final int PAYBUYSHOP = 4;
    private final ShopCarAdapter dataAdapter;
    private final List<ShopCarResponse.DataBean.OrderListBean> list=new ArrayList<>();
    private ShopCarActivity activity;
    private SwipeRefreshLayout swiper;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private int pageIndex=1,totalPages;
    private CheckBox checkBox;
    private int orderAttributeId,count;
    private Button btnPay;
    private TextView txtRight,txtSum,txtSumPrice;
    private boolean isDel=true;
    private List<Integer> orderAttributeIds=new ArrayList<>();
    private String s;
    private int ammount;


    public ShopCarPresenter(Context context){
        super(context);
        activity = (ShopCarActivity) context;
        dataAdapter = new ShopCarAdapter(this.context);
        dataAdapter.setListItems(list);
        dataAdapter.setOnItemClickListener(this);
    }

    public void init() {
        this.recyclerView = activity.findViewById(R.id.recyclerView);
        this.swiper=activity.findViewById(R.id.swiper);
        this.swiper.setOnRefreshListener(this);
        this.checkBox = activity.findViewById(R.id.checkbox);
        Drawable drawable = context.getResources().getDrawable(R.drawable.selector_shop_car_checkbox);
        drawable.setBounds(0,0,80,80);
//            if(Build.VERSION.SDK_INT>=21)
//                drawable.setTint(context.getResources().getColor(R.color.mainColor));
        checkBox.setCompoundDrawables(drawable,null,null,null);
        this.recyclerView.setAdapter(dataAdapter);
        this.recyclerView.setNestedScrollingEnabled(false);
        gridLayoutManager=new GridLayoutManager(context,1);
        this.recyclerView.setLayoutManager(gridLayoutManager);
        this.btnPay=activity.findViewById(R.id.btn_buy);
        this.txtSum=activity.findViewById(R.id.txt_sum);
        this.txtSumPrice=activity.findViewById(R.id.txt_sum_price);

        atm.request(GETSHOPCAR,ShopCarPresenter.this);
    }

    @Override
    public void onItemClick(View v, ShopCarResponse.DataBean.OrderListBean item, int num, int pos) {
        switch (v.getId()) {
            case R.id.img_product:
                ProductDetailActivity.StartActivity(activity,item.getProductId()+"");
                break;
            case R.id.list_checkbox:
                ShopCarResponse.DataBean.OrderListBean entity=list.get(pos);
                if(((CheckBox)v).isChecked())
                {
                    entity.setCheck(true);
                    orderAttributeIds.add(item.getOrderAttributeID());
                    entity.setTotalPrice(num);
                    ammount+=entity.getTotalPrice();
                    this.txtSumPrice.setText("¥:"+ammount+"");
                }
                else
                {
                    entity.setCheck(false);
                    orderAttributeIds.remove((Object)item.getOrderAttributeID());
                    entity.setTotalPrice(num);
                    ammount-=entity.getTotalPrice();
                    if(orderAttributeIds.size()==0){
                        ammount=0;
                    }
                    this.txtSumPrice.setText("¥:"+ammount+"");

                }
                break;
        }
    }

    @Override
    public void onNumClick(ShopCarResponse.DataBean.OrderListBean item, int count, int pos) {
        //改变订单数量
        this.orderAttributeId=item.getOrderAttributeID();
        this.count=count;

        ShopCarResponse.DataBean.OrderListBean entity=list.get(pos);
        if(entity.isCheck()) {
            ammount -= entity.getTotalPrice();
            entity.setQuantity(count);
            entity.setTotalPrice(entity.getPriceNow() * entity.getQuantity());
            ammount += entity.getTotalPrice();
            this.txtSumPrice.setText("¥:" + ammount + "");
        }
        atm.request(UPDATEBUYSHOP,ShopCarPresenter.this);
    }

    @Override
    public Object doInBackground(int requestCode, String parameter) throws HttpException {
        switch (requestCode) {
            case GETSHOPCAR :
                return userAction.getMyOrder(pageIndex+"",null,"289");
            case UPDATEBUYSHOP :
                return userAction.updateBuyShop(this.orderAttributeId,this.count);
            case DELETEBUYSHOP:
                return userAction.deleteBuyShop(this.orderAttributeIds.toString());
            case PAYBUYSHOP:
                return userAction.payBuyShop(this.orderAttributeIds.toString());
        }
        return null;
    }

    @Override
    public void onSuccess(int requestCode, Object result) {
        LoadDialog.dismiss(context);
        if (result==null)return;
        switch (requestCode) {
            case GETSHOPCAR:
                this.swiper.setRefreshing(false);
                ShopCarResponse shopCarResponse = (ShopCarResponse) result;
                if (shopCarResponse.getState() == Const.SUCCESS) {
                    totalPages=shopCarResponse.getTotalPages();
                    if (shopCarResponse.getData().size() == 0) {
                    }
                    else {
                        list.addAll(shopCarResponse.getData().get(0).getOrderList());
                        dataAdapter.notifyDataSetChanged();
                        this.swiper.setRefreshing(false);
                    }
                }else {
                    NToast.shortToast(context, shopCarResponse.getMsg());
                }
                break;
            case UPDATEBUYSHOP:
                CommonResponse commonResponse = (CommonResponse) result;
                if (commonResponse.getState() == Const.SUCCESS) {
                }else {
                    NToast.shortToast(context, commonResponse.getMsg());
                }
                break;
            case DELETEBUYSHOP:
                CommonResponse commonResponse2 = (CommonResponse) result;
                if (commonResponse2.getState() == Const.SUCCESS) {
                    list.clear();
                    dataAdapter.notifyDataSetChanged();
                    ammount=0;
                    this.txtSumPrice.setText("0");
                    orderAttributeIds.clear();
                    atm.request(GETSHOPCAR,ShopCarPresenter.this);
                }else {
                    NToast.shortToast(context, commonResponse2.getMsg());
                }
                break;
            case PAYBUYSHOP:
                AddOrderResponse addOrderResponse = (AddOrderResponse) result;
                if (addOrderResponse.getState() == Const.SUCCESS) {
                    OrderDetailActivity.StartActivity(context,addOrderResponse.getOrderId());
                }else
                    NToast.shortToast(context, addOrderResponse.getMsg());
                break;
        }
    }
    @Override
    public void onRefresh() {
        this.list.clear();
        dataAdapter.notifyDataSetChanged();
        atm.request(GETSHOPCAR,ShopCarPresenter.this);
    }

    public void onRightClick() {
        if(isDel){
            this.btnPay.setBackgroundColor(activity.getResources().getColor(R.color.red));
            this.btnPay.setTextColor(activity.getResources().getColor(R.color.white));
            this.btnPay.setText("删除");
            this.txtSum.setVisibility(View.GONE);
            this.txtSumPrice.setVisibility(View.GONE);
            isDel=false;
        }
        else
        {
            this.btnPay.setBackgroundColor(activity.getResources().getColor(R.color.mainColor));
            this.btnPay.setTextColor(activity.getResources().getColor(R.color.white));
            this.btnPay.setText("付款");
            this.txtSum.setVisibility(View.VISIBLE);
            this.txtSumPrice.setVisibility(View.VISIBLE);
            isDel=true;
        }
    }


    public void onPayClick() {
        if(this.orderAttributeIds.size()==0)
        {
        NToast.shortToast(context,"请选择商品");
        return;
        }

        if(!isDel){
            LoadDialog.show(context);
            atm.request(DELETEBUYSHOP,ShopCarPresenter.this);
        }
        else
        {
           LoadDialog.show(context);
           atm.request(PAYBUYSHOP,ShopCarPresenter.this);
        }

    }

    public void onCheckAll(CheckBox checkBox) {
        ammount=0;
        if(checkBox.isChecked()) {
            for (ShopCarResponse.DataBean.OrderListBean item :    list) {
                item.setCheck(true);
                ammount += item.getTotalPrice();
            }
        }
        else
        {
            for (ShopCarResponse.DataBean.OrderListBean item :     list) {
                item.setCheck(false);
            }
        }
        dataAdapter.notifyDataSetChanged();
        this.txtSumPrice.setText("¥:" + ammount + "");
    }
}
