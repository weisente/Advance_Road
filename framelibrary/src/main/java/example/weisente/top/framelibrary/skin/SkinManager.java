package example.weisente.top.framelibrary.skin;

import android.app.Activity;
import android.content.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import example.weisente.top.framelibrary.skin.attr.SkinView;

/**
 * Created by san on 2017/11/10.
 */

public class SkinManager {
    private static SkinManager mInstance;
    private Context mContext;
    private SkinResource mSkinResource;

    private Map<Activity,List<SkinView>> mSkinViews = new HashMap<>();

    static {
        mInstance = new SkinManager();
    }

    public static SkinManager getInstance() {
        return mInstance;
    }

    public void init(Context context){
        this.mContext = context.getApplicationContext();
    }


    /**
     * 加载皮肤
     * @param skinPath
     * @return
     */
    public int loadSkin(String skinPath) {
        // 校验签名  增量更新再说

        // 最好把他复制走，用户不能轻易删除的地方  cache目录下面

        // 初始化资源管理
        mSkinResource = new SkinResource(mContext,skinPath);

        // 改变皮肤
        Set<Activity> keys = mSkinViews.keySet();

        for (Activity key : keys) {
            List<SkinView> skinViews = mSkinViews.get(key);
            for (SkinView skinView : skinViews) {
                skinView.skin();
            }
        }

        return 0;
    }

    /**
     * 恢复默认
     * @return
     */
    public int restoreDefault() {
        return 0;
    }

    /**
     * 获取SkinView通过activity
     * @param activity
     * @return
     */
    public List<SkinView> getSkinViews(Activity activity) {
        return mSkinViews.get(activity);
    }

    /**
     * 注册
     * @param activity
     * @param skinViews
     */
    public void register(Activity activity, List<SkinView> skinViews) {
        mSkinViews.put(activity,skinViews);
    }

    /**
     * 获取当前的皮肤资源
     * @return
     */
    public SkinResource getSkinResource() {
        return mSkinResource;
    }
}
