package de.moelschl.hhnhochschulapp.forum;

import java.util.ArrayList;

/**
 * a calss which holds the information of the forum
 *
 * Created by Hasbert on 01.06.2016.
 */
public class ListContext {

    private String title;
    private String description;
    private int imageResId;

    private String subCategorie;
    private ArrayList<String> subcatList;

    private String comment;
    private ArrayList<String> commentList;



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImgResId() {
        return imageResId;
    }

    public void setImgResId(int imgResId) {
        this.imageResId = imgResId;
    }
}
