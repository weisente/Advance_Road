package example.weisente.top.ParallaxDemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import example.weisente.top.R;

/**
 * Created by san on 2017/11/27.
 *
 * 视察的viewpager
 */

public class ParallaxViewPager extends ViewPager {
    private List<ParallaxFragment> mFragments;

    public ParallaxViewPager(Context context) {
        this(context,null);
    }

    public ParallaxViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mFragments = new ArrayList<>();
    }

    /**
     *
     * @param fm
     * @param layoutIds
     */
    public void setLayout(FragmentManager fm,int[] layoutIds){
        mFragments.clear();
        for (int layoutId : layoutIds) {
            ParallaxFragment fragment = new ParallaxFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(ParallaxFragment.LAYOUT_ID_KEY,layoutId);
            fragment.setArguments(bundle);
            mFragments.add(fragment);
        }
        // 设置我们的 ViewPager 的Adapter
        setAdapter(new ParallaxPagerAdapter(fm));

        addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                ParallaxFragment outFragment = mFragments.get(position);
                //获取fragment里面的所有view
                List<View> parallaxViews = outFragment.getParallaxViews();
                for (View parallaxView : parallaxViews){
                    ParallaxTag tag = (ParallaxTag) parallaxView.getTag(R.id.parallax_tag);
                    // 为什么这样写 ？
                    parallaxView.setTranslationX((-positionOffsetPixels)*tag.translationXOut);
                    parallaxView.setTranslationY((-positionOffsetPixels)*tag.translationYOut);
                }
                //获取下一个fragment
                try {
                    ParallaxFragment inFragment = mFragments.get(position+1);
                    parallaxViews = inFragment.getParallaxViews();
                    for (View parallaxView : parallaxViews) {
                        ParallaxTag tag = (ParallaxTag) parallaxView.getTag(R.id.parallax_tag);
                        parallaxView.setTranslationX((getMeasuredWidth()-positionOffsetPixels)*tag.translationXIn);
                        parallaxView.setTranslationY((getMeasuredWidth()-positionOffsetPixels)*tag.translationYIn);
                    }
                }catch (Exception e){}
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }




    private class ParallaxPagerAdapter extends FragmentPagerAdapter {

        public ParallaxPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}
