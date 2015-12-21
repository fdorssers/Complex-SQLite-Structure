package com.example.sqlsampleapp;

import com.example.sqlsampleapp.db.DbModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by frank on 15-12-2015.
 */
@Singleton
@Component(modules = {ApplicationModule.class, DbModule.class})
public interface ApplicationComponent {
    void inject(MainActivity mainActivity);
}
