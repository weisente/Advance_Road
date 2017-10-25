package example.weisente.top.baselibrary.loadpage.callback;

import android.content.Context;
import android.view.View;

/**
 * Created by san on 2017/10/25.
 */

public class SuccessCallback extends BaseCallback {


    public SuccessCallback(View view, Context context, OnReloadListener onReloadListener) {
        super(view, context, onReloadListener);
    }

    public void hide() {
        obtainRootView().setVisibility(View.GONE);
    }

    public void show() {
        obtainRootView().setVisibility(View.VISIBLE);
    }

    public void showWithCallback(boolean successVisible) {
        obtainRootView().setVisibility(successVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    protected int onCreateView() {
        return 0;
    }
}
