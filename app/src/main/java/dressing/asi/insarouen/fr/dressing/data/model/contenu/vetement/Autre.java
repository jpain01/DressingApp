package dressing.asi.insarouen.fr.dressing.data.model.contenu.vetement;

import dressing.asi.insarouen.fr.dressing.data.model.contenu.Vetement;
import dressing.asi.insarouen.fr.dressing.elements.Couleur;
import dressing.asi.insarouen.fr.dressing.elements.utilisateur.Morphologie;
import dressing.asi.insarouen.fr.dressing.elements.vetement.Matiere;
import dressing.asi.insarouen.fr.dressing.elements.vetement.Niveau;
import dressing.asi.insarouen.fr.dressing.elements.vetement.autre.CoupeAutre;
import dressing.asi.insarouen.fr.dressing.elements.vetement.autre.TypeAutre;

/**
 * Created by julie on 22/10/16.
 */

public class Autre extends Vetement {
    private TypeAutre typeA;
    private CoupeAutre coupeA;

    public Autre() {
    }

    public Autre(Couleur couleur, String image, int idDressing, int idObjet, Matiere matiere, boolean sale,  TypeAutre typeA, CoupeAutre coupeA, int couche, Niveau niveau) {
        super(couleur, image, idDressing, idObjet, matiere,  sale, couche, niveau);
        this.typeA = typeA;
        this.coupeA = coupeA;
    }

    /**
     * getTypeA permet de connaître le type du vêtement
     *
     * @return TypeAutre
     */
    public TypeAutre getTypeA() {
        return typeA;
    }

    /**
     * setTypeA permet d'attribuer le type au vêtement
     *
     * @param typeA
     */
    public void setTypeA(TypeAutre typeA) {
        this.typeA = typeA;
    }

    /**
     * getCoupeA permet de connaître la coupe du vêtement
     *
     * @return CoupeAutre
     */
    public CoupeAutre getCoupeA() {
        return coupeA;
    }

    /**
     * setCoupeA permet d'attribuer la coupe au vêtement
     *
     * @param coupeA
     */
    public void setCoupeA(CoupeAutre coupeA) {
        this.coupeA = coupeA;
    }

    @Override
    public String toString() {
        return "\nAutre :\n\t" + "\n\tType : " + typeA.name() + "\n\tCoupe : " + getCoupeA() + "\n\tCouleur : " + getCouleur() + "\n\tidDressing : " + getIdDressing() + "\n\tidObjet : " + getIdObjet();
    }
}
