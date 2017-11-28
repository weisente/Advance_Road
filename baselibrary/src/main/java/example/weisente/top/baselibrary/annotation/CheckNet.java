package example.weisente.top.baselibrary.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by san on 2017/11/27.
 * 使用注解 检查网络
 */
@Target(ElementType.METHOD)//放在方法的上面
@Retention(RetentionPolicy.RUNTIME)// RUNTIME 运行时 xUtils  CLASS 代表编译时期 ButterKnife   SOURCE 代表资源
public @interface CheckNet {
}
