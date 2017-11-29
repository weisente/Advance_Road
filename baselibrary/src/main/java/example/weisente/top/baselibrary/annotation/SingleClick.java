package example.weisente.top.baselibrary.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by san on 2017/11/29.
 */

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface SingleClick {
}
