package com.zhailiw.app.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.fyales.tagcloud.library.TagCloudLayout;
import com.zhailiw.app.Adapter.BuyTabAdapter;
import com.zhailiw.app.R;
import com.zhailiw.app.common.NToast;
import com.zhailiw.app.server.response.OrderDetailResponse;
import com.zhailiw.app.server.response.ProductAttributeResponse;
import com.zhailiw.app.server.response.ProductResponse;

import java.util.List;

public class DialogPay extends Dialog implements View.OnClickListener {

    private Context context;
    private Button btnSubmit;
    private static DialogPay instance;
    private TextView txtProductPrice,txtOrderNo;
    private DialogPopListener listener;
    private int type;
    private CheckBox checkBoxWx,checkBoxAliPay;

    public DialogPopListener getListener() {
        return listener;
    }
    public void setListener(DialogPopListener listener) {
        this.listener = listener;
    }

    public DialogPay(Context context) {
        super(context, R.style.dialogFullscreenTranslucent);
    this.context=context;
    }
    public DialogPay(Context context, int theme) { super(context, theme);   }

    public DialogPay(Context context, String s) {
        super(context, R.style.dialogFullscreenTranslucent);
    }
        public static DialogPay getInstance(Context context) {
        if (instance == null) {
            instance = new DialogPay(context);
        }
        return instance;
    }
    public interface DialogPopListener {
        void onSubmit(int i);
    }
    public void setData(OrderDetailResponse order){
        TextPaint tp=this.txtProductPrice.getPaint();
        tp.setFakeBoldText(true);
        this.txtProductPrice.setText("￥:"+order.getData().getTotal()+" 元");
        this.txtOrderNo.setText("订单号:"+order.getData().getOrderNo());
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_pay);
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.5f;
        window.setGravity(Gravity.BOTTOM);
        window.setAttributes(layoutParams);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //window.setBackgroundDrawableResource(R.drawable.transparent_drawable);

        btnSubmit =  findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(this);
        txtProductPrice=findViewById(R.id.txt_product_price);
        txtOrderNo=findViewById(R.id.txt_order_no);

        checkBoxWx = findViewById(R.id.checkbox_wx);
        Drawable drawable = context.getResources().getDrawable(R.drawable.selector_shop_car_checkbox);
        drawable.setBounds(0,0,80,80);
//            if(Build.VERSION.SDK_INT>=21)
//                drawable.setTint(context.getResources().getColor(R.color.mainColor));
        checkBoxWx.setCompoundDrawables(drawable,null,null,null);
        checkBoxWx.setOnClickListener(this);

        checkBoxAliPay = findViewById(R.id.checkbox_alipay);
        Drawable drawable2 = context.getResources().getDrawable(R.drawable.selector_shop_car_checkbox);
        drawable2.setBounds(0,0,80,80);
        checkBoxAliPay.setCompoundDrawables(drawable2,null,null,null);
        checkBoxAliPay.setOnClickListener(this);

        findViewById(R.id.img_close).setOnClickListener(this);

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
                if (type == 0){
                    NToast.shortToast(context,"请选择支付方式！");
                    return;
                }
                listener.onSubmit(type);
                break;
            case R.id.checkbox_wx:
                type = 1;
                checkBoxAliPay.setChecked(false);
                break;
            case R.id.checkbox_alipay:
                type = 2;
                checkBoxWx.setChecked(false);
                break;
            case R.id.img_close:
                dismiss();
                break;

        }
            return;
        }
    }

