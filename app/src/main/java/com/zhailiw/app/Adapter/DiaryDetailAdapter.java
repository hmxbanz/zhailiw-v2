package com.zhailiw.app.Adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;
import com.zhailiw.app.Const;
import com.zhailiw.app.R;
import com.zhailiw.app.common.CommonTools;
import com.zhailiw.app.loader.GlideImageLoader;
import com.zhailiw.app.server.response.DecorateDetailResponse;
import com.zhailiw.app.server.response.DecorateListResponse;
import com.zhailiw.app.widget.progressBar.MaterialProgressBar;

import java.util.ArrayList;
import java.util.List;


public class DiaryDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private List<DecorateListResponse.DataBean> listItems;
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
    private int roleId;
    private int taskState;

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

    public DiaryDetailAdapter(Context c){
        this.context=c;
        this.layoutInflater= LayoutInflater.from(c);
        glideImageLoader=new GlideImageLoader();
    }
    public void setListItems(List<DecorateListResponse.DataBean> l)
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
            View v = layoutInflater.inflate(R.layout.listitem_diary_detail, parent, false);
            return new DataHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (getItemViewType(position) == TYPE_FOOTER) return;
        final int pos = getRealPosition(holder);

        if (getItemViewType(position) == TYPE_HEADER) return;
        final DecorateListResponse.DataBean listItem = listItems.get(pos);
        if (holder instanceof DataHolder) {
            final DataHolder dataHolder = (DataHolder) holder;
            dataHolder.setData(listItem);
            dataHolder.txtTypeName.setText(listItem.getDetailTypeName());
            String remark=listItem.getRemark();
            int remarkLength=CommonTools.length(remark);
            if(remarkLength>200)
            {
                dataHolder.txtShowMove.setVisibility(View.VISIBLE);
                dataHolder.txtRemark.setText(remark.substring(0,60)+"...");
            }
            else
            {
                dataHolder.txtShowMove.setVisibility(View.INVISIBLE);
                dataHolder.txtRemark.setText(listItem.getRemark());
            }

            dataHolder.txtCreateDate.setText(CommonTools.formatDateTime4(listItem.getCreateDate()));
            if(listItem.getIsTop()==1)
            {
                dataHolder.layoutView.setBackgroundColor(context.getResources().getColor(R.color.mainColorBg));
                dataHolder.txtToTop.setVisibility(View.INVISIBLE);
                dataHolder.txtDelete.setText("已作废");
            }
            else
            {
                dataHolder.layoutView.setBackgroundColor(context.getResources().getColor(R.color.white));
                dataHolder.txtToTop.setVisibility(View.VISIBLE);
                dataHolder.txtDelete.setText("作废");
            }

            if(listItem.getIsTop()>2)
            {
                dataHolder.txtToTop.setText("取消置顶");
            }
            else
            {
                dataHolder.txtToTop.setText("置顶");
            }

            if(taskState==329)
            {
                dataHolder.txtToTop.setVisibility(View.INVISIBLE);
                dataHolder.txtDelete.setVisibility(View.INVISIBLE);
            }

            if(roleId !=0){
                dataHolder.txtToTop.setVisibility(View.INVISIBLE);
                dataHolder.txtDelete.setVisibility(View.INVISIBLE);
            }

            ArrayList<ImageInfo> imageInfo=new ArrayList<>();
            if(listItem.getPic1_Big()!=null){
                ImageInfo img=new ImageInfo();
                img.setBigImageUrl(Const.SERVERURI+listItem.getPic1_Big());
                img.setThumbnailUrl(Const.SERVERURI+listItem.getPic1());
                imageInfo.add(img);
            }

            if(listItem.getPic2_Big()!=null){
                ImageInfo img2=new ImageInfo();
                img2.setBigImageUrl(Const.SERVERURI+listItem.getPic2_Big());
                img2.setThumbnailUrl(Const.SERVERURI+listItem.getPic2());
                imageInfo.add(img2);
            }

            if(listItem.getPic3_Big()!=null){
                ImageInfo img3=new ImageInfo();
                img3.setBigImageUrl(Const.SERVERURI+listItem.getPic3_Big());
                img3.setThumbnailUrl(Const.SERVERURI+listItem.getPic3());
                imageInfo.add(img3);
            }
            if(listItem.getPic4_Big()!=null){
                ImageInfo img4=new ImageInfo();
                img4.setBigImageUrl(Const.SERVERURI+listItem.getPic4_Big());
                img4.setThumbnailUrl(Const.SERVERURI+listItem.getPic4());
                imageInfo.add(img4);
            }
            if(listItem.getPic5_Big()!=null){
                ImageInfo img5=new ImageInfo();
                img5.setBigImageUrl(Const.SERVERURI+listItem.getPic5_Big());
                img5.setThumbnailUrl(Const.SERVERURI+listItem.getPic5());
                imageInfo.add(img5);
            }
            if(listItem.getPic6_Big()!=null){
                ImageInfo img6=new ImageInfo();
                img6.setBigImageUrl(Const.SERVERURI+listItem.getPic6_Big());
                img6.setThumbnailUrl(Const.SERVERURI+listItem.getPic6());
                imageInfo.add(img6);
            }
            if(listItem.getPic7_Big()!=null){
                ImageInfo img7=new ImageInfo();
                img7.setBigImageUrl(Const.SERVERURI+listItem.getPic7_Big());
                img7.setThumbnailUrl(Const.SERVERURI+listItem.getPic7());
                imageInfo.add(img7);
            }
            if(listItem.getPic8_Big()!=null){
                ImageInfo img8=new ImageInfo();
                img8.setBigImageUrl(Const.SERVERURI+listItem.getPic8_Big());
                img8.setThumbnailUrl(Const.SERVERURI+listItem.getPic8());
                imageInfo.add(img8);
            }
            if(listItem.getPic9_Big()!=null){
                ImageInfo img9=new ImageInfo();
                img9.setBigImageUrl(Const.SERVERURI+listItem.getPic9_Big());
                img9.setThumbnailUrl(Const.SERVERURI+listItem.getPic9());
                imageInfo.add(img9);
            }


            dataHolder.nineGrid.setAdapter(new NineGridViewClickAdapter(context, imageInfo));

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

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public void setTaskState(int taskState) {
        this.taskState = taskState;
    }


    public interface ItemClickListener {
        void onItemClick(View v, DecorateDetailResponse.DataBean item);

        void onDelClick(DecorateListResponse.DataBean data);

        void onToTopClick(DecorateListResponse.DataBean data);
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
        private TextView txtTypeName,txtCreateDate, txtRemark,txtDelete,txtToTop,txtShowMove;
        private NineGridView nineGrid;
        private LinearLayout layoutView;
        private DecorateListResponse.DataBean data;

        public DataHolder(View itemView) {
            super(itemView);
            txtTypeName = itemView.findViewById(R.id.txt_type_name);
            txtCreateDate = itemView.findViewById(R.id.txt_create_date);
            txtRemark =  itemView.findViewById(R.id.txt_remark);
            txtShowMove =  itemView.findViewById(R.id.txt_show_move);
            txtDelete =  itemView.findViewById(R.id.txt_delete);
            txtToTop =  itemView.findViewById(R.id.txt_to_top);
            nineGrid =  itemView.findViewById(R.id.nineGrid);
            layoutView = itemView.findViewById(R.id.layout);
            layoutView.setOnClickListener(this);
            txtDelete.setOnClickListener(this);
            txtToTop.setOnClickListener(this);
            txtShowMove.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.layout:
                    break;
                case R.id.txt_delete:
                    mListener.onDelClick(data);
                    break;
                case R.id.txt_to_top:
                    mListener.onToTopClick(data);
                    break;
                case R.id.txt_show_move:
                    this.txtRemark.setText(data.getRemark());
                    this.txtShowMove.setVisibility(View.INVISIBLE);
                    break;
            }
        }

        public void setData(DecorateListResponse.DataBean data) {
            this.data = data;
        }

    }
}
