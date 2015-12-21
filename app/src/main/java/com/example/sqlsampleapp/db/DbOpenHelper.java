package com.example.sqlsampleapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sqlsampleapp.db.table.BillTable;
import com.example.sqlsampleapp.db.table.BillUserOwesTable;
import com.example.sqlsampleapp.db.table.BillUserPaidTable;
import com.example.sqlsampleapp.db.table.GroupTable;
import com.example.sqlsampleapp.db.table.GroupUserTable;
import com.example.sqlsampleapp.db.table.NoteTable;
import com.example.sqlsampleapp.db.table.UserTable;

/**
 * Created by frank on 15-12-2015.
 */
public class DbOpenHelper extends SQLiteOpenHelper {

    public final static String DB_NAME = "sample.db";

    private final static int VERSION = 1;

    public DbOpenHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BillTable.CREATE);
        db.execSQL(BillUserPaidTable.CREATE);
        db.execSQL(BillUserOwesTable.CREATE);
        db.execSQL(GroupTable.CREATE);
        db.execSQL(GroupUserTable.CREATE);
        db.execSQL(NoteTable.CREATE);
        db.execSQL(UserTable.CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BillTable.CREATE);
        db.execSQL("DROP TABLE IF EXISTS " + BillUserPaidTable.CREATE);
        db.execSQL("DROP TABLE IF EXISTS " + BillUserOwesTable.CREATE);
        db.execSQL("DROP TABLE IF EXISTS " + GroupTable.CREATE);
        db.execSQL("DROP TABLE IF EXISTS " + GroupUserTable.CREATE);
        db.execSQL("DROP TABLE IF EXISTS " + NoteTable.CREATE);
        db.execSQL("DROP TABLE IF EXISTS " + UserTable.CREATE);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }
}
