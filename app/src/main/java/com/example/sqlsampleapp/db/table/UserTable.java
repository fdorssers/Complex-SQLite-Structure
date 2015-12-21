package com.example.sqlsampleapp.db.table;

import android.content.ContentValues;

/**
 * Created by frank on 15-12-2015.
 */
public class UserTable {
    public final static String TABLE_NAME = "user";

    public final static String ID = "_id";
    public final static String NAME = "name";
    public final static String EMAIL = "email";
    public final static String MEMBER_SINCE = "member_since";
    public final static String AVATAR = "avatar";

    public final static String CREATE = ""
            + "CREATE TABLE " + TABLE_NAME + "("
            + ID + " INTEGER NOT NULL PRIMARY KEY,"
            + NAME + " TEXT NOT NULL,"
            + EMAIL + " TEXT NOT NULL,"
            + MEMBER_SINCE + " TEXT NOT NULL,"
            + AVATAR + " TEXT"
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

        public ContentValuesBuilder email(String email) {
            values.put(EMAIL, email);
            return this;
        }

        public ContentValuesBuilder memberSince(String memberSince) {
            values.put(MEMBER_SINCE, memberSince);
            return this;
        }

        public ContentValuesBuilder avatar(String avatar) {
            values.put(AVATAR, avatar);
            return this;
        }

        public ContentValues build() {
            return values;
        }
    }
}
