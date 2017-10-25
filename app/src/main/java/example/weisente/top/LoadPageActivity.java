package example.weisente.top;

import android.content.Context;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import example.weisente.top.baselibrary.base.BaseActivity;
import example.weisente.top.baselibrary.loadpage.LoadManager;
import example.weisente.top.baselibrary.loadpage.LoadService;
import example.weisente.top.baselibrary.loadpage.Transport;
import example.weisente.top.baselibrary.loadpage.callback.BaseCallback;
import example.weisente.top.callback.EmptyCallback;
import example.weisente.top.callback.LoadingCallback;

/**
 * Created by san on 2017/10/25.
 */

public class LoadPageActivity extends BaseActivity {

    private LoadService loadService;
    @Override
    protected void initData() {
        loadService = LoadManager.getDefault().register(this, new BaseCallback.OnReloadListener() {
            @Override
            public void onReload(View v) {
//                //展示一个加载中的布局
//                //进行网络的访问
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //展示一个加载中的布局
                        loadService.showCallback(LoadingCallback.class);
                        //do retry logic...
                        SystemClock.sleep(500);
                        //callback
                        loadService.showSuccess();
                    }
                }).start();
            }
        }).setCallBack(EmptyCallback.class, new Transport() {
            @Override
            public void order(Context context, View view) {
                TextView mTvEmpty = (TextView) view.findViewById(R.id.tv_empty);
                mTvEmpty.setText("fine, no data. You must fill it!");
            }
        });
//        loadService.showCallback();
        PostUtil.postCallbackDelayed(loadService, EmptyCallback.class);

//                .setCallBack(EmptyCallback.class, new Transport() {
//            @Override
//            public void order(Context context, View view) {
//                TextView mTvEmpty = (TextView) view.findViewById(R.id.tv_empty);
//                mTvEmpty.setText("fine, no data. You must fill it!");
//            }
//        });
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_loadpage);
    }
}
