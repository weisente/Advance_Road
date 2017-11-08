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
import android.util.TypedValue;
import android.view.View;

import example.weisente.top.R;

/**
 * Created by san on 2017/11/7.
 */

public class WalkView extends View {

    private int mOuterColor = Color.RED;
    private int mInnerColor = Color.BLUE;
    private int mBorderWidth = 20;// 20px
    private int mStepTextSize  = 15;
    private int mStepTextColor = Color.BLACK;

    private Paint mOutPaint,mInnerPaint,mTextPaint;

    // 总共的，当前的步数
    private int mStepMax = 0;
    private int mCurrentStep = 0;


    public WalkView(Context context) {
        this(context,null);
    }

    public WalkView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WalkView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        invalidate();

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.QQStepView);
        mOuterColor = array.getColor(R.styleable.QQStepView_outerColor,mOuterColor);
        mInnerColor = array.getColor(R.styleable.QQStepView_innerColor, mInnerColor);
        mBorderWidth = (int) array.getDimension(R.styleable.QQStepView_borderWidth,mBorderWidth);
        mStepTextSize = array.getDimensionPixelSize(R.styleable.QQStepView_stepTextSize,mStepTextSize);
        mStepTextColor = array.getColor(R.styleable.QQStepView_stepTextColor, mStepTextColor);
        array.recycle();
        //三只画笔  只要是应付不种不同的
        mOutPaint = new Paint();
        mOutPaint.setAntiAlias(true);
        //设置宽度  设置paint
        mOutPaint.setStrokeWidth(mBorderWidth);
        mOutPaint.setColor(mOuterColor);
        //both sides is round
        mOutPaint.setStrokeCap(Paint.Cap.ROUND);
        mOutPaint.setStyle(Paint.Style.STROKE);// 画笔空心

        mInnerPaint = new Paint();
        mInnerPaint.setAntiAlias(true);
        mInnerPaint.setStrokeWidth(mBorderWidth);
        mInnerPaint.setColor(mInnerColor);
        mInnerPaint.setStrokeCap(Paint.Cap.ROUND);
        mInnerPaint.setStyle(Paint.Style.STROKE);// 画笔空心


        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(mStepTextColor);
        mTextPaint.setTextSize(sp2px(mStepTextSize));
    }


    public int sp2px(int value){
       return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,value,getResources().getDisplayMetrics());
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(width>height?height:width,width>height?height:width);
//        getme
//        widthMeasureSpec
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
////        Log.e("onDraw--measuredHeight",heigh+"");
////        Log.e("onDraw--measuredWidth",with+"");
////        measure(0,0);
////        int measuredHeight = getMeasuredHeight();
////        int measuredWidth = getMeasuredWidth();
//        RectF rectF = new RectF(mBorderWidth/2,mBorderWidth/2
//                ,getWidth()-mBorderWidth/2,getHeight()-mBorderWidth/2);
//        // 研究研究
//
//        canvas.drawArc(rectF,135,270,false,mOutPaint);
//
//        if(mStepMax == 0)return;
//
//        float sweepAngle = (float)mCurrentStep/mStepMax;
//        canvas.drawArc(rectF,135,sweepAngle*270,false,mInnerPaint);
//
//        String stepText = mCurrentStep+"";
//
//        Rect textBounds = new Rect();
//        //设置显示框的边距
//        mTextPaint.getTextBounds(stepText, 0, stepText.length(), textBounds);
//        int dx = getWidth()/2 - textBounds.width()/2;//找到起始点
//        // 基线 baseLine
//        Paint.FontMetricsInt  fontMetrics = mTextPaint.getFontMetricsInt();
//        //基线
//        int dy = (fontMetrics.bottom - fontMetrics.top)/2 - fontMetrics.bottom;
//        int baseLine = getHeight()/2 + dy;
//        canvas.drawText(stepText,dx,baseLine,mTextPaint);

        RectF rectF = new RectF(mBorderWidth / 2, mBorderWidth / 2, getWidth() - mBorderWidth / 2, getHeight() - mBorderWidth / 2);
        canvas.drawArc(rectF,135,270,false,mOutPaint);

        float sweepAngle = mCurrentStep / mStepMax;
        canvas.drawArc(rectF,135,270*sweepAngle,false,mOutPaint);

        String stepText = mCurrentStep+"";
        Rect textBounds = new Rect();

        mTextPaint.getTextBounds(stepText, 0, stepText.length(), textBounds);

        int dx = getWidth() / 2 - textBounds.width() / 2;
        Paint.FontMetricsInt  fontMetrics = mTextPaint.getFontMetricsInt();
        int dy = (fontMetrics.bottom - fontMetrics.top)/2 - fontMetrics.bottom;
        int baseLine = getHeight()/2 + dy;
        canvas.drawText(stepText,dx,baseLine,mTextPaint);


    }

    // 7.其他 写几个方法动起来
    public synchronized void setStepMax(int stepMax){
        this.mStepMax = stepMax;
    }

    public synchronized void setCurrentStep(int currentStep){
        this.mCurrentStep = currentStep;
        // 不断绘制  onDraw()
        invalidate();
    }
}
