package dressing.asi.insarouen.fr.dressing.data;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import dressing.asi.insarouen.fr.dressing.data.dao.UtilisateurDAO;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.ChaussuresDAO;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.SacDAO;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.vetement.AutreDAO;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.vetement.HautDAO;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.vetement.PantalonDAO;


/**
 * Created by julie on 20/10/16.
 */

public class DBHelper extends SQLiteOpenHelper{

    private final Context mycontext;
    private static DBHelper sInstance;

    private static final int DATABASE_VERSION = 3; // l'incrément appelle onUpgrade(), décrément => onDowngrade()
    private String DATABASE_PATH; // chemin défini dans le constructeur
    private static final String DATABASE_NAME = "dressing.db";

    public static synchronized DBHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DBHelper(context);
        }
        return sInstance;
    }

    /**
     * DBHelper permet de créer une instance de la classe DBHelper avec le contexte de l'application, le nom de la base de données ainsi que sa version
     *
     * @param context contexte de l'application
     */
    private DBHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mycontext=context;
        String filesDir = context.getFilesDir().getPath(); // /data/data/dressing.asi.insarouen.fr.dressing/files/
        DATABASE_PATH = filesDir.substring(0, filesDir.lastIndexOf("/")) + "/databases/"; // /data/data/dressing.asi.insarouen.fr.dressing/databases/

        // Si la bdd n'existe pas dans le dossier de l'app
        if (!checkdatabase()) {
            // copy db de 'assets' vers DATABASE_PATH
            copydatabase();
        }
    }

    private boolean checkdatabase() {
        // retourne true/false si la bdd existe dans le dossier de l'app
        File dbfile = new File(DATABASE_PATH + DATABASE_NAME);
        return dbfile.exists();
    }

    // On copie la base de "assets" vers "/data/data/com.package.nom/databases"
    // ceci est fait au premier lancement de l'application
    private void copydatabase() {

        final String outFileName = DATABASE_PATH + DATABASE_NAME;

        InputStream myInput;
        try {
            // Ouvre la bdd de 'assets' en lecture
            myInput = mycontext.getAssets().open(DATABASE_NAME);

            // dossier de destination
            File pathFile = new File(DATABASE_PATH);
            if(!pathFile.exists()) {
                if(!pathFile.mkdirs()) {
                    Toast.makeText(mycontext, "Erreur : copydatabase(), pathFile.mkdirs()", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            // Ouverture en écriture du fichier bdd de destination
            OutputStream myOutput = new FileOutputStream(outFileName);

            // transfert de inputfile vers outputfile
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }

            // Fermeture
            myOutput.flush();
            myOutput.close();
            myInput.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(mycontext, "Erreur : copydatabase()", Toast.LENGTH_SHORT).show();
        }

        // on greffe le numéro de version
        try{
            SQLiteDatabase checkdb = SQLiteDatabase.openDatabase(DATABASE_PATH + DATABASE_NAME, null, SQLiteDatabase.OPEN_READWRITE);
            checkdb.setVersion(DATABASE_VERSION);
        }
        catch(SQLiteException e) {
            // bdd n'existe pas
        }

    } // copydatabase()



    /**
     * onCreate permet de créer la base de données lors du lancement de l'application
     *
     * @param db objet de type SQLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    /**
     * onUpgrade est appelée lorsque le numéro de version de la base de données a changé
     *
     * @param db base de données
     * @param oldVersion numéro de l'ancienne version
     * @param newVersion numéro de la nouvelle version
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion){
            //Log.d("debug", "onUpgrade() : oldVersion=" + oldVersion + ",newVersion=" + newVersion);
            mycontext.deleteDatabase(DATABASE_NAME);
            copydatabase();
        }
    }
}
