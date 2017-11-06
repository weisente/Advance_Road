package example.weisente.top.framelibrary.db;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by san on 2017/11/6.
 */

public interface IDaoSoupport<T> {

    void init(SQLiteDatabase sqLiteDatabase, Class<T> clazz);

    // 插入数据
    public long insert(T t);

    // 批量插入  检测性能
    public void insert(List<T> datas);

    // 查询所有
    public List<T> query();

    // 按照语句查询



}
