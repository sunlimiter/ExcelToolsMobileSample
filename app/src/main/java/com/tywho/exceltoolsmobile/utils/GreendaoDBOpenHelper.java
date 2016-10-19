package com.tywho.exceltoolsmobile.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.tywho.exceltoolsmobile.db.DaoMaster;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * http://www.cgw360.com
 *
 * @author：litianyi
 * @create：2016-10-18 11:14
 */
public class GreendaoDBOpenHelper extends DaoMaster.DevOpenHelper {

    private static final String TAG = "GreendaoDBOpenHelper";

    public GreendaoDBOpenHelper(Context context, String dbName) {
        super(context, dbName, null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, String.format("数据库升级 oldVersion=%s,newVersion=%s", oldVersion, newVersion));
//        StandardDatabase database=new StandardDatabase(db);
//        MigrationHelper.migrate(database, AppInfoDao.class);
//        MigrationHelper.migrate(database, PlateImgInfoDao.class);
    }


    private Context context;

    private SQLiteDatabase sqliteDatabase;

    private static String DB_PATH;

    private static String DB_NAME;

    public GreendaoDBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
        this.context = context;
        this.DB_NAME = name;
        if (android.os.Build.VERSION.SDK_INT >= 17) {
            this.DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        } else {
            this.DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }
        try {
            createDataBase();
        } catch (Exception ioe) {
            throw new Error("Unable to create database");
        }
    }


    /**
     * Open Database for Use
     */
    public void openDatabase() {
        String databasePath = DB_PATH + DB_NAME;
        sqliteDatabase = SQLiteDatabase.openDatabase(databasePath, null, (SQLiteDatabase.OPEN_READWRITE));
    }

    /**
     * Close Database after use
     */
    @Override
    public synchronized void close() {
        if ((sqliteDatabase != null) && sqliteDatabase.isOpen()) {
            sqliteDatabase.close();
        }
        super.close();
    }

    /**
     * Get database instance for use
     */
    public SQLiteDatabase getSqliteDatabase() {
        return sqliteDatabase;
    }

    /**
     * Create new database if not present
     */
    public void createDataBase() {
        SQLiteDatabase sqliteDatabase = null;

        if (databaseExists()) {
            /* Check for Upgrade */
        } else {
            /* Database does not exists create blank database */
            sqliteDatabase = this.getReadableDatabase();
            sqliteDatabase.close();

            copyDataBase();
        }
    }

    /**
     * Check Database if it exists
     */
    private boolean databaseExists() {
        SQLiteDatabase sqliteDatabase = null;
        try {
            String databasePath = DB_PATH + DB_NAME;
            sqliteDatabase = SQLiteDatabase.openDatabase(databasePath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (Exception e) {
        }

        if (sqliteDatabase != null) {
            sqliteDatabase.close();
        }
        return sqliteDatabase != null ? true : false;
    }

    /**
     * Copy existing database file in system
     */
    public void copyDataBase() {

        int length;
        byte[] buffer = new byte[1024];
        String databasePath = DB_PATH + DB_NAME;

        try {
            InputStream databaseInputFile = this.context.getAssets().open(DB_NAME);
            OutputStream databaseOutputFile = new FileOutputStream(databasePath);

            while ((length = databaseInputFile.read(buffer)) > 0) {
                databaseOutputFile.write(buffer, 0, length);
                databaseOutputFile.flush();
            }
            databaseInputFile.close();
            databaseOutputFile.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}