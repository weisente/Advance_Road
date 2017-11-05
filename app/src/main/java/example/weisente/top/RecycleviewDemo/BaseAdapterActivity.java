package example.weisente.top.RecycleviewDemo;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import example.weisente.top.R;
import example.weisente.top.baselibrary.base.BaseActivity;
import example.weisente.top.baselibrary.recycleviewbase.adapter.OnItemClickListener;
import example.weisente.top.baselibrary.recycleviewbase.widget.LoadRefreshRecyclerView;
import example.weisente.top.bean.ChannelListResult;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by san on 2017/11/5.
 */

public class BaseAdapterActivity  extends BaseActivity implements OnItemClickListener {
    LoadRefreshRecyclerView rv;
    private OkHttpClient mOkHttpClient;
    private static Handler mHandler = new Handler();
    @Override
    protected void initData() {
        rv = (LoadRefreshRecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        Request.Builder builder = new Request.Builder();
        builder.url("http://is.snssdk.com/2/essay/discovery/v3/?iid=6152551759&channel=360&aid=7" +
                "&app_name=joke_essay&version_name=5.7.0&ac=wifi&device_id=30036118478&device_brand=Xiaomi&update_version_code=5701&" +
                "manifest_version_code=570&longitude=113.000366&latitude=28.171377&device_platform=android");
        mOkHttpClient = new OkHttpClient();
        mOkHttpClient.newCall(builder.build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                // Gson 解析成对象
                ChannelListResult channelList = new Gson().fromJson(result, ChannelListResult.class);
                // 获取列表数据
                final List<ChannelListResult.DataBean.CategoriesBean.CategoryListBean> categoryList =
                        channelList.getData().getCategories().getCategory_list();
                // 该方法不是在主线程中
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        showListData(categoryList);
                    }
                });
            }
        });
    }
    List<ChannelListResult.DataBean.CategoriesBean.CategoryListBean> mData;
    private void showListData(List<ChannelListResult.DataBean.CategoriesBean.CategoryListBean> categoryList) {
        mData = categoryList;

        final CategoryListAdapter listAdapter = new CategoryListAdapter(this, categoryList);
        listAdapter.setOnItemClickListener(this);
        rv.setAdapter(listAdapter);

        ItemTouchHelper ith = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                /* 从RecyclerView中删除，需要在两个地方删除 */
                // 现在数据集合中删除，通知适配器删除的条目
                mData.remove(viewHolder.getAdapterPosition());
                listAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });
        ith.attachToRecyclerView(rv);
    }

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

    @Override
    public void onItemClick(int position) {
        Toast.makeText(this, "" + mData.get(position).getName(), Toast.LENGTH_SHORT).show();
    }
}
