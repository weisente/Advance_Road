package example.weisente.top.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import example.weisente.top.R;

/**
 * Created by san on 2017/11/8.
 */

public class RatingBar extends View {

    private static final String TAG = "RatingBar";

    private Bitmap mStarNormalBitmap, mStarFocusBitmap;
    private int mGradeNumber = 5;

    private int mCurrentGrade = 2;
    //间隔参数  然后需要转换
    private float interval = 0;




    public RatingBar(Context context) {
        this(context,null);
    }

    public RatingBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RatingBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RatingBar);
        int starNormalId = array.getResourceId(R.styleable.RatingBar_starNormal, 0);
        if (starNormalId == 0) {
            throw new RuntimeException("请设置属性 starNormal ");
        }
        mStarNormalBitmap = BitmapFactory.decodeResource(getResources(), starNormalId);
        int starFocusId = array.getResourceId(R.styleable.RatingBar_starFocus, 0);
        if (starFocusId == 0) {
            throw new RuntimeException("请设置属性 starFocus ");
        }
        mStarFocusBitmap = BitmapFactory.decodeResource(getResources(), starFocusId);
        mGradeNumber = array.getInt(R.styleable.RatingBar_gradeNumber, mGradeNumber);
        if(mGradeNumber == 0){
            throw  new RuntimeException("请设置显示的个数 RatingBar_gradeNumber");
        }
        interval = array.getDimension(R.styleable.RatingBar_interval, 0);
        array.recycle();
    }


    public float dp2dx(float interval){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,interval,getResources().getDisplayMetrics());
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = mStarFocusBitmap.getHeight();
        int width = (int) (mStarFocusBitmap.getWidth() * mGradeNumber+(mGradeNumber -1)*dp2dx(interval));
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        int x = 0;
        for (int i = 0; i < mGradeNumber; i++) {
            // i*星星的宽度
//            int x = i * mStarFocusBitmap.getWidth();

            // 结合第二个步骤 触摸的时候mCurrentGrade值是不断变化
            if(mCurrentGrade>i){// 1  01
                // 当前分数之前
                canvas.drawBitmap(mStarFocusBitmap, x, 0, null);
            }else{
                canvas.drawBitmap(mStarNormalBitmap, x, 0, null);
            }
            x = (int) (x + dp2dx(interval));
            x = (int) (x +  mStarFocusBitmap.getWidth());
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            // case MotionEvent.ACTION_DOWN: // 按下 尽量减少onDraw()的调用
            case MotionEvent.ACTION_MOVE: // 移动
                float moveX = event.getX();//event.getX()相对于当前控件的位置   event.getRawX()获取幕的x位置
                // Log.e(TAG, "moveX -> " + moveX +"");
                // 计算分数
                int currentGrade = (int) (moveX/(mStarFocusBitmap.getWidth()+dp2dx(interval))+1);

                // 范围问题
                if(currentGrade<0){
                    currentGrade = 0;
                }
                if(currentGrade>mGradeNumber){
                    currentGrade = mGradeNumber;
                }
                // 分数相同的情况下不要绘制了 , 尽量减少onDraw()的调用
                if(currentGrade == mCurrentGrade){
                    return true;
                }

                // 再去刷新显示
                mCurrentGrade = currentGrade;
                invalidate();// onDraw()  尽量减少onDraw()的调用，目前是不断调用，怎么减少？


                break;
        }
        return true;// onTouch 后面看源码（2天,3个小时） false 不消费 第一次 DOWN false DOWN以后的事件是进不来的
    }

    public int getCurrentStartByGetX(float x){

        int number = 0;
        float currentx = x;
        while(x>0){
            number++;
            currentx =  (currentx - dp2dx(interval) - mStarFocusBitmap.getWidth());
        }

        return number;
    }








}
