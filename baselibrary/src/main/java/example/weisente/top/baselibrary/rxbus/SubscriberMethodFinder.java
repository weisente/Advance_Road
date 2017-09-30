package example.weisente.top.baselibrary.rxbus;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by san on 2017/9/30.
 * 通过反射获取该类的所有观察者需要调用的方法
 */

public class SubscriberMethodFinder {

    private static final int BRIDGE = 0x40;
    private static final int SYNTHETIC = 0x1000;
    //模仿寄存器的写法  不同的权限不同位  然后判断是否置位然后判断是否有权限
    private static final int MODIFIERS_IGNORE = Modifier.ABSTRACT | Modifier.STATIC | BRIDGE | SYNTHETIC;


    public List<SubscriberMethod> findSubscriberMethods(Class<?> subscriberClass){
        //包装了一下findUsingReflection方法  对subscriberClass进行了校验 类里面起码有一个Subscriber
        List<SubscriberMethod> subscriberMethods = findUsingReflection(subscriberClass);
        if (subscriberMethods.isEmpty()){
            throw new RxBusException("Subscriber " + subscriberClass
                    + " and its super classes have no public methods with the @Subscribe annotation");
        }
        return subscriberMethods;
    }

    /**
     * 通过反射获取当类的所有注解 并且整合到一个list里面
     * @param subscriberClass
     * @return
     */

    private List<SubscriberMethod> findUsingReflection(Class<?> subscriberClass){
        List<SubscriberMethod> subscriberMethods = new ArrayList<>();
        //获取所有方法
        Method[] methods = subscriberClass.getDeclaredMethods();
        for(Method method : methods){
            //@Subscribe 的注解方法必须是 public, non-static, and non-abstract  仿制Event的规定
            int modifiers = method.getModifiers();
            if ((modifiers & Modifier.PUBLIC) != 0 && (modifiers & MODIFIERS_IGNORE) == 0) {{
                    //获取方法的参数
                     Class<?>[] parameterTypes = method.getParameterTypes();
                    if(parameterTypes.length == 1){
                        //获取是否有Subscribe注解
                        Subscribe subscribeAnnotation = method.getAnnotation(Subscribe.class);
                        if (subscribeAnnotation != null) {
                            //获取是什么线程会调
                            ThreadMode threadMode = subscribeAnnotation.threadMode();
                            Class<?> eventType = parameterTypes[0];//输入参数只有一个嘛  也就是他的事件类型
                            subscriberMethods.add(new SubscriberMethod(method, eventType, getThreadMode(threadMode)));
                        }
                    }
                }
            }

        }
        return subscriberMethods;
    }
    //根据不同的类型进行 回调的线程选择
    private Scheduler getThreadMode(ThreadMode threadMode) {
        Scheduler scheduler;
        switch (threadMode) {
            case MAIN:
                scheduler = AndroidSchedulers.mainThread();
                break;
            case IO:
                scheduler = Schedulers.io();
                break;
            case COMPUTATION:
                scheduler = Schedulers.computation();
                break;
            case SINGLE:
                scheduler = Schedulers.single();
                break;
            case TRAMPOLINE:
                scheduler = Schedulers.trampoline();
                break;
            case NEW_THREAD:
                scheduler = Schedulers.newThread();
                break;
            default:
                scheduler = AndroidSchedulers.mainThread();
                break;
        }
        return scheduler;
    }
}
