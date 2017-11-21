package example.weisente.top;

import android.app.Application;
import android.util.Log;

import com.alipay.euler.andfix.patch.PatchManager;

import example.weisente.top.baselibrary.loadpage.LoadManager;
import example.weisente.top.callback.EmptyCallback;
import example.weisente.top.callback.ErrorCallback;
import example.weisente.top.callback.LoadingCallback;
import example.weisente.top.framelibrary.skin.SkinManager;

/**
 * Created by san on 2017/10/7.
 */

public class BaseApplication extends Application {

    public static PatchManager mPatchManager;
    @Override
    public void onCreate() {
        super.onCreate();
        LoadManager.beginBuilder()
                .addCallback(new LoadingCallback())
                .addCallback(new ErrorCallback())
                .addCallback(new EmptyCallback())
                .setDefaultCallback(LoadingCallback.class).commit();

//        new Handler();
        Log.e("测试","进来了");
        SkinManager.getInstance().init(this);



        // 设置全局异常捕捉类
//        ExceptionCrashHandler.getInstance().init(this);
//
//        // 初始化阿里的热修复
//        mPatchManager = new PatchManager(this);
//
//        try {
//            // 初始化版本，获取当前应用的版本
//            PackageManager packageManager = this.getPackageManager();
//            PackageInfo packageInfo = packageManager.getPackageInfo(this.getPackageName(), 0);
//            String versionName = packageInfo.versionName;
////            。。
//            mPatchManager.init(versionName);
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        // 加载之前的 apatch 包
//        mPatchManager.loadPatch();
    }
}
