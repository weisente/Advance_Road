package example.weisente.top.framelibrary.skin.attr;

import android.view.View;

/**
 * Created by san on 2017/11/10.
 */

public class SkinAttr {
    private String mResName;
    private SkinType mSkinType;

    public SkinAttr(String resName, SkinType skinType) {
        this.mResName = resName;
        this.mSkinType = skinType;
    }

    public void skin(View view) {
        mSkinType.skin(view,mResName);
    }
}
