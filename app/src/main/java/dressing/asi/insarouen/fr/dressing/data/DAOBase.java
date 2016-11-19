package dressing.asi.insarouen.fr.dressing.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by julie on 20/10/16.
 */

public class DAOBase {

    protected SQLiteDatabase mDb = null;
    protected DBHelper mHelper = null;

    public DAOBase(Context pContext){
        this.mHelper = new DBHelper(pContext);
    }

    public SQLiteDatabase open() {
        mDb = mHelper.getWritableDatabase();
        return mDb;
    }

    public void close() {
        mDb.close();
    }

    public SQLiteDatabase getDb() {
        return mDb;
    }
}
