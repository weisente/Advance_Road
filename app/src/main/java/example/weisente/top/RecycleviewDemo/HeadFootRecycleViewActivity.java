package example.weisente.top.RecycleviewDemo;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;

import java.util.List;

import example.weisente.top.R;
import example.weisente.top.baselibrary.base.BaseActivity;
import example.weisente.top.baselibrary.recycleviewbase.widget.WrapRecyclerView;
import okhttp3.OkHttpClient;

/**
 * Created by san on 2017/11/1.
 */

public class HeadFootRecycleViewActivity extends BaseActivity {
    private WrapRecyclerView mRecyclerView;
    private List<String> mDatas;
    private OkHttpClient mOkHttpClient;
    private static Handler mHandler = new Handler();
    @Override
    protected void initData() {
        mRecyclerView = (WrapRecyclerView) findViewById(R.id.rv);
        // 设置显示分割 ListView样式
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mOkHttpClient = new OkHttpClient();
    }

    /**
     * 请求列表数据
     */
    private void requestListData(){

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_recycleview);
    }
}
