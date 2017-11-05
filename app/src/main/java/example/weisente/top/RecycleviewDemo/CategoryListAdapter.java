package example.weisente.top.RecycleviewDemo;

import android.content.Context;
import android.text.Html;
import android.view.View;

import java.util.List;

import example.weisente.top.R;
import example.weisente.top.baselibrary.recycleviewbase.adapter.CommonRecyclerAdapter;
import example.weisente.top.baselibrary.recycleviewbase.adapter.ViewHolder;
import example.weisente.top.baselibrary.recycleviewbase.common.GlideImageLoader;
import example.weisente.top.bean.ChannelListResult;

/**
 * Created by san on 2017/11/5.
 */

public class CategoryListAdapter extends CommonRecyclerAdapter<ChannelListResult.DataBean.CategoriesBean.CategoryListBean> {
    public CategoryListAdapter(Context context, List<ChannelListResult.DataBean.CategoriesBean.CategoryListBean> data) {
        super(context, data, R.layout.channel_list_item);
    }

    @Override
    public void convert(ViewHolder holder, ChannelListResult.DataBean.CategoriesBean.CategoryListBean item) {
// 显示数据
        String str = item.getSubscribe_count() + " 订阅 | " +
                "总帖数 <font color='#FF678D'>" + item.getTotal_updates() + "</font>";
        holder.setText(R.id.channel_text, item.getName())
                .setText(R.id.channel_topic, item.getIntro())
                .setText(R.id.channel_update_info, Html.fromHtml(str));

        // 是否是最新
        if (item.isIs_recommend()) {
            holder.setViewVisibility(R.id.recommend_label, View.VISIBLE);
        } else {
            holder.setViewVisibility(R.id.recommend_label, View.GONE);
        }
        // 加载图片
        holder.setImageByUrl(R.id.channel_icon, new GlideImageLoader(item.getIcon_url()));
    }
}
