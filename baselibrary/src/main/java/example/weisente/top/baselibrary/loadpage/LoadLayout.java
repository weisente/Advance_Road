package example.weisente.top.baselibrary.loadpage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.FrameLayout;

import java.util.HashMap;
import java.util.Map;

import example.weisente.top.baselibrary.loadpage.callback.BaseCallback;
import example.weisente.top.baselibrary.loadpage.callback.SuccessCallback;

/**
 * Created by Administrator on 2017/10/25 0025.
 */

public class LoadLayout extends FrameLayout {
    //Map  stroe different kind of callback
    private Map<Class<? extends BaseCallback>, BaseCallback> callbacks = new HashMap<>();
    private Context context;
    private BaseCallback.OnReloadListener onReloadListener;
    private Class<? extends BaseCallback> preCallback;
    private static final int CALLBACK_CUSTOM_INDEX = 1;

    public LoadLayout(@NonNull Context context) {
        super(context);
    }
    public LoadLayout(@NonNull Context context, BaseCallback.OnReloadListener onReloadListener) {
        this(context);
        this.context = context;
        this.onReloadListener = onReloadListener;
    }

    public void setupCallback(BaseCallback callback) {
        BaseCallback cloneCallback = callback.copy();
        cloneCallback.setCallback(null, context, onReloadListener);
        addCallback(cloneCallback);
    }

    //setting  a successlayout
    public void setupSuccessLayout(BaseCallback callback) {
        addCallback(callback);
        View successView = callback.getRootView();
        successView.setVisibility(View.GONE);
        addView(successView);
    }
//
    public void addCallback(BaseCallback callback) {
        if (!callbacks.containsKey(callback.getClass())) {
            callbacks.put(callback.getClass(), callback);
        }
    }
    private void postToMainThread(final Class<? extends BaseCallback> status) {
        post(new Runnable() {
            @Override
            public void run() {
                showCallbackView(status);
            }
        });
    }

    public void showCallback(final Class<? extends BaseCallback> callback) {
        checkCallbackExist(callback);
        if (LoadManagerUtil.isMainThread()) {
            showCallbackView(callback);
        } else {
            postToMainThread(callback);
        }
    }


    private void showCallbackView(Class<? extends BaseCallback> status){
        if (preCallback != null) {
            if (preCallback == status) {
                return;
            }
            callbacks.get(preCallback).onDetach();
        }
        if (getChildCount() > 1) {
            removeViewAt(CALLBACK_CUSTOM_INDEX);
        }
        for (Class key : callbacks.keySet()){
            if (key == status){
                SuccessCallback successCallback = (SuccessCallback) callbacks.get(SuccessCallback.class);
                if(key  == SuccessCallback.class){
                    successCallback.show();
                }else{
                    successCallback.showWithCallback(callbacks.get(key).getSuccessVisible());
                    View rootView = callbacks.get(key).getRootView();
                    addView(rootView);
                    callbacks.get(key).onAttach(context, rootView);
                }
                preCallback = status;
            }
        }
    }

    private void checkCallbackExist(Class<? extends BaseCallback> callback) {
        if (!callbacks.containsKey(callback)) {
            throw new IllegalArgumentException(String.format("The Callback (%s) is nonexistent.", callback
                    .getSimpleName()));
        }
    }

    public void setCallBack(Class<? extends BaseCallback> callback, Transport transport) {
        if (transport == null) {
            return;
        }
        checkCallbackExist(callback);
        transport.order(context, callbacks.get(callback).obtainRootView());
    }

}
