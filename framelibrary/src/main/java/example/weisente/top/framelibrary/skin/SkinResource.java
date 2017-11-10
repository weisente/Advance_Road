package example.weisente.top.framelibrary.skin;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Environment;

import java.io.File;
import java.lang.reflect.Method;

/**
 * Created by san on 2017/11/10.
 */

public class SkinResource {
    // 资源通过这个对象获取
    private Resources mSkinResource;
    private String mPackageName;
    private static final String TAG = "SkinResource";


    public SkinResource(Context context, String skinPath){


        try {
             // 读取本地的一个 .skin里面的资源
            Resources superRes = context.getResources();
            // 创建AssetManager
            AssetManager asset = AssetManager.class.newInstance();
            // 添加本地下载好的资源皮肤   Native层c和c++怎么搞的
            Method method = AssetManager.class.getDeclaredMethod("addAssetPath", String.class);
            method.invoke(asset, Environment.getExternalStorageDirectory().getAbsolutePath() +
                    File.separator + "red.skin");
            mSkinResource = new Resources(asset, superRes.getDisplayMetrics(),
                    superRes.getConfiguration());
            mPackageName = context.getPackageManager().getPackageArchiveInfo(
                    skinPath, PackageManager.GET_ACTIVITIES).packageName;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 通过名字获取Drawable
     * @param resName
     * @return
     */
    public Drawable getDrawableByName(String resName){
        int resId = mSkinResource.getIdentifier(resName, "drawable", mPackageName);
        Drawable drawable = mSkinResource.getDrawable(resId);
        return drawable;
    }
    /**
     * 通过名字获取颜色
     * @param resName
     * @return
     */
    public ColorStateList getColorByName(String resName){
        try {
            int resId = mSkinResource.getIdentifier(resName, "color", mPackageName);
            ColorStateList color = mSkinResource.getColorStateList(resId);
            return color;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
