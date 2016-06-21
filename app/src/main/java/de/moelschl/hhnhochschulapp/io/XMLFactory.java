package de.moelschl.hhnhochschulapp.io;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import de.moelschl.hhnhochschulapp.R;
import de.moelschl.hhnhochschulapp.model.ForumListItem;


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

    /**
     * constructor for the factory default.
     */

    public XMLFactory(){

    }

    /**
     * creates a List of {@link de.moelschl.hhnhochschulapp.model.ForumListItem} which are
     * the information for the Forum. This list consists of topics and the first informatiopn which is
     * visible
     * @param contextIn the context of the shown window
     */

    public static ArrayList<ForumListItem> createTopic(Context contextIn) {
        themeList = new ArrayList<>();

            Element root = buildRootElement(contextIn);
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

    /**
     * creates a List of {@link de.moelschl.hhnhochschulapp.model.ForumListItem} which are
     * the information for the Forum. This list consists of subThemes.
     *
     * @param parentname the clicked String title of the item in the list
     * @return the subtheme list depended on the parentname laded from XML
     */
    public static ArrayList<ForumListItem> createSubListByParent (String parentname){

        subThemeList = new ArrayList<>();

            for (Element theme: topics){
                String themeItem = theme.getChild("topName").getText();
                if (parentname.equals(themeItem)){

                    subTopics = theme.getChildren("subcat");

                    for (Element subtheme: subTopics){
                        String subTitle = subtheme.getChildText("subName");
                        String commentCount = "Kommentare: " + createCommentListByParent(subTitle).size();
                        String type = "subTheme";

                        ForumListItem listItem = new ForumListItem(subTitle, commentCount, type);
                        subThemeList.add(listItem);
                    }
                }
            }

        return subThemeList;
    }

    /**
     * creates a List of {@link de.moelschl.hhnhochschulapp.model.ForumListItem} which are
     * the information for the Forum. This list consists of comments.
     *
     * @param parentname the clicked String subtitle of the item in the list
     * @return the comment list depended on the parentname laded from XML
     */
    public static ArrayList<ForumListItem> createCommentListByParent (String parentname){

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

    /**
     * builds the root element to iterate over with SaxxBUilder and buffered reader
     *
     * @param contextIn {@link android.content.Context}
     * @return the root element to iterate over
     */
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

    /**
     * getter of the static commentList
     * @return the static commentList
     */
    public static ArrayList<ForumListItem> getCommentList() {
        return commentList;
    }

    /**
     * getter of the static subTheeList
     * @return the static subTheeList
     */
    public static ArrayList<ForumListItem> getSubThemeList() {
        return subThemeList;
    }

    /**
     * getter of the static themeList
     * @return the static themeList
     */
    public static ArrayList<ForumListItem> getThemeList() {
        return themeList;
    }
}
