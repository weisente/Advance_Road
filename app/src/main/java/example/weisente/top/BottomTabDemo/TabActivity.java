package example.weisente.top.BottomTabDemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import example.weisente.top.R;
import example.weisente.top.widget.TabBottomNavigation.TabBottomNavigation;
import example.weisente.top.widget.TabBottomNavigation.TabViewImpl;
import example.weisente.top.widget.TabBottomNavigation.iterator.TabListIterator;

/**
 * Created by san on 2017/12/7.
 */

public class TabActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
//        TabBottomNavigation navigation = (TabBottomNavigation) findViewById(R.id.bottomNav);
//
//
//        ListIterator listIterator = new ListIterator();
//        listIterator.addItem( new BottomTabItemImpl(new BottomTabItemImpl.Builder(this).resIcon(R.drawable.main_tab_item).text("text1")));
//        listIterator.addItem( new BottomTabItemImpl(new BottomTabItemImpl.Builder(this).resIcon(R.drawable.main_tab_item).text("text2")));
//        listIterator.addItem( new BottomTabItemImpl(new BottomTabItemImpl.Builder(this).resIcon(R.drawable.main_tab_item).text("text3")));
//        listIterator.addItem( new BottomTabItemImpl(new BottomTabItemImpl.Builder(this).resIcon(R.drawable.main_tab_item).text("text4")));
////        navigation.addView(listIterator);.
//        navigation.addTabItem(listIterator);

        TabBottomNavigation navigation = (TabBottomNavigation) findViewById(R.id.bottomNav);
        TabListIterator<TabViewImpl> tabViewTabListIterator = new TabListIterator<>();
        tabViewTabListIterator.addItem(new TabViewImpl.Builder(this).resIcon(R.drawable.main_tab_item).text("text1").create());
        tabViewTabListIterator.addItem(new TabViewImpl.Builder(this).resIcon(R.drawable.main_tab_item).text("text2").create());
        tabViewTabListIterator.addItem(new TabViewImpl.Builder(this).resIcon(R.drawable.main_tab_item).text("text3").create());
        tabViewTabListIterator.addItem(new TabViewImpl.Builder(this).resIcon(R.drawable.main_tab_item).text("text4").create());
        navigation.addTabItem(tabViewTabListIterator);

    }
}
