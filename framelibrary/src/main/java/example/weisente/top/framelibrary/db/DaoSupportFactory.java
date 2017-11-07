package example.weisente.top.framelibrary.db;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;

/**
 * Created by san on 2017/11/7.
 */

public class DaoSupportFactory {
    private static DaoSupportFactory mFactory;

    // 持有外部数据库的引用
    private SQLiteDatabase mSqLiteDatabase;

    private DaoSupportFactory(){
        // 把数据库放到内存卡里面  判断是否有存储卡 6.0要动态申请权限
        File dbRoot = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath() + File.separator + "nhdz" + File.separator + "database");
        //建立一系列的文件
        if (!dbRoot.exists()) {
            dbRoot.mkdirs();
        }
        File dbFile = new File(dbRoot, "nhdz.db");
        // 打开或者创建一个数据库
        mSqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(dbFile, null);
    }

    public static DaoSupportFactory getFactory() {
        if (mFactory == null) {
            synchronized (DaoSupportFactory.class) {
                if (mFactory == null) {
                    mFactory = new DaoSupportFactory();
                }
            }
        }
        return mFactory;
    }
    public <T> IDaoSupport<T> getDao(Class<T> clazz) {
        IDaoSupport<T> daoSoupport = new DaoSupport();
        daoSoupport.init(mSqLiteDatabase,clazz);
        return daoSoupport;
    }

}
