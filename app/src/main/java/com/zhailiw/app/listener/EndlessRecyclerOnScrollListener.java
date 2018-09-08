package com.zhailiw.app.listener;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * Created by PVer on 2017/6/12.
 */

public abstract class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener {
    private boolean loading = true;
    private int firstVisibleItem,lastVisibleItem, visibleItemCount, previousTotal,totalItemCount;

    private int currentPage = 1;
    //是否可加载更多
    private boolean canloadMore = true;

    private RecyclerView.LayoutManager mLayoutManager;

    public EndlessRecyclerOnScrollListener(LinearLayoutManager linearLayoutManager) {
        this.mLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mLayoutManager.getItemCount();
//      firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition()

        if (mLayoutManager instanceof GridLayoutManager) {
            lastVisibleItem = ((GridLayoutManager) mLayoutManager).findLastVisibleItemPosition();
        } else if (mLayoutManager instanceof StaggeredGridLayoutManager) {
            int[] into = new int[((StaggeredGridLayoutManager) mLayoutManager).getSpanCount()];
            ((StaggeredGridLayoutManager) mLayoutManager).findLastVisibleItemPositions(into);
            lastVisibleItem = last(into);
        } else {
            lastVisibleItem = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition();
        }

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }

//        NLog.d("滑动可见",visibleItemCount);
//        NLog.d("滑动总数",totalItemCount);
//        NLog.d("滑动最后可见位置", lastVisibleItem);
        if (newState == RecyclerView.SCROLL_STATE_IDLE && !loading) {

            if (visibleItemCount > 0  && lastVisibleItem >= totalItemCount - 1 && canloadMore){
                currentPage++;
                onLoadMore(currentPage);
                loading = true;
            }


        }
   }

    //取到最后的一个节点
    private int last(int[] lastPositions) {
        int last = lastPositions[0];
        for (int value : lastPositions) {
            if (value > last) {
                last = value;
            }
        }
        return last;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
//
//            if (totalItemCount > previousTotal) {
//                loading = true;
//                previousTotal = totalItemCount;
//            }
//
//        if (!loading   && (totalItemCount - visibleItemCount) <= lastVisibleItem) {
//            currentPage++;
//            onLoadMore(currentPage);
//            loading = true;
//        }
    }

    //设置是否可加载更多
    public void setCanloadMore(boolean flag) {
        canloadMore = flag;
    }
    //重置参数
    public void reset() {
        loading=true;
        canloadMore=true;
        previousTotal=0;
        visibleItemCount =0;
        totalItemCount = 0;
        lastVisibleItem = 0;
        currentPage=1;
    }
    public abstract void onLoadMore(int currentPage);
}
