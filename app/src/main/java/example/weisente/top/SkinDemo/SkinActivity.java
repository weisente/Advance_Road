package example.weisente.top.SkinDemo;

import android.content.Intent;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

import example.weisente.top.MainActivity;
import example.weisente.top.R;
import example.weisente.top.framelibrary.BaseSkinActivity;

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









//        View test_tv = findViewById(R.id.test_tv);
//        test_tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //调用换肤程序
//                ChangeShin();
//            }
//        });
//         image_iv = (ImageView)findViewById(R.id.image_iv);

    }
//    private void ChangeShin() {
//        Toast.makeText(this,"点击",Toast.LENGTH_LONG).show();
//
//        try {
//
//            //读取资源
//            Resources superRes = getResources();
//            // 创建AssetManager
//            AssetManager asset = AssetManager.class.newInstance();
//            // 添加本地下载好的资源皮肤   Native层c和c++怎么搞的
//            Method method = AssetManager.class.getDeclaredMethod("addAssetPath",String.class);
//            //读取该位置皮肤资源
//            method.invoke(asset, Environment.getExternalStorageDirectory().getAbsolutePath()+
//                    File.separator + "red.skin");
//            Resources resource = new Resources(asset,superRes.getDisplayMetrics(),
//                    superRes.getConfiguration());
//            // 获取资源 id
//            int drawableId = resource.getIdentifier("image_src","drawable", "com.hc.essay.joke");
//            Drawable drawable = resource.getDrawable(drawableId);
//            image_iv.setImageDrawable(drawable);
//
////            image_iv
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

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
        // 换肤
//        int result = SkinManager.getInstance().loadSkin(SkinPath);
    }

    public void skin1(View view){
        // 恢复默认
//        int result = SkinManager.getInstance().restoreDefault();
    }


    public void skin2(View view){
        // 跳转
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
//    Activity


}
