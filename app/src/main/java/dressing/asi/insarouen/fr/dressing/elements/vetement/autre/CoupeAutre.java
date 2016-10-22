package dressing.asi.insarouen.fr.dressing.elements.vetement.autre;

/**
 * Created by julie on 22/10/16.
 */

public enum CoupeAutre {
    Court, Long;

    public static CoupeAutre get(String saisie){
        for(CoupeAutre cv :  values()){
            if( saisie.equals(cv.toString()))
                return cv;
        }
        return null;
    }

    public static CoupeAutre getfromInt(int numero){
        for(CoupeAutre c :  values()){
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
