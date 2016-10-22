package dressing.asi.insarouen.fr.dressing.data.model;

import dressing.asi.insarouen.fr.dressing.elements.Couleur;

/**
 * Created by julie on 22/10/16.
 */

public class Contenu {
    private Couleur couleur;
    private String image;
    private int idObjet;
    private int idDressing;

    public Contenu() {
    }

    public Contenu(Couleur couleur, String image, int idObjet, int idDressing) {
        this.couleur = couleur;
        this.image = image;
        this.idObjet = idObjet;
        this.idDressing = idDressing;
    }


    public Couleur getCouleur() {
        return couleur;
    }

    public int getIdObjet() {
        return idObjet;
    }

    public int getIdDressing() {
        return idDressing;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    public void setIdObjet(int idObjet) {
        this.idObjet = idObjet;
    }

    public void setIdDressing(int idDressing) {
        this.idDressing = idDressing;
    }

}
