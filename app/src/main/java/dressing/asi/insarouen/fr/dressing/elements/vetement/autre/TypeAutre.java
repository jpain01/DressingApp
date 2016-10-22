package dressing.asi.insarouen.fr.dressing.elements.vetement.autre;

/**
 * Created by julie on 22/10/16.
 */

public enum TypeAutre {

    Jupe("Jupe"),
    Short("Short"),
    Robe("Robe"),
    Combinaison("Combinaison");

    private String chaine;

    TypeAutre(String chaine){
        this.chaine = chaine;
    }

    public static TypeAutre get(String saisie){
        for(TypeAutre n :  values()){
            if( saisie.equals(n.toString()))
                return n;
        }
        return null;
    }

    public static TypeAutre getfromInt(int numero){
        for(TypeAutre n :  values()){
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
