package com.koakh.greendaostartup.app;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.koakh.greendaostartup.model.DaoMaster;
import com.koakh.greendaostartup.model.DaoSession;

/**
 * Created by mario on 31/01/2015.
 */
public class Singleton extends Application {

  //Constants
  public final static String TAG = "GreenDaoStartup";

  //Singleton
  private static Singleton mOurInstance = new Singleton();
  //Dao
  public DaoSession daoSession;

  public static Singleton getInstance() {
    return mOurInstance;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    setupDatabase();
  }

  private void setupDatabase() {
    Log.i(this.TAG, "setupDatabase");
    DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "greendaostartup-db", null);
    SQLiteDatabase db = helper.getWritableDatabase();
    DaoMaster daoMaster = new DaoMaster(db);
    daoSession = daoMaster.newSession();
  }

  public DaoSession getDaoSession() {
    return daoSession;
  }

}