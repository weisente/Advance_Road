package example.weisente.top;

import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

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
        //我只是为了测试
    }

    @Override
    protected void initView() {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),2/1+"测试成功",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void initTitle() {
        File fixFile = new File(Environment.getExternalStorageDirectory(), "fix.apatch");

        if (fixFile.exists()) {
            // 修复Bug
            try {
                Log.d("测试",fixFile.exists()+"");
//                Log.d("测试",fixFile.getAbsolutePath());
                // 立马生效不需要重启
                  BaseApplication.mPatchManager.addPatch(fixFile.getAbsolutePath());
                Toast.makeText(this, "修复成功", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "修复失败", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
    }


}
