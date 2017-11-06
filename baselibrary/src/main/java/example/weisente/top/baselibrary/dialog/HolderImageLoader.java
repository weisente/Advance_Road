package example.weisente.top.baselibrary.dialog;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by san on 2017/11/6.
 * 图片加载的抽象类
 */

public  abstract class HolderImageLoader {
    private String mImagePath;

    //默认构造器
    public HolderImageLoader(){};

    public HolderImageLoader(String imagePath) {
        this.mImagePath = imagePath;
    }

    public String getImagePath() {
        return mImagePath;
    }

    public abstract void displayImage(Context context, ImageView imageView, String imagePath);
}
