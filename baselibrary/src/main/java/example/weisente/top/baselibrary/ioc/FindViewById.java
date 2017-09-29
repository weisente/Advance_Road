package example.weisente.top.baselibrary.ioc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by san on 2017/9/29.
 */
//作用在字段上
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)//运行时期
public @interface FindViewById {
    int value();

}
