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

    private List<Element> topics;
    private List<Element> subTopics;
    private List<Element> comment;

    public XMLFactory(){

    }

    /**
     * creates a List of Listcontexts which are the information for the Forum
     * @param contextIn the context of the shown window
     */

    public ArrayList<ForumListItem> createTopic(Context contextIn) {

        ArrayList<ForumListItem> themelist = new ArrayList<>();
        //  Card.getAllCards().clear();

            Element root = buildRootElement(contextIn);
            // put the topics into a list (<topic>)
            this.topics = root.getChildren("topic");

            for (Element topic : topics) {
                String title = topic.getChildText("topName");
                String description = topic.getChildText("description");
                String type = topic.getChildText("iAmA");

                ForumListItem listItem = new ForumListItem(title, description,type);
                themelist.add(listItem);
            }

        return themelist;
    }

    public ArrayList<ForumListItem> getSubListByParent (String parentname){

        ArrayList<ForumListItem> subThemeList = new ArrayList<>();

            for (Element theme: topics){
                if (parentname.equals(theme.getChild("topName").getText())){

                    this.subTopics = theme.getChildren("subcat");

                    for (Element subtheme: subTopics){
                        String subTitle = subtheme.getChildText("subName");
                        String description = "default desc";
                        String type = subtheme.getChildText("iAmA");

                        ForumListItem listItem = new ForumListItem(subTitle, description, type);
                        subThemeList.add(listItem);

                        System.out.println("--- subtheme -- " +parentname + " + " + subTitle);
                    }
                }
            }

        return subThemeList;
    }

    public ArrayList<ForumListItem> getCommentListByParent (String parentname){

        ArrayList<ForumListItem> commentList = new ArrayList<>();

        for (Element subTheme: subTopics){
            if (parentname.equals(subTheme.getChild("subName").getText())){

                this.comment = subTheme.getChildren("comment");

                for (Element com: comment){
                    String header = com.getChildText("header");
                    String author = com.getChildText("author");
                    String type = com.getChildText("iAmA");
                    //String description = "default desc";

                    ForumListItem listItem = new ForumListItem(header, author, type);
                    commentList.add(listItem);
                    //this.subthemeList.put(parentname, listItem);
                    //SubTheme mySubTheme = myTheme.createSubTheme(subTitle);
                }
            }
        }
        return commentList;
    }

    private Element buildRootElement(Context contextIn){
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
}
