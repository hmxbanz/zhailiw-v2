package com.zhailiw.app.Adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhailiw.app.R;
import com.zhailiw.app.common.NToast;
import com.zhailiw.app.loader.GlideImageLoader;
import com.zhailiw.app.server.response.DecorateAllResponse;
import com.zhailiw.app.view.activity.HouseDetailActivity;
import com.zhailiw.app.widget.progressBar.MaterialProgressBar;

import java.util.List;


public class DecorateAllAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private List<DecorateAllResponse.DataBean> listItems;
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

    public DecorateAllAdapter(Context c){
        this.context=c;
        this.layoutInflater= LayoutInflater.from(c);
        glideImageLoader=new GlideImageLoader();
    }
    public void setListItems(List<DecorateAllResponse.DataBean> l)
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
            View v = layoutInflater.inflate(R.layout.listitem_decorate, parent, false);
            return new DataHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (getItemViewType(position) == TYPE_FOOTER) return;
        final int pos = getRealPosition(holder);

        if (getItemViewType(position) == TYPE_HEADER) return;
        final DecorateAllResponse.DataBean listItem = listItems.get(pos);
        if (holder instanceof DataHolder) {
            final DataHolder dataHolder = (DataHolder) holder;
            dataHolder.setData(listItem);
            dataHolder.txtHouseName.setText(listItem.getHouseName());
            dataHolder.txtAddress.setText(listItem.getAddress());
            dataHolder.txtBudget.setText("预算:" + listItem.getBudget());
            //dataHolder.txtBudget.setText("风格:" );

            if (mListener == null) return;
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
        void onItemClick(View v, DecorateAllResponse.DataBean item);
    }
    class HeaderHolder extends RecyclerView.ViewHolder  {
        private TextView title;
        public HeaderHolder(View itemView) {
            super(itemView);
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
        private TextView txtHouseName,txtAddress,txtBudget,txtStyleName;
        private LinearLayout layoutView;
        private DecorateAllResponse.DataBean data;

        public DataHolder(View itemView) {
            super(itemView);
            txtHouseName = itemView.findViewById(R.id.txt_house_name);
            txtAddress =  itemView.findViewById(R.id.txt_address);
            txtStyleName =  itemView.findViewById(R.id.txt_style_name);
            txtBudget =  itemView.findViewById(R.id.txt_budget);
            layoutView = itemView.findViewById(R.id.layout);
            layoutView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.layout:
                    HouseDetailActivity.StartActivity(context,data.getHouseId());
                    //NToast.longToast(context,data.getAddress());
            }
        }

        public void setData(DecorateAllResponse.DataBean data) {
            this.data = data;
        }
    }
}
