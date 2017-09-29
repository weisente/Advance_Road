package example.weisente.top;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import example.weisente.top.baselibrary.ioc.FindViewById;
import example.weisente.top.baselibrary.ioc.OnClick;
import example.weisente.top.baselibrary.ioc.ViewUtil;

public class MainActivity extends AppCompatActivity {

    @FindViewById(R.id.test)
    TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //注入
        ViewUtil.inject(this);
        test.setText("ok");
    }
    @OnClick(R.id.test)
    public void show(){
        Toast.makeText(getApplicationContext(),"ni",Toast.LENGTH_SHORT).show();
    }
}
