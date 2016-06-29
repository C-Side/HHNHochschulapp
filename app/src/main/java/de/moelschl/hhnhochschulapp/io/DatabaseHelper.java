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
import java.util.HashMap;

import de.moelschl.hhnhochschulapp.model.CommentListItem;
import de.moelschl.hhnhochschulapp.model.ThemeListItem;
import de.moelschl.hhnhochschulapp.model.ThreadListItem;
import de.moelschl.hhnhochschulapp.model.User;


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
        ArrayList<ThreadListItem> threadList = new ArrayList<>();
        int commentCount = 0;

        //opens the dataBase and starts connection
        openDatabase();

        HashMap<Integer, Integer> comCount = getCount(mDatabase);
        final String QUERY =
                "SELECT * " +
                        "FROM thread " +
                        "WHERE thread.theme_topic = '" + navigationKEy + "'";

        //create a cursor who inspects a single row
        Cursor cursor = mDatabase.rawQuery(QUERY, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int id = cursor.getInt(0);
            String themeTopic = cursor.getString(1);
            String questionHeader = cursor.getString(2);
            String questionText = cursor.getString(3);
            String userNickname = cursor.getString(4);

            //checks if the key is in the hashmap with comment values
            if (comCount.containsKey(id))commentCount = comCount.get(id);
            else commentCount = 0;
            //if not 0 gets stored in the item
            listItem = new ThreadListItem(themeTopic, id, questionHeader, questionText,
                    userNickname, commentCount);
            threadList.add(listItem);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();

        return threadList;
    }

    private HashMap getCount(SQLiteDatabase mDatabase){

        HashMap<Integer, Integer> hash = new HashMap<>();
        final String COUNT_QUERY = " SELECT thread.id, COUNT (comments.answer_text)\n" +
                " FROM thread JOIN comments ON thread.id = comments.thread_id\n" +
                " GROUP BY thread.id";
        Cursor countCursor = mDatabase.rawQuery(COUNT_QUERY, null);
        countCursor.moveToFirst();

        while (!countCursor.isAfterLast()){
            hash.put(countCursor.getInt(0), countCursor.getInt(1));
            countCursor.moveToNext();
        }
        countCursor.close();

        return hash;
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
            listItem = new CommentListItem(answerText, userNickname);
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

    public String getPasswordByUsername(String username){
        openDatabase();
        String password = null;
        final String QUERY = "SELECT user.password FROM user " +
                "WHERE user.nickname = '" + username + "'";
        //create a cursor who inspects a single row
        Cursor cursor = mDatabase.rawQuery(QUERY, null);
        if (cursor.moveToFirst()){
            password = cursor.getString(cursor.getColumnIndex("password"));
            cursor.close();
        }
        closeDatabase();

        return password;
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
        threadContentValues.put("user_nickname", User.getUsername());

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

    public void addAnswerToComment(int id, String answer){
        ContentValues answerContent = new ContentValues();
        answerContent.put("thread_id", id);
        answerContent.put("answer_Text", answer);
        answerContent.put("user_nickname", User.getUsername());

        //opens Database and inserts the data
        openDatabase();
            mDatabase.insert("comments", null, answerContent);
        closeDatabase();
    }

}
