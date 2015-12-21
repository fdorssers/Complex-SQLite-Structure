package com.example.sqlsampleapp.db.table;

import android.content.ContentValues;

/**
 * Created by frank on 15-12-2015.
 */
public class BillUserOwesTable {
    public final static String TABLE_NAME = "bill_user_owes";

    public final static String ID = "_id";
    public final static String ID_BILL = "id_bill";
    public final static String ID_USER = "id_user";
    public final static String OWES = "owes";

    public final static String CREATE = ""
            + "CREATE TABLE " + TABLE_NAME + "("
            + ID + " INTEGER NOT NULL PRIMARY KEY,"
            + ID_BILL + " INTEGER NOT NULL,"
            + ID_USER + " INTEGER NOT NULL,"
            + OWES + " REAL NOT NULL,"
            + "FOREIGN KEY(" + ID_BILL + ") REFERENCES " + BillTable.TABLE_NAME + "(" + BillTable.ID + "),"
            + "FOREIGN KEY(" + ID_USER + ") REFERENCES " + UserTable.TABLE_NAME + "(" + UserTable.ID + ")"
            + ")";

    public static final class ContentValuesBuilder {
        private final ContentValues values = new ContentValues();

        public ContentValuesBuilder id(int id) {
            values.put(ID, id);
            return this;
        }

        public ContentValuesBuilder idBill(int idBill) {
            values.put(ID_BILL, idBill);
            return this;
        }

        public ContentValuesBuilder idUser(int idUser) {
            values.put(ID_USER, idUser);
            return this;
        }

        public ContentValuesBuilder owes(double owes) {
            values.put(OWES, owes);
            return this;
        }

        public ContentValues build() {
            return values;
        }
    }
}
