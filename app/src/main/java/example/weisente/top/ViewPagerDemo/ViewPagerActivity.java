package example.weisente.top.ViewPagerDemo;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import example.weisente.top.R;
import example.weisente.top.baselibrary.base.BaseActivity;

/**
 * Created by san on 2017/10/29 0029.
 */

public class ViewPagerActivity extends BaseActivity {
    @Override
    protected void initData() {
        ViewPager viewPager = (ViewPager)findViewById(R.id.vp);
//        viewPager.setAdapte;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void setContentView() {


        setContentView(R.layout.activity_viewpager);
    }
    class VpAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return false;
        }
    }

}
