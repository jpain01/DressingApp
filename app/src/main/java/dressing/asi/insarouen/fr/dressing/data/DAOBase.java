package dressing.asi.insarouen.fr.dressing.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by julie on 20/10/16.
 */

public class DAOBase {
    private Integer mOpenCounter = 0;

    protected SQLiteDatabase mDb = null;
    protected DBHelper mHelper = null;

    public DAOBase(DBHelper helper) {
        this.mHelper = helper;
    }

    public SQLiteDatabase open() {
        mOpenCounter+=1;
        if(mOpenCounter == 1) {
            // Opening new database
            mDb = mHelper.getWritableDatabase();
        }
        return mDb;
    }

    public void close() {
        mOpenCounter-=1;
        if(mOpenCounter == 0) {
            // Closing database
            mDb.close();
        }
    }

    public SQLiteDatabase getDb() {
        return mDb;
    }
}
