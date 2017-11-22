package example.weisente.top.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by san on 2017/11/22.
 * 手写一个流布局
 */

public class Streamlayout extends ViewGroup{

    private List<List<View>> mChildViews = new ArrayList<>();

    //代码生成
    public Streamlayout(Context context) {
        super(context);
    }
    //这个是XML生成
    public Streamlayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    //XML生成而且带style
    public Streamlayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取所有的子view的数目
        int childCount = getChildCount();

        int width = MeasureSpec.getSize(widthMeasureSpec);
        //自己的高度  加上view的高度
        int high = getPaddingBottom() + getPaddingTop();

        int linewith = getPaddingLeft();

        ArrayList<View> childViews = new ArrayList<>();
        mChildViews.add(childViews);

        // 子View高度不一致的情况下
        int maxHeight = 0;


        for (int i = 0 ;i <childCount;i++){
            //获取某一个孩子view
            View childAt = getChildAt(i);

            measureChild(childAt, widthMeasureSpec, heightMeasureSpec);

            ViewGroup.MarginLayoutParams params = (MarginLayoutParams) childAt.getLayoutParams();
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
