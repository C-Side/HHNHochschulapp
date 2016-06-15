package de.moelschl.hhnhochschulapp.forum.model;


/**
 * Created by Hasbert on 15.06.2016.
 */
public class ForumListItem {

    private String title;
    private String description;

    public ForumListItem(String title, String description){
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
