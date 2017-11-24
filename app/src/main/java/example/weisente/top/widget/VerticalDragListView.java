package example.weisente.top.widget;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.FrameLayout;

/**
 * Created by san on 2017/11/24.
 */

public class VerticalDragListView extends FrameLayout {

    // 可以认为这是系统给我们写好的一个工具类
    private ViewDragHelper mDragHelper;

    private View mDragListView;
    // 后面菜单的高度
    private int mMenuHeight;
    // 菜单是否打开
    private boolean mMenuIsOpen = false;

    public VerticalDragListView(@NonNull Context context) {
        this(context,null);
    }

    public VerticalDragListView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public VerticalDragListView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mDragHelper = ViewDragHelper.create(this, mDragHelperCallback);
    }
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            View menuView = getChildAt(0);
            mMenuHeight = menuView.getMeasuredHeight();
        }
    }
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        int childCount = getChildCount();
        if (childCount != 2) {
            throw new RuntimeException("VerticalDragListView 只能包含两个子布局");
        }

        mDragListView = getChildAt(1);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragHelper.processTouchEvent(event);
        return true;
    }



    // 现象就是ListView可以滑动，但是菜单滑动没有效果了
    private float mDownY;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // 菜单打开要拦截
        if (mMenuIsOpen) {
            return true;
        }

        // 向下滑动拦截，不要给ListView做处理
        // 谁拦截谁 父View拦截子View ，但是子 View 可以调这个方法
        // requestDisallowInterceptTouchEvent 请求父View不要拦截，改变的其实就是 mGroupFlags 的值
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownY = ev.getY();
                // 让 DragHelper 拿一个完整的事件
                mDragHelper.processTouchEvent(ev);
                break;
            case MotionEvent.ACTION_MOVE:
                float moveY = ev.getY();
                if ((moveY - mDownY) > 0 && !canChildScrollUp()) {
                    // 向下滑动 && 滚动到了顶部，拦截不让ListView做处理
                    return true;
                }
                break;
        }

        return super.onInterceptTouchEvent(ev);
    }

    /**
     * @return Whether it is possible for the child view of this layout to
     * scroll up. Override this if the child view is a custom view.
     * 判断View是否滚动到了最顶部,还能不能向上滚
     */
    public boolean canChildScrollUp() {
        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (mDragListView instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) mDragListView;
                return absListView.getChildCount() > 0
                        && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0)
                        .getTop() < absListView.getPaddingTop());
            } else {
                return ViewCompat.canScrollVertically(mDragListView, -1) || mDragListView.getScrollY() > 0;
            }
        } else {
            return ViewCompat.canScrollVertically(mDragListView, -1);
        }
    }



    // 1.拖动我们的子View
    private ViewDragHelper.Callback mDragHelperCallback = new ViewDragHelper.Callback(){

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return mDragListView == child;
        }
        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            // 垂直拖动移动的位置
            // 2.3 垂直拖动的范围只能是后面菜单 View 的高度
            if (top <= 0) {
                top = 0;
            }

            if (top >= mMenuHeight) {
                top = mMenuHeight;
            }
            return top;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            // Log.e("TAG", "yvel -> " + yvel + " mMenuHeight -> " + mMenuHeight);
            // Log.e("TAG", "top -> " + mDragListView.getTop());
            if (releasedChild == mDragListView) {
                if (mDragListView.getTop() > mMenuHeight / 2) {
                    // 滚动到菜单的高度（打开）
                    mDragHelper.settleCapturedViewAt(0, mMenuHeight);
                    mMenuIsOpen = true;
                } else {
                    // 滚动到0的位置（关闭）
                    mDragHelper.settleCapturedViewAt(0, 0);
                    mMenuIsOpen = false;
                }
                invalidate();
            }
        }
    };
    @Override
    public void computeScroll() {
        if (mDragHelper.continueSettling(true)) {
            invalidate();
        }
    }
}
