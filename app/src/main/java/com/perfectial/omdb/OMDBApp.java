package com.perfectial.omdb;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.perfectial.omdb.components.DaggerDiComponent;
import com.perfectial.omdb.components.DiComponent;
import com.perfectial.omdb.db.DataBaseHelper;
import com.perfectial.omdb.modules.AppModule;
import com.perfectial.omdb.modules.DBModule;

/**
 * Created by rursu on 08.04.16.
 */
public class OMDBApp extends Application {

    private static OMDBApp app;
    private static DiComponent appComponent;
    private DataBaseHelper databaseHelper = null;
    private int activeActivitiesNumber = 0;

    @Override
    public void onCreate() {
        super.onCreate();

        this.app = this;

        DataBaseHelper dataBaseHelper = getHelper();
        appComponent = DaggerDiComponent.builder()
                .appModule(new AppModule(this))
                .dBModule(new DBModule(dataBaseHelper))
                .build();

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                activeActivitiesNumber++;
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                activeActivitiesNumber--;
                if (activeActivitiesNumber == 0) {
                    releaseDBHelper();
                }
            }
        });
    }

    private void releaseDBHelper() {
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }

    private DataBaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this, DataBaseHelper.class);
        }

        return databaseHelper;
    }

    public static DiComponent getAppComponent() {
        return appComponent;
    }

    public static OMDBApp getApp() {
        return app;
    }
}
