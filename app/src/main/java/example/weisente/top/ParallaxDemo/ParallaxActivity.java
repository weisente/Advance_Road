package example.weisente.top.ParallaxDemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import example.weisente.top.R;


/**
 * Created by san on 2017/11/27.
 */

public class ParallaxActivity extends AppCompatActivity {
    // 2.2.1 先把布局和 Fragment 创建好
    private ParallaxViewPager mParallaxViewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parallax);
        mParallaxViewPager = (ParallaxViewPager) findViewById(R.id.parallax_vp);
        mParallaxViewPager.setLayout(getSupportFragmentManager(),
                new int[]{R.layout.fragment_page_first,R.layout.fragment_page_second,
                        R.layout.fragment_page_third,R.layout.fragment_page_first});
    }
}
