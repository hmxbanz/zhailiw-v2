package com.zhailiw.app.presenter;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.zhailiw.app.Const;
import com.zhailiw.app.R;
import com.zhailiw.app.common.NToast;
import com.zhailiw.app.listener.AlertDialogCallBack;
import com.zhailiw.app.loader.GlideImageLoader;
import com.zhailiw.app.server.HttpException;
import com.zhailiw.app.server.async.OnDataListener;
import com.zhailiw.app.server.response.AddOrderResponse;
import com.zhailiw.app.server.response.CommonResponse;
import com.zhailiw.app.server.response.ProductAttributeResponse;
import com.zhailiw.app.server.response.ProductResponse;
import com.zhailiw.app.view.activity.LoginActivity;
import com.zhailiw.app.view.activity.ProductDetailActivity;
import com.zhailiw.app.widget.DialogBuy;
import com.zhailiw.app.widget.DialogWithYesOrNoUtils;
import com.zhailiw.app.widget.LoadDialog;
import com.zhailiw.app.view.activity.OrderDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailPresenter extends BasePresenter implements OnDataListener, View.OnClickListener,DialogBuy.DialogPopListener,SwipeRefreshLayout.OnRefreshListener {
    private static final int GETPRODUCT = 1,GETPRODUCTATTRIBUTE=2,ADDFAVOR=3,ADDSHOPCAR=4,ADDORDER=5;
    private final BasePresenter basePresenter;
    private ProductDetailActivity activity;
    private String productId;
    private Banner banner;
    private GlideImageLoader glideImageLoader;
    private Button btnAddShopCar,btnBuy;
    private DialogBuy dialog;
    private ProductAttributeResponse productAttributeResponse;
    private ProductResponse productResponse;
    private Button btnClick;
    private int orderType;
    private int quantity;
    private int productAttributeId;
    private TextView txtProductName,txtProductNo,txtProductInfo;
    private SwipeRefreshLayout swiper;

    public ProductDetailPresenter(Context context){
        super(context);
        activity = (ProductDetailActivity) context;
        basePresenter = BasePresenter.getInstance(context);
        glideImageLoader = new GlideImageLoader();
        Intent intent = activity.getIntent();
        productId = intent.getStringExtra("productId");
    }

    public void init() {
        this.swiper = activity.findViewById(R.id.swiper);
        this.swiper.setOnRefreshListener(this);
        this.banner = activity.findViewById(R.id.banner);
        btnAddShopCar=activity.findViewById(R.id.btn_add_shop_car);
        btnAddShopCar.setOnClickListener(this);
        btnBuy=activity.findViewById(R.id.btn_buy);
        btnBuy.setOnClickListener(this);

        txtProductName = activity.findViewById(R.id.txt_product_name);
        txtProductNo = activity.findViewById(R.id.txt_product_no);
        txtProductInfo = activity.findViewById(R.id.txt_product_type);
        activity.findViewById(R.id.img_favor).setOnClickListener(this);
        LoadDialog.show(context);
        atm.request(GETPRODUCT,this);
    }


    @Override
    public Object doInBackground(int requestCode, String parameter) throws HttpException {
        switch (requestCode) {
            case GETPRODUCT:
                return userAction.getProduct(productId);
            case GETPRODUCTATTRIBUTE:
                return userAction.getProductAttribute(productId);
            case ADDFAVOR:
                return userAction.addFavor(productId);
            case ADDSHOPCAR:
            case ADDORDER:
                return userAction.addOrderCar(orderType+"",quantity+"",productAttributeId+"");
        }
        return null;
    }

    @Override
    public void onSuccess(int requestCode, Object result) {
        LoadDialog.dismiss(context);
        switch (requestCode) {
            case GETPRODUCT:
                this.swiper.setRefreshing(false);
                productResponse = (ProductResponse) result;
                if (productResponse.getState() == Const.SUCCESS) {
                    List<String> images = new ArrayList<>();
                    for (ProductResponse.DataBean.ADPhotoesBean bean : productResponse.getData().getADPhotoes()) {
                        String s=Const.IMGURI+bean.getPhotoBig();
                        images.add(s);
                    }
                    banner.setImageLoader(glideImageLoader);
                    banner.setImages(images);//设置图片集合
                    banner.start();
                    this.txtProductName.setText(productResponse.getData().getProductName());
                    this.txtProductNo.setText("￥:"+productResponse.getData().getProductPrice()+"元");
                    this.txtProductInfo.setText(productResponse.getData().getProductInfo());

                    LinearLayout layoutContent=(LinearLayout) activity.findViewById(R.id.layout_content);
                    layoutContent.removeAllViews();
                    List<ProductResponse.DataBean.DetailPhotoesBean> photoes = productResponse.getData().getDetailPhotoes();
                    for (int i = 0; i < photoes.size(); i++) {
                        ImageView imageView = new ImageView(activity);
                        imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));  //设置图片宽高
                        //imageView.setImageResource(R.drawable.bg_getuser2); //图片资源
                        glideImageLoader.displayImage(context,Const.SERVERURI+photoes.get(i).getPhotoBig(),imageView);
                        layoutContent.addView(imageView); //动态添加图片
                    }
                }
                else
                    NToast.shortToast(context, productResponse.getMsg());
                break;
            case GETPRODUCTATTRIBUTE:
                productAttributeResponse = (ProductAttributeResponse) result;
                if (productAttributeResponse.getState() == Const.SUCCESS) {
                    showPhotoDialog(btnClick);
                }
                else
                    NToast.shortToast(context, productResponse.getMsg());
                break;
            case ADDSHOPCAR:
                AddOrderResponse AddOrderResponse = (AddOrderResponse) result;
                if (AddOrderResponse.getState() == Const.SUCCESS) {
                    NToast.shortToast(context,"成功添加到购物车！");
                    dialog.dismiss();
                }
                else
                    NToast.shortToast(context, AddOrderResponse.getMsg());
                break;
            case ADDORDER:
                AddOrderResponse addOrderResponse = (AddOrderResponse) result;
                if (addOrderResponse.getState() == Const.SUCCESS) {
                    dialog.dismiss();
                    OrderDetailActivity.StartActivity(context,addOrderResponse.getOrderId());
                }
                NToast.shortToast(context, addOrderResponse.getMsg());
                break;
            case ADDFAVOR:
                CommonResponse commonResponse = (CommonResponse) result;
                if (commonResponse.getState() == Const.SUCCESS) {
                }
                NToast.shortToast(context, commonResponse.getMsg());
                break;
        }

    }

    @Override
    public void onClick(View v) {
        basePresenter.initData();
        if(!basePresenter.isLogin)
        {
            DialogWithYesOrNoUtils.getInstance().showDialog(context, "请先登录", new AlertDialogCallBack(){
                @Override
                public void executeEvent() {
                    super.executeEvent();
                    LoginActivity.StartActivity(activity);
                }
            });
            return;
        }
        switch (v.getId()) {
            case R.id.btn_add_shop_car:
                atm.request(GETPRODUCTATTRIBUTE,this);
                btnClick=btnAddShopCar;
                break;
            case R.id.btn_buy:
                atm.request(GETPRODUCTATTRIBUTE,this);
                btnClick=btnBuy;
                break;
            case R.id.img_favor:
                LoadDialog.show(context);
                atm.request(ADDFAVOR,this);
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
        dialog = new DialogBuy(context);
        dialog.setListener(this);
        dialog.show();
        dialog.setData(productAttributeResponse,productResponse,v);
    }

    @Override
    public void onSubmit(int orderType, int quantity, int productAttributeId) {
        LoadDialog.show(context);
        this.orderType=orderType;
        this.quantity=quantity;
        this.productAttributeId=productAttributeId;
        if(orderType==289)
            atm.request(ADDSHOPCAR, this);
        else
            atm.request(ADDORDER,this);
    }

    @Override
    public void onRefresh() {
        atm.request(GETPRODUCT, this);
    }
}