package example.weisente.top.RecycleviewDemo;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import example.weisente.top.R;
import example.weisente.top.baselibrary.base.BaseActivity;
import example.weisente.top.baselibrary.recycleviewbase.widget.LoadRefreshRecyclerView;

/**
 * Created by san on 2017/11/4.
 */

public class RefreshLoadActivity extends BaseActivity {
    LoadRefreshRecyclerView rv;
    private List<String> mDatas;
    private RecycleviewMainActivity.HomeAdapter mAdapter;
    @Override
    protected void initData() {
        rv = (LoadRefreshRecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addLoadViewCreator(new DefaultLoadCreator());

        mDatas = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
        mAdapter = new RecycleviewMainActivity.HomeAdapter(this, mDatas);
        rv.setAdapter(mAdapter);
//        rv.setOnRefreshListener(new RefreshRecyclerView.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        rv.onStopRefresh();
//                    }
//                }, 2000);
//            }
//        });
        rv.setOnLoadMoreListener(new LoadRefreshRecyclerView.OnLoadMoreListener() {
            @Override
            public void onLoad() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                        rv.onStopLoad();
//                        mAdapter.notifyDataSetChanged();
                    }
                }, 2000);
            }
        });
    }
//
//    @Override
//    public void onLoad() {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                initData();
//                mRecyclerView.onStopLoad();
//                mAdapter.notifyDataSetChanged();
//            }
//        }, 2000);
//    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_refreshload);
    }
}
