package de.moelschl.hhnhochschulapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Created by Moelscmar on 14.06.2016.
 */
public class SQLConnection {

    public class Connect {

        public void connect() {

            String url = "jdbc:mysql://93.104.211.23:3306/interfaceprojects";
            Connection dbVerbindung=null;
            Statement st = null;


            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                System.out.println("Treiber erfolgreich geladen...");
            }
            catch(ClassNotFoundException e)
            {
                System.out.println("Fehler beim Laden des Treibers"+e);
                System.exit(0);
            }
            try
            {
                dbVerbindung = DriverManager.getConnection(url, "root", "rEV4j3ocHOZPL");
                System.out.println("Verbindung erfolgreich...");
                dbVerbindung.close();
                System.out.println("Verbindung geschlossen...");
            }
            catch(SQLException e)
            {
                System.out.println("DB-Verbindungsfehler: "+e);
                System.exit(0);
            }
        }
    }
}
