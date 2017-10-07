package example.weisente.top;

import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import example.weisente.top.baselibrary.base.BaseActivity;


public class MainActivity extends BaseActivity {


    @Bind(R.id.text_view)
    Button textView;
    @Bind(R.id.text_view1)
    TextView textView1;
    @Bind(R.id.fl_fragmentA)
    FrameLayout flFragmentA;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),2/0+"测试成功",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
    }


}
