package de.moelschl.hhnhochschulapp.forum.model;


/**
 * Created by Hasbert on 15.06.2016.
 */
public class ForumListItem {

    private String title;
    private String description;
    private String iAmA;

    public ForumListItem(String title, String description, String iAmA){
        this.title = title;
        this.description = description;
        this.iAmA = iAmA;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getListHirarchie() {
        return iAmA;
    }
}
