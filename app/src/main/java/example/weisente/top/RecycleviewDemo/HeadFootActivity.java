package example.weisente.top.RecycleviewDemo;

import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import example.weisente.top.R;
import example.weisente.top.baselibrary.base.BaseActivity;
import example.weisente.top.baselibrary.recycleviewbase.widget.WrapRecyclerView;

/**
 * Created by san on 2017/11/4.
 */

public class HeadFootActivity  extends BaseActivity {
    WrapRecyclerView rv;
    private List<String> mDatas;
    private RecycleviewMainActivity.HomeAdapter mAdapter;

    @Override
    protected void initData() {
        rv  = (WrapRecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        mDatas = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
        mAdapter = new RecycleviewMainActivity.HomeAdapter(this, mDatas);
        rv.setAdapter(mAdapter);
//        TextView textView = new TextView(this);
//        textView.setText("头部");
        View inflate = LayoutInflater.from(this).inflate(R.layout.headview, null);
        rv.addHeaderView(inflate);

        TextView foot = new TextView(this);
        foot.setText("尾部");
        rv.addFooterView(foot);

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_foothead);
    }
}
