package dressing.asi.insarouen.fr.dressing.elements.vetement.haut;

/**
 * Created by julie on 22/10/16.
 */

public enum CoupeHaut {
    Cintre, Droit, Large;

    public static CoupeHaut get(String saisie){
        for(CoupeHaut cv :  values()){
            if( saisie.equals(cv.toString()))
                return cv;
        }
        return null;
    }

    public static CoupeHaut getfromInt(int numero){
        for(CoupeHaut c :  values()){
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
