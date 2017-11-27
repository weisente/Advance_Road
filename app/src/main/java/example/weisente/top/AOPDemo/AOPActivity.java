package example.weisente.top.AOPDemo;

import android.view.View;
import android.widget.Toast;

import example.weisente.top.R;
import example.weisente.top.baselibrary.AOPCheckNet.CheckNet;
import example.weisente.top.baselibrary.base.BaseActivity;

/**
 * Created by san on 2017/11/27.
 */

public class AOPActivity extends BaseActivity {
    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_aop);
    }

    @CheckNet
    public void check(View view){
        Toast.makeText(getApplicationContext(),"点击通过",Toast.LENGTH_SHORT).show();
    }
}
