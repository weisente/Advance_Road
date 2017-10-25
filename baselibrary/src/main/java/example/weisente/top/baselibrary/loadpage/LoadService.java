package example.weisente.top.baselibrary.loadpage;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import example.weisente.top.baselibrary.loadpage.callback.BaseCallback;
import example.weisente.top.baselibrary.loadpage.callback.SuccessCallback;

/**
 * Created by Administrator on 2017/10/25 0025.
 * theory: find the oldContent and wrap a new layout
 */

public class LoadService<T> {
    private LoadLayout loadLayout;
    private Convertor<T> convertor;

    LoadService(Convertor<T> convertor, TargetContext targetContext, BaseCallback.OnReloadListener onReloadListener,
                LoadManager.Builder builder){
        this.convertor = convertor;
        Context context = targetContext.getContext();
        //
        View oldContent = targetContext.getOldContent();
        ViewGroup.LayoutParams oldLayoutParams = oldContent.getLayoutParams();
        //add this layout
        loadLayout = new LoadLayout(context, onReloadListener);
        //setting the success layout  to tell user success get the data
        loadLayout.setupSuccessLayout(new SuccessCallback(oldContent, context,
                onReloadListener));
        //in order to make fragemt hava loadlayout
        if(targetContext.getParentView() != null){
            targetContext.getParentView().addView(loadLayout, targetContext.getChildIndex(), oldLayoutParams);
        }
        initCallback(builder);
    }
    private void initCallback(LoadManager.Builder builder){
        //having two types of callback
        List<BaseCallback> callbacks = builder.getCallbacks();
        Class<? extends BaseCallback> defalutCallback = builder.getDefaultCallback();

        if (callbacks != null && callbacks.size() > 0){
            for (BaseCallback callback : callbacks){
                //here is a map
                loadLayout.setupCallback(callback);
            }
        }
        if (defalutCallback != null) {
            loadLayout.showCallback(defalutCallback);
        }
    }

    public void showSuccess(){
        //add a default layout as a succseelayout
        loadLayout.showCallback(SuccessCallback.class);
    }

    public void showCallback(Class<? extends BaseCallback> callback) {
        loadLayout.showCallback(callback);
    }

    public void showWithConvertor(T t) {
        if (convertor == null) {
            throw new IllegalArgumentException("You haven't set the Convertor.");
        }
        loadLayout.showCallback(convertor.map(t));
    }

    public LoadLayout getLoadLayout() {
        return loadLayout;
    }

    /**
     * obtain rootView if you want keep the toolbar in Fragment
     *
     * @since 1.2.2
     *
     * newRootView  add a titleView
     *rootView  remove a titleview
     *they are convert
     */
    public LinearLayout getTitleLoadLayout(Context context, ViewGroup rootView, View titleView) {
        LinearLayout newRootView = new LinearLayout(context);
        newRootView.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        newRootView.setLayoutParams(layoutParams);
        rootView.removeView(titleView);
        newRootView.addView(titleView);
        newRootView.addView(loadLayout, layoutParams);
        return newRootView;
    }
    /**
     * modify the callback dynamically
     *
     * @param callback  which callback you want modify(layout, event)
     * @param transport a interface include modify logic
     * @since 1.2.2
     */
    public LoadService<T> setCallBack(Class<? extends BaseCallback> callback, Transport transport) {
        loadLayout.setCallBack(callback, transport);
//        loadLayout.setCa
        return this;
    }


}
