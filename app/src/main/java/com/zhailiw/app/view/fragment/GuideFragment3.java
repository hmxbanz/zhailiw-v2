package com.zhailiw.app.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zhailiw.app.view.activity.MainActivity;
import com.zhailiw.app.view.activity.StartActivity;
import com.zhailiw.app.R;


@SuppressLint({"ValidFragment"})
public class GuideFragment3 extends Fragment{
    private Context context;
    public GuideFragment3(Context context)
    {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = View.inflate(context, R.layout.fragment_guide3, null);
        Button mBtn = (Button) view.findViewById(R.id.MyLoginBtn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
            }
        });
        return view;
    }


}



