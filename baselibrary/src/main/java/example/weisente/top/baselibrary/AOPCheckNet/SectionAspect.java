package example.weisente.top.baselibrary.AOPCheckNet;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by san on 2017/11/27.
 * 需要导入一个 aspectjrt
 */
@Aspect
public class SectionAspect {
    /**
     * 找到处理的切点
     * * *(..)  可以处理所有的方法
     */
    @Pointcut("example.weisente.top.baselibrary.AOPCheckNet.CheckNet * *(..))")
    public void checkNetBehavior() {
        Log.e("checkNetBehavior","这是一个点");
    }

    /**
     * 处理切面
     */
    @Around("checkNetBehavior()")
    public Object checkNet(ProceedingJoinPoint joinPoint) throws Throwable{
        Log.e("checkNet","这是一个面");
        return joinPoint.proceed();
    }
}
