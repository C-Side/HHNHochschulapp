package de.moelschl.hhnhochschulapp.model;


/**
 * class which holds information for the forum rows.
 */
public class ThemeListItem {

    private String topic;
    private String description;
    private int threadCounter;

    /**
     * constructor to initialize a Object
     *
     * @param topic the title of the row
     * @param description the descripotion or additional information of the title or the forum row
     */
    public ThemeListItem(String topic, String description, int threadCounter){
        this.topic = topic;
        this.description = description;
        this.threadCounter = threadCounter;
    }

    /**
     * getter for the title
     * @return the title
     */

    public String getTopic() {
        return topic;
    }

    /**
     * getter for the description
     * @return the description
     */

    public String getDescription() {
        return description;
    }

    /**
     * getter for the number of threads in this theme
     * @return the threadCounter
     */

    public int getThreadCounter() {
        return threadCounter;
    }
}
