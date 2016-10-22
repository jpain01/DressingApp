package dressing.asi.insarouen.fr.dressing.app;

import android.app.Application;
import android.content.Context;

import dressing.asi.insarouen.fr.dressing.data.DAOBase;
import dressing.asi.insarouen.fr.dressing.data.DBHelper;

/**
 * Created by julie on 20/10/16.
 */

public class App extends Application {
    private static Context context;
    private static DBHelper dbHelper;

    @Override
    public void onCreate()
    {
        super.onCreate();
        context = this.getApplicationContext();
        dbHelper = new DBHelper();
        new DAOBase(dbHelper);

    }

    public static Context getContext(){
        return context;
    }

    public static DBHelper getDbHelper(){
        return dbHelper;
    }
}
