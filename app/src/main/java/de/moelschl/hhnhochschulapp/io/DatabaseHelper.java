package de.moelschl.hhnhochschulapp.io;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import de.moelschl.hhnhochschulapp.model.CommentListItem;
import de.moelschl.hhnhochschulapp.model.ThemeListItem;
import de.moelschl.hhnhochschulapp.model.ThreadListItem;


/**
 * Created by Hasbert on 25.06.2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "foDB.sqlite";
    public static final String DBLOCATION = "/data/data/de.moelschl.hhnhochschulapp/databases/";
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public DatabaseHelper(Context context){
        super(context, DBNAME, null, 1);
        this.mContext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * opens the database of the project
     *
     */

    public void openDatabase(){
        String dbPath = mContext.getDatabasePath(DBNAME).getPath();
        if ((mDatabase != null && mDatabase.isOpen())){
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    /**
     * copies the .sqlite File of the assets Folder
     *
     * @param context {@link android.content.Context}
     * @return true to print out a loading compleat massage false to negate the msg
     */

    public boolean copyDatabase(Context context) {
        try {

            InputStream inputStream = context.getAssets().open(DBNAME);
            String outfileName = DBLOCATION + DBNAME;
            OutputStream outputStream = new FileOutputStream(outfileName);
            byte[] buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("MainActivity", "DB copied");
            return true;


        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * closes the database of the project
     *
     */

    public void closeDatabase(){
        if(mDatabase != null){
            this.close();
        }
    }


    /**
     * method to perform query "SELECT * FROM theme"
     *
     * @return a List of ListItems for the adapter to show
     */

    public ArrayList<ThemeListItem> getThemeList(){
        ThemeListItem listItem = null;
        ArrayList<ThemeListItem> mList = new ArrayList<>();
        openDatabase();

        final String QUERY = "SELECT * FROM expandedTheme";
        //create a cursor who inspects a single row
        Cursor cursor = mDatabase.rawQuery(QUERY, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String topic = cursor.getString(0);
            String description = cursor.getString(1);
            int threadCount = cursor.getInt(2);
            listItem = new ThemeListItem(topic, description , threadCount);
            mList.add(listItem);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();

        return mList;
    }

    public ArrayList<ThreadListItem> getThreadList(String navigationKEy){
        ThreadListItem listItem = null;
        ArrayList<ThreadListItem> mList = new ArrayList<>();
        openDatabase();

        final String QUERY =
                "SELECT thread.theme_topic, thread.id, thread.questionHeader, thread.questionText, " +
                        "thread.user_nickname " +
                "COUNT (thread.id) AS threads " +
                "FROM thread, comments " +
                "WHERE thread.id = comments.thread_id " +
                "AND thread.theme_topic = '" + navigationKEy + "' " +
                "GROUP BY thread.theme_topic, thread.id, thread.questionHeader, thread.questionText, " +
                        "thread.user_nickname) ";

        //create a cursor who inspects a single row
        Cursor cursor = mDatabase.rawQuery(QUERY, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String themeTopic = cursor.getString(0);
            int id = cursor.getInt(1);
            String questionHeader = cursor.getString(2);
            String questionText = cursor.getString(3);
            String userNickname = cursor.getString(4);
            int commentCount = cursor.getInt(5);
            listItem = new ThreadListItem(themeTopic, id, questionHeader, questionText,
                    userNickname, commentCount);
            mList.add(listItem);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();

        return mList;
    }

    public ArrayList<CommentListItem> getCommentList(int navigationKEy){
        CommentListItem listItem = null;
        ArrayList<CommentListItem> mList = new ArrayList<>();
        openDatabase();

        final String QUERY = "SELECT * FROM comments " + "WHERE  comments.thread_id = '" + navigationKEy + "'";
        //create a cursor who inspects a single row
        Cursor cursor = mDatabase.rawQuery(QUERY, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String answerText = cursor.getString(1);
            String userNickname = cursor.getString(2);
            listItem = new CommentListItem(answerText, userNickname );
            mList.add(listItem);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();

        return mList;
    }

    public String getDescByTopic(String text){

        openDatabase();

        final String QUERY = "SELECT theme.description FROM theme " +
                "WHERE  theme.topic = '" + text + "'";
        //create a cursor who inspects a single row
        Cursor cursor = mDatabase.rawQuery(QUERY, null);
        cursor.moveToFirst();
        String description = cursor.getString(0);

        cursor.close();
        closeDatabase();

        return description;
    }

    public void addQuestionToDatabase(String themeTopic, String themeDesc, String questionHeader,
                                      String question, Context context){

        //DatabaseHelper dbHelper = new DatabaseHelper(mContext);
        //insert into table thread
        //unbedingt final variablen nehmen! + user erstellen dann user nickname eintragen
        ContentValues threadContentValues = new ContentValues();
        threadContentValues.put("theme_topic", themeTopic);
        threadContentValues.put("questionHeader", questionHeader);
        threadContentValues.put("questionText", question);
        threadContentValues.put("user_nickname", "aForeignStranger");

        //performs the insertions
        openDatabase();
            if (isNotInList(themeTopic)){
                //insert into table theme
                ContentValues themeContentValues = new ContentValues();
                themeContentValues.put("topic", themeTopic);
                themeContentValues.put("description", themeDesc);

                //insertions
                mDatabase.insert("thread", null, threadContentValues);
                mDatabase.insert("theme", null, themeContentValues);
            }
            else {
                mDatabase.insert("thread", null, threadContentValues);
            }
            closeDatabase();
    }

    private boolean isNotInList(String key) {
        boolean inList = true;
        for (ThemeListItem listItem : getThemeList()) {
            if(listItem.getTopic().equals(key)){
                inList = false;
                return inList;
            }
            else {
                inList = true;
            }
        }
        return inList;
    }
}
