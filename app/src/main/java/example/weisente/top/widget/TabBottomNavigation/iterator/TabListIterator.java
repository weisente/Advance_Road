package example.weisente.top.widget.TabBottomNavigation.iterator;

import java.util.ArrayList;
import java.util.List;

import example.weisente.top.widget.TabBottomNavigation.AbsTabView;

/**
 * Created by san on 2017/12/7.
 */

public class TabListIterator<T extends AbsTabView> implements TabIterator {
    private List<T> mTabItems;
    private int index;

    public TabListIterator(){
        mTabItems = new ArrayList<>();
    }

    public void addItem(T item){
        mTabItems.add(item);
    }
    @Override
    public AbsTabView next() {
        return mTabItems.get(index++);
    }

    @Override
    public boolean hasNext() {
        return index < mTabItems.size();
    }
}
