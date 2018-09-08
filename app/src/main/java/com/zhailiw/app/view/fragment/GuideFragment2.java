package com.zhailiw.app.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zhailiw.app.R;


@SuppressLint({"ValidFragment"})
public class GuideFragment2 extends Fragment{
    private Context context;
    public GuideFragment2(Context context)
    {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view;
        view = View.inflate(context, R.layout.fragment_guide2, null);
        LinearLayout mLinear = (LinearLayout) view
                .findViewById(R.id.GuideFragment02_LinearLayout);
        mLinear.setBackgroundResource(R.drawable.welcome_pic);
        return view;
    }


}



