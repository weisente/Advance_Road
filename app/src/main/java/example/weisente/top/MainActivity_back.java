package example.weisente.top;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import example.weisente.top.baselibrary.ioc.FindViewById;
import example.weisente.top.baselibrary.ioc.OnClick;
import example.weisente.top.baselibrary.ioc.ViewUtil;

public class MainActivity_back extends AppCompatActivity {

    @FindViewById(R.id.text_view)
    TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //注入  初级版本运行时注入  需要反射消耗大量性能
        ViewUtil.inject(this);
        test.setText("ok");
    }
    @OnClick(R.id.text_view)
    public void show(){
        Toast.makeText(getApplicationContext(),"ni",Toast.LENGTH_SHORT).show();
    }
}
