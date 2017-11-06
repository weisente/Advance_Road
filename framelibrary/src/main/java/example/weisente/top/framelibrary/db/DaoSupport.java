package example.weisente.top.framelibrary.db;

import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by san on 2017/11/6.
 *
 * 主要实现增删改查
 */

public class DaoSupport<T> implements IDaoSoupport<T>  {

    // SQLiteDatabase
    private SQLiteDatabase mSqLiteDatabase;
    // 泛型类
    private Class<T> mClazz;

    private String TAG = "DaoSupport";

    private static final Object[] mPutMethodArgs = new Object[2];


    @Override
    public void init(SQLiteDatabase sqLiteDatabase, Class<T> clazz) {
        this.mSqLiteDatabase = sqLiteDatabase;
        this.mClazz = clazz;

        StringBuffer sb = new StringBuffer();
        sb.append("create table if not exists ")
                .append(DaoUtil.getTableName(mClazz))
                .append("(id integer primary key autoincrement, ");

        Field[] fields = mClazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);// 设置权限
            String name = field.getName();
            String type = field.getType().getSimpleName();// int String boolean
            //  type需要进行转换 int --> integer, String text;
            sb.append(name).append(DaoUtil.getColumnType(type)).append(", ");
        }

    }

    @Override
    public long insert(T t) {
        return 0;
    }

    @Override
    public void insert(List<T> datas) {

    }

    @Override
    public List<T> query() {
        return null;
    }
}
