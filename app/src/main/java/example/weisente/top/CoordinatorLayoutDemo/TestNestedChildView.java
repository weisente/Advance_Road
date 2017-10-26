package example.weisente.top.CoordinatorLayoutDemo;

import android.content.Context;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by san on 2017/10/26.
 */

public class TestNestedChildView extends LinearLayout implements NestedScrollingChild {
    private NestedScrollingChildHelper helper;

    public TestNestedChildView(Context context) {
        super(context);
        init();
    }

    public TestNestedChildView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        helper = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);
    }


    public void setNestedScrollingEnabled(boolean enabled) {
        helper.setNestedScrollingEnabled(enabled);
    }

    public boolean isNestedScrollingEnabled() {
        return true;
    }

    public boolean startNestedScroll(int axes) {
        return helper.startNestedScroll(axes);
    }

    public void stopNestedScroll() {
        helper.stopNestedScroll();
    }

    public boolean hasNestedScrollingParent() {
        return helper.hasNestedScrollingParent();
    }

    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed,
                                        int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow) {
        return helper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        return helper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }

    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return helper.dispatchNestedFling(velocityX, velocityY, consumed);
    }

    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return helper.dispatchNestedPreFling(velocityX, velocityY);
    }

    private final int[] mNestedOffsets = new int[2];
    private final int[] mScrollOffset = new int[2];
    private final int[] mScrollConsumed = new int[2];
    private int mLastTouchX;
    private int mLastTouchY;

    @Override
    public boolean onTouchEvent(MotionEvent e) {

        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            mNestedOffsets[0] = mNestedOffsets[1] = 0;
        }

        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                mLastTouchX = (int) (e.getX() + 0.5f);
                mLastTouchY = (int) (e.getY() + 0.5f);
                startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL);
            }
            break;
            case MotionEvent.ACTION_MOVE: {
                Log.e("touch ",""+e.getY());
                final int x = (int) e.getX();
                final int y = (int) e.getY();
                int dx = mLastTouchX - x;
                int dy = mLastTouchY - y;
                if (dispatchNestedPreScroll(dx, dy, mScrollConsumed, mScrollOffset)) {
                    dx -= mScrollConsumed[0];
                    dy -= mScrollConsumed[1];
                    mNestedOffsets[0] += mScrollOffset[0];
                    mNestedOffsets[1] += mScrollOffset[1];
                }
                if(getScrollY()+dy>0) {
                    scrollTo(0, getScrollY()+dy);
                    dispatchNestedScroll(0,dy,dx,0,mNestedOffsets);
                }else{
                    if(getScrollY()>0){
                        scrollBy(0,-getScrollY());
                        dispatchNestedScroll(0,getScrollY(),dx,dy-getScrollY(),mNestedOffsets);
                    }
                    scrollTo(0, 0);
                }
                mLastTouchX = x - mScrollOffset[0];
                mLastTouchY = y - mScrollOffset[1];
            }
            break;
            case MotionEvent.ACTION_UP: {
                stopNestedScroll();
            }
            break;
        }
        return true;
    }

}
