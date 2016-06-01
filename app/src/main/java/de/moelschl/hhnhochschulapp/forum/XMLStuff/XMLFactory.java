package de.moelschl.hhnhochschulapp.forum.XMLStuff;

import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;


/**
 * the factory creats the cards out of the xml file
 *
 * @author Lukas, Johann
 * @version Mai 2016
 *
 */
public class XMLFactory {

    private static final String xmlTopics = "res/arrays.xml";

    /**
     * creates the cards out of the xml File
     */
    public static void createTopic() {

      //  Card.getAllCards().clear();

        try {
            // read the information into a jdom file
            Document XMLDoc = new SAXBuilder().build(xmlTopics);
            // get the root Element (<resources>)
            Element root = XMLDoc.getRootElement();
            // put the topics into a list (<topic>)
            List<Element> topics = root.getChildren("topic");
            //put the subtopics into a list (<subcat>)
            List<Element> subcategories = root.getChildren("subcat");
            //put the subtopics into a list (<subcat>)
            List<Element> comments = root.getChildren("comment");


            // create the Cards
            for (Element topic : topics) {
                // data variables
                String title = null;
                String description =  null;

                title = topic.getChildText("topic");
                description = topic.getChildText("description");

                System.out.println(title +" " + description);
                for (Element subcat :subcategories) {
                    String subCategorie = null;
                    subCategorie = subcat.getChildText("subcat");
                    System.out.println(subCategorie);
                    for (Element comment :comments) {
                        String userText = null;
                        userText = comment.getChildText("comment");
                        System.out.println(userText);
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

    /**
     * loads the levels out of a xml file

    public static ObservableList<String> loadLevels() {

        ObservableList<String> listOfLevels = FXCollections.observableArrayList();

        try {
            // read the information into a jdom file
            Document XMLDoc = new SAXBuilder().build(xmlLevels);
            // get the root Element (<Level>)
            Element root = XMLDoc.getRootElement();
            // put the Levels into a list
            List<Element> levels = root.getChildren();
            // create the Levels
            for (Element level : levels) {

                //add the Levels to the list
                listOfLevels.add(level.getText());
            }

        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfLevels;
    }

    /**
     * loads the categories out of a xml file

    public static ObservableList<String> loadCategories() {

        ObservableList<String> listOfCategories = FXCollections.observableArrayList();

        try {
            // read the information into a jdom file
            Document XMLDoc = new SAXBuilder().build(xmlCategories);
            // get the root Element (<Categories>)
            Element root = XMLDoc.getRootElement();
            // put the categories into a list
            List<Element> categories = root.getChildren("cat");
            // create the categories
            for (Element category : categories) {
                //initialize fields
                String name = null;

                //read the data into the fields
                name = category.getChildText("name");

                //add the categories to the list
                listOfCategories.add(name);
            }

        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfCategories;
    }

    /**
     * loads the subCategories out of a xml file

    public static ObservableList<String> loadSubCategories(String categoryPar) {

        ObservableList<String> listOfSubCategories = FXCollections.observableArrayList();

        try {
            // read the information into a jdom file
            Document XMLDoc = new SAXBuilder().build(xmlCategories);
            // get the root Element (<categories>)
            Element root = XMLDoc.getRootElement();
            // put the categories into a list
            List<Element> categories = root.getChildren();

            //load the subCategories
            for (Element category : categories) {
                if (category.getChildText("name").equals(categoryPar)) {
                    List<Element> subCategories = category.getChildren("subCat");
                    //add the subCategories to the list
                    for (Element subCat: subCategories) {
                        listOfSubCategories.add(subCat.getText());
                    }
                }
            }

        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfSubCategories;
    }
    */
}
