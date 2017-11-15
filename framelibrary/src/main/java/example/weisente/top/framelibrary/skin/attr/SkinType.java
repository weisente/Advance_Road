package example.weisente.top.framelibrary.skin.attr;

import android.view.View;

import example.weisente.top.framelibrary.skin.SkinManager;
import example.weisente.top.framelibrary.skin.SkinResource;

/**
 * Created by san on 2017/11/15.
 *
 * 皮肤的类型
 */

public enum  SkinType {

    TEXT_COLOR("textColor"){
        @Override
        public void skin(View view, String resName){

        }

    },BACKGROUND("background") {
        @Override
        public void skin(View view, String resName) {

        }
    },SRC("src"){
        @Override
        public void skin(View view, String resName) {

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
