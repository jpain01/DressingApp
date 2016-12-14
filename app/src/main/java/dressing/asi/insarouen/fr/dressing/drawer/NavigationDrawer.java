package dressing.asi.insarouen.fr.dressing.drawer;

/**
 * Created by julie on 14/12/16.
 */

public class NavigationDrawer {
    private String mTitle;
    private int mIcon;

    public NavigationDrawer(String title, int icon){
        this.mTitle = title;
        this.mIcon = icon;
    }

    public String getTitle(){
        return this.mTitle;
    }

    public int getIcon(){
        return this.mIcon;
    }

    public void setTitle(String title){
        this.mTitle = title;
    }

    public void setIcon(int icon){
        this.mIcon = icon;
    }

}

