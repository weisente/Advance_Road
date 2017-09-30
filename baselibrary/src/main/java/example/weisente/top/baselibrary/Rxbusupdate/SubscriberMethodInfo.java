package example.weisente.top.baselibrary.Rxbusupdate;

import java.lang.reflect.Method;

/**
 * Created by san on 2017/9/30.
 */

public class SubscriberMethodInfo {

    private Object subscriber;
    private Method method;
    private Class<?> eventType;
    private int code;
    private ThreadMode threadMode;
    private boolean receiveStickyEvent;


    /**
     * 订阅者方法信息
     *
     * @param subscriber         订阅者
     * @param method             订阅者的订阅方法
     * @param eventType          订阅的事件类型
     * @param code               自定义的code
     * @param threadMode         线程类型{@link ThreadMode}
     * @param receiveStickyEvent 是否接收粘性事件
     */
    public SubscriberMethodInfo(Object subscriber, Method method, Class<?> eventType, int code, ThreadMode threadMode, boolean receiveStickyEvent) {
        this.subscriber = subscriber;
        this.method = method;
        this.eventType = eventType;
        this.code = code;
        this.threadMode = threadMode;
        this.receiveStickyEvent = receiveStickyEvent;
    }

    public Object getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Object subscriber) {
        this.subscriber = subscriber;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Class<?> getEventType() {
        return eventType;
    }

    public void setEventType(Class<?> eventType) {
        this.eventType = eventType;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ThreadMode getThreadMode() {
        return threadMode;
    }

    public void setThreadMode(ThreadMode threadMode) {
        this.threadMode = threadMode;
    }

    public boolean isReceiveStickyEvent() {
        return receiveStickyEvent;
    }

    public void setReceiveStickyEvent(boolean receiveStickyEvent) {
        this.receiveStickyEvent = receiveStickyEvent;
    }

    @Override
    public String toString() {
        return "SubscriberMethodInfo{" +
                "subscriber=" + subscriber +
                ", method=" + method +
                ", eventType=" + eventType +
                ", code=" + code +
                ", threadMode=" + threadMode +
                ", receiveStickyEvent=" + receiveStickyEvent +
                '}';
    }
}
