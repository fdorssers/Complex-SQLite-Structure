package com.example.sqlsampleapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by frank on 15-12-2015.
 */
@Module
public class DbModule {

    @Singleton
    @Provides
    SQLiteOpenHelper provideSQLiteOpenHelper(Context context) {
        return new DbOpenHelper(context);
    }
}
