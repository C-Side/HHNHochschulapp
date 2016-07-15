package de.moelschl.hhnhochschulapp.model;

/**
 * Created by Hasbert on 29.06.2016.
 */
public final class User {
    private static String username;

    private User(){
    }

    public static String getUsername(){
        return username;
    }

    public static void setUsername(String newUsername) {
        username = newUsername;
    }
}
