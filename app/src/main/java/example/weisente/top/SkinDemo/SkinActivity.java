package example.weisente.top.SkinDemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import example.weisente.top.R;
import example.weisente.top.baselibrary.base.BaseActivity;

/**
 * Created by san on 2017/11/9.
 */

public class SkinActivity extends BaseActivity {

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_skin);
//    }

    @Override
    protected void initData() {
        View test_tv = findViewById(R.id.test_tv);
        test_tv

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_skin);
    }
}
