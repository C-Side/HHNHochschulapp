package de.moelschl.hhnhochschulapp.forum;

/**
 * Created by Hasbert on 13.06.2016.
 */
public class Comment{
    private String headerInfo = "sbdshbd";
    private String author;
    private String text;

    public Comment(String header, String author, String text){
        this.headerInfo = header;
        this.author = author;
        this.text = text;
    }

    public String getHeaderInfo() {
        return headerInfo;
    }

    public void setHeaderInfo(String headerInfo) {
        this.headerInfo = headerInfo;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String writer) {
        this.author = writer;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
