package example.weisente.top.baselibrary.banner;

import android.view.View;

/**
 * Created by san on 2017/11/1.
 */

public abstract class BannerAdapter {
    /**
     * 1.获取根据位置获取ViewPager里面的子View
     */
    public abstract View getView(int position, View convertView);
    /**
     * 5.获取轮播的数量
     */
    public abstract int getCount();

    /**
     * 6.根据位置获取广告位描述
     */
    public String getBannerDesc(int position){
        return "";
    }

}
