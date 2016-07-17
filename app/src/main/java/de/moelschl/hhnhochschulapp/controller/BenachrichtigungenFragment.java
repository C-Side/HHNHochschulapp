package de.moelschl.hhnhochschulapp.controller;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

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

/**
 * Class that loads, displays and is controlling the message System
 *
 * Created by moelscmar
 */
public class BenachrichtigungenFragment extends Fragment {

    public static String auswahl;
    private InputStream is;
    private String[] results;
    private OnWindowTitleSet titleSetter;
    private View myFragmentView;


    /**
     *
     * the initialization method is like a constructor. it loads the main layout and set them
     * active, then gives the information to other classes.
     *
     * @param inflater Instantiates a layout XML file into its corresponding View Objects.
     * @param container Container for View Objects.
     * @param savedInstanceState a mapping form String values to whatever you want.
     * @return the showable View.
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        myFragmentView = inflater.inflate(R.layout.fragment_benachrichtigungen, container, false);
        getData();
        init();
        titleSetter.setWindowTitle("Benachrichtigungen");
        return myFragmentView;
    }


    /**
     * initiates the ListAdapter and the Listener
     */
    private void init() {
        ListAdapter messageAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, results);
        ListView myListView = (ListView) myFragmentView.findViewById(R.id.listView1);
        myListView.setAdapter(messageAdapter);

        myListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        auswahl = String.valueOf(parent.getItemAtPosition(position));
                        Intent intent = new Intent(getActivity(), NachrichtActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }

    /**
     * loads the messages from a php file and shows it in the ListAdapter
     */
    private void getData() {
        String result = "";
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("https://moelschl.de/interfaceprojects.php");
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

            }

         public static String getAuswahl() {
            return auswahl;
        }


    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
        try {
            titleSetter = (OnWindowTitleSet) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnWindowTitleSet");
        }
    }


    /**
     * sets variable to default
     */
    @Override
    public void onDetach() {
        super.onDetach();
        titleSetter = null;
    }

    }



