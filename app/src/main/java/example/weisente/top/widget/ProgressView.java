package example.weisente.top.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import example.weisente.top.R;

/**
 * Created by san on 2017/11/8.
 *
 */

public class ProgressView extends View {


    private int mMaxProgressColor = Color.RED;
    private int mCurrentProgressColor = Color.BLUE;
    private int mBorderWidth = 20;// 20px
    private int mStepTextSize  = 200;
    private int mStepTextColor = Color.BLACK;

    private Paint mMaxProgressPaint,mCurrentProgressPaint,mTextPaint;

    // 总共的，当前的步数
    private int mMaxProgress = 100;
    private int mCurrentProgress = 180;



    public ProgressView(Context context) {
        this(context,null);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ProgressView);
        mMaxProgressColor = array.getColor(R.styleable.ProgressView_maxProgressColor,mMaxProgressColor);
        mCurrentProgressColor = array.getColor(R.styleable.ProgressView_currentProgressColor,mCurrentProgressColor);
        mStepTextColor = array.getColor(R.styleable.ProgressView_progressTextColor,mStepTextColor);
        mBorderWidth = (int) array.getDimension(R.styleable.ProgressView_progressborderWidth,mBorderWidth);
        mStepTextSize = (int) array.getDimension(R.styleable.ProgressView_progressTextColor,mStepTextSize);

        array.recycle();

        mMaxProgressPaint = new Paint();
        mMaxProgressPaint.setAntiAlias(true);
        mMaxProgressPaint.setStrokeWidth(mBorderWidth);
        mMaxProgressPaint.setColor(mMaxProgressColor);
        mMaxProgressPaint.setStrokeCap(Paint.Cap.ROUND);
        mMaxProgressPaint.setStyle(Paint.Style.STROKE);// 画笔空心

        mCurrentProgressPaint = new Paint();
        mCurrentProgressPaint.setAntiAlias(true);
        mCurrentProgressPaint.setStrokeWidth(mBorderWidth);
        mCurrentProgressPaint.setColor(mCurrentProgressColor);
        mCurrentProgressPaint.setStrokeCap(Paint.Cap.ROUND);
        mCurrentProgressPaint.setStyle(Paint.Style.STROKE);// 画笔空心


        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(mStepTextColor);
        mTextPaint.setTextSize(mStepTextSize);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(width>height?height:width,width>height?height:width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF = new RectF(mBorderWidth/2,mBorderWidth/2
                ,getWidth()-mBorderWidth/2,getHeight()-mBorderWidth/2);

        canvas.drawCircle(getWidth()/2,getWidth()/2,getWidth()/2-mBorderWidth/2,mMaxProgressPaint);

        if(mMaxProgress == 0){
            return;
        }
        //扫描的百分百计算
        float sweepAngle = (float)mCurrentProgress/mMaxProgress;







        canvas.drawArc(rectF,0,sweepAngle*360,false,mCurrentProgressPaint);


        String stepText = mCurrentProgress+"%";


//        String substring = stepText.substring(0, 5);
        Rect textBounds = new Rect();
        mTextPaint.getTextBounds(stepText, 0, stepText.length(), textBounds);
        int dx = getWidth()/2 - textBounds.width()/2;
        // 基线 baseLine
        Paint.FontMetricsInt  fontMetrics = mTextPaint.getFontMetricsInt();
        int dy = (fontMetrics.bottom - fontMetrics.top)/2 - fontMetrics.bottom;
        int baseLine = getHeight()/2 + dy;
        canvas.drawText(stepText,dx,baseLine,mTextPaint);

    }

    // 7.其他 写几个方法动起来
    public synchronized void setMaxProgress(int maxProgress){
        this.mMaxProgress = maxProgress;
    }

    public synchronized void setCurrentStep(int currentProgress){
        this.mCurrentProgress = currentProgress;
        // 不断绘制  onDraw()
        invalidate();
    }
}
