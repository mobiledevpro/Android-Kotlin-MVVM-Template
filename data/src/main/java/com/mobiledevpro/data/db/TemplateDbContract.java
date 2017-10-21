package com.mobiledevpro.data.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import com.mobiledevpro.data.Constants;
import com.mobiledevpro.data.model.TemplateModel;

/**
 * Template contract for sqlite db
 * <p>
 * Created by Dmitriy V. Chernysh
 * dmitriy.chernysh@gmail.com
 * <p>
 * https://fb.me/mobiledevpro/
 * <p>
 * #MobileDevPro
 */

class TemplateDbContract {

    private TemplateDbContract() {
    }

    static void onCreate(SQLiteDatabase db) {
        db.execSQL(Table.SQL_CREATE_TABLE);
    }

    static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //do something when DB upgraded (add/delete columns, etc)
    }

    /***
     * Query all
     *
     * @param db         SQLiteDatabase
     * @return Cursor
     */
    static Cursor queryAll(SQLiteDatabase db) {
        return db.query(
                Table.TABLE_NAME,
                Table.QUERY_PROJECTION,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    /***
     * Query by ID
     *
     * @param db         SQLiteDatabase
     * @param id    id
     * @return Cursor
     */
    static Cursor query(SQLiteDatabase db, int id) {
        return db.query(
                Table.TABLE_NAME,
                Table.QUERY_PROJECTION,
                "CAST (" + Table._ID + " as TEXT) =?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null
        );
    }

    /**
     * Insert
     *
     * @param db     SQLiteDatabase
     * @param object Object
     * @return True - inserted successfully
     */
    static boolean insert(SQLiteDatabase db, TemplateModel object) {
        if (object == null) return false;
        long insertedRowId = -1;

        ContentValues cv = new ContentValues();
        cv.put(Table.COLUMN_VALUE, object.getValue());

        db.beginTransaction();
        try {
            insertedRowId = db.insert(
                    Table.TABLE_NAME,
                    null,
                    cv
            );
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(Constants.LOG_TAG_ERROR, "insert: EXCEPTION - " + e.getLocalizedMessage(), e);
        } finally {
            db.endTransaction();
        }

        return insertedRowId > -1;
    }

    /**
     * Update
     *
     * @param db     SQLiteDatabase
     * @param object Object
     * @return True - updated successfully
     */
    static boolean update(SQLiteDatabase db, TemplateModel object) {
        if (object == null) return false;
        int updatedRows = 0;

        ContentValues cv = new ContentValues();
        cv.put(Table.COLUMN_VALUE, object.getValue());


        db.beginTransaction();
        try {
            updatedRows = db.update(
                    Table.TABLE_NAME,
                    cv,
                    "CAST (" + Table._ID + " as TEXT) = ?",
                    new String[]{String.valueOf(object.getId())}
            );
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(Constants.LOG_TAG_ERROR, "update: EXCEPTION - " + e.getLocalizedMessage(), e);
            updatedRows = 0;
        } finally {
            db.endTransaction();
        }

        return updatedRows > 0;
    }

    /**
     * Delete
     *
     * @param db     SQLiteDatabase
     * @param object Object
     */
    static boolean delete(SQLiteDatabase db, TemplateModel object) {
        long result = 0;
        db.beginTransaction();
        try {
            result = db.delete(
                    Table.TABLE_NAME,
                    "CAST (" + Table._ID + " as TEXT) =?",
                    new String[]{String.valueOf(object.getId())}
            );

            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(Constants.LOG_TAG_ERROR, "delete: exception - " + e.getMessage(), e);
        } finally {
            db.endTransaction();
        }

        return result > 0;
    }

    /**
     * Check if customer exists by id
     *
     * @param db SQLiteDatabase
     * @return True - exists
     */
    static boolean isEntryExists(SQLiteDatabase db, TemplateModel object) {
        Cursor cursor;
        int rowCount = 0;

        cursor = db.query(
                Table.TABLE_NAME,
                new String[]{Table._ID},
                "CAST (" + Table._ID + " as TEXT) = ?",
                new String[]{String.valueOf(object.getId())},
                null,
                null,
                null,
                null
        );

        try {
            rowCount = cursor.getCount();
        } catch (Exception e) {
            Log.e(Constants.LOG_TAG_ERROR, "isEntryExists: EXCEPTION - " + e.getMessage(), e);
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return rowCount > 0;
    }

    /**
     * Convert cursor data to object
     *
     * @param cursor Cursor
     * @return Object
     */
    static TemplateModel getObjectFromCursor(Cursor cursor) {
        if (cursor == null) return null;
        return new TemplateModel(
                cursor.getInt(cursor.getColumnIndex(Table._ID)),
                cursor.getString(cursor.getColumnIndex(Table.COLUMN_VALUE))
        );
    }


    private static class Table implements BaseColumns {
        private static final String TABLE_NAME = "template_table";
        private static final String COLUMN_VALUE = "value";

        //table create sql
        private static final String SQL_CREATE_TABLE = "create table IF NOT EXISTS "
                + TABLE_NAME
                + "("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_VALUE + " TEXT"
                //another columns here
                + ");";

        private static final String[] QUERY_PROJECTION = {
                _ID,
                COLUMN_VALUE
                //another columns here
        };

    }
}
