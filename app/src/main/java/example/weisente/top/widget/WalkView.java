package example.weisente.top.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by san on 2017/11/7.
 */

public class WalkView extends View {

    int with;
    int heigh;
    public WalkView(Context context) {
        this(context,null);
    }

    public WalkView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WalkView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        with = MeasureSpec.getSize(widthMeasureSpec);
        heigh= MeasureSpec.getSize(heightMeasureSpec);
        Log.e("measuredHeight",heigh+"");
        Log.e("measuredWidth",with+"");

//        getme
//        widthMeasureSpec
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("onDraw--measuredHeight",heigh+"");
        Log.e("onDraw--measuredWidth",with+"");
//        measure(0,0);
//        int measuredHeight = getMeasuredHeight();
//        int measuredWidth = getMeasuredWidth();

    }
}
