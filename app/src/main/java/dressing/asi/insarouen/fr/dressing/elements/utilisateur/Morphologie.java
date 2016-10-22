package dressing.asi.insarouen.fr.dressing.elements.utilisateur;

/**
 * Created by julie on 22/10/16.
 */

public enum Morphologie {
    Huit, V, O, H, A, X;

    public static Morphologie get(String saisie){
        for(Morphologie s :  values()){
            if( saisie.equals(s.toString()))
                return s;
        }
        return null;
    }

    public static Morphologie getfromInt(int numero){
        for(Morphologie s :  values()){
            if( numero==(s.ordinal()+1))
                return s;
        }
        return null;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
