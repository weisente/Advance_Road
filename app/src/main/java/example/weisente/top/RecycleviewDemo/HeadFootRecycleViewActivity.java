package example.weisente.top.RecycleviewDemo;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import example.weisente.top.R;
import example.weisente.top.baselibrary.base.BaseActivity;
import example.weisente.top.baselibrary.recycleviewbase.widget.WrapRecyclerView;
import example.weisente.top.bean.ChannelListResult;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
        Request.Builder builder = new Request.Builder();
        builder.url("http://is.snssdk.com/2/essay/discovery/v3/?iid=6152551759&channel=360&aid=7" +
                "&app_name=joke_essay&version_name=5.7.0&ac=wifi&device_id=30036118478&device_brand=Xiaomi&update_version_code=5701&" +
                "manifest_version_code=570&longitude=113.000366&latitude=28.171377&device_platform=android");
        mOkHttpClient.newCall(builder.build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                // Gson 解析成对象
                final ChannelListResult channelList = new Gson().fromJson(result, ChannelListResult.class);
                // 获取列表数据
                final List<ChannelListResult.DataBean.CategoriesBean.CategoryListBean> categoryList =
                        channelList.getData().getCategories().getCategory_list();
                // 该方法不是在主线程中
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        showListData(categoryList);
                        addBannerView(channelList.getData().getRotate_banner().getBanners());
                    }
                });
            }
        });
    }
    List<ChannelListResult.DataBean.CategoriesBean.CategoryListBean> mData;
    /**
     * 显示列表数据
     *
     * @param categoryList
     */
    private void showListData(List<ChannelListResult.DataBean.CategoriesBean.CategoryListBean> categoryList){
        mData = categoryList;
        final CategoryListAdapter listAdapter = new CategoryListAdapter(this, categoryList);
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
