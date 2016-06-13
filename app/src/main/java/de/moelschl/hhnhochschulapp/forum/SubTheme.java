package de.moelschl.hhnhochschulapp.forum;

import java.util.ArrayList;

/**
 * Created by Hasbert on 13.06.2016.
 */
public class SubTheme {

    private String subTitle;

    private ArrayList<Comment> commentList;

    public SubTheme(String subTitle) {
        this.subTitle = subTitle;
        this.commentList = new ArrayList<>();
    }

    public void createComment(String headerInfo, String author, String text){
        Comment comment = new Comment(headerInfo, author, text);
        commentList.add(comment);
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public ArrayList<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(ArrayList<Comment> commentList) {
        this.commentList = commentList;
    }

    public int getAmountCom(){
        return commentList.size();
    }
}
