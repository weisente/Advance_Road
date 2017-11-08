package example.weisente.top.EasyUi;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import example.weisente.top.R;

/**
 * Created by san on 2017/11/7.
 */

public class EasyUiActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easyui);



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
