package example.weisente.top.baselibrary.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import example.weisente.top.baselibrary.R;

/**
 * Created by san on 2017/11/5.
 */

public class AlertDialog extends Dialog {

    private AlertController mAlert;

    public AlertDialog(Context context, int themeResId) {
        super(context, themeResId);
        mAlert = new AlertController(this, getWindow());
    }
    /**
     * 设置文本
     *
     * @param viewId
     * @param text
     */
    public void setText(int viewId, CharSequence text) {
        mAlert.setText(viewId,text);
    }

    public <T extends View> T getView(int viewId) {
        return mAlert.getView(viewId);
    }
    /**
     * 设置点击事件
     *
     * @param viewId
     * @param listener
     */
    public void setOnclickListener(int viewId, View.OnClickListener listener) {
        mAlert.setOnclickListener(viewId,listener);
    }

    public static class Builder {
        private final AlertController.AlertParams P;
        public Builder(Context context) {
            this(context, R.style.dialog);
        }

        public Builder(Context context, int themeResId) {
            P = new AlertController.AlertParams(context, themeResId);
        }
        public Builder setContentView(View view) {
            P.mView = view;
            P.mViewLayoutResId = 0;
            return this;
        }
        public Builder setContentView(int layoutId) {
            P.mView = null;
            P.mViewLayoutResId = layoutId;
            return this;
        }
        public Builder setText(int viewId,CharSequence text){
            P.mTextArray.put(viewId,text);
            return this;
        }
        public Builder setOnClickListener(int view , View.OnClickListener listener){
            P.mClickArray.put(view,listener);
            return this;
        }
        public Builder fullWidth(){
            P.mWidth = ViewGroup.LayoutParams.MATCH_PARENT;
            return this;
        }
        public Builder formBottom(boolean isAnimation){
            if(isAnimation){
                P.mAnimations = R.style.dialog_from_bottom_anim;
            }
            P.mGravity = Gravity.BOTTOM;
            return this;
        }
        /**
         * 设置Dialog的宽高
         * @param width
         * @param height
         * @return
         */
        public Builder setWidthAndHeight(int width, int height){
            P.mWidth = width;
            P.mHeight = height;
            return this;
        }
        /**
         * 添加默认动画
         * @return
         */
        public Builder addDefaultAnimation(){
            P.mAnimations = R.style.dialog_scale_anim;
            return this;
        }
    }
}
