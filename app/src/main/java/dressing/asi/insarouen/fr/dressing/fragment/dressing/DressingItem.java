package dressing.asi.insarouen.fr.dressing.fragment.dressing;

/**
 * Created by julie on 15/12/16.
 */

public class DressingItem {
    private String mTitle;
    private String mIcon;

    public DressingItem(String title, String icon){
        this.mTitle = title;
        this.mIcon = icon;
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
}
