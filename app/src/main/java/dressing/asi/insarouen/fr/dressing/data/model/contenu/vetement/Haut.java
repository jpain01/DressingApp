package dressing.asi.insarouen.fr.dressing.data.model.contenu.vetement;

import dressing.asi.insarouen.fr.dressing.data.model.contenu.Vetement;
import dressing.asi.insarouen.fr.dressing.elements.Couleur;
import dressing.asi.insarouen.fr.dressing.elements.utilisateur.Morphologie;
import dressing.asi.insarouen.fr.dressing.elements.vetement.Matiere;
import dressing.asi.insarouen.fr.dressing.elements.vetement.Niveau;
import dressing.asi.insarouen.fr.dressing.elements.vetement.haut.CoupeHaut;
import dressing.asi.insarouen.fr.dressing.elements.vetement.haut.TypeHaut;

/**
 * Created by julie on 22/10/16.
 */

public class Haut extends Vetement {
    private TypeHaut typeH;
    private CoupeHaut coupeH;

    public Haut() {
    }

    public Haut(Couleur couleur, String image,  int idDressing, int idObjet, Matiere matiere,  boolean sale, TypeHaut typeH, CoupeHaut coupeH, int couche, Niveau niveau) {
        super(couleur, image,  idDressing, idObjet, matiere,  sale, couche, niveau);
        this.typeH = typeH;
        this.coupeH = coupeH;
    }

    public TypeHaut getTypeH() {
        return typeH;
    }

    public void setTypeH(TypeHaut typeH) {
        this.typeH = typeH;
    }

    public CoupeHaut getCoupeH() {
        return coupeH;
    }

    public void setCoupeH(CoupeHaut coupeH) {
        this.coupeH = coupeH;
    }

    @Override
    public String toString() {
        return "\nHaut :\n\t" + "\n\tType : " + typeH.name() + "\n\tCoupe : " + getCoupeH() + "\n\tCouleur : " + getCouleur() + "\n\tidDressing : " + getIdDressing() + "\n\tidObjet : " + getIdObjet();
    }
}
