package com.zhailiw.app.Adapter;

import android.content.Context;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhailiw.app.R;
import com.zhailiw.app.common.CommonTools;
import com.zhailiw.app.server.response.ProgressListResponse;
import com.zhailiw.app.widget.draglist.AD_DragBase;

public class ProgressEditAdapter extends AD_DragBase<ProgressListResponse.DataBean> {

    private final Context context;
    LayoutInflater inflater;
    private TextView txtDel;
    private ItemClickListener listener;

    public void setListener(ItemClickListener listener) {
        this.listener = listener;
    }

    public ProgressEditAdapter(Context context) {
        super(context);
        this.context=context;
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
        TextPaint tp = txtName.getPaint();
        tp.setFakeBoldText(true);
        TextView txtDate= (TextView) item.findViewById(R.id.txt_date);
        TextView txtDoneDate= (TextView) item.findViewById(R.id.txt_done_date);
        ImageView iconState = item.findViewById(R.id.img_state);
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
        txtDoneDate.setText("完工日期："+CommonTools.formatDateTime4(entity.getDoneDate()));
        switch (entity.getState()) {
            case 318:
                iconState.setImageDrawable(context.getResources().getDrawable(R.drawable.icon_not_start));
                break;
            case 319:
                iconState.setImageDrawable(context.getResources().getDrawable(R.drawable.icon_working));
                break;
            case 329:
                iconState.setImageDrawable(context.getResources().getDrawable(R.drawable.icon_done));
                break;
        }
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
