package example.weisente.top.baselibrary.AOPSingleClick;

import android.util.Log;
import android.view.View;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Calendar;

import example.weisente.top.baselibrary.R;

/**
 * Created by san on 2017/11/29.
 */
@Aspect
public class SingleClickAspect {
    static int TIME_TAG = R.id.click_time;
    public static final int MIN_CLICK_DELAY_TIME = 600;

    @Pointcut("execution(@example.weisente.top.baselibrary.annotation.SingleClick * *(..))")//方法切入点
    public void methodAnnotated() {
    }

    @Around("methodAnnotated()")//在连接点进行方法替换
    public void aroundJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        View view = null;
        for (Object arg : joinPoint.getArgs())
            if (arg instanceof View) view = (View) arg;
        if (view != null) {
            Object tag = view.getTag(TIME_TAG);
            long lastClickTime = ((tag != null) ? (long) tag : 0);
//            LogUtils.showLog("SingleClickAspect", "lastClickTime:" + lastClickTime);
            Log.e("SingleClickAspect","lastClickTime:" + lastClickTime);
            long currentTime = Calendar.getInstance().getTimeInMillis();
            if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {//过滤掉600毫秒内的连续点击
                view.setTag(TIME_TAG, currentTime);
//                LogUtils.showLog("SingleClickAspect", "currentTime:" + currentTime);
                Log.e("SingleClickAspect","currentTime:" + currentTime);
                joinPoint.proceed();//执行原方法
            }
        }
    }
}
