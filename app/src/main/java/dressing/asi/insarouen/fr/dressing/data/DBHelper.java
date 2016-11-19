package dressing.asi.insarouen.fr.dressing.data;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
    // creation de la table MATIERE_SAISON
    private static String MATIERE_SAISON = "CREATE TABLE MATIERE_SAISON (" +
            "matiere VARCHAR(30) PRIMARY KEY CHECK (matiere IN ('Laine','Coton','Jean','Lin','Velours','Cuir','Dentelle','Daim', 'Satin','Paillete'))," +
            "saison VARCHAR(30) CHECK (saison IN ('Toutes','Automne/Hiver','Printemps/Ete'))" +
            ");";
    // suppression de la table MATIERE_SAISON
    private static String DROP_MATIERE_SAISON = "DROP TABLE MATIERE_SAISON;";

    // creation de la table FORME
    private static String FORME = "CREATE TABLE FORME (" +
            "signe VARCHAR(5) PRIMARY KEY CHECK (signe IN ('Huit','V','O','A','H','X'))" +
            ");";
    // suppression de la table FORME
    private static String DROP_FORME = "DROP TABLE FORME;";

    // creation de la table DRESSING
    private static String DRESSING = "CREATE TABLE DRESSING (" +
            "idDressing PRIMARY KEY AUTOINCREMENT," +
            "idPers INTEGER REFERENCES PERSONNE(id)" +
            ");";
    // suppression de la table DRESSING
    private static String DROP_DRESSING = "DROP TABLE DRESSING;";

    // creation de la table CORRESPOND pour PANTALON
    private static String CORRESPOND_PANTALON = "CREATE TABLE CORRESPOND_PANTALON (" +
            "idObjet INTEGER," +
            "signe VARCHAR(5) REFERENCES PANTALON(signe) ON DELETE CASCADE," +
            "PRIMARY KEY (idObjet,signe)" +
            ");";
    // creation de la table CORRESPOND pour AUTRE
    private static String CORRESPOND_AUTRE = "CREATE TABLE CORRESPOND_AUTRE (" +
            "idObjet INTEGER," +
            "signe VARCHAR(5) REFERENCES AUTRE(signe) ON DELETE CASCADE," +
            "PRIMARY KEY (idObjet,signe)" +
            ");";
    // creation de la table CORRESPOND pour HAUT
    private static String CORRESPOND_HAUT = "CREATE TABLE CORRESPOND_HAUT (" +
            "idObjet INTEGER," +
            "signe VARCHAR(5) REFERENCES HAUT(signe) ON DELETE CASCADE," +
            "PRIMARY KEY (idObjet,signe)" +
            ");";
    // suppresion des tables CORRSEPOND
    private static String DROP_CORRESPOND = "DROP TABLE CORRESPOND_PANTALON;"+
            "DROP TABLE CORRESPOND_HAUT;"+
            "DROP TABLE CORRESPOND_AUTRE;";

    // TRIGGERS

    // Lorsque l'on insert une personne, le dressing correspondant à cette personne doit être automatiquement créé.
    private static String TRIGGER_ATTRIBUER_DRESSING = "CREATE TRIGGER  attribuerDressing" +
            "AFTER INSERT ON PERSONNE" +
            "BEGIN " +
            "INSERT INTO DRESSING(idPers) VALUES (NEW.idPers);" +
            "RETURN NEW;" +
            "END ;";
    private static  String DROP_ATTRIBUER_DRESSING = "DROP TRIGGER attribuerDressing;";
    // Lorsqu'on supprime une personne, le dressing correspondant à cette personne doit être supprimé.
    private static String TRIGGER_SUPPRIMER_DRESSING = "CREATE TRIGGER  supprimerDressingPersonne" +
            "BEFORE DELETE ON PERSONNE" +
            "BEGIN " +
            "DELETE FROM DRESSING d WHERE d.idPers=OLD.idPers;" +
            "RETURN OLD;" +
            "END;";
    private static String DROP_SUPPRIMER_DRESSING = "DROP TRIGGER supprimerDressingPersonne;";
    // Lorsqu'on supprime un dressing, le contenu correspondant à ce dressing doit être supprimé.
    private static String TRIGGER_SUPPRIMER_CONTENU = "CREATE TRIGGER  supprimerContenuDressing" +
            "BEFORE DELETE ON DRESSING" +
            "BEGIN " +
            "DELETE FROM SAC s, PANTALON p, AUTRE a, HAUT h, CHAUSSURE c WHERE c.idDressing=OLD.idDressing OR WHERE p.idDressing=OLD.idDressing OR WHERE h.idDressing=OLD.idDressing OR WHERE a.idDressing=OLD.idDressing OR WHERE s.idDressing=OLD.idDressing;" +
            "RETURN OLD;" +
            "END;";
    private static  String DROP_SUPPRIMER_CONTENU = "DROP TRIGGER supprimerContenuDressing;";
    // Permet d'attribuer une couche (1 ou 2) au vêtement en fonction du type du vetement.
    private static String TRIGGER_COUCHE_HAUT = "CREATE TRIGGER  attribuerCoucheH" +
            "BEFORE INSERT ON HAUT " +
            "BEGIN" +
            "CASE NEW.typeH" +
            "WHEN 'Veste' THEN NEW.couche=2;" +
            "WHEN 'Manteau' THEN NEW.couche=2;" +
            "ELSE NEW.couche=1;" +
            "END CASE;" +
            "RETURN NEW;" +
            "END;";
    private static String DROP_COUCHE_HAUT = "DROP TRIGGER attribuerCoucheH;";
    private static String TRIGGER_COUCHE_PANTALON = "CREATE TRIGGER  attribuerCoucheP" +
            "BEFORE INSERT ON PANTALON" +
            "BEGIN " +
            "NEW.couche=1;" +
            "RETURN NEW;" +
            "END;";
    private static String DROP_COUCHE_PANTALON = "DROP TRIGGER attribuerCoucheP;";
    private static String TRIGGER_COUCHE_AUTRE = "CREATE TRIGGER  attribuerCoucheA" +
            "BEFORE INSERT ON AUTRE" +
            "BEGIN " +
            "NEW.couche=1;" +
            "RETURN NEW;" +
            "END;";
    private static String DROP_COUCHE_AUTRE = "DROP TRIGGER attribuerCoucheA;";
    // Permet d'attribuer un niveau (Haut, Bas, Hautbas) à un vêtement
    private static String TRIGGER_NIVEAU_HAUT = "CREATE TRIGGER  attribuerNiveauH" +
            "BEFORE INSERT ON HAUT " +
            "BEGIN " +
            "NEW.niveau='Haut';" +
            "RETURN NEW;" +
            "END;";
    private static String DROP_NIVEAU_HAUT = "DROP TRIGGER attribuerNiveauH;";
    private static String TRIGGER_NIVEAU_PANTALON = "CREATE TRIGGER  attribuerNiveauP" +
            "BEFORE INSERT ON PANTALON " +
            "BEGIN " +
            "NEW.niveau='Bas';" +
            "RETURN NEW;" +
            "END;";
    private static String DROP_NIVEAU_PANTALON = "DROP TRIGGER attribuerNiveauP;";
    private static  String TRIGGER_NIVEAU_AUTRE = "CREATE TRIGGER  attribuerNiveauA" +
            "BEFORE INSERT ON AUTRE" +
            "BEGIN " +
            "CASE NEW.typeA" +
            "WHEN 'Combinaison' THEN NEW.niveau='Hautbas';" +
            "WHEN 'Robe' THEN NEW.niveau='Hautbas';" +
            "ELSE NEW.niveau='Bas';" +
            "END CASE;" +
            "RETURN NEW;" +
            "END;";
    private static String DROP_NIVEAU_AUTRE = "DROP TRIGGER attribuerNiveauA;";
    // Permet d'attribuer une saison pour chaque matiere
    private static String TRIGER_SAISON_MATIERE = "CREATE TRIGGER  attribuerSaisonMatiere" +
            "BEFORE INSERT ON MATIERE_SAISON" +
            "BEGIN " +
            "CASE NEW.matiere" +
            "WHEN 'Laine' THEN NEW.saison='Automne/Hiver';" +
            "WHEN 'Velours' THEN NEW.saison='Automne/Hiver';" +
            "WHEN 'Lin' THEN NEW.saison='Printemps/Ete';" +
            "ELSE NEW.saison='Toutes';" +
            "END CASE;" +
            "RETURN NEW;" +
            "END;";
    private static String DROP_SAISON_MATIERE = "DROP TRIGGER attribuerSaisonMAtiere;";
    // Permet d'attribuer à chaque vetement les formes qui lui correspondent selon sa coupe
    private static String TRIGGER_FORME_HAUT = "CREATE TRIGGER  attribuerFormesH" +
            "AFTER INSERT ON HAUT" +
            "BEGIN " +
            "CASE NEW.coupeH " +
            "WHEN 'Cintre' THEN " +
            "INSERT INTO CORRESPOND_HAUT VALUES (NEW.idobjet,'H');" +
            "INSERT INTO CORRESPOND_HAUT VALUES (NEW.idobjet,'Huit');" +
            "INSERT INTO CORRESPOND_HAUT VALUES (NEW.idobjet,'V');" +
            "INSERT INTO CORRESPOND_HAUT VALUES (NEW.idobjet,'X');" +
            "INSERT INTO CORRESPOND_HAUT VALUES (NEW.idobjet,'A');" +
            "WHEN 'Droit' THEN " +
            "INSERT INTO CORRESPOND_HAUT VALUES (NEW.idobjet,'H');" +
            "INSERT INTO CORRESPOND_HAUT VALUES (NEW.idobjet,'O');" +
            "INSERT INTO CORRESPOND_HAUT VALUES (NEW.idobjet,'A');" +
            "ELSE " +
            "INSERT INTO CORRESPOND_HAUT VALUES (NEW.idobjet,'O');" +
            "INSERT INTO CORRESPOND_HAUT VALUES (NEW.idobjet,'Huit');" +
            "INSERT INTO CORRESPOND_HAUT VALUES (NEW.idobjet,'V');" +
            "INSERT INTO CORRESPOND_HAUT VALUES (NEW.idobjet,'X');" +
            "END CASE;" +
            "RETURN NEW;" +
            "END;";
    private static String DROP_FORME_HAUT = "DROP TRIGGER attribuerFormesH;";
    private static String TRIGGER_FORME_PANTALON = "CREATE TRIGGER  attribuerFormesP" +
            "AFTER INSERT ON PANTALON" +
            "BEGIN " +
            "CASE NEW.coupeP" +
            "WHEN 'Droit' THEN " +
            "INSERT INTO CORRESPOND_PANTALON VALUES (NEW.idobjet,'H');" +
            "INSERT INTO CORRESPOND_PANTALON VALUES (NEW.idobjet,'Huit');" +
            "INSERT INTO CORRESPOND_PANTALON VALUES (NEW.idobjet,'O');" +
            "INSERT INTO CORRESPOND_PANTALON VALUES (NEW.idobjet,'X');" +
            "INSERT INTO CORRESPOND_PANTALON VALUES (NEW.idobjet,'A');" +
            "WHEN 'Slim' THEN " +
            "INSERT INTO CORRESPOND_PANTALON VALUES (NEW.idobjet,'H');" +
            "INSERT INTO CORRESPOND_PANTALON VALUES (NEW.idobjet,'V');" +
            "INSERT INTO CORRESPOND_PANTALON VALUES (NEW.idobjet,'A');" +
            "INSERT INTO CORRESPOND_PANTALON VALUES (NEW.idobjet,'X');" +
            "WHEN 'Evase' THEN " +
            "INSERT INTO CORRESPOND_PANTALON VALUES (NEW.idobjet,'Huit');" +
            "INSERT INTO CORRESPOND_PANTALON VALUES (NEW.idobjet,'O');" +
            "ELSE " +
            "INSERT INTO CORRESPOND_PANTALON VALUES (NEW.idobjet,'O');" +
            "INSERT INTO CORRESPOND_PANTALON VALUES (NEW.idobjet,'Huit');" +
            "INSERT INTO CORRESPOND_PANTALON VALUES (NEW.idobjet,'V');" +
            "END CASE;" +
            "RETURN NEW;" +
            "END;";
    private static String DROP_FORME_PANTALON = "DROP TRIGGER attribuerFormesP;";
    private static String TRIGGER_FORME_AUTRE = "CREATE TRIGGER  attribuerFormesA" +
            "AFTER INSERT ON AUTRE" +
            "BEGIN " +
            "CASE NEW.coupeA " +
            "WHEN 'Longue' THEN " +
            "INSERT INTO CORRESPOND_AUTRE VALUES (NEW.idobjet,'H');" +
            "INSERT INTO CORRESPOND_AUTRE VALUES (NEW.idobjet,'Huit');" +
            "INSERT INTO CORRESPOND_AUTRE VALUES (NEW.idobjet,'O');" +
            "INSERT INTO CORRESPOND_AUTRE VALUES (NEW.idobjet,'X');" +
            "INSERT INTO CORRESPOND_AUTRE VALUES (NEW.idobjet,'A');" +
            "INSERT INTO CORRESPOND_AUTRE VALUES (NEW.idobjet,'V');" +
            "ELSE " +
            "INSERT INTO CORRESPOND_AUTRE VALUES (NEW.idobjet,'H');" +
            "INSERT INTO CORRESPOND_AUTRE VALUES (NEW.idobjet,'Huit');" +
            "INSERT INTO CORRESPOND_AUTRE VALUES (NEW.idobjet,'V');" +
            "END CASE;" +
            "RETURN NEW;" +
            "END;";
    private static String DROP_FORME_AUTRE = "DROP TRIGGER attribuerFormesA;";

    private static final String ENABLE_FOREIGN_KEY="PRAGMA foreign_keys=ON";
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION =2;
    // Database Name
    private static final String DATABASE_NAME = "dressing.db";
    private static final String TAG = DBHelper.class.getSimpleName();

    /**
     * DBHelper permet de créer une instance de la classe DBHelper avec le contexte de l'application, le nom de la base de données ainsi que sa version
     *
     * @param context contexte de l'application
     */
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * onCreate permet de créer la base de données lors du lancement de l'application
     *
     * @param db objet de type SQLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here
        db.execSQL(ENABLE_FOREIGN_KEY);
        db.execSQL(MATIERE_SAISON);
        db.execSQL(FORME);
        db.execSQL(UtilisateurDAO.createTable());
        db.execSQL(DRESSING);
        db.execSQL(SacDAO.createTable());
        db.execSQL(ChaussuresDAO.createTable());
        db.execSQL(HautDAO.createTable());
        db.execSQL(PantalonDAO.createTable());
        db.execSQL(AutreDAO.createTable());
        db.execSQL(CORRESPOND_PANTALON);
        db.execSQL(CORRESPOND_AUTRE);
        db.execSQL(CORRESPOND_HAUT);
        db.execSQL(TRIGGER_ATTRIBUER_DRESSING);
        db.execSQL(TRIGGER_SUPPRIMER_DRESSING);
        db.execSQL(TRIGGER_SUPPRIMER_CONTENU);
        db.execSQL(TRIGGER_COUCHE_HAUT);
        db.execSQL(TRIGGER_COUCHE_PANTALON);
        db.execSQL(TRIGGER_COUCHE_AUTRE);
        db.execSQL(TRIGGER_NIVEAU_HAUT);
        db.execSQL(TRIGGER_NIVEAU_PANTALON);
        db.execSQL(TRIGGER_NIVEAU_AUTRE);
        db.execSQL(TRIGER_SAISON_MATIERE);
        db.execSQL(TRIGGER_FORME_HAUT);
        db.execSQL(TRIGGER_FORME_PANTALON);
        db.execSQL(TRIGGER_FORME_AUTRE);
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
        Log.d(TAG, String.format("SQLiteDatabase.onUpgrade(%d -> %d)", oldVersion, newVersion));

        db.execSQL(DROP_FORME_AUTRE);
        db.execSQL(DROP_FORME_PANTALON);
        db.execSQL(DROP_FORME_HAUT);
        db.execSQL(DROP_SAISON_MATIERE);
        db.execSQL(DROP_NIVEAU_AUTRE);
        db.execSQL(DROP_NIVEAU_PANTALON);
        db.execSQL(DROP_NIVEAU_HAUT);
        db.execSQL(DROP_COUCHE_AUTRE);
        db.execSQL(DROP_COUCHE_PANTALON);
        db.execSQL(DROP_COUCHE_HAUT);
        db.execSQL(DROP_SUPPRIMER_CONTENU);
        db.execSQL(DROP_SUPPRIMER_DRESSING);
        db.execSQL(DROP_CORRESPOND);
        db.execSQL(AutreDAO.dropTable());
        db.execSQL(PantalonDAO.dropTable());
        db.execSQL(HautDAO.dropTable());
        db.execSQL(ChaussuresDAO.dropTable());
        db.execSQL(SacDAO.dropTable());
        db.execSQL(DROP_DRESSING);
        db.execSQL(UtilisateurDAO.dropTable());
        db.execSQL(DROP_FORME);
        db.execSQL(DROP_MATIERE_SAISON);
        onCreate(db);
    }
}
