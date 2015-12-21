package com.example.sqlsampleapp.db.table;

import android.content.ContentValues;

/**
 * Created by frank on 15-12-2015.
 */
public class GroupUserTable {
    public final static String TABLE_NAME = "group_user";

    public final static String ID = "_id";
    public final static String ID_GROUP = "id_group";
    public final static String ID_USER = "id_user";
    public final static String BALANCE = "balance";

    public final static String CREATE = ""
            + "CREATE TABLE " + TABLE_NAME + "("
            + ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
            + ID_GROUP + " INTEGER NOT NULL,"
            + ID_USER + " INTEGER NOT NULL,"
            + BALANCE + " REAL NOT NULL,"
            + "FOREIGN KEY(" + ID_GROUP + ") REFERENCES " + GroupTable.TABLE_NAME + "(" + GroupTable.ID + "),"
            + "FOREIGN KEY(" + ID_USER + ") REFERENCES " + UserTable.TABLE_NAME + "(" + UserTable.ID + ")"
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

        public ContentValuesBuilder idUser(int idUser) {
            values.put(ID_USER, idUser);
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
