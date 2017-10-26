package example.weisente.top.CoordinatorLayoutDemo;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

/**
 * Created by san on 2017/10/26.
 */

public class Follow2Behavior extends CoordinatorLayout.Behavior {

    public Follow2Behavior(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof Button;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        child.setX(dependency.getX());
        child.setY(dependency.getY() + dependency.getHeight());

        return true;
    }
}
