package com.zlcp.latteec.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.zlcp.test.dao.DaoMaster;

import org.greenrobot.greendao.database.Database;

/**
 * 作者：zl_freedom
 * 时间：2019/7/26 19:30
 * Note：
 */
public class ReleaseOpenHelper extends DaoMaster.OpenHelper {
    public ReleaseOpenHelper(Context context, String name) {
        super(context, name);
    }

    public ReleaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onCreate(Database db) {
        super.onCreate(db);
    }
}
