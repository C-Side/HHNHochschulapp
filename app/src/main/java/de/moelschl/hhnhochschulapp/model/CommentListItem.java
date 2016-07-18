package de.moelschl.hhnhochschulapp.model;

/**
 * Created by Hasbert on 24.06.2016.
 */
public class CommentListItem {

    private String answerText;
    private String userNickname;
    private int id;

    /**
     * constructor to initialize a Object
     *
     * @param answerText the answer text to the given question
     * @param userNickname the users nickname
     */
    public CommentListItem(String answerText, String userNickname, int id){
        this.answerText = answerText;
        this.userNickname = userNickname;
        this.id = id;
    }

    /**
     * getter for the answer to the given questiopn in the thread
     * @return the answerText
     */

    public String getAnswerText() {
        return answerText;
    }


    /**
     * getter for the Uusers nickname
     * @return the ueser nickname
     */

    public String getUserNickname() {
        return userNickname;
    }

    /**
     * getter for the comments id
     * @return the comments id
     */

    public int getId() {
        return id;
    }
}
