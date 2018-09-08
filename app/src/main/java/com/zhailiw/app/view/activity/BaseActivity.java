package com.zhailiw.app.view.activity;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;
import com.jaeger.library.StatusBarUtil;
import com.zhailiw.app.R;

import java.lang.reflect.Method;

import static com.zhailiw.app.common.CommonTools.checkDeviceHasNavigationBar;
import static com.zhailiw.app.common.CommonTools.getActionBarHeight;

/**
 * Created by hmx on 2016/5/21.
 */
public class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    protected final static String TAG = BaseActivity.class.getSimpleName();
    protected TextView txtTitle,txtRight;
    protected RelativeLayout layoutBack,layoutRight;
    private ViewFlipper contentView;
    private LinearLayout headLayout;
    protected int paddingParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //x.view().inject(this);
        //        if(this instanceof DetailActivity)
//            return;
//        else
        //if(!checkDeviceHasNavigationBar(this))
paddingParams=getActionBarHeight(this);
            setStatusBar();
        }

    protected void setStatusBar() {
        StatusBarUtil.setTransparent(this);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.mainColor));
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 设置头部是否可见
     *
     * @param visibility
     */
    public void setHeadVisibility(int visibility) {
        headLayout.setVisibility(visibility);
    }
    /**
     * 设置左边是否可见
     *
     * @param visibility
     */
    public void setHeadLeftButtonVisibility(int visibility) {
        layoutBack.setVisibility(visibility);
    }
    /**
     * 设置右边是否可见
     *
     * @param visibility
     */
    public void setHeadRightButtonVisibility(int visibility) {
        layoutRight.setVisibility(visibility);
    }
    /**
     * 设置标题
     */
    public void setTitle(int titleId) {
        setTitle(getString(titleId), false);
    }
    /**
     * 设置标题
     */
    public void setTitle(int titleId, boolean flag) {
        setTitle(getString(titleId), flag);
    }
    /**
     * 设置标题
     */
    public void setTitle(String title) {
        setTitle(title, false);
    }
    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title, boolean flag) {
        txtTitle.setText(title);
        if (flag) {
            //mBtnLeft.setCompoundDrawables(null, null, null, null);
        } else {
            //mBtnLeft.setCompoundDrawables(mBtnBackDrawable, null, null, null);
        }
    }
    /**
     * 点击左按钮
     */
    public void onHeadLeftClick(View v) {
        finish();
    }
}
