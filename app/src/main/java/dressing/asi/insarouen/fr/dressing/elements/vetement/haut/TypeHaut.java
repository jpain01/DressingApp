package dressing.asi.insarouen.fr.dressing.elements.vetement.haut;

/**
 * Created by julie on 22/10/16.
 */

public enum TypeHaut {
    Teeshirt("Tee-shirt"),
    Chemisier("Chemisier"),
    Pull("Pull"),
    Veste("Veste"),
    Manteau("Manteau");

    private String chaine;

    TypeHaut(String chaine){
        this.chaine = chaine;
    }

    public static TypeHaut get(String saisie){
        for(TypeHaut n :  values()){
            if( saisie.equals(n.toString()))
                return n;
        }
        return null;
    }

    public static TypeHaut getfromInt(int numero){
        for(TypeHaut n :  values()){
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
