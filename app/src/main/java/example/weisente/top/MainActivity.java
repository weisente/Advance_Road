package example.weisente.top;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import example.weisente.top.baselibrary.rxbus.RxBus2;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().add(R.id.fl_fragmentA, new FragmentA()).commit();
        Button button = (Button) findViewById(R.id.text_view);
//        RxBus2.getDefault().register(this   );
//        new Integer()
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxBus2.getDefault().post(new Integer(1) );
            }
        });

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        RxBus2.getDefault().unregister(this);
    }

}
