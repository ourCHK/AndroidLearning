package com.chk.androidlearning.custom.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Scroller;

import com.chk.androidlearning.R;

import androidx.annotation.Nullable;

/**
 * Created by CHK on 21-1-5.
 */
public class CustomBanner extends ViewGroup {

    private static final String TAG = CustomBanner.class.getSimpleName();

    int touchX;
    int detalX;

    int childCount = 3;

    int maxScroll = 0;
    Scroller mScroller;

    VelocityTracker mTracker;
    int interceptorSlop = 4;    //拦截距离

    public CustomBanner(Context context) {
        super(context);
        init();
    }

    public CustomBanner(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomBanner(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init() {
        addChildView();
        mScroller = new Scroller(getContext());
    }

    void addChildView() {
        for (int i=0; i<childCount; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_background));
            addView(imageView);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureHeight = getMeasuredHeight();
        int measureWidth = getMeasuredWidth();
        int maxScroll = measureWidth;
        Log.i(TAG,"measureHeight:"+measureHeight+" measureWidth:"+measureWidth);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        layoutChild(changed, l, t, r, b);
    }

    void layoutChild(boolean changed, int l, int t, int r, int b) {
        Log.i(TAG,"l:"+l+" t:"+t+" r:"+r+" b:"+b);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        for (int i=0; i<getChildCount(); i++) {
            View child = getChildAt(i);
            child.layout(l + width * i,t,l + width * (i + 1),t+height);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        initTracker(ev);
        Log.i(TAG,"dispatchTouchEvent");

        return super.dispatchTouchEvent(ev);
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

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i(TAG,"onInterceptTouchEvent");

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchX = (int) ev.getX();
                return true;
            case MotionEvent.ACTION_MOVE:
                Log.i(TAG,"move");
                if (Math.abs(ev.getX() - touchX) > interceptorSlop) {
                    Log.i(TAG,"interceptor touch!");
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
//                if (!mScroller.isFinished()) {  //如果动画没有停止
//                    mScroller.abortAnimation(); //停止动画
//                }
                detalX = (int) event.getX() - touchX;   //判断左滑还是右滑
                Log.i(TAG,"try to scroll");
                if (detalX < 0) {   //左滑
                    scrollBy(-detalX,0);
                } else {
                    scrollBy(detalX,0);
                }
                break;
        }
        return super.onTouchEvent(event);
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
}
