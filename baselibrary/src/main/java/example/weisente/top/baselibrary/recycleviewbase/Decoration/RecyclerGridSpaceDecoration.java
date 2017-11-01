package example.weisente.top.baselibrary.recycleviewbase.Decoration;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * 使用代码的形式来做分格线
 * Created by san on 2017/11/1.
 */

public class RecyclerGridSpaceDecoration extends RecyclerView.ItemDecoration {

    private int mDivisionSpace;

    public RecyclerGridSpaceDecoration(int space) {
        this.mDivisionSpace = space;
    }

    /**
     * 获取列数
     */
    private int getSpanCount(RecyclerView parent) {
        // 列数
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            spanCount = ((StaggeredGridLayoutManager) layoutManager)
                    .getSpanCount();
        }
        return spanCount;
    }

    /**
     *  是否是最后一列
     * @param parent
     * @param pos
     * @param spanCount
     * @param childCount
     * @return
     */
    private boolean isLastColum(RecyclerView parent, int pos, int spanCount,
                                int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
            {
                return true;
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
                {
                    return true;
                }
            } else {
                childCount = childCount - childCount % spanCount;
                if (pos >= childCount)// 如果是最后一列，则不需要绘制右边
                    return true;
            }
        }
        return false;
    }

    //这个函数专门是描绘分割线的
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        //分割线有两种类型 一种横着的  另一中竖着的
        drawVertical(c, parent);
        drawHorizontal(c, parent);
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getLeft() - params.leftMargin;
            int right = child.getRight() + params.rightMargin + mDivisionSpace;
            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivisionSpace;
            Rect rect = new Rect(left, top, right, bottom);
//            new Paint()
            c.drawRect(rect,new Paint());
        }
    }


    private void drawVertical(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++){
            View child = parent.getChildAt(i);
            //获取这个view的一个布局的参数
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getRight() + params.rightMargin;
            int right = left + mDivisionSpace;  //这个是分割线的预留空间
            int top = child.getTop() - params.topMargin;
            int bottom = child.getBottom() + params.bottomMargin;
            Rect rect = new Rect(left, top, right, bottom);
//            new Paint()
            c.drawRect(rect,new Paint());
        }
    }

    //这个是为分割线搞偏移量的
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
    }
}
