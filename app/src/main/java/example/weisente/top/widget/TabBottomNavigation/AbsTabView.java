package example.weisente.top.widget.TabBottomNavigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by san on 2017/12/7.
 */

public abstract class AbsTabView  {
    public int mLayoutId;
    public Context mContext;
    public View TabView;

    AbsTabView(Context context,int layoutId){
        mContext = context;
        mLayoutId = layoutId;
    }

    public View getView(){
        if(TabView == null){
            TabView = LayoutInflater.from(mContext).inflate(mLayoutId, null);
            initLayout();
        }
        return TabView;
    }

    /**
     * 初始化显示
     */
    protected abstract void initLayout();

    protected <T> T findViewById(int id){
        return (T)TabView.findViewById(id);
    }

    /**
     * 是否选择当前条目
     * @param selected
     */
    protected abstract void setSelected(boolean selected);
}
