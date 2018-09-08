package com.zhailiw.app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.zhailiw.app.R;
import com.zhailiw.app.server.response.SystemObjResponse;

import java.util.List;

public class MyTabAdapter extends BaseAdapter {

    private Context mContext;
    private List<SystemObjResponse.SysObjBean.ChildDictionariesBean> mList;

    public MyTabAdapter(Context context, List<SystemObjResponse.SysObjBean.ChildDictionariesBean> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public SystemObjResponse.SysObjBean.ChildDictionariesBean getItem(int position) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_tab, null);
            holder = new ViewHolder();
            holder.tagBtn = (Button) convertView.findViewById(com.fyales.tagcloud.library.R.id.tag_btn);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        final SystemObjResponse.SysObjBean.ChildDictionariesBean text = getItem(position);
        holder.tagBtn.setText(text.getName());
        return convertView;
    }

    static class ViewHolder {
        Button tagBtn;
    }
}
