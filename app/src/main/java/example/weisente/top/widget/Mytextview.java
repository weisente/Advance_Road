package example.weisente.top.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import example.weisente.top.R;

/**
 * Created by san on 2017/11/7.
 */

public class Mytextview extends View {

    private String mText;
    private int mTextSize = 15;
    private int mTextColor = Color.BLACK;

    private Paint mPaint;

    public Mytextview(Context context) {
        this(context,null);
    }

    public Mytextview(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
//        Toast.makeText(context,"我进来了",Toast.LENGTH_SHORT).show();
    }

    public Mytextview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Toast.makeText(context,"我进来了",Toast.LENGTH_SHORT).show();
        Log.e("测试","我进来了");
        //获取属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Mytextview);
        mTextColor = typedArray.getColor(R.styleable.Mytextview_mycolor, mTextColor);
        mTextSize = (int) typedArray.getDimension(R.styleable.Mytextview_mytextsize, ds2px(mTextSize));
        mText = typedArray.getString(R.styleable.Mytextview_mytext);
        Log.e("测试",mText);
        Log.e("测试",mTextColor+"");
        Log.e("测试",mTextSize+"");
        //防止内存泄漏
        typedArray.recycle();

        mPaint = new Paint();
        // 抗锯齿
        mPaint.setAntiAlias(true);
        // 设置字体的大小和颜色
        mPaint.setTextSize(mTextSize);
        mPaint.setColor(mTextColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        // 1.确定的值，这个时候不需要计算，给的多少就是多少
        int width = MeasureSpec.getSize(widthMeasureSpec);
        if(widthMode == MeasureSpec.AT_MOST){
            // 计算的宽度 与 字体的长度有关  与字体的大小  用画笔来测量
            Rect bounds = new Rect();
            // 获取文本的Rect
            if(mText!=null)
            mPaint.getTextBounds(mText,0,mText.length(),bounds);
            width = bounds.width() + getPaddingLeft() +getPaddingRight();
        }

        int height = MeasureSpec.getSize(heightMeasureSpec);

        if(heightMode == MeasureSpec.AT_MOST){
            // 计算的宽度 与 字体的长度有关  与字体的大小  用画笔来测量
            Rect bounds = new Rect();
            // 获取文本的Rect
            if(mText!=null)
            mPaint.getTextBounds(mText,0,mText.length(),bounds);
            height = bounds.height() + getPaddingTop() + getPaddingBottom();
        }

        // 设置控件的宽高
        setMeasuredDimension(width,height);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        /*// 画文本
        canvas.drawText();
        // 画弧
        canvas.drawArc();
        // 画圆
        canvas.drawCircle();*/
        // 画文字 text  x  y  paint
        // x 就是开始的位置   0
        // y 基线 baseLine   求？   getHeight()/2知道的   centerY


        //dy 代表的是：高度的一半到 baseLine的距离
        Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
        // top 是一个负值  bottom 是一个正值    top，bttom的值代表是  bottom是baseLine到文字底部的距离（正值）
        // 必须要清楚的，可以自己打印就好
        int dy = (fontMetrics.bottom - fontMetrics.top)/2 - fontMetrics.bottom;
        int baseLine = getHeight()/2 + dy;

        int x = getPaddingLeft();

        canvas.drawText(mText,x,baseLine,mPaint);
    }


    private float ds2px(int mTextSize) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,mTextSize,getResources().getDisplayMetrics());
    }
}
