package com.example.gddemo;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.gddemo.database.greenDao.db.DaoMaster;
import com.example.gddemo.database.greenDao.db.DaoSession;

/**
 * 作者:      周来
 * 包名:      com.example.gddemo
 * 工程名:    MyMVPTest
 * 时间:      2018/11/19
 * 说明:
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "jone.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }


    private DaoSession daoSession;

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
