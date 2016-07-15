package de.moelschl.hhnhochschulapp.model;

/**
 * Created by Hasbert on 29.06.2016.
 */
public final class User {

    private static String userName;
    private static int writtenThreadsCount;
    private static int writtenCommentsCount;

    private User(){
    }


    /**
     * sets the username
     *
     * @param username the new username
     */

    public static void setUsername(String username){
        User.userName = username;
    }


    /**
     * gets the username
     *
     * @return the username
     */

    public static String getUsername(){
        return userName;
    }


    /**
     * gets the username
     *
     * @return the username
     */

    public static int getWrittenThreadsCount() {
        return writtenThreadsCount;
    }


    /**
     * sets the username
     *
     * @param writtenThreadsCount the new username
     */

    public static void setWrittenThreadsCount(int writtenThreadsCount) {
        User.writtenThreadsCount = writtenThreadsCount;
    }


    /**
     * gets the username
     *
     * @return the username
     */

    public static int getWrittenCommentsCount() {
        return writtenCommentsCount;
    }


    /**
     * sets the username
     *
     * @param writtenCommentsCount the new username
     */

    public static void setWrittenCommentsCount(int writtenCommentsCount) {
        User.writtenCommentsCount = writtenCommentsCount;
    }
}
