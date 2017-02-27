package com.sourabhkarkal.movieapp;


import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;

import io.realm.RealmConfiguration;

/**
 * Created by sourabhkarkal on 24/02/17.
 */
public class ApplicationController extends Application {

    public static final String TAG = ApplicationController.class
            .getSimpleName();
    private static ApplicationController mInstance;
    private static Context mContext;
    private RealmConfiguration localconfig = null;

    public static synchronized ApplicationController getInstance() {
        return mInstance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mContext = getApplicationContext();

    }

    public RealmConfiguration getRealmConfig() {
        //realm migration
            if (localconfig == null) {
                localconfig = new RealmConfiguration.Builder(getContext())
                        .name("LocalDB")
                        .schemaVersion(1)
                        //.migration(new RealmMigration())
                        .deleteRealmIfMigrationNeeded()
                        .build();
            }
            return localconfig;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

    }

}
