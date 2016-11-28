package dressing.asi.insarouen.fr.dressing.data.model.contenu;

import dressing.asi.insarouen.fr.dressing.data.model.Contenu;
import dressing.asi.insarouen.fr.dressing.elements.Couleur;
import dressing.asi.insarouen.fr.dressing.elements.sac.TypeSac;

/**
 * Created by julie on 22/10/16.
 */

public class Sac extends Contenu {
    private TypeSac typeS;


    public Sac(Couleur couleur, String image,  int idDressing, TypeSac typeS) {
        super(couleur, image,  idDressing);
        this.typeS = typeS;
    }

    public TypeSac getTypeS() {
        return typeS;
    }

    public void setTypeS(TypeSac typeS) {
        this.typeS = typeS;
    }
}
