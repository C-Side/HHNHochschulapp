package de.moelschl.hhnhochschulapp.forum.model;


/**
 * class which holds information for the forum rows.
 */
public class ForumListItem {

    private String title;
    private String description;
    private String iAmA;

    /**
     * constructor to initialize a Object
     *
     * @param title the title of the row
     * @param description the descripotion or additional information of the title or the forum row
     * @param iAmA tells the system what type of lsit the Object belongs to.
     */
    public ForumListItem(String title, String description, String iAmA){
        this.title = title;
        this.description = description;
        this.iAmA = iAmA;
    }

    /**
     * getter for the title
     * @return the title
     */

    public String getTitle() {
        return title;
    }

    /**
     * getter for the description
     * @return the description
     */

    public String getDescription() {
        return description;
    }

    /**
     * getter for the list the object belongs to
     * @return the String name of the list the object belongs to
     */

    public String getListHirarchie() {
        return iAmA;
    }
}
