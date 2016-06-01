package de.moelschl.hhnhochschulapp.forum.XMLStuff;

import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Card;

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
    public static void createCards() {

      //  Card.getAllCards().clear();

        try {
            // read the information into a jdom file
            Document XMLDoc = new SAXBuilder().build(xmlTopics);
            // get the root Element (<topic>)
            Element root = XMLDoc.getRootElement();
            // put the Cards into a list (<Card>)
            List<Element> cards = root.getChildren("Card");
            // create the Cards
            for (Element card : cards) {
                // data variables
                int id = 0;
                String category = null;
                String subCategory = null;
                String level = null;
                String question = null;
                String answer = null;
                int asked = 0;
                int known = 0;

                // read data for fields
                id = Integer.parseInt(card.getChildText("ID"));
                category = card.getChildText("category");
                subCategory = card.getChildText("subCategory");
                level = card.getChildText("level");
                question = card.getChildText("question");
                answer = card.getChildText("answer");
                asked = Integer.parseInt(card.getChildText("asked"));
                known = Integer.parseInt(card.getChildText("known"));

                Card.createCard(id, category, subCategory, level, question, answer, asked, known);

            }
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * loads the levels out of a xml file
     */
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
     */
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
     */
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
}
