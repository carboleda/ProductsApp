package co.gdgcali.productosapp;

import android.app.Application;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import co.gdgcali.productosapp.database.DBHelper;

/**
 * Created by krlosf on 22/10/16.
 */

public class MyApplication extends Application {
    private static MyApplication instance;
    private DBHelper dbHelper;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public DBHelper getDBHelper() {
        if(dbHelper == null) {
            dbHelper = OpenHelperManager.getHelper(this, DBHelper.class);
        }

        return dbHelper;
    }

    public void releaseHelper() {
        if(dbHelper != null) {
            OpenHelperManager.releaseHelper();
            dbHelper = null;
        }
    }
}
