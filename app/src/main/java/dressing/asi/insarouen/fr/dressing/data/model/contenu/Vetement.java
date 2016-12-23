package dressing.asi.insarouen.fr.dressing.data.model.contenu;

import dressing.asi.insarouen.fr.dressing.data.model.Contenu;
import dressing.asi.insarouen.fr.dressing.data.model.contenu.vetement.Autre;
import dressing.asi.insarouen.fr.dressing.data.model.contenu.vetement.Haut;
import dressing.asi.insarouen.fr.dressing.data.model.contenu.vetement.Pantalon;
import dressing.asi.insarouen.fr.dressing.elements.Couleur;
import dressing.asi.insarouen.fr.dressing.elements.vetement.Matiere;
import dressing.asi.insarouen.fr.dressing.elements.utilisateur.Morphologie;
import dressing.asi.insarouen.fr.dressing.elements.vetement.Niveau;

/**
 * Created by julie on 22/10/16.
 */

public class Vetement extends Contenu {
    private Matiere matiere;
    private int couche;
    private Niveau niveau;
    private boolean sale;
    private Morphologie[] signes;

    public Vetement() {
    }

    public Vetement(Couleur couleur, String image,  int idDressing, int idObjet, Matiere matiere, boolean sale, int couche, Niveau nveau) {
        super(couleur, image, idDressing, idObjet);
        this.matiere = matiere;
        this.sale = sale;
        this.couche = couche;
        this.niveau = niveau;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public int getCouche() {
        return couche;
    }

    public void setCouche(int couche) {
        this.couche = couche;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public boolean isSale() {
        return sale;
    }

    public void setSale(boolean sale) {
        this.sale = sale;
    }

    public Morphologie[] getSignes() {
        return signes;
    }

    public void setSignes(Morphologie[] signes) {
        this.signes = signes;
    }

    public boolean isAutre() {
        return this instanceof Autre;
    }

    public boolean isHaut() {
        return this instanceof Haut;
    }

    public boolean isPantalon() {
        return this instanceof Pantalon;
    }

}
