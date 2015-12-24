package com.alan.learndemo.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ListView;

/**
 * Created by jiaowei on 12/17/2015.
 */
public class MyListView extends ListView {
    private static int mMaxOverDistance = 100;
    private Context mContext;

    public MyListView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    private void initView(){
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        float density = displayMetrics.density;
        mMaxOverDistance = (int)(density * mMaxOverDistance);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, mMaxOverDistance, isTouchEvent);
    }
}
