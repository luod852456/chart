package com.luodong.chart;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

public class SAppView extends FrameLayout implements View.OnClickListener {
    public SAppView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        baseInit();
    }

    public SAppView(Context context, AttributeSet attrs) {
        super(context, attrs);
        baseInit();
    }

    public SAppView(Context context) {
        super(context);
        baseInit();
    }

    private void baseInit() {
        int layoutId = onCreateContentView();
        if (layoutId != 0) {
            setContentView(layoutId);
        }
    }

    /**
     * 可重写此方法返回布局id
     *
     * @return
     */
    protected int onCreateContentView() {
        return 0;
    }

    public Activity getActivity() {
        Context context = getContext();
        if (context instanceof Activity) {
            return (Activity) context;
        } else {
            return null;
        }
    }

    /**
     * 设置布局
     *
     * @param layoutId 布局id
     */
    public void setContentView(int layoutId) {
        removeAllViews();
        initLayoutInflater(layoutId);

    }

    protected View initLayoutInflater(int layoutId) {
        return LayoutInflater.from(getContext()).inflate(layoutId, this, true);
    }

//    public void setContentView(View contentView) {
//        removeAllViews();
//        addView(contentView);
//    }
//
//    public void setContentView(View contentView, ViewGroup.LayoutParams params) {
//        removeAllViews();
//        addView(contentView, params);
//    }

    @Override
    public void onClick(View v) {
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
