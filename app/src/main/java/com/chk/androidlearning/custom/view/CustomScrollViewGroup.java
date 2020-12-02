package com.chk.androidlearning.custom.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by CHK on 20-5-27.
 */
public class CustomScrollViewGroup extends ViewGroup {

    private final String TAG = CustomScrollViewGroup.class.getSimpleName();

    int touchX;
    int touchY;

    int detalX;
    int detalY;

    Scroller mScroller;

    boolean isIntercepted;

    int maxScroll;

    VelocityTracker mTracker;

    boolean moveLeftComplete;

    public CustomScrollViewGroup(Context context) {
        super(context);
        init(context);
    }

    public CustomScrollViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    void init(Context context) {
        Log.i(TAG,"touch_slop:"+ ViewConfiguration.get(getContext()).getScaledTouchSlop());
        mScroller = new Scroller(context);
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

//        measureBug(widthMeasureSpec,heightMeasureSpec);
        measureFixBug(widthMeasureSpec,heightMeasureSpec);

//        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? autoMeasureWidth : manualMeasureWidth,
//                heightMode == MeasureSpec.EXACTLY ? autoMeasureHeight + getPaddingTop() + getPaddingBottom() : manualMeasureHeight+ getPaddingTop() + getPaddingBottom());
    }

    void measureBug(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int autoMeasureWidth = MeasureSpec.getSize(widthMeasureSpec);   //自动测量
        int autoMeasureHeight = MeasureSpec.getSize(heightMeasureSpec);
        Log.i(TAG,"padding:"+getPaddingBottom());
        int manualMeasureWidth = 0;     //我们手动测量
        int manualMeasureHeight = 0;    //我们手动测量
        int maxHeight = 0;
        for (int i=0; i<getChildCount(); i++) {
            View child = getChildAt(i);
            measureChild(child,widthMeasureSpec,heightMeasureSpec);
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            manualMeasureWidth += childWidth;
            manualMeasureHeight = Math.max(childHeight,manualMeasureHeight);
        }
        findMaxHeight(widthMeasureSpec,heightMeasureSpec);
        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? autoMeasureWidth : manualMeasureWidth+getPaddingLeft()+getPaddingRight(),
                heightMode == MeasureSpec.EXACTLY ? autoMeasureHeight: manualMeasureHeight+getPaddingTop()+getPaddingBottom());
    }

    void measureFixBug(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int autoMeasureWidth = MeasureSpec.getSize(widthMeasureSpec);   //自动测量
        int autoMeasureHeight = MeasureSpec.getSize(heightMeasureSpec);
        Log.i(TAG,"widthMode:"+widthMode+" heightMode:"+heightMode+" autoMeasureWidth:"+autoMeasureWidth+ " autoMeasureHeight"+autoMeasureHeight);
        int manualMeasureWidth = 0;     //我们手动测量
        int maxHeight = findMaxHeight(widthMeasureSpec,heightMeasureSpec);
        for (int i=0; i<getChildCount(); i++) {
            View child = getChildAt(i);
//            MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();
//            int marginLeft = params.leftMargin;
//            int marginTop = params.topMargin;
//            int marginRight = params.rightMargin;
//            int marginBottom = params.bottomMargin;
//            heightMeasureSpec = MeasureSpec.makeMeasureSpec(maxHeight,MeasureSpec.EXACTLY);
            measureChild(child,widthMeasureSpec,heightMeasureSpec);
            manualMeasureWidth += child.getMeasuredWidth();
        }
        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? autoMeasureWidth : manualMeasureWidth+getPaddingLeft()+getPaddingRight(),
                maxHeight+getPaddingTop()+getPaddingBottom());

    }

    int findMaxHeight(int widthMeasureSpec, int heightMeasureSpec) {
        int maxHeight = 0;
        for (int i=0; i<getChildCount(); i++) {
            View child = getChildAt(i);
            MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();
            int marginTop = params.topMargin;
            int marginBottom = params.bottomMargin;
            measureChild(child,widthMeasureSpec,heightMeasureSpec);
            maxHeight = Math.max(maxHeight,child.getMeasuredHeight()+marginTop+marginBottom);
        }
        Log.i(TAG,"MaxHeight:"+maxHeight);
        return maxHeight;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        layoutBug(changed,l,t,r,b);
        Log.i(TAG,"onLayout:"+getWidth()+" "+ getHeight());
        for (int i=0;i<getChildCount(); i++) {
            View view = getChildAt(i);
            Log.i(TAG,"view"+i+" width:"+view.getWidth()+" height:"+view.getHeight());
        }
    }

    void layoutFixBug(boolean changed, int l, int t, int r, int b) {

    }

    void layoutBug(boolean changed, int l, int t, int r, int b) {
        Log.i(TAG,"onlayout:l"+l+" t:"+t+" r:"+r+" b:"+b+"  width:"+getWidth());
        int left = getPaddingLeft();
        int paddingTop = getPaddingTop();
        for (int i=0; i<getChildCount(); i++) {
            View child = getChildAt(i);
            MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();
            int marginLeft = params.leftMargin;
            int marginTop = params.topMargin;
            int marginRight = params.rightMargin;
            int marginBottom = params.bottomMargin;
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            child.layout(left+marginLeft,paddingTop+marginTop,childWidth+left-marginRight,b-getPaddingBottom()-marginBottom);
            left += childWidth;
            if (i > 0) {
                maxScroll += childWidth;
            }
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }



    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            invalidate();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAG,"dispatchTouchEvent");
        initTracker(ev);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i(TAG,"onInterceptTouchEvent");
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchX = (int) ev.getX();
                if (moveLeftComplete && ev.getX() < (getWidth() - maxScroll))
                    return true;
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs((int) ev.getX() - touchX) > 4) {
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (moveLeftComplete && ev.getX() < (getWidth() - maxScroll)) {  //在最左边，直接打劫事件
                    detalX = 0;
                    return true;
                }
        }
        return false;
    }


    /**
     * 初始化滑动速度追踪器
     * @param motionEvent
     */
    void initTracker(MotionEvent motionEvent) {
        if (mTracker == null) {
            mTracker = VelocityTracker.obtain();
            mTracker.addMovement(motionEvent);
        }
    }

    /**
     * 回收滑动速度追踪器
     */
    void recyclerTracker() {
        if (mTracker != null) {
            mTracker.clear();
            mTracker.recycle();
            mTracker = null;
        }
    }

    @Override
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
        Log.i(TAG,"requestDisallowInterceptTouchEvent");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                detalX = (int) event.getX() - touchX;
                if (detalX < 0 && getScrollX() <= maxScroll) {  //左滑,scrollX为正
                    if (getScrollX() + (-detalX) > maxScroll)  {
                        scrollTo(maxScroll,0);
                    } else {
                        scrollBy(-detalX,0);
                    }
                }
                if (detalX > 0) {   //右滑,scrollX为负
                    if (detalX - getScrollX() > 0) {    //说明左边界超出了
                        scrollBy(-getScrollX(),0);
                    } else {
                        scrollBy(-detalX,0);
                    }
                }
                touchX = (int) event.getX();
                touchY = (int) event.getY();
                break;
            case MotionEvent.ACTION_UP:
                mTracker.computeCurrentVelocity(200);
                Log.i(TAG,"Xv UP:"+mTracker.getXVelocity()+" deltaX:"+detalX);
                if (detalX < 0) {   //左滑
                    if (getScrollX() > maxScroll / 3 || Math.abs(mTracker.getXVelocity()) > 100) {
                        moveLeftComplete();
                    } else {
                        moveRightComplete();
                    }
                } else if (detalX > 0){    //右滑
                    if (getScrollX() < maxScroll * 2 / 3 || Math.abs(mTracker.getXVelocity()) > 100) {
                        moveRightComplete();
                    } else {
                        moveLeftComplete();
                    }
                } else {
                    Log.i(TAG,"detalX==0:"+moveLeftComplete);
                    if (moveLeftComplete) {
                        moveRightComplete();
                    } else {
                        moveRightComplete();
                    }
                }
                detalX = 0;
                invalidate();
                recyclerTracker();
                break;
        }
        return true;
    }

    void moveRightComplete() {
        mScroller.startScroll(getScrollX(),getScrollY(),-getScrollX(),-getScrollY(),500);
        moveLeftComplete = false;
    }

    void moveLeftComplete() {
        mScroller.startScroll(getScrollX(),getScrollY(),maxScroll - getScrollX(),-getScrollY(),500);
        moveLeftComplete = true;
    }


}
