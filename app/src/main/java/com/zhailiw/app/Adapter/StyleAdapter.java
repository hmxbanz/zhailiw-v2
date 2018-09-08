package com.zhailiw.app.Adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.zhailiw.app.Const;
import com.zhailiw.app.R;
import com.zhailiw.app.common.NToast;
import com.zhailiw.app.loader.GlideImageLoader;
import com.zhailiw.app.server.response.StyleResponse;

import java.util.List;


public class StyleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private List<StyleResponse.DataBean> listItems;
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
    private List<String> adImages;

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
    public void setFooterView(View headerView) {
        mFooterView = headerView;
        notifyItemInserted(0);//告知Adapter首位置项变动了
    }

    public StyleAdapter(Context c){
        this.context=c;
        this.layoutInflater= LayoutInflater.from(c);
        glideImageLoader=new GlideImageLoader();
    }
    public void setListItems(List<StyleResponse.DataBean> l)
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
            View v = layoutInflater.inflate(R.layout.listitem_style, parent, false);
            return new DataHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(getItemViewType(position) == TYPE_HEADER) return;
        if(getItemViewType(position) == TYPE_FOOTER) return;
        final int pos = getRealPosition(holder);
        //if(position==listItems.size())return;

        final StyleResponse.DataBean listItem = listItems.get(position);
        if(holder instanceof DataHolder) {
            DataHolder dataHolder=(DataHolder)holder;
            dataHolder.txtStyleName.setText(listItem.getName());
            glideImageLoader.displayImage(context, Const.IMGURI+listItem.getPic(),dataHolder.imageView);
            //Glide.with(context).load(listItem.getAvator()).asBitmap().into(holder.imageView);
            //holder.imageView.setImageResource(listItem.getImgResource());
            if(mListener == null) return;
            dataHolder.layoutView.setOnClickListener(new View.OnClickListener() {
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
            if (mRecyclerView == null && mRecyclerView != recyclerView) {
                mRecyclerView = recyclerView;
            }
            ifGridLayoutManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void ifGridLayoutManager() {
        if (mRecyclerView == null) return;
        final RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager.SpanSizeLookup originalSpanSizeLookup = ((GridLayoutManager) layoutManager).getSpanSizeLookup();
            ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (isHeaderView(position) || isFooterView(position)) ?
                            ((GridLayoutManager) layoutManager).getSpanCount() :
                            1;
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
    public void setAdImages(List<String> images) {
        this.adImages=images;
    }
    public interface ItemClickListener {
        void onItemClick(int position, StyleResponse.DataBean item);

    }
    class HeaderHolder extends RecyclerView.ViewHolder  {
        private Banner banner;
        public HeaderHolder(View itemView) {
            super(itemView);
        }
    }

    class DataHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private ImageView imageView;
        private TextView txtStyleName;
        private RelativeLayout layoutView;

        public DataHolder(View itemView) {
            super(itemView);
            imageView =  itemView.findViewById(R.id.image);
            txtStyleName = itemView.findViewById(R.id.txt_style_name);
            layoutView = itemView.findViewById(R.id.layout);
        }

        public TextView getTxtStyleName() {
            return txtStyleName;
        }

        public void setTxtStyleName(TextView txtStyleName) {
            this.txtStyleName = txtStyleName;
        }

        public ImageView getImageView() {
            return imageView;
        }

        public void setImageView(ImageView imageView) {
            this.imageView = imageView;
        }

        public RelativeLayout getLayoutView() {
            return layoutView;
        }

        public void setLayoutView(RelativeLayout layoutView) {
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
