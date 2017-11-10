package example.weisente.top.framelibrary.skin.attr;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import example.weisente.top.framelibrary.skin.SkinManager;
import example.weisente.top.framelibrary.skin.SkinResource;

/**
 * Created by san on 2017/11/10.
 */

public enum SkinType {
    TEXT_COLOR("textColor") {
        @Override
        public void skin(View view, String resName) {
            SkinResource skinResource = getSkinResource();
            ColorStateList color = skinResource.getColorByName(resName);
            if(color==null){
                return;
            }
            TextView textView = (TextView) view;
            textView.setTextColor(color);
        }
    },BACKGROUND("background") {
        @Override
        public void skin(View view, String resName) {
            // 背景可能是图片也可能是颜色
            SkinResource skinResource = getSkinResource();
            Drawable drawable = skinResource.getDrawableByName(resName);
            if(drawable!=null){
                ImageView imageView = (ImageView) view;
                imageView.setBackgroundDrawable(drawable);
                return;
            }

            // 可能是颜色
            ColorStateList color = skinResource.getColorByName(resName);
            if(color!=null){
                view.setBackgroundColor(color.getDefaultColor());
            }
        }
    },SRC("src") {
        @Override
        public void skin(View view, String resName) {
            // 获取资源设置
            SkinResource skinResource = getSkinResource();
            Drawable drawable = skinResource.getDrawableByName(resName);
            if(drawable!=null){
                ImageView imageView = (ImageView) view;
                imageView.setImageDrawable(drawable);
                return;
            }
        }
    };

    // 会根据名字调对应的方法
    private String mResName;
    SkinType(String resName){
        this.mResName = resName;
    }

    // 抽象的
    public abstract void skin(View view, String resName);

    public String getResName() {
        return mResName;
    }

    public SkinResource getSkinResource() {
        return SkinManager.getInstance().getSkinResource();
    }
}
