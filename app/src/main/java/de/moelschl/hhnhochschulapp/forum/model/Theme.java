package de.moelschl.hhnhochschulapp.forum.model;

import java.util.ArrayList;

/**
 * a calss which holds the information of the forum with a list of it´s subtheme children
 *
 * Created by Hasbert on 01.06.2016.
 */
public class Theme {

    private static ArrayList<Theme> themeList = new ArrayList<>();

    private String themeTitle;
    private String description;
    private int imageResId;



    private ArrayList<SubTheme> subThemeList;

    /**
     * default constructor
     */
    public Theme(String themeTitle, String description){
        setTitle(themeTitle);
        setDescription(description);
        subThemeList = new ArrayList<>();
        themeList.add(this);
        //setImageResId(imageResId);
    }

    /**
     * method to create a subtitle in the preformed theme structur.
     * @param subTitle
     */
    public SubTheme createSubTheme(String subTitle){
        SubTheme subTheme = new SubTheme(subTitle);
        subThemeList.add(subTheme);
        return subTheme;
    }

    public ArrayList<SubTheme> getSubThemeList() {
        return subThemeList;
    }

    /**
     * getter for description
     * @return the theme description
     */
    public String getDescription() {
        return description;
    }

    /**
     * getter for title
     * @return the title
     */
    public String getTitle() {
        return themeTitle;
    }

    /**
     * getter for all subThemes
     * @return a list of subThemes

    public ArrayList<SubTheme> getAllSubs(){
        return subThemeList;
    }
    */

    /**
     * getter for ...
     * @return ....
     */
    public int getImageResId() {
        return imageResId;
    }

    public static ArrayList<Theme> getThemeList() {
        return themeList;
    }

    /**
     * setter for description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * setter for title
     * @param title
     */
    public void setTitle(String title) {
        this.themeTitle = title;
    }

    /**
     * setter for the iamge
     * @param imageResId
     */
    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    /**
     * setter for aubList
     * @param subs the list to insert
     */
    public void setAllSubs(ArrayList<SubTheme> subs) {
        this.subThemeList = subs;
    }
}
