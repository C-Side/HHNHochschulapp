package de.moelschl.hhnhochschulapp.model;

/**
 * model for the Thread Fragment List
 *
 */


public class ThreadListItem {

    private String themeTopic;
    private int id;
    private String questionHeader;
    private String questionText;
    private String ueserNickname;
    private int commentCount;


    /**
     * constructor to initialize a Object
     *
     * @param themeTopic the topic of the parent Theme
     * @param id the id of the thread entry
     * @param questionHeader the asked short question header
     * @param questionText the question itself
     * @param ueserNickname the nickname of the user who has written the thread
     * @param commentCount the number of written comments on this thread
     */

    public ThreadListItem(String themeTopic, int id, String questionHeader, String questionText,
                          String ueserNickname, int commentCount){

        this.themeTopic = themeTopic;
        this.id = id;
        this.questionHeader = questionHeader;
        this.questionText = questionText;
        this.ueserNickname = ueserNickname;
        this.commentCount = commentCount;
    }

    /**
     * getter for the written comments on this thread
     * @return number of written comments
     */

    public int getCommentCount() {
        return commentCount;
    }


    /**
     * getter for the Theme Topic
     * @return the ThemeTopic
     */

    public String getThemeTopic() {
        return themeTopic;
    }


    /**
     * getter for the id
     * @return the id
     */

    public int getId() {
        return id;
    }


    /**
     * getter for the question header
     * @return the question header
     */

    public String getQuestionHeader() {
        return questionHeader;
    }


    /**
     * getter for the question text
     * @return the question text
     */

    public String getQuestionText() {
        return questionText;
    }


    /**
     * getter for the uesers nickname
     * @return the usernickmname
     */

    public String getUeserNickname() {
        return ueserNickname;
    }
}
