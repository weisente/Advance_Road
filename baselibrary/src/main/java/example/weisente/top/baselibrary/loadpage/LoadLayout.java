package example.weisente.top.baselibrary.loadpage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.FrameLayout;

import java.util.HashMap;
import java.util.Map;

import example.weisente.top.baselibrary.loadpage.callback.BaseCallback;

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

    //setting  a successlayout
    public void setupSuccessLayout(BaseCallback callback) {
        addCallback(callback);
        View successView = callback.getRootView();
        successView.setVisibility(View.GONE);
        addView(successView);
    }

    public void addCallback(BaseCallback callback) {
        if (!callbacks.containsKey(callback.getClass())) {
            callbacks.put(callback.getClass(), callback);
        }
    }

}
