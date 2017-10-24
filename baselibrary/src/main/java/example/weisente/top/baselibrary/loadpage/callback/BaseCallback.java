package example.weisente.top.baselibrary.loadpage.callback;

import android.content.Context;
import android.view.View;

/**
 * Created by san on 2017/10/24.
 */

public abstract class BaseCallback {
    private View rootView;
    private Context context;
    //判断该视图 是否显示成功
    private boolean successViewVisible;

    public BaseCallback(){

    }
}
