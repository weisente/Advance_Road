package example.weisente.top.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import example.weisente.top.R;
import example.weisente.top.baselibrary.Util.ScreenUtils;

/**
 * Created by san on 2017/11/24.
 */

public class SlidingMenu extends HorizontalScrollView {
    private View mMenuView;
    private View mContentView;
    private int mMenuWidth;
    private Context mContext;

    // 手势处理类 主要用来处理手势快速滑动
    private GestureDetector mGestureDetector;

    // 菜单是否打开
    private boolean mMenuIsOpen = false;

    public SlidingMenu(Context context) {
        this(context,null);
    }

    public SlidingMenu(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
        mContext = context;
        // 获取自定义的右边留出的宽度
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SlidingMenu);
        float rightPadding = array.getDimension(
                R.styleable.SlidingMenu_menuRightMargin, ScreenUtils.dip2px(mContext,50));
        array.recycle();
        mMenuWidth = (int) (ScreenUtils.getScreenWidth(mContext) - rightPadding);
        mGestureDetector  = new GestureDetector(mContext,new GestureDetectorListener());
    }
    private class GestureDetectorListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if(mMenuIsOpen){
                if(velocityX<-500){
                    toggleMenu();
                    return true;
                }
            }else{
                if(velocityX>500){
                    toggleMenu();
                    return true;
                }
            }


            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }


    /**
     * 切换菜单的状态
     */
    private void toggleMenu() {
        if(mMenuIsOpen){
            closeMenu();
        }else{
            openMenu();
        }
    }
    private void openMenu() {
        smoothScrollTo(0, 0);
        mMenuIsOpen = true;
    }
    /**
     * 关闭菜单
     */
    private void closeMenu() {
        smoothScrollTo(mMenuWidth, 0);
        mMenuIsOpen = false;
    }



    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        
        ViewGroup viewParent = (ViewGroup)getChildAt(0);
        //获取孩子的数目
        int childCount = viewParent.getChildCount();
        if(childCount>2){

            throw  new IllegalStateException("SlidingMenu布局的孩子只能少于两个");
        }
        //菜单栏
        mMenuView = viewParent.getChildAt(0);
        //内容
        mContentView = viewParent.getChildAt(1);
        ViewGroup.LayoutParams mMenuView_lp = mMenuView.getLayoutParams();
        mMenuView_lp.width = mMenuWidth;
        ViewGroup.LayoutParams mContentView_lp = mContentView.getLayoutParams();
        mContentView_lp.width = ScreenUtils.getScreenWidth(mContext);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        // l 是 当前滚动的x距离  在滚动的时候会不断反复的回调这个方法
//        Log.e(TAG,l+"");
        // 6. 实现菜单左边抽屉样式的动画效果
        mMenuView.setTranslationX(l*0.8f);

        // 7.给内容添加阴影效果 - 计算梯度值
//        float gradientValue = l * 1f / mMenuWidth;// 这是  1 - 0 变化的值

//        // 7.给内容添加阴影效果 - 给阴影的View指定透明度   0 - 1 变化的值
//        float shadowAlpha = 1 - gradientValue;
//        mContentView.setAlpha(shadowAlpha);

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //把ontouch事件交给手势识别
        if(mGestureDetector.onTouchEvent(ev)){
            return mGestureDetector.onTouchEvent(ev);
        }

        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                // 手指抬起获取滚动的位置
                int currentScrollX = getScrollX();
                if (currentScrollX > mMenuWidth / 2) {
                    // 关闭菜单
                    closeMenu();
                } else {
                    // 打开菜单
                    openMenu();
                }
                return false;
        }
        return super.onTouchEvent(ev);
    }
}
