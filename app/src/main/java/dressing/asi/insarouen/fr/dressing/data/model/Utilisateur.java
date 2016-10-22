package dressing.asi.insarouen.fr.dressing.data.model;

import dressing.asi.insarouen.fr.dressing.elements.Couleur;
import dressing.asi.insarouen.fr.dressing.elements.utilisateur.CouleurCheveux;
import dressing.asi.insarouen.fr.dressing.elements.utilisateur.Morphologie;

/**
 * Created by julie on 22/10/16.
 */

public class Utilisateur {
    private String nom;
    private String prenom;
    private String login;
    private String mdp;
    private int id;
    private int age;
    private int taille;
    private Couleur couleurPreferee;
    private CouleurCheveux couleurCheveux;
    private Morphologie morphologie;

    public Utilisateur() {
    }

    public Utilisateur(String nom, String prenom, String login, String mdp, int id, int age, int taille, Couleur couleurPreferee, CouleurCheveux couleurCheveux, Morphologie morphologie) {
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.mdp = mdp;
        this.id = id;
        this.age = age;
        this.taille = taille;
        this.couleurPreferee = couleurPreferee;
        this.couleurCheveux = couleurCheveux;
        this.morphologie = morphologie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public Couleur getCouleurPreferee() {
        return couleurPreferee;
    }

    public void setCouleurPreferee(Couleur couleurPreferee) {
        this.couleurPreferee = couleurPreferee;
    }

    public Morphologie getMorphologie() {
        return morphologie;
    }

    public void setMorphologie(Morphologie morphologie) {
        this.morphologie = morphologie;
    }

    public CouleurCheveux getCouleurCheveux() {
        return couleurCheveux;
    }

    public void setCouleurCheveux(CouleurCheveux couleurCheveux) {
        this.couleurCheveux = couleurCheveux;
    }
}
