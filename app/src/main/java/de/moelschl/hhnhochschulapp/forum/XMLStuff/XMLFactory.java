package de.moelschl.hhnhochschulapp.forum.XMLStuff;

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
import de.moelschl.hhnhochschulapp.forum.SubTheme;
import de.moelschl.hhnhochschulapp.forum.Theme;


/**
 * the factory creats the cards out of the xml file
 *
 * @author Lukas, Johann
 * @version Mai 2016
 *
 */
public class XMLFactory {

    public XMLFactory(){

    }

    /**
     * creates a List of Listcontexts which are the information for the Forum
     * @param contextIn the context of the shown window
     */

    public static void createTopic(Context contextIn) {

      //  Card.getAllCards().clear();

        try {
            InputStream is = contextIn.getResources().openRawResource(R.raw.forum_information);

            // read the information into a jdom file
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            // read the information into a jdom file
            Document XMLDoc = new SAXBuilder().build(br);
            // get the root Element (<forumtopic>)
            Element root = XMLDoc.getRootElement();
            // put the topics into a list (<topic>)
            List<Element> topics = root.getChildren("topic");

            for (Element topic : topics) {
                String title = topic.getChildText("topName");
                String description = topic.getChildText("description");

                Theme myTheme = new Theme(title, description);

                List<Element> subcategory = topic.getChildren("subcat"); //put the subtopics into a list (<subcat>)

                for(Element subcat: subcategory){
                    String subTitle = subcat.getChildText("subName");

                    SubTheme mySubTheme = myTheme.createSubTheme(subTitle);
                    System.out.println(subTitle);
                    List<Element> comments = subcat.getChildren("comment");

                    for (Element comment :comments) {
                        String header = comment.getChildText("header");
                        String author = comment.getChildText("author");
                        String text = comment.getChildText("text");

                        mySubTheme.createComment(header, author, text);

                        System.out.println(header+ " " + author + " " + text);
                    }
                }
            }

                //Card.createCard(id, category, subCategory, level, question, answer, asked, known);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
