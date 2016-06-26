package de.moelschl.hhnhochschulapp.model;

/**
 * Created by Hasbert on 24.06.2016.
 */
public class CommentListItem {

    private String answerText;
    private String userNickname;

    /**
     * constructor to initialize a Object
     *
     * @param answerText the answer text to the given question
     * @param userNickname the users nickname
     */
    public CommentListItem(String answerText, String userNickname){
        this.answerText = answerText;
        this.userNickname = userNickname;
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
}
