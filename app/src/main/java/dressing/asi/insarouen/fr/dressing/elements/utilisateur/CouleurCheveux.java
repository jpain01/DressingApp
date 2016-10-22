package dressing.asi.insarouen.fr.dressing.elements.utilisateur;

/**
 * Created by julie on 22/10/16.
 */

public enum CouleurCheveux {
    Blond, Brun, Roux, Chatain;

    public static CouleurCheveux get(String saisie){
        for( CouleurCheveux c :  values()){
            if( saisie.equals(c.toString()))
                return c;
        }
        return null;
    }

    public static CouleurCheveux getfromInt(int numero){
        for(CouleurCheveux  c :  values()){
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
