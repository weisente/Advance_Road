package example.weisente.top.baselibrary.recycleviewbase.adapter;

/**
 * Description:  多布局支持接口
 */
public interface MultiTypeSupport<T> {
    // 根据当前位置或者条目数据返回布局
    public int getLayoutId(T item, int position);
}
