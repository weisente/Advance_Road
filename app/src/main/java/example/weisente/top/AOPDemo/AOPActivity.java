package example.weisente.top.AOPDemo;

import android.Manifest;
import android.view.View;
import android.widget.Toast;

import example.weisente.top.R;
import example.weisente.top.baselibrary.annotation.CheckNet;
import example.weisente.top.baselibrary.annotation.Permission;
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
    @Permission({Manifest.permission.WRITE_CONTACTS,Manifest.permission.CAMERA})
    public void check(View view){
        Toast.makeText(getApplicationContext(),"点击通过",Toast.LENGTH_SHORT).show();
//        MPermissionUtils.requestPermissionsResult();
    }
}
