package com.dystu.parallax;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * Created by Administrator on 2015/5/12.
 */
public class ParallaxList extends ListView {
    public ParallaxList(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private ImageView parallaxView;

    private int maxHeight;

    private int originalHeight;

    public void setParallaxView(ImageView parallaxView) {
        this.parallaxView = parallaxView;
        originalHeight = parallaxView.getHeight();
        maxHeight = parallaxView.getDrawable().getIntrinsicHeight();
    }

    /**
     * 当滑动到头的时候使用
     *
     * @param deltaX
     * @param deltaY  y方向滑动的距离，为正的时候底部到头，为负的时候顶部到头
     * @param scrollX
     * @param scrollY
     * @param scrollRangeX
     * @param scrollRangeY
     * @param maxOverScrollX
     * @param maxOverScrollY：到头后，最大可滚动的范围
     * @param isTouchEvent：是否是触摸滑动，true：手指拖动到头，false:惯性滑动到头
     * @return
     */
    @Override
    protected boolean overScrollBy(int deltaX, int deltaY,
                                   int scrollX, int scrollY,
                                   int scrollRangeX, int scrollRangeY,
                                   int maxOverScrollX, int maxOverScrollY,
                                   boolean isTouchEvent) {

        Log.e("Test", "deltaY:" + deltaY + ";maxOverScrollY:" + maxOverScrollY + ";isTouchEvent" + isTouchEvent);
        //我们的效果发生在下面的情况下
        //手指拖动到顶部
        if (deltaY < 0 && isTouchEvent) {
            //动态设置parallaxView的高度
            int newHeight = parallaxView.getHeight() - deltaY / 3;

            if (newHeight > maxHeight) {
                newHeight = maxHeight;
            }

            parallaxView.getLayoutParams().height = newHeight;//高度加上滑动的距离


            parallaxView.requestLayout();//让其重新测量,布局
        }
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

    @Override
       public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_UP:
                ResetHeaderAnimation resetHeaderAnimation = new ResetHeaderAnimation(parallaxView,originalHeight);
                startAnimation(resetHeaderAnimation);
                break;
        }
        return super.onTouchEvent(ev);
    }
}
