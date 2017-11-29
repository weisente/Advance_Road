package example.weisente.top.baselibrary.AOPTimeLog;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.concurrent.TimeUnit;

/**
 * Created by san on 2017/11/29.
 */
@Aspect
public class TimeLogAspect {

    @Pointcut("execution(@example.weisente.top.baselibrary.annotation.TimeLog * *(..))")//方法切入点
    public void methodAnnotated() {
    }

    @Pointcut("execution(@example.weisente.top.baselibrary.annotation.TimeLog *.new(..))")//构造器切入点
    public void constructorAnnotated() {
    }

    @Around("methodAnnotated() || constructorAnnotated()")//在连接点进行方法替换
    public Object aroundJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        LogUtils.showLog("TimeLog getDeclaringClass", methodSignature.getMethod().getDeclaringClass().getCanonicalName());
        Log.e("TimeLog getDeclaringClass", methodSignature.getMethod().getDeclaringClass().getCanonicalName());

        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        long startTime = System.nanoTime();
        Object result = joinPoint.proceed();//执行原方法
        StringBuilder keyBuilder = new StringBuilder();
        keyBuilder.append(methodName + ":");
        for (Object obj : joinPoint.getArgs()) {
            if (obj instanceof String) keyBuilder.append((String) obj);
            else if (obj instanceof Class) keyBuilder.append(((Class) obj).getSimpleName());
        }
        String key = keyBuilder.toString();
//        LogUtils.showLog("TimeLog", (className + "." + key + joinPoint.getArgs().toString() + " --->:" + "[" + (TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime)) + "ms]"));// 打印时间差

        Log.e("TimeLog", (className + "." + key + joinPoint.getArgs().toString() + " --->:" + "[" + (TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime)) + "ms]"));
        return result;
    }
}

