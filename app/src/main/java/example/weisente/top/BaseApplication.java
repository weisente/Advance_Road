package example.weisente.top;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.alipay.euler.andfix.patch.PatchManager;

import example.weisente.top.baselibrary.ExceptionCrashHandler;

/**
 * Created by san on 2017/10/7.
 */

public class BaseApplication extends Application {

    public static PatchManager mPatchManager;
    @Override
    public void onCreate() {
        super.onCreate();
        // 设置全局异常捕捉类
        ExceptionCrashHandler.getInstance().init(this);

        // 初始化阿里的热修复
        mPatchManager = new PatchManager(this);

        try {
            // 初始化版本，获取当前应用的版本
            PackageManager packageManager = this.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(this.getPackageName(), 0);
            String versionName = packageInfo.versionName;
//            。。
            mPatchManager.init(versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        // 加载之前的 apatch 包
        mPatchManager.loadPatch();
    }
}
