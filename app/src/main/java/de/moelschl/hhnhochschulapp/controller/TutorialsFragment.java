package de.moelschl.hhnhochschulapp.controller;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import de.moelschl.hhnhochschulapp.R;

/**
 * Class to display and control an overview of the Tutorials to select one
 *
 * Created by moelscmar.
 */
public class TutorialsFragment extends Fragment {

    private OnWindowTitleSet titleSetter;
    private View myFragmentView;
    public static String auswahl;



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

        myFragmentView = inflater.inflate(R.layout.fragment_tutorials, container, false);
        initListViewIlias();
        titleSetter.setWindowTitle("Tutorials");
        return myFragmentView;
    }

    /**
     * initiates the ListAdapters and the Listeners
     */
    private void initListViewIlias() {
        String[] iliasTutorials = {"Ilias-Kurse beitreten", "Ilias-Kurse verlassen"};
        String[] lindaTutorials = {"Prüfungsergebnise einsehen", "Studiumsbescheinigung herunterladen"};
        ListAdapter messageAdapterIlias = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, iliasTutorials);
        ListView myListViewIlias = (ListView) myFragmentView.findViewById(R.id.listViewIlias);
        ListAdapter messageAdapterLinda = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, lindaTutorials);
        ListView myListViewLinda = (ListView) myFragmentView.findViewById(R.id.listViewLinda);
        myListViewIlias.setAdapter(messageAdapterIlias);
        myListViewLinda.setAdapter(messageAdapterLinda);

        myListViewIlias.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        auswahl = String.valueOf(parent.getItemAtPosition(position));
                        if(auswahl.equals("Ilias-Kurse beitreten")){
                            Intent intent = new Intent(getActivity(), IliasTutorial1Activity.class);
                            startActivity(intent);
                        }
                        else if (auswahl.equals("Ilias-Kurse verlassen")){
                            Intent intent = new Intent(getActivity(), IliasTutorial2Activity.class);
                            startActivity(intent);
                        }

                    }
                }
        );

        myListViewLinda.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        auswahl = String.valueOf(parent.getItemAtPosition(position));
                        if(auswahl.equals("Prüfungsergebnise einsehen")){
                            Intent intent = new Intent(getActivity(), LindaTutorial1Activity.class);
                            startActivity(intent);
                        }
                        else if (auswahl.equals("Studiumsbescheinigung herunterladen")){
                            Intent intent = new Intent(getActivity(), LindaTutorial2Activity.class);
                            startActivity(intent);
                        }

                    }
                }
        );
    }


    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
        try {
            titleSetter = (OnWindowTitleSet) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnThreadManage or OnWindowTitleSet");
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
