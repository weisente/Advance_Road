package example.weisente.top.baselibrary.rxbus;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

/**
 * Created by san on 2017/9/30.
 * 根据eventbus原理，打造基于Rxjava2.0的轮子
 */

public class RxBus2 {
    private volatile static RxBus2 mRxbus;
    private final FlowableProcessor<Object> mFlowableProcessor;
    private final SubscriberMethodFinder mSubscriberMethodFinder;

    private static Map<Class<?>, Map<Class<?>, Disposable>> mDisposableMap = new HashMap<>();


    private RxBus2(){
        mFlowableProcessor = PublishProcessor.create().toSerialized();
        //通过注解反射获取方法
        mSubscriberMethodFinder = new SubscriberMethodFinder();
    }

    public static RxBus2 getDefault(){
        if(mRxbus == null){
            synchronized (RxBus2.class){
                if(mRxbus == null){
                    mRxbus = new RxBus2();
                }
            }
        }
        return mRxbus;
    }

    /**
     *该Activity注册rxbua事件，使用了register后需要在不需要使用时候调用unregister
     * 需要注册监听的方法必须使用@Subscribe标识  使用@ThreadMode来表示使用的线程
     * @param subsciber
     */
    public void register(Object subsciber){
        //获取当前的类
        Class<?> subsciberClass = subsciber.getClass();
        //避免重复添加  做判断

        List<SubscriberMethod> subscriberMethods = mSubscriberMethodFinder.findSubscriberMethods(subsciberClass);
        for (SubscriberMethod subscriberMethod : subscriberMethods) {
            addSubscriber(subsciber, subscriberMethod);
        }
    }

    private void addSubscriber(final Object subsciber, final SubscriberMethod subscriberMethod){
        Class<?> subsciberClass = subsciber.getClass();
        Class<?> eventType = subscriberMethod.getEventType();
        Disposable disposable = mFlowableProcessor.ofType(eventType).observeOn(subscriberMethod.getThreadMode())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        //利用反射调用方法
                        invokeMethod(subsciber, subscriberMethod, o);
                    }
                });
        //
        Map<Class<?>, Disposable> disposableMap = mDisposableMap.get(subsciberClass);
        if (disposableMap == null) {
            disposableMap = new HashMap<>();
            mDisposableMap.put(subsciberClass, disposableMap);
        }
        disposableMap.put(eventType, disposable);
    }
    private void invokeMethod(Object subscriber, SubscriberMethod subscriberMethod, Object obj) {
        try {
            subscriberMethod.getMethod().invoke(subscriber, obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    /**
     * Posts the given event to the RxBus.
     */
    public void post(Object obj) {
        if (mFlowableProcessor.hasSubscribers()) {
            mFlowableProcessor.onNext(obj);
        }
    }

    public void unregister(Object subscriber){
        Class<?> subscriberClass = subscriber.getClass();
        Map<Class<?>, Disposable> disposableMap = mDisposableMap.get(subscriberClass);
        if (disposableMap == null) {
            throw new IllegalArgumentException(subscriberClass.getSimpleName() + " haven't registered RxBus");
        }
        Set<Class<?>> keySet = disposableMap.keySet();
        for (Class<?> evenType : keySet) {
            Disposable disposable = disposableMap.get(evenType);
            disposable.dispose();
        }
        mDisposableMap.remove(subscriberClass);
    }

    //取消关注该类的某个eventType
    public void unregister(Object subscriber, Class<?> eventType) {
        Class<?> subscriberClass = subscriber.getClass();
        Map<Class<?>, Disposable> disposableMap = mDisposableMap.get(subscriberClass);
        if (disposableMap == null) {
            throw new IllegalArgumentException(subscriberClass.getSimpleName() + " haven't registered RxBus");
        }
        if (!disposableMap.containsKey(eventType)) {
            throw new IllegalArgumentException("The event with type of " + subscriberClass.getSimpleName() + " is not" +
                    " required in " + subscriberClass.getSimpleName());
        }
        Disposable disposable = disposableMap.get(eventType);
        disposable.dispose();
        mDisposableMap.remove(eventType);
    }


}
