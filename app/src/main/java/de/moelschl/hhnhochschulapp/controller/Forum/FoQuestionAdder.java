package de.moelschl.hhnhochschulapp.controller.Forum;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import de.moelschl.hhnhochschulapp.R;
import de.moelschl.hhnhochschulapp.io.DatabaseHelper;
import de.moelschl.hhnhochschulapp.model.ThemeListItem;
import de.moelschl.hhnhochschulapp.tools.CustomAutoCompleteView;

/**
 * Created by Hasbert on 27.06.2016.
 */

public class FoQuestionAdder extends Fragment implements View.OnClickListener{

    private OnStoreData dataOutput;
    private CustomAutoCompleteView textAutoComplete;
    private ArrayAdapter<String> adapter;

    private EditText themeDesc;
    private EditText question;
    private EditText questionHeader;

    private Button saveButton;

    private View rootView;
    private DatabaseHelper dbHelper;
    private Context context;

    /**
     *
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.context = getContext();
        this.dbHelper = new DatabaseHelper(context);
        this.rootView = inflater.inflate(R.layout.fo_question_adder, container, false);

        createAutoCompleteTextView();
        //add fields to insert data
        this.themeDesc =  (EditText) rootView.findViewById(R.id.theme_desc_text);
        this.question =  (EditText) rootView.findViewById(R.id.question_text_insert);
        this.questionHeader =  (EditText) rootView.findViewById(R.id.question_header_text);

        //add Button to save all in db
        this.saveButton = (Button) rootView.findViewById(R.id.save_button_question);
        saveButton.setOnClickListener(this);

        return rootView;
    }

    /**
     * creates a autocomplete Textview and adds a adapter to show results in a drop down menu
     *
     */

    private void createAutoCompleteTextView(){
        //adds a adapter for a drop down suggestion list
       adapter = new ArrayAdapter<String>(context,
               android.R.layout.simple_dropdown_item_1line, getListData());

        textAutoComplete = (CustomAutoCompleteView) rootView.findViewById(R.id.theme_text_autocomplete);
        textAutoComplete.setAdapter(adapter);

    }

    private String[] getListData(){
        ArrayList<String> stringList = new ArrayList<>();
        for(ThemeListItem listItem: dbHelper.getThemeList()){
            stringList.add(listItem.getTopic());
        }
        String[] themes = stringList.toArray(new String[stringList.size()]);
        return themes;
    }

    @Override
    public void onClick(View v) {
        String themeTopic = textAutoComplete.getText().toString();
        String themeDesc = this.themeDesc.getText().toString();
        String questionHeader = this.questionHeader.getText().toString();
        String question = this.question.getText().toString();
        String nuller = "";

        if (themeTopic.equals(nuller) || themeDesc.equals(nuller) || questionHeader.equals(nuller)
                || question.equals(nuller)){
            Toast.makeText(context, "Bitte alle Felder ausfüllen", Toast.LENGTH_SHORT).show();
        }
        else {
            dataOutput.storeNewQuestion(themeTopic, themeDesc, questionHeader, question, context);
        }
    }

    public interface OnStoreData{
        void storeNewQuestion(String themeTopic,String themeDesc,String questionHeader,
                              String question,Context context);
    }

    /**
     * checks that the activity has implemaentated OnThemeSelectedListener
     *
     * @param context {@link android.content.Context}
     */

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
        try {
            dataOutput = (OnStoreData) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }


    /**
     * sets variable to default
     */

    @Override
    public void onDetach() {
        super.onDetach();
        dataOutput = null;
    }
}
