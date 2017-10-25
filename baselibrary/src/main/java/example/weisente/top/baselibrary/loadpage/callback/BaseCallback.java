package example.weisente.top.baselibrary.loadpage.callback;

import android.content.Context;
import android.view.View;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by san on 2017/10/24.
 */

public abstract class BaseCallback {
    private View rootView;
    private Context context;
    //判断该视图 是否显示成功
    private boolean successViewVisible;
    private OnReloadListener onReloadListener;

    public BaseCallback(){

    }
    BaseCallback(View view, Context context, OnReloadListener onReloadListener) {
        this.rootView = view;
        this.context = context;
        this.onReloadListener = onReloadListener;
    }

    public BaseCallback setCallback(View view, Context context, OnReloadListener onReloadListener) {
        this.rootView = view;
        this.context = context;
        this.onReloadListener = onReloadListener;
        return this;
    }
    protected abstract int onCreateView();

    public View getRootView(){
        //
        int resId = onCreateView();

        if (resId == 0 && rootView != null) {
            return rootView;
        }
        //in order to expand  function
        if (onBuildView(context) != null) {
            rootView = onBuildView(context);
        }
        // if rootview is null  inflate a new  view
        if (rootView == null) {
            rootView = View.inflate(context, onCreateView(), null);
        }
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onReloadEvent(context, rootView)) {
                    return;
                }
                if (onReloadListener != null) {
                    onReloadListener.onReload(v);
                }
            }
        });
        onViewCreate(context, rootView);
        return rootView;

    }

    protected View onBuildView(Context context) {
        return null;
    }

                /**
                 * Called immediately after {@link #onCreateView()}
                 *
                 * @since 1.2.2
                 */
    protected void onViewCreate(Context context, View view) {
    }


    protected boolean onReloadEvent(Context context, View view) {
        return false;
    }


    public BaseCallback copy() {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ObjectOutputStream oos;
        Object obj = null;
        try {
            oos = new ObjectOutputStream(bao);
            oos.writeObject(this);
            oos.close();
            ByteArrayInputStream bis = new ByteArrayInputStream(bao.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (BaseCallback) obj;
    }


    //setting the ReloadListener
    public interface OnReloadListener extends Serializable {
        void onReload(View v);
    }
    //return
    public View obtainRootView() {
        if (rootView == null) {
            rootView = View.inflate(context, onCreateView(), null);
        }
        return rootView;
    }
    //
    public boolean getSuccessVisible() {
        return successViewVisible;
    }

    /**
     * Called when the rootView of Callback is removed from its LoadLayout.
     *
     */
    public void onDetach() {
    }

    /**
     * 生命周期
     * life cycle
     * * Called when the rootView of Callback is attached to its LoadLayout.
     * @param context
     * @param view
     */
    public void onAttach(Context context, View view) {
    }

}
