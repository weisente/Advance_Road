package example.weisente.top.framelibrary.skin;

import android.content.Context;

/**
 * Created by san on 2017/11/15.
 */

public class SkinManager {

    private volatile static SkinManager Instance;
    private Context mContext;


    static {
        //饿汉式
        Instance  = new SkinManager();
    }

    //获取全局的Context
    public void init(Context context){
        mContext = context.getApplicationContext();
    }

    //读取资源文件
    public int loadSkin(String skinPath){
        //为什么用int  为了后面使用增量更新


        return 0;
    }

}
