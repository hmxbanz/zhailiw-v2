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

import com.fyales.tagcloud.library.TagCloudLayout;
import com.zhailiw.app.Const;
import com.zhailiw.app.R;
import com.zhailiw.app.loader.GlideImageLoader;
import com.zhailiw.app.server.response.GalleryResponse;
import com.zhailiw.app.server.response.SystemObjResponse;
import com.zhailiw.app.widget.progressBar.MaterialProgressBar;

import java.util.ArrayList;
import java.util.List;


public class GalleryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private List<GalleryResponse.DataBean> listItems;
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
    private List<SystemObjResponse.SysObjBean.ChildDictionariesBean> tabs;

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

    public GalleryAdapter(Context c){
        this.context=c;
        this.layoutInflater= LayoutInflater.from(c);
        glideImageLoader=new GlideImageLoader();
        View v = layoutInflater.inflate(R.layout.listitem_gallery_header,null, false);
        mHeaderView=v;
    }
    public void setListItems(List<GalleryResponse.DataBean> l)
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
            View v = layoutInflater.inflate(R.layout.listitem_gallery, parent, false);
            return new DataHolder(v);
        }
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(getItemViewType(position) == TYPE_FOOTER) return;
        final int pos = getRealPosition(holder);

        if(holder instanceof HeaderHolder) {
            final HeaderHolder headerHolder=(HeaderHolder)holder;
//            final ArrayList mList = new ArrayList<>();
//            mList.add("欧美");
//            mList.add("休闲");
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
        final GalleryResponse.DataBean listItem = listItems.get(pos);
        if(holder instanceof DataHolder) {
            final DataHolder dataHolder=(DataHolder)holder;
            glideImageLoader.displayImage(context, Const.IMGURI+listItem.getGalleryCover(),dataHolder.imageView);
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
    public void setTabList(List<SystemObjResponse.SysObjBean.ChildDictionariesBean> tabs) {
        this.tabs=tabs;
    }
    public interface ItemClickListener {
        void onItemClick(int position, GalleryResponse.DataBean item);
        void onTabItemClick(int position, SystemObjResponse.SysObjBean.ChildDictionariesBean item);
        void onTabExpand();
    }
    class HeaderHolder extends RecyclerView.ViewHolder  {
        private TagCloudLayout tagCloudLayout;
        private TextView title;
        public HeaderHolder(View itemView) {
            super(itemView);
            tagCloudLayout =  itemView.findViewById(R.id.tab_container);
            title =  itemView.findViewById(R.id.title);
        }
    }

    public void onLoading(){
        mFooterView.setVisibility(View.VISIBLE);
        TextView tips=mFooterView.findViewById(R.id.tips);
        MaterialProgressBar progressBar=mFooterView.findViewById(R.id.progress_wheel);
        mFooterView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        tips.setText(R.string.layout_dialog_loading);
    }
    public void onLoadingDone(){
        mFooterView.setVisibility(View.VISIBLE);
        TextView tips=mFooterView.findViewById(R.id.tips);
        MaterialProgressBar progressBar=mFooterView.findViewById(R.id.progress_wheel);
        progressBar.setVisibility(View.GONE);
        tips.setText("我是有底线的");
    }

    public class DataHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private ImageView imageView;
        private RelativeLayout layoutView;
        public DataHolder(View itemView) {
            super(itemView);
            imageView =  itemView.findViewById(R.id.image);
            layoutView = itemView.findViewById(R.id.layout);
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
