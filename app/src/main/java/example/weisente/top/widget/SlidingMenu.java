package example.weisente.top.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;

import example.weisente.top.R;

/**
 * Created by san on 2017/11/24.
 */

public class SlidingMenu extends HorizontalScrollView {
    private View mMenuView;
    private View mContentView;
    private int mMenuWidth;

    public SlidingMenu(Context context) {
        this(context,null);
    }

    public SlidingMenu(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 获取自定义的右边留出的宽度
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SlidingMenu);
        float rightPadding = array.getDimension(
                R.styleable.SlidingMenu_menuRightMargin,dip2px(50));
        // 计算菜单的宽度 = 屏幕的宽度 - 自定义右边留出的宽度
        mMenuWidth = (int) (getScreenWidth() - rightPadding);
        array.recycle();

    }
    /**
     * Dip into pixels
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
