package example.weisente.top.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by san on 2017/11/8.
 *
 * 因为我懒的问题  重用WalkView的自定义属性
 */

public class ProgressView extends View {


    private int mMaxProgressColor = Color.RED;
    private int mCurrentProgressColor = Color.BLUE;
    private int mBorderWidth = 20;// 20px
    private int mStepTextSize  = 15;
    private int mStepTextColor = Color.BLACK;

    private Paint mMaxProgressPaint,mCurrentProgressPaint,mTextPaint;

    // 总共的，当前的步数
    private int mMaxProgress = 0;
    private int mCurrentProgress = 0;



    public ProgressView(Context context) {
        this(context,null);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }
}
