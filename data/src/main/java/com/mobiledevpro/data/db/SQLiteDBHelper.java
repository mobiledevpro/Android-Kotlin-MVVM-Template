package com.mobiledevpro.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mobiledevpro.data.BuildConfig;

/**
 * DB helper class
 * <p>
 * Created by Dmitriy V. Chernysh
 * dmitriy.chernysh@gmail.com
 * <p>
 * https://fb.me/mobiledevpro/
 * <p>
 * #MobileDevPro
 */


public class SQLiteDBHelper extends SQLiteOpenHelper implements ISQLiteDBHelper {

    private static final String DB_NAME = BuildConfig.APPLICATION_ID + ".db";
    private static final int DB_VERSION = 1;

    private static SQLiteDBHelper sDBHelperInstance;

    private SQLiteDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * Get DBHelper instance
     *
     * @return DBHelper instance
     */
    public static synchronized SQLiteDBHelper getInstance(Context appContext) {
        if (sDBHelperInstance == null) {
            sDBHelperInstance = new SQLiteDBHelper(appContext);
        }
        return sDBHelperInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //TODO: TemplateDbContract uses only as example (remove it in you project!)
        TemplateDbContract.onCreate(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //TODO: TemplateDbContract uses only as example (remove it in you project!)
        TemplateDbContract.onUpgrade(sqLiteDatabase, oldVersion, newVersion);
    }
}
