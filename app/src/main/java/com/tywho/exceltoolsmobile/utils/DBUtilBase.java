package com.tywho.exceltoolsmobile.utils;

import android.content.Context;

import com.tywho.exceltoolsmobile.db.DaoMaster;
import com.tywho.exceltoolsmobile.db.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * http://www.cgw360.com
 *
 * @author：litianyi
 * @create：2016-10-18 11:13
 */
public class DBUtilBase {
    private DaoSession daoSession = null;
    private DaoMaster daoMaster = null;
    private final static String DB_NAME = "question.db";
    private static DBUtilBase singleDB = new DBUtilBase();

    //私有默认构造子
    private DBUtilBase() {
    }

    // 静态工厂方法
    public static DBUtilBase getInstance() {
        return singleDB;
    }

    public DaoMaster getDaoMaster(Context context) {
        if (daoMaster == null) {
            GreendaoDBOpenHelper helper = new GreendaoDBOpenHelper(context, DB_NAME, null);
            Database db = helper.getWritableDb();
            daoMaster = new DaoMaster(db);
        }
        return daoMaster;
    }

    public DaoSession getDaoSession(Context context) {
        if (daoSession == null) {
            daoSession = getDaoMaster(context).newSession();
        }
        return daoSession;
    }
}
