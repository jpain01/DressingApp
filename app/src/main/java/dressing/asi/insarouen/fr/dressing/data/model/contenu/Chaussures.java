package dressing.asi.insarouen.fr.dressing.data.model.contenu;

import dressing.asi.insarouen.fr.dressing.data.model.Contenu;
import dressing.asi.insarouen.fr.dressing.elements.Couleur;
import dressing.asi.insarouen.fr.dressing.elements.chaussures.TypeChaussures;

/**
 * Created by julie on 22/10/16.
 */

public class Chaussures extends Contenu {
    private TypeChaussures typeC;

    public Chaussures() {
    }

    public Chaussures(Couleur couleur, String image, int idObjet, int idDressing, TypeChaussures typeC) {
        super(couleur, image, idObjet, idDressing);
        this.typeC = typeC;
    }

    public TypeChaussures getTypeC() {
        return typeC;
    }

    public void setTypeC(TypeChaussures typeC) {
        this.typeC = typeC;
    }
}
