package dressing.asi.insarouen.fr.dressing.data.model.contenu;

import dressing.asi.insarouen.fr.dressing.data.model.Contenu;
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

    public Vetement(Couleur couleur, String image, int idObjet, int idDressing, Matiere matiere, int couche, Niveau niveau, boolean sale, Morphologie[] signes) {
        super(couleur, image, idObjet, idDressing);
        this.matiere = matiere;
        this.couche = couche;
        this.niveau = niveau;
        this.sale = sale;
        this.signes = signes;
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
}
