package example.weisente.top.framelibrary.db;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import example.weisente.top.framelibrary.db.crud.QuerySupport;

/**
 * Created by san on 2017/11/7.
 */

public interface IDaoSupport<T> {


    void init(SQLiteDatabase sqLiteDatabase, Class<T> clazz);
    // 插入数据
    public long insert(T t);

    // 批量插入  检测性能
    public void insert(List<T> datas);

    // 获取专门查询的支持类
    QuerySupport<T> querySupport();

    // 按照语句查询



    int delete(String whereClause, String... whereArgs);

    int update(T obj, String whereClause, String... whereArgs);
}
