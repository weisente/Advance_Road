package example.weisente.top.baselibrary.Rxbusupdate;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.disposables.Disposable;


/**
 * Created by san on 2017/9/30.
 * 加入粘性 以及各种思路升级
 *
 *
 *
 *
 */

public class RxBus {
    private final String TAG = "RxBus2";
    private static volatile RxBus rxBus;
    //存储所有订阅者
    private Map<Object, Object> subscribersMap = new HashMap<>();
    //存储所有订阅的方法中带有@Subscribe方法的信息
    private Map<Object, List<SubscriberMethodInfo>> subscriberMethodInfosMap = new HashMap<>();
    //存储所有订阅者返回的Disposable,便于取消订阅
    private Map<Object, List<Disposable>> disposablesMap = new HashMap<>();
    //存储所有粘性事件
    private final Map<Class<?>, StickyMessage> stickyEventMap = new ConcurrentHashMap<>();

    /** 保存粘性事件的最大值 */
    private int MAX_STICKY_EVENT_COUNT = 10000;

    private RxBus() {
    }
    public static RxBus getInstance() {
        if (rxBus == null) {
            synchronized (RxBus.class) {
                if (rxBus == null) {
                    rxBus = new RxBus();
                }
            }
        }
        return rxBus;
    }
    /** 注册 */
    public void register(Object subscriber){
        //避免重复
        if (subscribersMap.containsKey(subscriber)) {
            return;
        }
        Class<?> subClass = subscriber.getClass();
        Method[] methods = subClass.getDeclaredMethods();
        for (Method method : methods){
            //jdk1.8获取注解的新方法
            //检测是否拥有Subscribe注解
            if (method.isAnnotationPresent(Subscribe.class)){
                //获得参数类型
                Class[] parameterType = method.getParameterTypes();
                if (parameterType != null && parameterType.length == 1){
                    Subscribe sub = method.getAnnotation(Subscribe.class);
                    int code = sub.code();
                    ThreadMode threadMode = sub.threadMode();
                    boolean receiveStickyEvent = sub.receiveStickyEvent();
                    //因为这个说明了基本类型是不可以转换的
                    Class eventType = parameterType[0];
                    //把方法的信息包装成一个方法
                    SubscriberMethodInfo subscriberMethodInfo = new SubscriberMethodInfo(subscriber,
                            method, eventType, code, threadMode, receiveStickyEvent);

                    addSubscriber(subscriber);
                    addSubscriberMethodInfoToMap(subscriber, subscriberMethodInfo);
                }

            }
        }
    }

    /**
     * 将订阅者添加到map中，便于避免重复注册
     *有点特殊 key与value都是一样的
     * @param subscriber 订阅者
     */
    private void addSubscriber(Object subscriber) {
        subscribersMap.put(subscriber, subscriber);
    }

    /**
     * 将所有订阅者的订阅方法信息 以subscriber为key保存到map中，便于反注册和执行
     *
     * @param subscriber           订阅者
     * @param subscriberMethodInfo 订阅者的订阅方法信息
     */
    private void addSubscriberMethodInfoToMap(Object subscriber, SubscriberMethodInfo subscriberMethodInfo) {
        List<SubscriberMethodInfo> subscriberMethodInfos = subscriberMethodInfosMap.get(subscriber);
        if (subscriberMethodInfos == null) {
            subscriberMethodInfos = new ArrayList<>();
            subscriberMethodInfosMap.put(subscriber, subscriberMethodInfos);
        }

        if (!subscriberMethodInfos.contains(subscriberMethodInfo)) {
            subscriberMethodInfos.add(subscriberMethodInfo);
        }
    }

}
