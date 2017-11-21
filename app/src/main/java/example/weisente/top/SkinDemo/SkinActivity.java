package example.weisente.top.SkinDemo;

import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

import example.weisente.top.R;
import example.weisente.top.framelibrary.BaseSkinActivity;
import example.weisente.top.framelibrary.skin.SkinManager;

/**
 * Created by san on 2017/11/9.
 */

public class SkinActivity extends BaseSkinActivity {
    private ImageView image_iv;

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_skin);
//    }

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
        setContentView(R.layout.activity_skin);
    }


    public void skin(View view){
        // 从服务器上下载

        String SkinPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator +"red.skin";
//        SkinManager.getInstance().register(this,);
        // 换肤
        int result = SkinManager.getInstance().loadSkin(SkinPath);
        Log.e("测试","SkinPath  = "+SkinPath);
        Log.e("测试","result  = "+result);
    }

    public void skin1(View view){
        // 恢复默认
        int result = SkinManager.getInstance().restoreDefault();


    }


    public void skin2(View view){
        // 跳转
//        Intent intent = new Intent(this,MainActivity.class);
//        startActivity(intent);
        Toast.makeText(getApplicationContext(),"问题",Toast.LENGTH_SHORT).show();
    }
//    Activity


}
