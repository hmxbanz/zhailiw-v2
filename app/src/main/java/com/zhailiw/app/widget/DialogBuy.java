package com.zhailiw.app.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.fyales.tagcloud.library.TagCloudLayout;
import com.zhailiw.app.Adapter.BuyTabAdapter;
import com.zhailiw.app.R;
import com.zhailiw.app.common.NToast;
import com.zhailiw.app.server.response.ProductAttributeResponse;
import com.zhailiw.app.server.response.ProductResponse;

import java.util.List;

public class DialogBuy extends Dialog implements View.OnClickListener {

    private Context context;
    private Button btnSubmit;
    private static DialogBuy instance;
    private TagCloudLayout tagCloudLayout;
    private ProductAttributeResponse productAttributeResponse;
    private TextView txtProductName,txtProductPrice,txtQuantity,txtDecrease,txtIncrease;
    private DialogPopListener listener;
    private int type;
    private int quantity=1,productAttributeId=0;
    private BuyTabAdapter mAdapter;

    public DialogPopListener getListener() {
        return listener;
    }
    public void setListener(DialogPopListener listener) {
        this.listener = listener;
    }

    public DialogBuy(Context context) {
        super(context, R.style.dialogFullscreenTranslucent);
    this.context=context;
    }
    public DialogBuy(Context context, int theme) { super(context, theme);   }

    public DialogBuy(Context context, String s) {
        super(context, R.style.dialogFullscreenTranslucent);
    }
        public static DialogBuy getInstance(Context context) {
        if (instance == null) {
            instance = new DialogBuy(context);
        }
        return instance;
    }
    public interface DialogPopListener {
        void onSubmit(int i, int quantity, int productAttributeId);
    }
    public void setData(ProductAttributeResponse productAttributeResponse, ProductResponse productResponse,View v){
        this.productAttributeResponse=productAttributeResponse;
        this.txtProductName.setText(productResponse.getData().getProductName());
        this.txtProductPrice.setText(productResponse.getData().getProductPrice()+" 元");
        switch (v.getId()) {
            case R.id.btn_add_shop_car:
                this.type=289;
                this.btnSubmit.setText("加入购物车");
                this.btnSubmit.setBackgroundResource(R.color.orange);
                break;
            case R.id.btn_buy:
                this.type=278;
                this.btnSubmit.setText("立即购买");
        }
        mAdapter = new BuyTabAdapter(context,productAttributeResponse.getData());
        tagCloudLayout.setAdapter(mAdapter);

    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_buy);
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.5f;
        window.setGravity(Gravity.BOTTOM);
        window.setAttributes(layoutParams);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //window.setBackgroundDrawableResource(R.drawable.transparent_drawable);

        findViewById(R.id.root_view).setOnClickListener(this);


        btnSubmit =  findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(this);
        tagCloudLayout =  findViewById(R.id.tab_container);
        TagCloudLayout.TagItemClickListener tagItemClickListener = new TagCloudLayout.TagItemClickListener() {
            public ProductAttributeResponse.DataBean item;

            @Override
            public void itemClick(int position) {
                List<ProductAttributeResponse.DataBean> items = productAttributeResponse.getData();
                for (ProductAttributeResponse.DataBean bean : items
                        ) {
                    bean.setIsSelect(false);
                }
                item=items.get(position);
                item.setIsSelect(true);
                mAdapter.notifyDataSetChanged();
                productAttributeId =item.getProductAttributeId();
            }
        };
        tagCloudLayout.setItemClickListener(tagItemClickListener);
        txtProductName=findViewById(R.id.product_name);
        txtProductPrice=findViewById(R.id.product_price);
        //txtProductInfo=findViewById(R.id.txt_product_info);
        txtQuantity=findViewById(R.id.txt_quantity);
        this.quantity=Integer.parseInt(txtQuantity.getText().toString());
        findViewById(R.id.txt_decrease).setOnClickListener(this);
        findViewById(R.id.txt_increase).setOnClickListener(this);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (v.getId()) {
            case R.id.btn_submit:
                if (productAttributeId == 0){
                    NToast.shortToast(context,"请选择规格！");
                    return;
                }
                listener.onSubmit(type,quantity,productAttributeId);
                break;
            case R.id.txt_decrease:
                quantity--;
                break;
            case R.id.txt_increase:
                quantity++;
                break;
            case R.id.root_view:
                dismiss();
                break;
        }
        this.txtQuantity.setText(quantity+"");
            return;
        }
    }

