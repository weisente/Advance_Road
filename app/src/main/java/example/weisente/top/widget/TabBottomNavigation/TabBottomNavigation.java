package example.weisente.top.widget.TabBottomNavigation;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import example.weisente.top.widget.TabBottomNavigation.iterator.TabIterator;

/**
 * Created by san on 2017/12/7.
 */

public class TabBottomNavigation extends LinearLayout {
    private List<AbsTabView> mTabItems;
    private int mCurrentIndex = -1;

    public TabBottomNavigation(Context context) {
        this(context,null);
    }

    public TabBottomNavigation(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public TabBottomNavigation(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);
        mTabItems = new ArrayList<>();
    }

    public void addTabItem(TabIterator<TabViewImpl> tabIterator){
        mTabItems.clear();
        // 当前的位置
        int index = 0;
        while (tabIterator.hasNext()){
            TabViewImpl tabItem = tabIterator.next();
            View tabView = tabItem.getView();
            addView(tabView);

            LinearLayout.LayoutParams params = (LayoutParams) tabView.getLayoutParams();
            params.weight = 1;
            params.gravity = Gravity.CENTER;
            tabView.setLayoutParams(params);

            // 给条目设置点击事件，等等
            setItemClickListener(tabView,index++);
            mTabItems.add(tabItem);
        }

        // 第一个位置要设置为选中
        mTabItems.get(0).setSelected(true);
        mCurrentIndex = 0;
    }

    private void setItemClickListener(View tabView, final int position) {
        tabView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCurrentIndex != position){
                    // 原来的标为非选中
                    mTabItems.get(mCurrentIndex).setSelected(false);
                    // 把当前设置为选中
                    mCurrentIndex = position;
                    mTabItems.get(mCurrentIndex).setSelected(true);
                    // 把点击的为用接口回调出去供外部使用，调整显示
                }
            }
        });
    }


}
