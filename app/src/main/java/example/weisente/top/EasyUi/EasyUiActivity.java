package example.weisente.top.EasyUi;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import example.weisente.top.R;


/**
 * Created by san on 2017/11/7.
 */

public class EasyUiActivity extends Activity {

    private List<String> mItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easyuiv3);


        mItems = new ArrayList<String>();

        for (int i=0;i<200;i++){
            mItems.add("i -> "+i);
        }
        ListView mListView = (ListView) findViewById(R.id.list_view);

        mListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return mItems.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView item = (TextView) LayoutInflater.from(EasyUiActivity.this)
                        .inflate(R.layout.item_lv, parent, false);
                item.setText(mItems.get(position));
                return item;
            }
        });

//        new BaseAdapter() {
//            @Override
//            public int getCount() {
//                return 0;
//            }
//
//            @Override
//            public Object getItem(int position) {
//                return null;
//            }
//
//            @Override
//            public long getItemId(int position) {
//                return 0;
//            }
//
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent) {
//                return null;
//            }
//        };


//        final ProgressView progressview = (ProgressView)findViewById(R.id.progressView);
////        progressview.setMaxProgress(100);
//        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 100);
//        valueAnimator.setDuration(2000);
//        valueAnimator.setInterpolator(new DecelerateInterpolator());
//
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                float currentStep = (float) animation.getAnimatedValue();
//                progressview.setCurrentStep((int)currentStep);
//            }
//        });
//        valueAnimator.start();
//








//        final WalkView walkView = (WalkView) findViewById(R.id.walkview);
//        walkView.setStepMax(4000);
//        //一个属性的值  从0到3000
//        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 3000);
//        valueAnimator.setDuration(1000);
//        valueAnimator.setInterpolator(new DecelerateInterpolator());
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                float currentStep = (float) animation.getAnimatedValue();
//                walkView.setCurrentStep((int)currentStep);
//            }
//        });
//        valueAnimator.start();
//        StudyListview lv = (StudyListview) findViewById(R.id.lv);
//        ArrayList<String> strings = new ArrayList<>();
//        for(int i = 0;i < 30 ;i++){
//            strings.add("wenben"+i);
//        }
//
//
//        lv.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,strings));

//        ListView
    }
}
