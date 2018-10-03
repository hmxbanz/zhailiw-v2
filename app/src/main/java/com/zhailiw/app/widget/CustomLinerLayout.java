package com.zhailiw.app.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * 自定义控件：圆角RelativeLayout
 */
public class CustomLinerLayout extends LinearLayout {
    private RoundViewDelegate mRoundViewDelegate;

    public CustomLinerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (mRoundViewDelegate == null) {
            mRoundViewDelegate = new RoundViewDelegate(this, getContext());
            mRoundViewDelegate.setRectAdius(30);
        }
    }

    public CustomLinerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (mRoundViewDelegate == null) {
            mRoundViewDelegate = new RoundViewDelegate(this, getContext());
        }
    }

    public CustomLinerLayout(Context context) {
        super(context);
        if (mRoundViewDelegate == null) {
            mRoundViewDelegate = new RoundViewDelegate(this, getContext());
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
                            int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int w = getWidth();
        int h = getHeight();
        mRoundViewDelegate.roundRectSet(w, h);
    }

    @Override
    public void draw(Canvas canvas) {
        mRoundViewDelegate.canvasSetLayer(canvas);
        super.draw(canvas);
        canvas.restore();
    }

}
