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

    public Haut(Couleur couleur, String image,  int idDressing, Matiere matiere,  boolean sale, TypeHaut typeH, CoupeHaut coupeH) {
        super(couleur, image,  idDressing, matiere,  sale);
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
}
