package example.weisente.top.CoordinatorLayoutDemo;

import android.animation.ObjectAnimator;
import android.view.View;

import example.weisente.top.R;
import example.weisente.top.baselibrary.base.BaseActivity;

/**
 * Created by san on 2017/10/26.
 */

public class CoodinatorActivity extends BaseActivity {
    @Override
    protected void initData() {
        View viewById = findViewById(R.id.frist);
        float translationX = viewById.getTranslationX();
        ObjectAnimator//
                .ofFloat(viewById, "translationY", translationX, 500f,translationX)//
                .setDuration(5000)//
                .start();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_coordinator);
    }
}
