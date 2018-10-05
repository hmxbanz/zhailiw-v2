package com.zhailiw.app.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhailiw.app.common.NLog;

//import butterknife.ButterKnife;
//import butterknife.Unbinder;

/**
 * Created by asus on 2016/3/26.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    private boolean isVisible = false;//当前Fragment是否可见
    private boolean isInitView = false;//是否与View建立起映射关系
    private boolean isFirstLoad = true;//是否是第一次加载数据

    private View view;
    private SparseArray<View> mViews;

    //private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        NLog.w(this.getClass().getSimpleName(),"onCreateView" );
        view = inflater.inflate(getLayoutId(), container, false);
        mViews = new SparseArray<>();
        //unbinder = ButterKnife.bind(this, view);//返回一个Unbinder值（进行解绑），注意这里的this不能使用getActivity()
        initViews();
        isInitView = true;
        lazyLoadData();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NLog.w(this.getClass().getSimpleName(),"onViewCreated" );
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        NLog.w(this.getClass().getSimpleName(),"onAttach" );
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        NLog.w(this.getClass().getSimpleName(),"isVisibleToUser " + isVisibleToUser );
        if (isVisibleToUser) {
            isVisible = true;
            lazyLoadData();
        } else {
            isVisible = false;
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    private void lazyLoadData() {
        if (isFirstLoad) {
            NLog.w(this.getClass().getSimpleName(),"第一次未加载 " + " isInitView  " + isInitView + "  isVisible  " + isVisible );
        } else {
            NLog.w(this.getClass().getSimpleName(),"第二次加载" + " isInitView  " + isInitView + "  isVisible  " + isVisible );
        }

        if (!isFirstLoad || !isVisible || !isInitView) {
            NLog.w(this.getClass().getSimpleName(),"不加载" + " isInitView  " + isInitView + "  isVisible  " + isVisible );
            return;
        }

        initData();
        NLog.w(this.getClass().getSimpleName(),"完成数据第一次加载");
        isFirstLoad = false;
    }

    /**
     * 加载页面布局文件
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 让布局中的view与fragment中的变量建立起映射
     */
    protected abstract void initViews();

    /**
     * 加载要显示的数据
     */
    protected abstract void initData();

    /**
     * fragment中可以通过这个方法直接找到需要的view，而不需要进行类型强转
     * @param viewId
     * @param <E>
     * @return
     */
    protected <E extends View> E findView(int viewId) {
        if (view != null) {
            E view = (E) mViews.get(viewId);
            if (view == null) {
                view = (E) this.view.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return view;
        }
        return null;
    }


    @Override
    public void onStart() {
        super.onStart();
        NLog.w(this.getClass().getSimpleName(),"onStart" );
    }

    @Override
    public void onResume() {
        super.onResume();
        NLog.w(this.getClass().getSimpleName(),"onResume" );
    }

    @Override
    public void onPause() {
        super.onPause();
        NLog.w(this.getClass().getSimpleName(),"onPause" );
    }

    @Override
    public void onStop() {
        super.onStop();
        NLog.w(this.getClass().getSimpleName(),"onStop" );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        NLog.w(this.getClass().getSimpleName(),"onDestroy" );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
          isVisible = false;
          isInitView = false;
          isFirstLoad = true;
//        if (unbinder != null)
//          unbinder.unbind();
    }

    @Override
    public void onClick(View v) {

    }
}
