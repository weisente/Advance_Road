package example.weisente.top.baselibrary.ioc;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by san on 2017/9/29.
 */

public class ViewUtil {

    public static void inject(Activity activity){
        inject(new ViewFinder(activity),activity);
    }
    //兼容 上面三个方法  object-->反射需要执行的类
    private static void inject(ViewFinder finder,Object object){
        //对属性进行赋值
        injectField(finder,object);
        injectEvent(finder,object);
    }

    private static void injectEvent(ViewFinder finder, final Object object) {
        //1.获取类里面所有的方法
        Class<?> clazz = object.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (final Method method : methods){
            //尝试获取onclick注解
            OnClick annotation = method.getAnnotation(OnClick.class);
            if(annotation!=null){
                //然后分别对不同的控件进行赋值
                int[] value = annotation.value();
                for(int id: value){
                    View view = finder.findViewById(id);
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            try {
                                //反射调用
                                method.invoke(object);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }
    }

    private static void injectField(ViewFinder finder, Object object) {
        Class<?> aClass = object.getClass();
        //获取所有属性  包含私有的
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field fild : declaredFields ) {
            //获取注解上面的值
            //FindViewById（R.id.xx）
            FindViewById ViewId = fild.getAnnotation(FindViewById.class);
//            finder.findViewById(findViewById);
            //findViewById 如果注解存在的化
            if(ViewId != null){
//              ViewId.value() 获取当前的注解里面的R.id.xx --> int 值
                View view = finder.findViewById(ViewId.value());
                if(view!=null){
                    //让这个objec的当前fild的字段赋值
                    try {
                        fild.set(object,view);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


//    private
}
