package dressing.asi.insarouen.fr.dressing.elements.vetement.pantalon;

/**
 * Created by julie on 22/10/16.
 */

public enum TypePantalon {

    Pantalon("Pantalon"),
    Pantacourt("Pantacourt"),
    Jogging("Jogging");

    private String chaine;

    TypePantalon(String chaine){
        this.chaine = chaine;
    }

    public static TypePantalon get(String saisie){
        for(TypePantalon n :  values()){
            if( saisie.equals(n.toString()))
                return n;
        }
        return null;
    }

    public static TypePantalon getfromInt(int numero){
        for(TypePantalon n :  values()){
            if( numero==(n.ordinal()+1))
                return n;
        }
        return null;
    }

    public String getNom() {
        return chaine;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
