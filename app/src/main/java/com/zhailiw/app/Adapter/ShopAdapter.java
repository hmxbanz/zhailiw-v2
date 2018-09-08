package com.zhailiw.app.Adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fyales.tagcloud.library.TagBaseAdapter;
import com.fyales.tagcloud.library.TagCloudLayout;
import com.youth.banner.Banner;
import com.zhailiw.app.Const;
import com.zhailiw.app.R;
import com.zhailiw.app.loader.GlideImageLoader;
import com.zhailiw.app.server.response.ADResponse;
import com.zhailiw.app.server.response.GalleryResponse;
import com.zhailiw.app.server.response.ShopResponse;
import com.zhailiw.app.server.response.SystemObjResponse;

import java.util.ArrayList;
import java.util.List;


public class ShopAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private List<ShopResponse.DataBean> listItems;
    private LayoutInflater layoutInflater;
    private  final int TYPE_AD = 0;
    private  final int TYPE_HEADER = 1;
    private  final int TYPE_NORMAL = 2;
    private  final int TYPE_FOOTER = 3;
    private View mADView,mHeaderView,mFooterView;
    private ItemClickListener mListener;
    private RecyclerView mRecyclerView;
    private GlideImageLoader glideImageLoader;
    private Context context;
    private List<String> adImages;
    private ADHolder adHolder;
    private List<SystemObjResponse.SysObjBean.ChildDictionariesBean> tabs;
    public void setOnItemClickListener(ItemClickListener listener) {
        mListener = listener;
    }

    public List<SystemObjResponse.SysObjBean.ChildDictionariesBean> getTabs() {
        return tabs;
    }

    public void setTabs(List<SystemObjResponse.SysObjBean.ChildDictionariesBean> tabs) {
        this.tabs = tabs;
    }

    public View getHeaderView() {
        return mHeaderView;
    }
    public void setHeaderView(View v) {
        mHeaderView = v;
        if(mADView==null)
        notifyItemInserted(0);//告知Adapter首位置项变动了
        else
            notifyItemInserted(0);//告知Adapter首位置项变动了
    }
    public View getFooterView() {
        return mFooterView;
    }
    public void setFooterView(View v) {
        mFooterView = v;
        notifyItemInserted(getItemCount() - 1);//告知Adapter首位置项变动了
    }
    public View getmADView() {
        return mADView;
    }
    public void setmADView(View v) {
        mADView = v;
        notifyItemInserted(0);//告知Adapter首位置项变动了
    }
    public void setListItems(List<ShopResponse.DataBean> l)
    {
        this.listItems=l;
    }

    public ADHolder getAdHolder() {
        return adHolder;
    }
    public void setAdHolder(ADHolder adHolder) {
        this.adHolder = adHolder;
    }

    public ShopAdapter(Context c){
        this.context=c;
        this.layoutInflater= LayoutInflater.from(c);
        glideImageLoader=new GlideImageLoader();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_AD)
        {
            View v = layoutInflater.inflate(R.layout.listitem_shop_ad, parent, false);
            mADView=v;
            return new ADHolder(v);
        }
        else if(viewType == TYPE_HEADER)
        {
            View v = layoutInflater.inflate(R.layout.listitem_shop_header,null, false);
            return new HeaderHolder(v);
        }
        else if(mFooterView != null &&viewType == TYPE_FOOTER)
        {
            return new DataHolder(mFooterView);
        }
        else {
            View v = layoutInflater.inflate(R.layout.listitem_shop, parent, false);
            return new DataHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(getItemViewType(position) == TYPE_FOOTER) return;
        if(holder instanceof ADHolder){this.adHolder=(ADHolder) holder;
        }
        if(getItemViewType(position) == TYPE_AD) return;
        final int pos = getRealPosition(holder);

        if(holder instanceof HeaderHolder) {
            final HeaderHolder headerHolder=(HeaderHolder)holder;
            final ArrayList mList = new ArrayList<>();
            mList.add("现代");
            mList.add("欧美");
            mList.add("古代");
            final MyTabAdapter mAdapter = new MyTabAdapter(context,tabs);
            headerHolder.title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(headerHolder.tagCloudLayout.getVisibility()==View.GONE)
                        headerHolder.tagCloudLayout.setVisibility(View.VISIBLE);
                    else
                    {
                        headerHolder.tagCloudLayout.setVisibility(View.GONE);
                        mListener.onTabExpand();
                    }

                }
            });
            headerHolder.tagCloudLayout.setAdapter(mAdapter);
            headerHolder.tagCloudLayout.setItemClickListener(new TagCloudLayout.TagItemClickListener() {
                @Override
                public void itemClick(int position) {
                    mListener.onTabItemClick(position,tabs.get(position));
                }
            });
        }
        if(getItemViewType(position) == TYPE_HEADER) return;
        final ShopResponse.DataBean listItem = listItems.get(pos);
        if(holder instanceof DataHolder) {
            final DataHolder dataHolder=(DataHolder)holder;
            dataHolder.txtName.setText(listItem.getProductName());
            dataHolder.txtPrice.setText(listItem.getProductPrice()+" 元");
            glideImageLoader.displayImage(context, Const.IMGURI+listItem.getProductImage(),dataHolder.imageView);
            //Glide.with(context).load(listItem.getAvator()).asBitmap().into(holder.imageView);
            //holder.imageView.setImageResource(listItem.getImgResource());
            if(mListener == null) return;
            dataHolder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(position,listItem);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        int count = (listItems == null ? 0 : listItems.size());
        if (mHeaderView != null)   count++;
        if (mFooterView != null)   count++;
        if (mADView != null)   count++;
        return count+1;
    }
    @Override
    public int getItemViewType(int position) {
        if(isADView((position)))
            return TYPE_AD;
        else if (isHeaderView(position)) {
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
                    return (isADView(position) ||isHeaderView(position) || isFooterView(position)) ? l.getSpanCount() :1;
                }
            });
        }
    }
    private boolean isADView(int position) {
        return  (position == 0);
    }
    private boolean isHeaderView(int position) {
            return  (position == 1);
    }
    private boolean isFooterView(int position) { return (mFooterView != null) && (position == getItemCount() - 1);    }
    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        //return mHeaderView == null ? position : position - 2;
        return position - 2;
    }
    public void setAdImages(List<String> images) {
        this.adImages=images;
    }
    public interface ItemClickListener {
        void onItemClick(int position, ShopResponse.DataBean item);
        void onTabItemClick(int position, SystemObjResponse.SysObjBean.ChildDictionariesBean item);
        void onTabExpand();

    }
    public class ADHolder extends RecyclerView.ViewHolder  {
        private Banner banner;
        public ADHolder(View itemView) {
            super(itemView);
            banner =  itemView.findViewById(R.id.banner);
        }

        public Banner getBanner() {
            return banner;
        }

        public void setBanner(Banner banner) {
            this.banner = banner;
        }
    }
    public class HeaderHolder extends RecyclerView.ViewHolder  {
        private TagCloudLayout tagCloudLayout;
        private TextView title;
        public HeaderHolder(View itemView) {
            super(itemView);
            tagCloudLayout =  itemView.findViewById(R.id.tab_container);
            title =  itemView.findViewById(R.id.title);
        }
    }

    public class DataHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private ImageView imageView;
        private TextView txtName;
        private TextView txtPrice;

        private LinearLayout layoutView;
        public DataHolder(View itemView) {
            super(itemView);
            imageView =  itemView.findViewById(R.id.image);
            txtName =  itemView.findViewById(R.id.txt_name);
            txtPrice =  itemView.findViewById(R.id.txt_price);
            layoutView = itemView.findViewById(R.id.layout);
        }

        public TextView getTxtName() {
            return txtName;
        }

        public void setTxtName(TextView txtName) {
            this.txtName = txtName;
        }

        public TextView getTxtPrice() {
            return txtPrice;
        }

        public void setTxtPrice(TextView txtPrice) {
            this.txtPrice = txtPrice;
        }

        public ImageView getImageView() {
            return imageView;
        }

        public void setImageView(ImageView imageView) {
            this.imageView = imageView;
        }

        public LinearLayout getLayoutView() {
            return layoutView;
        }

        public void setLayoutView(LinearLayout layoutView) {
            this.layoutView = layoutView;
        }

        @Override
        public void onClick(View v) {
            //mListener.onItemClick(getAdapterPosition(),"a");
            switch (v.getId())
            {
                case R.id.layout:
            }
        }
    }
}
