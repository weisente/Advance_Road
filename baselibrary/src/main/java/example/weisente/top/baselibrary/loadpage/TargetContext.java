package example.weisente.top.baselibrary.loadpage;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by san on 2017/10/25.
 */

public class TargetContext {
    //manager the context content
    private Context context;
    private ViewGroup parentView;
    private View oldContent;
    private int childIndex;

    public TargetContext(Context context,ViewGroup parentView,View oldContent,int childIndex){
        this.context = context;
        this.parentView = parentView;
        this.oldContent  = oldContent;
        this.childIndex = childIndex;
    }

    public Context getContext() {
        return context;
    }

    View getOldContent() {
        return oldContent;
    }

    int getChildIndex() {
        return childIndex;
    }

    ViewGroup getParentView() {
        return parentView;
    }
}
