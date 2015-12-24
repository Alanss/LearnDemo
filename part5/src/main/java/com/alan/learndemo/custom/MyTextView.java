package com.alan.learndemo.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;
import android.widget.TextView;

/**
 * Created by jiaowei on 12/18/2015.
 */
public class MyTextView extends TextView {
    private int lastX = 0;
    private int lastY = 0;
    private Scroller mScroller;

    private static final String TAG = "xjw";

    public MyTextView(Context context) {
        super(context);
        mScroller = new Scroller(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = (int) event.getX();
                lastY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                // 偏移量
                int offsetX = x - lastX;
                int offsetY = y - lastY;

                //layout(getLeft() + offsetX , getTop()+offsetY , getRight() + offsetX , getBottom() + offsetY) ;

                ((View) getParent()).scrollBy(-offsetX, -offsetY);
                break;
            case MotionEvent.ACTION_UP:
                // 手指离开时，执行滑动过程
                Log.e(TAG, "手指离开时，执行滑动过程");
                View view = (View) getParent();
                // mScroller.startScroll(view.getScrollX(), view.getScrollY(), -view.getScrollX(), -view.getScrollY());
                Log.e(TAG, "mScroller.getCurrX:" + view.getScrollX());
                Log.e(TAG, "mScroller.getCurrY:" + view.getScrollY());
                mScroller.startScroll(-view.getScrollX(), -view.getScrollY(), view.getScrollX(), view.getScrollY());
                invalidate();
                break;
        }
        return true;
    }

    /**
     * 系统在绘制view的时候会在onDraw方法中调用该方法
     * 该方法是不会自动调用的，只能通过invalidate()->onDraw()->computeScroll()来间接调用该方法
     */
    @Override
    public void computeScroll() {
        super.computeScroll();
        // 判断Scroller是否执行完毕
        if (mScroller.computeScrollOffset()) {
            //((View) getParent()).scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            ((View) getParent()).scrollTo(-mScroller.getCurrX(), -mScroller.getCurrY());
            Log.e(TAG, "mScroller.getCurrX:" + mScroller.getCurrX());
            Log.e(TAG, "mScroller.getCurrY:" + mScroller.getCurrY());
            //通过调用invalidate不断执行computeScroll
            invalidate();
        }
    }

}
