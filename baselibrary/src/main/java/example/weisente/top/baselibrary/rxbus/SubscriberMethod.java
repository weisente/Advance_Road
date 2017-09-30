package example.weisente.top.baselibrary.rxbus;

import java.lang.reflect.Method;

import io.reactivex.Scheduler;

/**
 * Created by san on 2017/9/30.
 * 订阅者订阅方法的信息
 */

public class SubscriberMethod {

    final Scheduler threadMode;
    final Method method;
    final Class<?> eventType;


    public SubscriberMethod(Method method, Class<?> eventType,Scheduler threadMode) {
        this.method = method;
        this.eventType = eventType;
        this.threadMode = threadMode;

    }
    public Method getMethod() {
        return method;
    }

    public Class<?> getEventType() {
        return eventType;
    }

    public Scheduler getThreadMode() {
        return threadMode;
    }
}
