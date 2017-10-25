package example.weisente.top.baselibrary.loadpage.callback;

import android.support.annotation.DrawableRes;
import android.support.annotation.StyleRes;

/**
 * Created by san on 2017/10/25.
 */

public class HintCallback extends BaseCallback  {
    private String title;
    private String subTitle;
    private int imgResId;
    private int titleStyleRes;
    private int subTitleStyleRes;
    //inner Builder in order to manager parameter
    public HintCallback(Builder builder) {
        this.title = builder.title;
        this.subTitle = builder.subTitle;
        this.imgResId = builder.imgResId;
        this.subTitleStyleRes = builder.subTitleStyleRes;
        this.titleStyleRes = builder.titleStyleRes;
    }

    @Override
    protected int onCreateView() {
        return 0;
    }

    public static class Builder {
        private String title;
        private String subTitle;
        private int imgResId = -1;
        private int subTitleStyleRes = -1;
        private int titleStyleRes = -1;

        public Builder setHintImg(@DrawableRes int imgResId) {
            this.imgResId = imgResId;
            return this;
        }

        public Builder setTitle(String title) {
            return setTitle(title, -1);
        }

        public Builder setTitle(String title, @StyleRes int titleStyleRes) {
            this.title = title;
            this.titleStyleRes = titleStyleRes;
            return this;
        }

        public Builder setSubTitle(String subTitle) {
            return setSubTitle(subTitle, -1);
        }

        public Builder setSubTitle(String subTitle, @StyleRes int subTitleStyleRes) {
            this.subTitle = subTitle;
            this.subTitleStyleRes = subTitleStyleRes;
            return this;
        }

        public HintCallback build() {
            return new HintCallback(this);
        }
    }
}
