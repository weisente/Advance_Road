package example.weisente.top.baselibrary.banner;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * Created by san on 2017/11/1.
 */

public class BannerScroller extends Scroller {
    // 3.改变ViewPager切换的速率 - 动画持续的时间
    private int mScrollerDuration = 950;

    /**
     * 设置切换页面持续的时间
     */
    public void setScrollerDuration(int scrollerDuration){
        this.mScrollerDuration = scrollerDuration;
    }
    public BannerScroller(Context context) {
        super(context);
    }
    public BannerScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    public BannerScroller(Context context, Interpolator interpolator, boolean flywheel) {
        super(context, interpolator, flywheel);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy,int duration) {
        super.startScroll(startX, startY, dx, dy,mScrollerDuration);
    }

}
