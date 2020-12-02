package com.chk.androidlearning.custom.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * Created by CHK on 20-5-27.
 */
public class CustomScrollTextView extends AppCompatTextView {

    private final static String TAG = CustomScrollTextView.class.getSimpleName();

    int touchX;
    int touchY;
    int detalX;
    int detalY;
    Scroller mScoller;
    boolean touch;

    public CustomScrollTextView(Context context) {
        super(context);
        mScoller = new Scroller(context);
    }

    public CustomScrollTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScoller = new Scroller(context);
    }


    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScoller.computeScrollOffset() && !touch) {
            ((View)getParent()).scrollTo(mScoller.getCurrX(),mScoller.getCurrY());
            Log.i(TAG,"go");
            invalidate();
        } else {
            Log.i(TAG,"finish");
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        scrollViewWithLayout(event);
//        scrollViewWithOffset(event);
        scrollViewWithScrollBy(event);
        return true;
    }



    /**
     * 注意，使用ScrollBy或者ScrollTo方法移动的是View的Content,
     * 对于ViewGroup来讲，移动的就是childView
     * 而对于View来讲，移动的就是view的content
     * @param event
     */
    void scrollViewWithScrollBy(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touch = true;
                touchX = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                detalX = (int) event.getX() - touchX;
                ((View)getParent()).scrollBy(-detalX,0);
//                touchX = (int) event.getX();
//                layout(getLeft() + detalX,getTop(),getRight() + detalX,getBottom());
                break;
            case MotionEvent.ACTION_UP:
                touch = false;
                View viewGroup = ((View)getParent());
                mScoller.startScroll(viewGroup.getScrollX(),viewGroup.getScrollY(),
                        -viewGroup.getScrollX(),-viewGroup.getScrollY(),2000);
                invalidate();
                Log.i(TAG,"scrollX:"+viewGroup.getScrollX()+" scrollY:"+viewGroup.getScrollY());
                break;
        }
    }

    /**
     * 通过重新Layout来滑动View
     * @param event
     */
    void scrollViewWithLayout(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchX = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                detalX = (int) event.getX() - touchX;
                layout(getLeft() + detalX,getTop(),getRight() + detalX,getBottom());
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
    }

    /**
     * 通过Offset方法来滑动View
     * @param event
     */
    void scrollViewWithOffset(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchX = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                detalX = (int) event.getX() - touchX;
                offsetLeftAndRight(detalX);
                break;
            case MotionEvent.ACTION_UP:
                offsetLeftAndRight(-detalX);
                break;
        }
    }

    @Override
    protected void onScrollChanged(int horiz, int vert, int oldHoriz, int oldVert) {
        super.onScrollChanged(horiz, vert, oldHoriz, oldVert);
    }
}
