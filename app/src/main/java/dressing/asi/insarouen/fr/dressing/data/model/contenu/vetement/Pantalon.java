package dressing.asi.insarouen.fr.dressing.data.model.contenu.vetement;

import dressing.asi.insarouen.fr.dressing.data.model.contenu.Vetement;
import dressing.asi.insarouen.fr.dressing.elements.Couleur;
import dressing.asi.insarouen.fr.dressing.elements.utilisateur.Morphologie;
import dressing.asi.insarouen.fr.dressing.elements.vetement.Matiere;
import dressing.asi.insarouen.fr.dressing.elements.vetement.Niveau;
import dressing.asi.insarouen.fr.dressing.elements.vetement.pantalon.CoupePantalon;
import dressing.asi.insarouen.fr.dressing.elements.vetement.pantalon.TypePantalon;

/**
 * Created by julie on 22/10/16.
 */

public class Pantalon extends Vetement {
    private TypePantalon typeP;
    private CoupePantalon coupeP;

    public Pantalon() {
    }

    public Pantalon(Couleur couleur, String image,  int idDressing, Matiere matiere,  boolean sale, TypePantalon typeP, CoupePantalon coupeP) {
        super(couleur, image, idDressing, matiere,  sale);
        this.typeP = typeP;
        this.coupeP = coupeP;
    }

    public TypePantalon getTypeP() {
        return typeP;
    }

    public void setTypeP(TypePantalon typeP) {
        this.typeP = typeP;
    }

    public CoupePantalon getCoupeP() {
        return coupeP;
    }

    public void setCoupeP(CoupePantalon coupeP) {
        this.coupeP = coupeP;
    }
}
