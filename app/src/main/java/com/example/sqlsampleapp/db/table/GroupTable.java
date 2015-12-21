package com.example.sqlsampleapp.db.table;

import android.content.ContentValues;

/**
 * Created by frank on 15-12-2015.
 */
public class GroupTable {

    public final static String TABLE_NAME = "user_group";

    public final static String ID = "_id";
    public final static String NAME = "name";
    public final static String BALANCE = "balance";

    public final static String CREATE = ""
            + "CREATE TABLE " + TABLE_NAME + "("
            + ID + " INTEGER NOT NULL PRIMARY KEY,"
            + NAME + " TEXT NOT NULL,"
            + BALANCE + " REAL NOT NULL"
            + ")";

    public static final class ContentValuesBuilder {
        private final ContentValues values = new ContentValues();

        public ContentValuesBuilder id(int id) {
            values.put(ID, id);
            return this;
        }

        public ContentValuesBuilder name(String name) {
            values.put(NAME, name);
            return this;
        }

        public ContentValuesBuilder balance(double balance) {
            values.put(BALANCE, balance);
            return this;
        }

        public ContentValues build() {
            return values;
        }
    }
}
