package example.weisente.top.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by san on 2017/11/7.
 */

public class WalkView extends View {
    public WalkView(Context context) {
        this(context,null);
    }

    public WalkView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WalkView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        invalidate();
    }
}
