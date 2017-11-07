package example.weisente.top.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import static android.view.View.MeasureSpec.EXACTLY;

/**
 * Created by san on 2017/11/7.
 */

public class StudyListview extends ListView {
    public StudyListview(Context context) {
        super(context);
    }

    public StudyListview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StudyListview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2
                , EXACTLY);
//        EXACTLY  可以但是他可能知道listview的长度过长
//        AT_MOST  高度适配
//        UNSPECIFIED  显示一行
//        MeasureSpec
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
