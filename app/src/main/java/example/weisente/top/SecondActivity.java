package example.weisente.top;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import example.weisente.top.baselibrary.rxbus.Subscribe;


/**
 * Created by san on 2017/10/6.
 */

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        RxBus.getInstance().register(this);
    }

    //接受粘性的消息
    @Subscribe(receiveStickyEvent = true)
    public void onRxBusEvent3(String msg){
        TextView textView = (TextView)findViewById(R.id.text);
        textView.setText(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        RxBus.getInstance().
    }
}
