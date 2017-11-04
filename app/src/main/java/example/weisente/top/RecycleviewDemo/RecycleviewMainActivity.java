package example.weisente.top.RecycleviewDemo;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import example.weisente.top.R;
import example.weisente.top.baselibrary.base.BaseActivity;
import example.weisente.top.baselibrary.recycleviewbase.Decoration.RecyclerGridSpaceDecoration;
import example.weisente.top.baselibrary.recycleviewbase.widget.WrapRecyclerView;

/**
 * Created by san on 2017/11/1.
 */

public class RecycleviewMainActivity extends BaseActivity {
    private WrapRecyclerView recyclerView;
    private List<String> mDatas;
    private HomeAdapter mAdapter;
    @Override
    protected void initData() {

        recyclerView = (WrapRecyclerView) findViewById(R.id.rv);

        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
        mAdapter = new HomeAdapter(this, mDatas);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.addItemDecoration(new RecyclerGridSpaceDecoration(30));
        // 设置item动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        TextView textView = new TextView(RecycleviewMainActivity.this);
//        textView.setText("测试");
//        textView.setGravity(Gravity.CENTER);
//        recyclerView.addHeaderView(textView);
//        recyclerView.addHeaderView(textView);
//        recyclerView.addHeaderView(textView);
//        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initView() {


    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void setContentView() {
        //设置recycleview的视图
        setContentView(R.layout.activity_recycleview);
    }


    public static class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

        private List<String> mDatas;
        private LayoutInflater mInflater;


        public HomeAdapter(Context context, List<String> datas) {
            mInflater = LayoutInflater.from(context);
            mDatas = datas;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(mInflater.inflate(
                    R.layout.item_home, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            holder.tv.setText(mDatas.get(position));
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView tv;

            public MyViewHolder(View view) {
                super(view);
                tv = (TextView) view.findViewById(R.id.id_num);
            }
        }
    }
}
