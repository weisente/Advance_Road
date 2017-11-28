package example.weisente.top.baselibrary.Util;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.view.View;

/**
 * Created by san on 2017/11/28.
 */

public class ContextUtil {

    private ContextUtil(){

    }

    /**
     * 通过对象获取上下文
     *
     * @param object
     * @return
     */
    public static Context getContext(Object object) {
        if (object instanceof Activity) {
            return (Activity) object;
        } else if (object instanceof Fragment) {
            Fragment fragment = (Fragment) object;
            return fragment.getActivity();
        } else if (object instanceof View) {
            View view = (View) object;
            return view.getContext();
        }
        return null;
    }
}
