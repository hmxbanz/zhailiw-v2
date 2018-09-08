package com.zhailiw.app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhailiw.app.R;
import com.zhailiw.app.common.CommonTools;
import com.zhailiw.app.server.response.ProgressListResponse;
import com.zhailiw.app.widget.draglist.AD_DragBase;

public class ProgressEditAdapter extends AD_DragBase<ProgressListResponse.DataBean> {

    LayoutInflater inflater;
    private TextView txtDel;
    private ItemClickListener listener;

    public void setListener(ItemClickListener listener) {
        this.listener = listener;
    }

    public ProgressEditAdapter(Context context) {
        super(context);
        inflater=LayoutInflater.from(context);
    }
    public interface ItemClickListener {
        void onTxtDelClick(View v, ProgressListResponse.DataBean entity);
        void onItemClick(View v, ProgressListResponse.DataBean entity);
    }

    @Override
    public View initItemView(int position, View convertView, ViewGroup parent) {
        View item=inflater.inflate(R.layout.listitem_progress_edit,null);
        final ProgressListResponse.DataBean entity=ts.get(position);
        TextView txtName= (TextView) item.findViewById(R.id.txt_name);
        TextView txtDate= (TextView) item.findViewById(R.id.txt_date);
        txtDel= (TextView) item.findViewById(R.id.txt_del);
        txtDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onTxtDelClick(v,entity);
            }
        });
        item.findViewById(R.id.layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v,entity);
            }
        });
        txtDate.setText("施工周期："+ CommonTools.formatDateTime2(entity.getStartDate())+" 至 "+CommonTools.formatDateTime2(entity.getEndDate()));
        txtName.setText(entity.getName());
        //Toast.makeText(MainActivity.this,tv.getText(),Toast.LENGTH_SHORT).show();
        //Log.e("hmxbanz:",tv.getText().toString());
        return item;
    }
//    @Override
//    protected View newView(Context context, int position, ViewGroup group) {
//        View v = inflater.inflate(R.layout.listitem_progress_edit, group, false);
//        bindView(v,position,list.get(position));
//        return v;
//    }
//
//    @Override
//    protected void bindView(View v, int position, String data) {
//
//    }
}
