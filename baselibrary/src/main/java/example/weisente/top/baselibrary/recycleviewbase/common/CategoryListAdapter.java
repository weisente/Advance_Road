package example.weisente.top.baselibrary.recycleviewbase.common;

import android.content.Context;

import java.util.List;

import example.weisente.top.baselibrary.R;
import example.weisente.top.baselibrary.recycleviewbase.adapter.CommonRecyclerAdapter;
import example.weisente.top.baselibrary.recycleviewbase.adapter.MultiTypeSupport;
import example.weisente.top.baselibrary.recycleviewbase.adapter.ViewHolder;
import example.weisente.top.bean.ChannelListResult;

/**
 * Created by san on 2017/11/1.
 */

public class CategoryListAdapter extends CommonRecyclerAdapter<ChannelListResult.DataBean.CategoriesBean.CategoryListBean> {


    public CategoryListAdapter(Context context, List<ChannelListResult.DataBean.CategoriesBean.CategoryListBean> data, int layoutId) {
        super(context, datas, R.layout.channel_list_item);
    }

    public CategoryListAdapter(Context context, List<ChannelListResult.DataBean.CategoriesBean.CategoryListBean> data, MultiTypeSupport<ChannelListResult.DataBean.CategoriesBean.CategoryListBean> multiTypeSupport) {
        super(context, data, multiTypeSupport);
    }

    @Override
    public void convert(ViewHolder holder, ChannelListResult.DataBean.CategoriesBean.CategoryListBean item) {

    }
}
