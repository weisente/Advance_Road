package example.weisente.top.widget.TabBottomNavigation;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import example.weisente.top.R;

/**
 * Created by san on 2017/12/7.
 */

public class TabViewImpl extends AbsTabView {

    private Builder builder;

    TabViewImpl(Context context) {
        super(context, R.layout.tab_main_bottom_item);
    }

    public TabViewImpl(Builder builder) {
        this(builder.context);
        this.builder = builder;
    }

    @Override
    protected void initLayout() {
        TextView tabText = findViewById(R.id.tab_text);
        ImageView tabIcon = findViewById(R.id.tab_icon);

        if(!TextUtils.isEmpty(builder.text)){
            tabText.setText(builder.text);
        }

        if(builder.resIconId != 0){
            tabIcon.setImageResource(builder.resIconId);
        }
    }

//    @Override
//    public View getView() {
//        return super.getView();
//    }

    @Override
    protected void setSelected(boolean selected) {
        TextView tabText = findViewById(R.id.tab_text);
        ImageView tabIcon = findViewById(R.id.tab_icon);

        tabText.setSelected(selected);
        tabIcon.setSelected(selected);
    }


    //建造者  因为每一个Impl的不一样
    public static class Builder{
        Context context;
        String text;
        int resIconId;

        public Builder(Context context){
            this.context = context;
        }

        public Builder text(String text){
            this.text = text;
            return this;
        }

        public Builder resIcon(int resIconId){
            this.resIconId = resIconId;
            return this;
        }

        public TabViewImpl create(){
            return new TabViewImpl(this);
        }
    }
}
