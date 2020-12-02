package com.chk.androidlearning.custom.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/**
 * Created by CHK on 20-5-27.
 */
public class CustomScrollLayout extends LinearLayout {

    int touchX;
    int touchY;
    int detalX;
    int detalY;

    public CustomScrollLayout(Context context) {
        super(context);
    }

    public CustomScrollLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
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
                touchX = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                detalX = (int) event.getX() - touchX;
                scrollBy(-detalX,0);
                touchX = (int) event.getX();
//                layout(getLeft() + detalX,getTop(),getRight() + detalX,getBottom());
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
    }


}
