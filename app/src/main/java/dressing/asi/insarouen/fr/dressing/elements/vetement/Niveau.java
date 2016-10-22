package dressing.asi.insarouen.fr.dressing.elements.vetement;

/**
 * Created by julie on 22/10/16.
 */

public enum Niveau {
    Haut, Bas, Hautbas;

    public static Niveau get(String saisie){
        for(Niveau n :  values()){
            if( saisie.equals(n.toString()))
                return n;
        }
        return null;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
