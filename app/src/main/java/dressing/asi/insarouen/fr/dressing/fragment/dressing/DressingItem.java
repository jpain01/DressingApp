package dressing.asi.insarouen.fr.dressing.fragment.dressing;

/**
 * Created by julie on 15/12/16.
 */

public class DressingItem {
    private String mTitle;
    private String mIcon;
    private int idUtilisateur;

    public DressingItem(String title, String icon, int idUtilisateur){
        this.mTitle = title;
        this.mIcon = icon;
        this.idUtilisateur = idUtilisateur;
    }

    public String getTitle(){
        return this.mTitle;
    }

    public String getIcon() {
        return this.mIcon;
    }

    public void setTitle(String title){
        this.mTitle = title;
    }

    public void setIcon(String icon){
        this.mIcon = icon;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
}
