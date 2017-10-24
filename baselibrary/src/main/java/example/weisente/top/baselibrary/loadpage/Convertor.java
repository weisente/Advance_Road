package example.weisente.top.baselibrary.loadpage;

import example.weisente.top.baselibrary.loadpage.callback.BaseCallback;

/**
 * Created by Administrator on 2017/10/25 0025.
 */

public interface  Convertor<T> {
    Class<?extends BaseCallback> map(T t);
}
