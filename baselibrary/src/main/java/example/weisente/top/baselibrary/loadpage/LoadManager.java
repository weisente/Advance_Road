package example.weisente.top.baselibrary.loadpage;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import example.weisente.top.baselibrary.loadpage.callback.BaseCallback;

/**
 * Created by san on 2017/10/24.
 */

public class LoadManager {

    //全局单例的Manager  控制全局的显示
    private static volatile LoadManager loadManager;
    //内部类
    private Builder builder;

    //DCL
    public static LoadManager getDefault() {
        if (loadManager == null) {
            synchronized (LoadManager.class) {
                if (loadManager == null) {
                    loadManager = new LoadManager();
                }
            }
        }
        return loadManager;
    }
    //默认构造器
    private LoadManager() {
        this.builder = new Builder();
    }


    private LoadManager(Builder builder) {
        this.builder = new Builder();
    }

    private void setBuilder(@NonNull Builder builder) {
        this.builder = builder;
    }

    //using @NonNull  parameter can't be null
    public LoadService register(@NonNull Object target){
        return register(target,null,null);
    }

    public <T> LoadService register(Object tagert,BaseCallback.OnReloadListener onReloadListener ){
        return register(tagert,onReloadListener,null);
    }

    public <T> LoadService register(Object target, BaseCallback.OnReloadListener onReloadListener, Convertor<T>
            convertor) {
        TargetContext targetContext = LoadManagerUtil.getTargetContext(target);
        return new LoadService<>(convertor, targetContext, onReloadListener, builder);
    }







    public static class Builder {
        //用户信添加的callback
        private List<BaseCallback> callbacks = new ArrayList<>();
        //基础的callback
        private Class<? extends BaseCallback> defaultCallback;

        public Builder addCallback(@NonNull BaseCallback callback) {
            callbacks.add(callback);
            return this;
        }

        public Builder setDefaultCallback(@NonNull Class<? extends BaseCallback> defaultCallback) {
            this.defaultCallback = defaultCallback;
            return this;
        }

        List<BaseCallback> getCallbacks() {
            return callbacks;
        }


        Class<? extends BaseCallback> getDefaultCallback() {
            return defaultCallback;
        }
        public void commit() {
            getDefault().setBuilder(this);
        }

//        public LoadManager build() {
//            return new LoadManager(this);
//        }
        public LoadManager build() {
            return new LoadManager(this);
        }
    }

}
