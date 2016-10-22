package dressing.asi.insarouen.fr.dressing.elements.vetement.pantalon;


/**
 * Created by julie on 22/10/16.
 */

public enum CoupePantalon {
    Slim, Droit, Evase, Baggy;

    public static CoupePantalon get(String saisie){
        for(CoupePantalon cv :  values()){
            if( saisie.equals(cv.toString()))
                return cv;
        }
        return null;
    }

    public static CoupePantalon getfromInt(int numero){
        for(CoupePantalon c :  values()){
            if( numero==(c.ordinal()+1))
                return c;
        }
        return null;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
