package com.zhailiw.app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.zhailiw.app.R;
import com.zhailiw.app.server.response.ProductAttributeResponse;
import com.zhailiw.app.server.response.SystemObjResponse;

import java.util.List;

public class BuyTabAdapter extends BaseAdapter {

    private Context mContext;
    private List<ProductAttributeResponse.DataBean> mList;

    public BuyTabAdapter(Context context, List<ProductAttributeResponse.DataBean> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public ProductAttributeResponse.DataBean getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_buy_tab, null);
            holder = new ViewHolder();
            holder.tagBtn = (Button) convertView.findViewById(com.fyales.tagcloud.library.R.id.tag_btn);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        final ProductAttributeResponse.DataBean text = getItem(position);
        if(text.getIsSelect()){
            holder.tagBtn.setBackgroundResource(R.color.orange);
            holder.tagBtn.setTextColor(mContext.getResources().getColor(R.color.white));
        }

        holder.tagBtn.setText(text.getType());
        return convertView;
    }

    static class ViewHolder {
        Button tagBtn;
    }
}
