package com.zhailiw.app.view.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhailiw.app.R;
import com.zhailiw.app.common.NLog;
import com.zhailiw.app.common.PhotoUtils;
import com.zhailiw.app.presenter.MineFragmentPresenter;
import com.zhailiw.app.view.activity.AboutActivity;
import com.zhailiw.app.view.activity.AddressActivity;
import com.zhailiw.app.view.activity.FavorActivity;
import com.zhailiw.app.view.activity.MainActivity;
import com.zhailiw.app.view.activity.MeActivity;
import com.zhailiw.app.view.activity.OrderActivity;
import com.zhailiw.app.view.activity.ShopCarActivity;
import com.zhailiw.app.widget.SelectableRoundedImageView;
import com.zhailiw.app.widget.progressBar.MaterialProgressBar;


/**
 * Created by AMing on 16/6/21.
 * Company RongCloud
 */
public class MineFragment extends Fragment implements View.OnClickListener {
    private static final int COMPAREVERSION = 54;
    public static final String SHOWRED = "SHOWRED";
    public static MineFragment mFragment = null;
    private View view;

    private SelectableRoundedImageView mImageView;
    private ImageView mImageSetting;
    private PhotoUtils photoUtils;
    public static final int REQUEST_CODE_ASK_PERMISSIONS = 101;
    private Uri selectUri;
    private MaterialProgressBar progressBar;
    private LinearLayout mTxtMe;
    private TextView title;
    private MineFragmentPresenter presenter;
    private TextView nickName;

    public static MineFragment getInstance() {
        if (mFragment == null) {
            mFragment = new MineFragment();
        }
        return mFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mine, null);
        initViews();
        presenter = new MineFragmentPresenter(getActivity());
        presenter.init(mImageView,nickName);
        //initData();
//        compareVersion();
        NLog.d("fragment-----","onCreateView");
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.getInfo();
        NLog.d("fragment-----","onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        NLog.d("fragment-----","onResume");
    }

    private void initViews() {
        mImageView = view.findViewById(R.id.img_avatar);
        mImageView.setOnClickListener(this);
        nickName= view.findViewById(R.id.nick_name);

        view.findViewById(R.id.layout_buy_shop).setOnClickListener(this);
        view.findViewById(R.id.layout_collection).setOnClickListener(this);
        view.findViewById(R.id.layout_coupon).setOnClickListener(this);
        view.findViewById(R.id.layout_notpay).setOnClickListener(this);
        view.findViewById(R.id.layout_payed).setOnClickListener(this);
        view.findViewById(R.id.layout_done).setOnClickListener(this);

        view.findViewById(R.id.layout_address).setOnClickListener(this);
        view.findViewById(R.id.layout_info).setOnClickListener(this);
        view.findViewById(R.id.layout_nickname).setOnClickListener(this);
        view.findViewById(R.id.layout_about).setOnClickListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //BroadcastManager.getInstance(getActivity()).destroy(MineFragmentPresenter.UPDATEUNREAD);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.layout_buy_shop:
                ShopCarActivity.StartActivity(getContext());
                break;
            case R.id.layout_nickname:
                startActivity(new Intent(getActivity(), MeActivity.class));
                break;
            case R.id.layout_collection:
                startActivity(new Intent(getActivity(), FavorActivity.class));
                break;
            case R.id.layout_notpay:
                OrderActivity.StartActivity(getContext(),1);
                break;
            case R.id.layout_payed:
                OrderActivity.StartActivity(getContext(),2);
                break;
            case R.id.layout_done:
                OrderActivity.StartActivity(getContext(),3);
                break;
            case R.id.layout_about:
                startActivity(new Intent(getActivity(), AboutActivity.class));
                break;
            case R.id.layout_address:
                startActivity(new Intent(getActivity(), AddressActivity.class));
                break;
            case R.id.layout_info:
            case R.id.img_avatar:
                startActivity(new Intent(getActivity(), MeActivity.class));
                break;
        }
    }



}
