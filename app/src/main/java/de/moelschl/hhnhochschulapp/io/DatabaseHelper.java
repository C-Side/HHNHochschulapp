package de.moelschl.hhnhochschulapp.io;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import de.moelschl.hhnhochschulapp.model.ThemeListItem;


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
            mDatabase.close();
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

        //create a cursor who inspects a single row
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM expandedTheme", null);
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

        //prints out the data
        for (ThemeListItem themeListItem: mList){
            Log.w("OUTPUT", themeListItem.getTopic() + " and desc "+ themeListItem.getDescription());
        }

        return mList;
    }
}
