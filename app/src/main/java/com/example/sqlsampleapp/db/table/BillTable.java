package com.example.sqlsampleapp.db.table;

import android.content.ContentValues;

/**
 * Created by frank on 15-12-2015.
 */
public class BillTable {
    public final static String TABLE_NAME = "bill";

    public final static String ID = "_id";
    public final static String ID_GROUP = "id_group";
    public final static String TITLE = "title";
    public final static String AMOUNT = "amount";
    public final static String DATE = "date";
    public final static String CREATED_BY = "created_by";

    public final static String CREATE = ""
            + "CREATE TABLE " + TABLE_NAME + "("
            + ID + " INTEGER NOT NULL PRIMARY KEY,"
            + ID_GROUP + " INTEGER NOT NULL,"
            + TITLE + " TEXT NOT NULL,"
            + AMOUNT + " REAL NOT NULL,"
            + DATE + " TEXT NOT NULL,"
            + CREATED_BY + " INTEGER NOT NULL,"
            + "FOREIGN KEY(" + ID_GROUP + ") REFERENCES " + GroupTable.TABLE_NAME + "(" + GroupTable.ID + ")"
            + ")";

    public static final class ContentValuesBuilder {
        private final ContentValues values = new ContentValues();

        public ContentValuesBuilder id(int id) {
            values.put(ID, id);
            return this;
        }

        public ContentValuesBuilder idGroup(int idGroup) {
            values.put(ID_GROUP, idGroup);
            return this;
        }

        public ContentValuesBuilder title(String title) {
            values.put(TITLE, title);
            return this;
        }

        public ContentValuesBuilder amount(double amount) {
            values.put(AMOUNT, amount);
            return this;
        }

        public ContentValuesBuilder date(String date) {
            values.put(DATE, date);
            return this;
        }

        public ContentValuesBuilder createdBy(int createdBy) {
            values.put(CREATED_BY, createdBy);
            return this;
        }

        public ContentValues build() {
            return values;
        }
    }
}
