package com.zhailiw.app.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhailiw.app.Const;
import com.zhailiw.app.R;
import com.zhailiw.app.common.NToast;
import com.zhailiw.app.loader.GlideImageLoader;
import com.zhailiw.app.server.response.ShopCarResponse;
import com.zhailiw.app.widget.progressBar.MaterialProgressBar;

import java.util.List;

public class ShopCarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private List<ShopCarResponse.DataBean.OrderListBean> listItems;
    private LayoutInflater layoutInflater;
    private  final int TYPE_HEADER = 0;
    private  final int TYPE_NORMAL = 1;
    private  final int TYPE_FOOTER = 2;
    private View mHeaderView;
    private View mFooterView;
    private ItemClickListener mListener;
    private RecyclerView mRecyclerView;
    private GlideImageLoader glideImageLoader;
    private Context context;

    public ShopCarAdapter(Context c){
        this.context=c;
        this.layoutInflater= LayoutInflater.from(c);
        glideImageLoader=new GlideImageLoader();
    }
    public void setOnItemClickListener(ItemClickListener listener) {
        mListener = listener;
    }
    public View getHeaderView() {
        return mHeaderView;
    }
    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);//告知Adapter首位置项变动了
    }
    public View getFooterView() {
        return mFooterView;
    }
    public void setFooterView(View footerView) {
        mFooterView = footerView;
        notifyItemInserted(0);//告知Adapter首位置项变动了
    }
    public void setListItems(List<ShopCarResponse.DataBean.OrderListBean> l)
    {
        this.listItems=l;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mHeaderView != null && viewType == TYPE_HEADER)
        {
            return new HeaderHolder(mHeaderView);
        }
        else if(mFooterView != null &&viewType == TYPE_FOOTER)
        {
            return new DataHolder(mFooterView);
        }
        else {
            View v = layoutInflater.inflate(R.layout.listitem_shop_car, parent, false);
            return new DataHolder(v);
        }
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(getItemViewType(position) == TYPE_HEADER) return;
        if(getItemViewType(position) == TYPE_FOOTER) return;
        final int pos = getRealPosition(holder);
        final ShopCarResponse.DataBean.OrderListBean listItem = listItems.get(pos);
        if(holder instanceof DataHolder) {
            final DataHolder dataHolder=(DataHolder)holder;
            dataHolder.checkBox.setChecked(false);
            if(listItem.isCheck()){
                dataHolder.checkBox.setChecked(true);
            }
            dataHolder.layoutView.setOnClickListener(dataHolder);
            dataHolder.txtProductName.setText(listItem.getProductName());
            dataHolder.txtProductInfo.setText(listItem.getProductInfo());
            dataHolder.txtProductPrice.setText(listItem.getPriceNow()+"元");
            dataHolder.txtQuantity.setText(listItem.getQuantity()+"");
            glideImageLoader.displayImage(context, Const.IMGURI+listItem.getPhotoSmall(),dataHolder.imageView);
            if(mListener == null) return;
            dataHolder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int num=Integer.parseInt(dataHolder.txtQuantity.getText().toString());
                    mListener.onItemClick(v,listItem,num*listItem.getPriceNow(),pos);
                }
            });
            dataHolder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int num=Integer.parseInt(dataHolder.txtQuantity.getText().toString());
                    mListener.onItemClick(v,listItem,num*listItem.getPriceNow(),pos);
                }
            });
            dataHolder.txtIncrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int num=Integer.parseInt(dataHolder.txtQuantity.getText().toString())+1;
                    dataHolder.txtQuantity.setText(num + "");
                    mListener.onNumClick(listItem, num,pos);
                }
            });
            dataHolder.txtDecrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int num=Integer.parseInt(dataHolder.txtQuantity.getText().toString())-1;
                    if(num<1)
                    {
                        NToast.shortToast(context,"数量不能小于1");
                        return;
                    }
                    dataHolder.txtQuantity.setText(num + "");
                    mListener.onNumClick(listItem, num, pos);

                }
            });

        }
    }
    @Override
    public int getItemCount() {
        int count = (listItems == null ? 0 : listItems.size());
        if (mHeaderView != null)   count++;
        if (mFooterView != null)   count++;
        return count;
    }
    @Override
    public int getItemViewType(int position) {
        if (isHeaderView(position)) {
            return TYPE_HEADER;
        } else if (isFooterView(position)) {
            return TYPE_FOOTER;
        } else {
            return TYPE_NORMAL;
        }
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try {
            if (mRecyclerView == null) {
                mRecyclerView = recyclerView;
            }
            ifGridLayoutManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void ifGridLayoutManager() {
        final RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final  GridLayoutManager l=(GridLayoutManager)layoutManager;
            final GridLayoutManager.SpanSizeLookup originalSpanSizeLookup = l.getSpanSizeLookup();
            l.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (isHeaderView(position) || isFooterView(position)) ? l.getSpanCount() :1;
                }
            });
        }
    }
    private boolean isHeaderView(int position) {
        return (mHeaderView != null) && (position == 0);
    }
    private boolean isFooterView(int position) {
        return (mFooterView != null) && (position == getItemCount() - 1);
    }
    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }
    public interface ItemClickListener {
        void onItemClick(View v, ShopCarResponse.DataBean.OrderListBean item, int num, int pos);
        void onNumClick(ShopCarResponse.DataBean.OrderListBean item, int count, int pos);
    }
    public void onLoading(){
        TextView tips=mFooterView.findViewById(R.id.tips);
        MaterialProgressBar progressBar=mFooterView.findViewById(R.id.progress_wheel);
        mFooterView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        tips.setText(R.string.layout_dialog_loading);
    }
    public void onLoadingDone(){
        TextView tips=mFooterView.findViewById(R.id.tips);
        MaterialProgressBar progressBar=mFooterView.findViewById(R.id.progress_wheel);
        mFooterView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        tips.setText("我是有底线的");
    }
    public void onLoadingEnd(){
        mFooterView.setVisibility(View.GONE);
    }
    class HeaderHolder extends RecyclerView.ViewHolder  {
        public HeaderHolder(View itemView) {
            super(itemView);
        }
    }
    public class DataHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private CheckBox checkBox;
        private ImageView imageView;
        private TextView txtProductName;
        private TextView txtProductInfo;
        private TextView txtProductPrice;
        private TextView txtIncrease;
        private TextView txtQuantity;
        private TextView txtDecrease;
        private LinearLayout layoutView;

        public DataHolder(View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.list_checkbox);
            Drawable drawable = context.getResources().getDrawable(R.drawable.selector_shop_car_checkbox);
            drawable.setBounds(0,0,80,80);
//            if(Build.VERSION.SDK_INT>=21)
//                drawable.setTint(context.getResources().getColor(R.color.mainColor));
            checkBox.setCompoundDrawables(drawable,null,null,null);
            imageView =  itemView.findViewById(R.id.img_product);
            txtProductName =  itemView.findViewById(R.id.txt_product_name);
            txtProductInfo =  itemView.findViewById(R.id.txt_product_type);
            txtProductPrice =  itemView.findViewById(R.id.txt_product_price);
            txtIncrease =  itemView.findViewById(R.id.txt_increase);
            txtQuantity =  itemView.findViewById(R.id.txt_quantity);
            txtDecrease =  itemView.findViewById(R.id.txt_decrease);
            layoutView = itemView.findViewById(R.id.layout);
        }

        public CheckBox getCheckBox() {
            return checkBox;
        }

        public void setCheckBox(CheckBox checkBox) {
            this.checkBox = checkBox;
        }

        public TextView getTxtProductPrice() {
            return txtProductPrice;
        }

        public void setTxtProductPrice(TextView txtProductPrice) {
            this.txtProductPrice = txtProductPrice;
        }

        public TextView getTxtIncrease() {
            return txtIncrease;
        }

        public void setTxtIncrease(TextView txtIncrease) {
            this.txtIncrease = txtIncrease;
        }

        public TextView getTxtQuantity() {
            return txtQuantity;
        }

        public void setTxtQuantity(TextView txtQuantity) {
            this.txtQuantity = txtQuantity;
        }

        public TextView getTxtDecrease() {
            return txtDecrease;
        }

        public void setTxtDecrease(TextView txtDecrease) {
            this.txtDecrease = txtDecrease;
        }

        public ImageView getImageView() {
            return imageView;
        }
        public void setImageView(ImageView imageView) {
            this.imageView = imageView;
        }
        public TextView getTxtProductName() {
            return txtProductName;
        }
        public void setTxtProductName(TextView txtProductName) {
            this.txtProductName = txtProductName;
        }
        public TextView getTxtProductInfo() {
            return txtProductInfo;
        }
        public void setTxtProductInfo(TextView txtProductInfo) {
            this.txtProductInfo = txtProductInfo;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.layout:
            }
        }
    }
}
