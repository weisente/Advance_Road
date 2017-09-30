package example.weisente.top.baselibrary.Rxbusupdate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Subscribe {


    int code() default -1;

    /** 标识方法所运行的线程 */
    ThreadMode threadMode() default ThreadMode.CURRENT_THREAD;

    /** 标识方法是否接收粘性事件 */
    boolean receiveStickyEvent() default false;

}
