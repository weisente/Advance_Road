package example.weisente.top.baselibrary.AOPCheckNet;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import example.weisente.top.baselibrary.Util.ContextUtil;
import example.weisente.top.baselibrary.annotation.CheckNet;

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
    @Pointcut("execution(@example.weisente.top.baselibrary.AOPCheckNet.CheckNet * *(..))")
    public void checkNetBehavior() {
        Log.e("TAG","execution");

    }

    /**
     * 处理切面
     */
    @Around("checkNetBehavior()")
    public Object checkNet(ProceedingJoinPoint joinPoint) throws Throwable {
        Log.e("TAG", "checkNet");
        // 做埋点  日志上传  权限检测（我写的，RxPermission , easyPermission） 网络检测
        // 网络检测
        // 1.获取 CheckNet 注解  NDK  图片压缩  C++ 调用Java 方法
        //
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        CheckNet checkNet = signature.getMethod().getAnnotation(CheckNet.class);
        if (checkNet != null) {
            // 2.判断有没有网络  怎么样获取 context?
            Object object = joinPoint.getThis();// View Activity Fragment ； getThis() 当前切点方法所在的类
            Context context = ContextUtil.getContext(object);
            if (context != null) {
                if (!CheckNetUtil.isNetworkAvailable(context)) {
                    // 3.没有网络不要往下执行
                    Toast.makeText(context,"请检查您的网络",Toast.LENGTH_LONG).show();
                    //直接把方法中断了
                    return null;
                }
            }
        }
        return joinPoint.proceed();
    }


}
