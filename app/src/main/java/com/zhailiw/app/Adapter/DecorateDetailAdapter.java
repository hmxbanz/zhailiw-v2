package com.zhailiw.app.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhailiw.app.R;
import com.zhailiw.app.loader.GlideImageLoader;
import com.zhailiw.app.server.response.DecorateDetailResponse;
import com.zhailiw.app.server.response.HousePeopleResponse;
import com.zhailiw.app.view.activity.DecoratePeopleActivity;
import com.zhailiw.app.view.activity.DiaryDetailActivity;
import com.zhailiw.app.view.activity.DiaryNewActivity;
import com.zhailiw.app.view.activity.ProgressEditActivity;
import com.zhailiw.app.widget.progressBar.MaterialProgressBar;

import java.util.List;


public class DecorateDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private List<DecorateDetailResponse.DataBean> listItems;
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
    private List<HousePeopleResponse.DataBean> headerList;

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

    public DecorateDetailAdapter(Context c){
        this.context=c;
        this.layoutInflater= LayoutInflater.from(c);
        glideImageLoader=new GlideImageLoader();
        mHeaderView=layoutInflater.inflate(R.layout.listitem_decorate_detail_header, null, false);
    }
    public void setListItems(List<DecorateDetailResponse.DataBean> l)
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
            View v = layoutInflater.inflate(R.layout.listitem_decorate_detail, parent, false);
            return new DataHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (getItemViewType(position) == TYPE_FOOTER) return;
        final int pos = getRealPosition(holder);

        if (holder instanceof HeaderHolder) {
            final HeaderHolder headerHolder = (HeaderHolder) holder;
            final DecorateDetailResponse.DataBean listItem = listItems.get(0);
            headerHolder.setData(listItem);
            if (headerList !=null)
            {
                for (HousePeopleResponse.DataBean bean :
                        headerList) {
                    if (bean.getRoleID()==13)
                    {
                        headerHolder.txtDesignerName.setText(bean.getRealName().trim());
                        headerHolder.txtDesignerCellphone.setText(bean.getCellPhone().trim());
                    }
                    else if(bean.getRoleID()==14)
                    {
                        headerHolder.txtWorkerName.setText(bean.getRealName().trim());
                        headerHolder.txtWorkerCellphone.setText(bean.getCellPhone().trim());
                    }
                    else if(bean.getRoleID()==16)
                    {
                        headerHolder.txtSellerName.setText(bean.getRealName().trim());
                        headerHolder.txtSellerCellphone.setText(bean.getCellPhone().trim());
                    }

                }
            }
        }

        if(getItemViewType(position) == TYPE_HEADER) return;
        //holder.setIsRecyclable(false);
        final DecorateDetailResponse.DataBean listItem = listItems.get(pos);
        if (holder instanceof DataHolder) {
            final DataHolder dataHolder = (DataHolder) holder;
            dataHolder.setData(listItem);
            dataHolder.setPosition(position);
            //dataHolder.txtName.setText(pos + 1 + ":" + listItem.getProcessName());
            dataHolder.txtState.setText(listItem.getProcessStateName());
//            if(listItem.getProcessName()=="施工流程")
//                listItem.setName("展开");

            if(listItem.getName().contains("收起")){
                dataHolder.getRecyclerView().setVisibility(View.VISIBLE);
            }
            else
            {
                dataHolder.getRecyclerView().setVisibility(View.GONE);
            }

            switch (pos) {
                case 0:
                    dataHolder.layoutView.setBackground(context.getResources().getDrawable(R.drawable.title_bg_01));
                    break;
                case 1:
                    dataHolder.layoutView.setBackground(context.getResources().getDrawable(R.drawable.title_bg_02));
                    break;
                case 2:
                    dataHolder.layoutView.setBackground(context.getResources().getDrawable(R.drawable.title_bg_03));
                    break;
                case 3:
                    dataHolder.layoutView.setBackground(context.getResources().getDrawable(R.drawable.title_bg_04));
                    break;
                case 4:
                    dataHolder.layoutView.setBackground(context.getResources().getDrawable(R.drawable.title_bg_05));
                    break;
                case 5:
                    dataHolder.layoutView.setBackground(context.getResources().getDrawable(R.drawable.title_bg_06));
                    break;
                case 6:
                    dataHolder.layoutView.setBackground(context.getResources().getDrawable(R.drawable.title_bg_07));
                    break;
                case 7:
                    dataHolder.layoutView.setBackground(context.getResources().getDrawable(R.drawable.title_bg_08));
                    break;
                case 8:
                    dataHolder.layoutView.setBackground(context.getResources().getDrawable(R.drawable.title_bg_09));
                    break;
                case 9:
                    dataHolder.layoutView.setBackground(context.getResources().getDrawable(R.drawable.title_bg_10));
                    break;
                case 10:
                    dataHolder.layoutView.setBackground(context.getResources().getDrawable(R.drawable.title_bg_11));
                    break;
            }


            switch (listItem.getProcessName()) {
                case "装修设计":
//                    dataHolder.txtTips.setText("点击上传设计方案");
//                    if(roleId==14 ){
//                        dataHolder.getImgArrow().setVisibility(View.INVISIBLE);
//                        dataHolder.txtTips.setOnClickListener(null);
//                        dataHolder.txtTips.setText("");
//                    }
//                    else if(roleId==15){
//                        dataHolder.txtTips.setOnClickListener(null);
//                        dataHolder.txtTips.setText("");
//                    }
                    break;
                case "装修预算":
//                    dataHolder.txtTips.setText("点击上传预算方案");
//                    if(roleId==14 ){
//                        dataHolder.getImgArrow().setVisibility(View.INVISIBLE);
//                        dataHolder.txtTips.setOnClickListener(null);
//                        dataHolder.txtTips.setText("");
//                    }
//                    else if(roleId==15){
//                        dataHolder.txtTips.setOnClickListener(null);
//                        dataHolder.txtTips.setText("");
//                    }
                    break;
                case "装修合同":
//                    dataHolder.txtTips.setText("点击上传合同");
//                    if(roleId==14 ){
//                        dataHolder.getImgArrow().setVisibility(View.INVISIBLE);
//                        dataHolder.txtTips.setOnClickListener(null);
//                        dataHolder.txtTips.setText("");
//                    }
//                    else if(roleId==15){
//                        dataHolder.txtTips.setOnClickListener(null);
//                        dataHolder.txtTips.setText("");
//                    }

                    break;
                case "施工流程":
                    if(listItem.getProcessState()==318)
                    dataHolder.getBtn_to_progress().setVisibility(View.VISIBLE);
                    dataHolder.getRecyclerView().setVisibility(View.VISIBLE);
                    dataHolder.getImgArrow().setImageDrawable(context.getResources().getDrawable(R.drawable.icon_up));
                    dataHolder.txtTips.setText("等待施工上传施工流程");
                    if(roleId==15 || (roleId==13 && listItem.getProcessState()==319)|| (roleId==14 && listItem.getProcessState()==327) )//待确认
                        dataHolder.getImgArrow().setVisibility(View.INVISIBLE);
                    mListener.onProgressLoadding(dataHolder, listItem);
                    break;

            }
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

    public void setHeaderList(List<HousePeopleResponse.DataBean> headerList) {
        this.headerList = headerList;
    }

    public interface ItemClickListener {
        void onItemClick(View v, DecorateDetailResponse.DataBean item);
        void onImgArrowClick(DataHolder dataHolder, DecorateDetailResponse.DataBean item, int position);
        void onProgressLoadding(DataHolder dataHolder, DecorateDetailResponse.DataBean item);
        void onPhoneClick(HeaderHolder v, DecorateDetailResponse.DataBean item, int role);
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

    public class HeaderHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        private DecorateDetailResponse.DataBean data;
        private TextView txtDesignerName,txtWorkerName,txtSellerName;
        private TextView txtDesignerCellphone,txtWorkerCellphone,txtSellerCellphone;
        private ImageButton btnDesigner,btnWorker,btnSeller;
        public void setData(DecorateDetailResponse.DataBean data) {
            this.data = data;
        }

        public TextView getTxtDesignerCellphone() {
            return txtDesignerCellphone;
        }

        public TextView getTxtWorkerCellphone() {
            return txtWorkerCellphone;
        }

        public TextView getTxtSellerCellphone() {
            return txtSellerCellphone;
        }

        public HeaderHolder(View itemView) {
            super(itemView);
            txtDesignerName =  itemView.findViewById(R.id.txt_designer_name);
            txtWorkerName =  itemView.findViewById(R.id.txt_worker_name);
            txtSellerName =  itemView.findViewById(R.id.txt_seller_name);
            txtDesignerCellphone =  itemView.findViewById(R.id.txt_designer_cellphone);
            txtWorkerCellphone =  itemView.findViewById(R.id.txt_worker_cellphone);
            txtSellerCellphone =  itemView.findViewById(R.id.txt_seller_cellphone);
            btnDesigner =  itemView.findViewById(R.id.btn_designer);
            btnWorker =  itemView.findViewById(R.id.btn_worker);
            btnSeller =  itemView.findViewById(R.id.btn_seller);
            btnDesigner.setOnClickListener(this);
            btnWorker.setOnClickListener(this);
            btnSeller.setOnClickListener(this);
            itemView.findViewById(R.id.layout_designer).setOnClickListener(this);
            itemView.findViewById(R.id.layout_worker).setOnClickListener(this);
            itemView.findViewById(R.id.layout_seller).setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.btn_designer:
                    mListener.onPhoneClick(this,data,1);
                break;
                case R.id.btn_worker:
                    mListener.onPhoneClick(this,data,2);
                    break;
                case R.id.btn_seller:
                    mListener.onPhoneClick(this,data,3);
                    break;
                case R.id.layout_designer:
                    if(roleId==16 || roleId==13)
                        DecoratePeopleActivity.StartActivity(context,1,data.getDecorateID(),data.getDesingerRealName(),data.getDesingerCellPhone());
                    break;
                case R.id.layout_worker:
                    if(roleId==16 || roleId==13)
                    DecoratePeopleActivity.StartActivity(context,2,data.getDecorateID(), data.getWorkerRealName(),data.getWorkerCellPhone());
                    break;
                case R.id.layout_seller:
                    if(roleId==16 || roleId==13)
                    DecoratePeopleActivity.StartActivity(context,3,data.getDecorateID(),data.getSellerRealName(),data.getSellerCellPhone());
                    break;
            }
        }
    }

    public class DataHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView txtName,txtState,btn_to_progress,txtTips;
        private ImageView imgArrow;
        private LinearLayout layoutView;
        private RecyclerView recyclerView;
        private DecorateDetailResponse.DataBean data;
        private int position;

        public TextView getBtn_to_progress() {
            return btn_to_progress;
        }

        public RecyclerView getRecyclerView() {
            return recyclerView;
        }
        public ImageView getImgArrow() {
            return imgArrow;
        }
        public TextView getTxtTips() {
            return txtTips;
        }

        public int getListPosition() {
            return position;
        }

        private int isExpand=1;

        public DataHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_name);
            txtState =  itemView.findViewById(R.id.txt_state);
            btn_to_progress =  itemView.findViewById(R.id.btn_to_progress);
            Drawable drawable = context.getResources().getDrawable(R.drawable.icon_edit2);
            drawable.setBounds(0,0,30,30);
            btn_to_progress.setCompoundDrawables(drawable,null,null,null);
            btn_to_progress.setOnClickListener(this);
            txtTips =  itemView.findViewById(R.id.txt_tips);
            imgArrow =  itemView.findViewById(R.id.img_arrow);
            imgArrow.setOnClickListener(this);
            recyclerView =  itemView.findViewById(R.id.recyclerView);
            layoutView = itemView.findViewById(R.id.layout);
            layoutView.setOnClickListener(this);
            txtTips.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.layout:
                    if(position!=5 && roleId !=14)
                    DiaryDetailActivity.StartActivity(context,data.getProcessID(),0,position,data.getProcessState());
                    break;
                case R.id.txt_tips:
                    DiaryNewActivity.StartActivity(context,data.getProcessID(),0,position);
                    break;
                case R.id.img_arrow:
                    //发请求
//                    if(position==5)
//                        ProgressEditActivity.StartActivity(context,data.getProcessID());
//                        else
                        mListener.onImgArrowClick(this,data,position);
                    break;
                case R.id.btn_to_progress:
                        ProgressEditActivity.StartActivity(context,data.getProcessID());
                    break;
            }
        }

        public void setData(DecorateDetailResponse.DataBean data) {
            this.data = data;
        }

        public void setPosition(int position) {
            this.position = position;
        }
    }

}
