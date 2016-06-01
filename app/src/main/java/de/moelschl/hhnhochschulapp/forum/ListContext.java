package de.moelschl.hhnhochschulapp.forum;

/**
 * a calss which holds information of the forum
 *
 * Created by Hasbert on 01.06.2016.
 */
public class ListContext {

    private String title;
    private String description;
    private int imageResId;

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
