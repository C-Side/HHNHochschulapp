package de.moelschl.hhnhochschulapp.controller;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Pattern;

import de.moelschl.hhnhochschulapp.R;
import de.moelschl.hhnhochschulapp.controller.BenachrichtigungenFragment;

/**
 * Class to load, control and display a single message
 *
 *  * Created by moelscmar
 */
public class NachrichtActivity extends AppCompatActivity {
    InputStream is;
    String[] results;
    String auswahl = BenachrichtigungenFragment.getAuswahl();
    String datum;
    String betreff;
    String absender;
    String nachricht;

    /**
     *
     * the initialization method is like a constructor. it loads the main layout and set them
     * active, then gives the information to other classes.
     *
     * @param savedInstanceState a mapping form String values to whatever you want.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nachricht);
        setTitle("Nachrichten");
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        getData();
        trimString();
    }

    /**
     * loads a single message from a php file and shows it in the ListAdapter
     */
    private void getData(){
        String result = "";
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("https://moelschl.de/interfaceprojects2.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        } catch (Exception e) {
            Log.e("log_tag", "Fehler bei der http Verbindung " + e.toString());
        }

        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            is.close();
            result=sb.toString();
            Log.e("log_Tag", result);
        }catch(Exception e){
            Log.e("log_tag", "Error converting result "+e.toString());
        }
        results = result.split(Pattern.quote("<br>"));
        for (String match:results){
           if(match.contains(auswahl)){
               auswahl = match;
           }
        }
        Log.e("EndAuswahl", auswahl);

    }

    /**
     * Method to trim the String from the php file into a desired format
     */
    private void trimString(){
        auswahl.replace("<br>", "");
        results = auswahl.split(Pattern.quote(":"));
        absender = results[0];
        betreff = results[1];
        datum = results[2];
        nachricht = results[3];
        Log.e("Testo", absender + betreff + datum + nachricht);
        TextView absenderView = (TextView) findViewById(R.id.absender2);
        absenderView.setText(absender);
        TextView datumView = (TextView) findViewById(R.id.datum2);
        datumView.setText(datum);
        TextView betreffView = (TextView) findViewById(R.id.betreff2);
        betreffView.setText(betreff);
        TextView nachrichtView = (TextView) findViewById(R.id.nachricht2);
        nachrichtView.setText(nachricht);


    }
}
