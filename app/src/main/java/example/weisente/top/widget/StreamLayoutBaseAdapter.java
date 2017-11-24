package example.weisente.top.widget;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by san on 2017/11/23.
 */

public abstract class StreamLayoutBaseAdapter {
    // 1.有多少个条目
    public abstract int getCount();

    // 2.getView通过position
    public abstract View getView(int position, ViewGroup parent);


    // 3.观察者模式及时通知更新
    public void unregisterDataSetObserver(DataSetObserver observer){

    }

    public void registerDataSetObserver(DataSetObserver observer){

    }

}
