package com.zhailiw.app.Adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.zhailiw.app.R;
import com.zhailiw.app.loader.GlideImageLoader;
import com.zhailiw.app.server.response.AddressResponse;

import java.util.List;


public class AddressAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private List<AddressResponse.DataBean> listItems;
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
    public void setFooterView(View headerView) {
        mFooterView = headerView;
        notifyItemInserted(0);//告知Adapter首位置项变动了
    }

    public AddressAdapter(Context c){
        this.context=c;
        this.layoutInflater= LayoutInflater.from(c);
        glideImageLoader=new GlideImageLoader();
    }
    public void setListItems(List<AddressResponse.DataBean> l)
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
            View v = layoutInflater.inflate(R.layout.listitem_address, parent, false);
            return new DataHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(getItemViewType(position) == TYPE_HEADER) return;
        if(getItemViewType(position) == TYPE_FOOTER) return;
        final int pos = getRealPosition(holder);
        //if(position==listItems.size())return;

        final AddressResponse.DataBean listItem = listItems.get(position);
        if(holder instanceof DataHolder) {
            DataHolder dataHolder=(DataHolder)holder;
            dataHolder.txtName.setText(listItem.getContact());
            dataHolder.txtCellphone.setText(listItem.getCellphone());
            dataHolder.txtAddress.setText(listItem.getAddress());
            if(listItem.getType()==1){
                dataHolder.imgSetAddress.setVisibility(View.VISIBLE);
                dataHolder.txtSetAddress.setText("默认地址");
                dataHolder.txtSetAddress.setTextColor(context.getResources().getColor(R.color.mainColor));
            }
            else
            {
                dataHolder.imgSetAddress.setVisibility(View.GONE);
                dataHolder.txtSetAddress.setText("设为默认");
                dataHolder.txtSetAddress.setTextColor(context.getResources().getColor(R.color.appTextColor));
            }

            if(mListener == null) return;
            dataHolder.layoutView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(position,listItem);
                }
            });
            dataHolder.txtSetAddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onTxtDelete(R.id.txt_set_address,listItem);
                }
            });
            dataHolder.txtDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onTxtDelete(R.id.txt_delete,listItem);
                }
            });
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
        void onItemClick(int position, AddressResponse.DataBean item);
        void onTxtDelete(int id, AddressResponse.DataBean item);

    }
    class HeaderHolder extends RecyclerView.ViewHolder  {
        private Banner banner;
        public HeaderHolder(View itemView) {
            super(itemView);
        }
    }

    class DataHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView txtName;
        private TextView txtCellphone;
        private TextView txtAddress;
        private TextView txtSetAddress;
        private TextView txtDelete;
        private ImageView imgSetAddress;
        private LinearLayout layoutView;

        public DataHolder(View itemView) {
            super(itemView);
            txtName =  itemView.findViewById(R.id.txt_name);
            txtCellphone =  itemView.findViewById(R.id.txt_cellphone);
            txtAddress =  itemView.findViewById(R.id.txt_address);
            txtDelete =  itemView.findViewById(R.id.txt_delete);
            txtSetAddress =  itemView.findViewById(R.id.txt_set_address);
            imgSetAddress=  itemView.findViewById(R.id.img_set_address);
            layoutView = itemView.findViewById(R.id.layout);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.layout:
                    break;
            }
        }
    }
}
