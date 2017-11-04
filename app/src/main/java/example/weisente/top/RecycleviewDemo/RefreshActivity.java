package example.weisente.top.RecycleviewDemo;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import example.weisente.top.R;
import example.weisente.top.baselibrary.base.BaseActivity;
import example.weisente.top.baselibrary.recycleviewbase.widget.RefreshRecyclerView;

/**
 * Created by san on 2017/11/4.
 */

public class RefreshActivity extends BaseActivity {
    RefreshRecyclerView rv;
    private List<String> mDatas;
    private RecycleviewMainActivity.HomeAdapter mAdapter;
    @Override
    protected void initData() {
        rv = (RefreshRecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        mDatas = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }

        mAdapter = new RecycleviewMainActivity.HomeAdapter(this, mDatas);
        rv.setAdapter(mAdapter);
        rv.addRefreshViewCreator(new DefaultRefreshCreator());
        rv.setOnRefreshListener(new RefreshRecyclerView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rv.onStopRefresh();
                    }
                }, 2000);
            }
        });
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_refresh);
    }
}
