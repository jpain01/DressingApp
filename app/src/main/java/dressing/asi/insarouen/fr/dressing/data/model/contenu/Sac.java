package dressing.asi.insarouen.fr.dressing.data.model.contenu;

import dressing.asi.insarouen.fr.dressing.data.model.Contenu;
import dressing.asi.insarouen.fr.dressing.elements.Couleur;
import dressing.asi.insarouen.fr.dressing.elements.sac.TypeSac;

/**
 * Created by julie on 22/10/16.
 */

public class Sac extends Contenu {
    private TypeSac typeS;


    public Sac(Couleur couleur, String image,  int idDressing, int idObjet, TypeSac typeS) {
        super(couleur, image,  idDressing, idObjet);
        this.typeS = typeS;
    }

    public TypeSac getTypeS() {
        return typeS;
    }

    public void setTypeS(TypeSac typeS) {
        this.typeS = typeS;
    }

    @Override
    public String toString() {
        return "Sac:\n\t" + "\n\tType : " + typeS + "\n\tCouleur : " + getCouleur() + "\n\tidDressing : " + getIdDressing() + "\n\tidObjet : " + getIdObjet() + "\n\timage :" + getImage();
    }
}
