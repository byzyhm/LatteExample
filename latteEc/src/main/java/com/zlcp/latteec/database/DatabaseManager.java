package com.zlcp.latteec.database;

import android.content.Context;

import com.zlcp.test.dao.DaoMaster;
import com.zlcp.test.dao.DaoSession;
import com.zlcp.test.dao.UserProfileDao;

import org.greenrobot.greendao.database.Database;

/**
 * 作者：zl_freedom
 * 时间：2019/7/26 21:27
 * Note：
 */
public class DatabaseManager {

    private DaoSession mDaoSession = null;
    private UserProfileDao mDao = null;

    private DatabaseManager() {
    }

    private static final class Holder {
        private static final DatabaseManager INSTANCE = new DatabaseManager();
    }

    public static final DatabaseManager getInstance() {
        return Holder.INSTANCE;
    }

    public void initDao(Context context) {
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context, "fast_ec.db");
        final Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
        mDao = mDaoSession.getUserProfileDao();
    }

    public final UserProfileDao getDao() {
        return mDao;
    }
}
