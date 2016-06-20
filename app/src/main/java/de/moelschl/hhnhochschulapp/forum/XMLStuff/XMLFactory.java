package de.moelschl.hhnhochschulapp.forum.XMLStuff;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import de.moelschl.hhnhochschulapp.R;
import de.moelschl.hhnhochschulapp.forum.model.ForumListItem;


/**
 * the factory creats the cards out of the xml file
 *
 * @author Lukas, Johann
 * @version Mai 2016
 *
 */
public class XMLFactory {

    //internal Lists to manage XML
    private static List<Element> topics;
    private static List<Element> subTopics;
    private static List<Element> comment;

    //external list for everyone
    private static ArrayList<ForumListItem> themeList;
    private static ArrayList<ForumListItem> subThemeList;
    private static ArrayList<ForumListItem> commentList;

    public XMLFactory(){

    }

    /**
     * creates a List of Listcontexts which are the information for the Forum
     * @param contextIn the context of the shown window
     */

    public static ArrayList<ForumListItem> createTopic(Context contextIn) {

        themeList = new ArrayList<>();
        //  Card.getAllCards().clear();

            Element root = buildRootElement(contextIn);
            // put the topics into a list (<topic>)
            topics = root.getChildren("topic");

            for (Element topic : topics) {
                String title = topic.getChildText("topName");
                String description = topic.getChildText("description");
                String type = "theme";

                ForumListItem listItem = new ForumListItem(title, description,type);
                themeList.add(listItem);
            }

        return themeList;
    }

    public static ArrayList<ForumListItem> getSubListByParent (String parentname){

        subThemeList = new ArrayList<>();

            for (Element theme: topics){
                String themeItem = theme.getChild("topName").getText();
                if (parentname.equals(themeItem)){

                    subTopics = theme.getChildren("subcat");

                    for (Element subtheme: subTopics){
                        String subTitle = subtheme.getChildText("subName");
                        String description = "default desc";
                        String type = "subTheme";

                        ForumListItem listItem = new ForumListItem(subTitle, description, type);
                        subThemeList.add(listItem);

                        System.out.println("--- subtheme -- " +parentname + " + " + subTitle);
                    }
                }
            }

        return subThemeList;
    }

    public static ArrayList<ForumListItem> getCommentListByParent (String parentname){

        commentList = new ArrayList<>();

        for (Element subTheme: subTopics){
            String subThemeItem = subTheme.getChild("subName").getText();
            if (parentname.equals(subThemeItem)){

                comment = subTheme.getChildren("comment");

                for (Element com: comment){
                    String header = com.getChildText("header");
                    String author = com.getChildText("author");
                    String type = "comment";
                    //String description = "default desc";

                    ForumListItem listItem = new ForumListItem(header, author, type);
                    commentList.add(listItem);
                }
            }
        }
        return commentList;
    }

    private static Element buildRootElement(Context contextIn){
        Element root = null;
        try {
            InputStream path = contextIn.getResources().openRawResource(R.raw.forum_information);

            // read the information into a jdom file
            BufferedReader br = new BufferedReader(new InputStreamReader(path));
            // building a document to get the root from
            Document XMLDoc = new SAXBuilder().build(br);
            // get the root Element (<forumtopic>)
            root = XMLDoc.getRootElement();
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }

    public static ArrayList<ForumListItem> getCommentList() {
        return commentList;
    }

    public static ArrayList<ForumListItem> getSubThemeList() {
        return subThemeList;
    }

    public static ArrayList<ForumListItem> getThemeList() {
        return themeList;
    }
}
